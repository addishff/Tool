package addis.execute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
}
