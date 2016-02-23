package addis.execute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by huangfeifeng on 1/18/16.
 */
public class MainClass {
    public static void main(String... args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ArrayBean arrayBean = ArrayBean.class.newInstance();
        JSONObject jsonObject = new JSONObject();
        Item[] array = new Item[3];
//        Integer[] array = new Integer[3];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Item(i, "ab" + (char) ('c' + i));
        }
        jsonObject.put("items", array);
        String jsonStr = jsonObject.toJSONString();
//        Convertor.setBean(JSONObject.parseObject(jsonStr), arrayBean);
        arrayBean = JSON.toJavaObject(JSON.parseObject(jsonStr), ArrayBean.class);
        java.lang.System.out.println(arrayBean.toString());
    }
}
