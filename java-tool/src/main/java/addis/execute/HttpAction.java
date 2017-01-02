package addis.execute;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.charset.Charset;

/**
 * Created by huangfeifeng on 09/11/2016.
 */
public class HttpAction {
    public static void testUploadVap()throws Exception{
        String url = "http://vap.gw.daily.weidian.com/upload/v2/direct?scope=test&fileType=image";

        BufferedInputStream in = new BufferedInputStream(new FileInputStream("/Users/huangfeifeng/Desktop/123.jpg"));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] content = out.toByteArray();
        JSONObject object = new JSONObject();
        object.put("image", "data:image/jpg;base64," + new BASE64Encoder().encode(content));
        object.put("uploadId", System.currentTimeMillis() / 1000);

        JSONObject request = new JSONObject();
//        request.put("request", object);
        request.put("request","data:image/jpg;base64," + new BASE64Encoder().encode(content));
        String body = request.toJSONString();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Pragma", "no-cache");
        httppost.addHeader("Cache-Control", "no-cache");
        httppost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httppost.addHeader("X-Requested-With", "XMLHttpRequest");
        httppost.addHeader("Content-Type", "text/plain");
//        httppost.addHeader("Content-Length", String.valueOf(body.length()));

        StringEntity entity = new StringEntity(body, Charset.forName("utf8"));
        httppost.setEntity(entity);

        CloseableHttpResponse response = httpclient.execute(httppost);

        try {
            HttpEntity httpEntity = response.getEntity();
            if (entity != null) {
                System.out.println("--------------------------------------");
                System.out.println("Response content: " + EntityUtils.toString(httpEntity, "UTF-8"));
                System.out.println("--------------------------------------");
            }
        } finally {
            response.close();
        }
    }

    public static void main(String...args){
        try {
            testUploadVap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
