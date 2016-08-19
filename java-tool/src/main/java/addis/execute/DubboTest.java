package addis.execute;

import com.vdian.fxmsg.client.model.MCenterComment;
import com.vdian.fxmsg.client.service.MCenterCommentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huangfeifeng on 8/1/16.
 */
public class DubboTest {
    public static void main(String... args) {
        MCenterComment mCenterComment = new MCenterComment();
        mCenterComment.setmCenterId(22222222l);
        mCenterComment.setContent("test MCENTER");
        mCenterComment.setClassId(11111111L);
        mCenterComment.setUserId(33333333L);

        MCenterComment reply = new MCenterComment();
        reply.setContent("reply MCENTER");
        reply.setUserId(mCenterComment.getmCenterId());
        reply.setmCenterId(mCenterComment.getmCenterId());
        reply.setClassId(mCenterComment.getClassId());
        reply.setIdReply(12124l);
        reply.setUserIdReply(mCenterComment.getUserId());

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        context.start();
        MCenterCommentService mCenterCommentService = (MCenterCommentService) context.getBean("mCenterCommentService");
        System.out.println(mCenterCommentService.classComments(mCenterComment.getmCenterId(), mCenterComment.getClassId(), 0, 15));
    }
}
