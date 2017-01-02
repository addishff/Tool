package addis.execute;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangfeifeng on 8/31/16.
 */
public class HelloVelocity {
    public static void main(String... args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        velocityEngine.init();

        Template template = velocityEngine.getTemplate("templates/HelloVelocity.vm");
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("name", "addis");
        velocityContext.put("date", new Date().toString());

        List<String> list = new ArrayList<String>();
        list.add("num. 1");
        list.add("num. 2");
        velocityContext.put("list",list);

        StringWriter sw = new StringWriter();
        template.merge(velocityContext, sw);
        System.out.println(sw.toString());
    }
}
