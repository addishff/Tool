package addis.execute.proxy;

/**
 * Created by huangfeifeng on 08/02/2017.
 */
public class HelloImpl implements Hello {
    @Override
    public String sayHello(String name) {
        System.out.println("say hello! to " + name);
        return "haha " + name;
    }
}
