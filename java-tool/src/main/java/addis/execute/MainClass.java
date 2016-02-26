package addis.execute;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by huangfeifeng on 1/18/16.
 */
public class MainClass {
    public static void main(String... args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String abc = "abc";
        Integer i = Integer.getInteger(abc);
        System.out.println(i);
    }
}
