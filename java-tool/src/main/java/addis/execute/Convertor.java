package addis.execute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koudai.fenxiao.client.model.FxIndexContent;
import com.koudai.fenxiao.client.model.FxIndexType;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangfeifeng on 2/17/16.
 */
public class Convertor {

    public static void setBean(JSONObject parameter, Object bean) {
        Class beanClass = bean.getClass();
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {

            String fieldName = field.getName();
            String fieldSetName = setName(fieldName);
            Class fieldType = field.getType();

            Object value = null;
            if (fieldType.isArray()) {
                Class componentType = fieldType.getComponentType();

                if (componentType.isPrimitive()) {
                    continue;
                } else {
                    JSONArray jsonArray = parameter.getJSONArray(fieldName);
                    /*
                    getJSONArray()的原理是什么？
                    因为返回的JSONArray的子项可能是json，也可能是string等
                    * */
                    if (jsonArray != null) {
                        Object[] jsons = jsonArray.toArray();
                        value = Array.newInstance(componentType, jsons.length);
                        for (int i = 0; i < jsons.length; i++) {
                            Array.set(value, i, JSONObject.toJavaObject((JSON) jsons[i], componentType));
                        }
                    }
                }

            } else {
                value = parameter.getObject(fieldName, fieldType);
            }

            if (value == null)
                continue;
            try {
                Method method = beanClass.getMethod(fieldSetName, fieldType);
                method.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static String setName(String fieldName) {
        return "set" + (char) (fieldName.charAt(0) - 32) + fieldName.substring(1);
    }

    public static List<FxIndexContent> beContent(String jsonStr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        List<FxIndexContent> res = new ArrayList<FxIndexContent>();

        for (FxIndexType fxIndexType : FxIndexType.values()) {
            if (fxIndexType.isArray()) {
                JSONArray array = jsonObject.getJSONArray(fxIndexType.name());
                if (array != null && array.size() > 0)
                    if (fxIndexType == FxIndexType.cateList)
                        for (int i = 0; i < array.size(); i++) {
                            JSONObject cate = array.getJSONObject(i);
                            JSONArray shopList = cate.getJSONArray(FxIndexType.shopList.name());
                            cate.remove(FxIndexType.shopList.name());
                            addContent(cate, makeLocation(fxIndexType.getType(), i + 1), res);
                            if (shopList != null && shopList.size() > 0)
                                for (int j = 0; j < shopList.size(); j++)
                                    addContent(shopList.get(j),
                                            makeShopLocation(FxIndexType.shopList.getType(), i + 1, j + 1), res);
                        }
                    else
                        for (int i = 0; i < array.size(); i++)
                            addContent(array.get(i), makeLocation(fxIndexType.getType(), i + 1), res);
            } else {
                String addition = jsonObject.getString(fxIndexType.name());
                if (addition != null || addition.length() != 0)
                    addContent(addition, makeLocation(fxIndexType.getType(), 1), res);
            }
        }
        return res;
    }

    public static long makeLocation(int type, int index) {
        long location = type;
        return (location << 48) + index;
    }

    public static long makeShopLocation(int type, int cate, int index) {
        long location = type;
        location = (location << 16) + cate;
        return (location << 32) + index;
    }

    private static void addContent(Object addition, long location, List<FxIndexContent> res) {
        Timestamp openDefault = new Timestamp(System.currentTimeMillis());
        Timestamp closeDefault = new Timestamp(System.currentTimeMillis() + (24 * 60 * 60 * 1000));

        FxIndexContent fxIndexContent = new FxIndexContent();
        fxIndexContent.setAddition(JSON.toJSONString(addition));
        fxIndexContent.setOpen(openDefault);
        fxIndexContent.setClose(closeDefault);
        res.add(fxIndexContent);
    }
}
