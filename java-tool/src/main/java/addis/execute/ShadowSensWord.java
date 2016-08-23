package addis.execute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vdian.common.http.HttpClientUtil;
import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Author addis
 */
public class ShadowSensWord extends TestCase {
    private JSONObject paramJson;


    public void parser() throws Exception {
        String url = "http://10.2.131.222:8080/keyword/highlight";

        List<String> list = new ArrayList<String>();
        FileInputStream f = new FileInputStream("/Users/huangfeifeng/Desktop/comment");
        BufferedReader dr = new BufferedReader(new InputStreamReader(f));
        String line = dr.readLine();
        while (line != null) {
            list.add(line);
            line = dr.readLine();
        }
        List arrayList = null;
        try {
            String body = getResult(url, JSON.toJSONString(list));
            if (StringUtils.isNotBlank(body) && body.contains("errorCode\":0")) {
                arrayList = (List) JSON.parseObject(body).getJSONArray("data");
            }
        } catch (Exception e) {
            arrayList = null;
        }

        HashSet<String> words = new HashSet<String>();
        for (Object str : arrayList)
            filterHitWord(str.toString(), words);
        for (String word : words)
            System.out.println(word);

    }

    public void filterHitWord(String l, Set<String> words) {
        String fromStr = "<font color='red'>";
        String toStr = "</font>";
        int from = l.indexOf(fromStr);
        int to = l.indexOf(toStr);

        while (from < to && from >= 0 && to >= 0) {
            words.add(l.substring(from + fromStr.length(), to));
            from = l.indexOf(fromStr, to);
            to = l.indexOf(toStr, from);
        }
    }

    public static String getResult(String url, String paramJson) {
        String body = null;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        try {
            //待开发,需要设定为utf-8
            body = new String(HttpClientUtil.post(url, paramJson, headers, 3000), "utf-8");
        } catch (Exception e) {
            return body;
        }
        return body;
    }

    public void distinct() throws Exception {
        FileInputStream f = new FileInputStream("/Users/huangfeifeng/Desktop/击中的词汇");
        BufferedReader dr = new BufferedReader(new InputStreamReader(f));
        String line = dr.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line);
            line = dr.readLine();
        }
        HashSet<String> words = new HashSet<String>();
        for (String word : sb.toString().split(" "))
            words.add(word);
        for (String word : words)
            System.out.println(word);

    }

}
