package addis.execute;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by huangfeifeng on 1/18/16.
 */
public class MainClass {

    public static void Ttest(ArrayList<Long> nums) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Boolean a = true;
        Method addMethod = nums.getClass().getDeclaredMethod("add", Object.class);
        System.out.println(addMethod);
        addMethod.invoke(nums, a);
        System.out.println(nums.size());
        Object e = nums.get(0);
        System.out.println(e.getClass());
        System.out.println(nums.get(0).getClass());
    }

    public static void main(String... args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Ttest(new ArrayList());
    }
}
