package addis.execute;

import com.alibaba.fastjson.JSON;
import com.vdian.imax.logic.service.MessageService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huangfeifeng on 8/1/16.
 */
public class DubboTest {
    public static void main(String... args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        context.start();
        MessageService messageService = (MessageService) context.getBean("messageService");

        System.out.println(JSON.toJSONString(messageService.getHistoryMessage(100l, 200l, 0, 10)));
//        Message msg = new Message();
//        msg.setAppId(1234L);
//        msg.setClientMid(1234L);
//        msg.setSenderId(1234L);
//        msg.setReceiverId(1234L);
//        System.out.println(messageService.sendMessage(msg));
        System.exit(0);
    }
}
