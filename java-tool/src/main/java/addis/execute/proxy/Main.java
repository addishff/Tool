package addis.execute.proxy;

/**
 * Created by huangfeifeng on 08/02/2017.
 */
public class Main {
    public static void main(String... args) {
        Hello hello = new HelloImpl();
        HelloProxy helloProxy = new HelloProxy();
        Hello hello1 = (Hello) helloProxy.bind(hello);
        System.out.println("result:" + hello1.sayHello("addis"));
    }
}
