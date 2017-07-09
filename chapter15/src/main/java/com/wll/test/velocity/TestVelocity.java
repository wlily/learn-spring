package com.wll.test.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;

/**
 * Created by wll on 12/15/15.
 */
public class TestVelocity {

//    private static String path = "/home/wll/0-ProjectCode/spring3.x practice/chapter15/src/main/java/com/wll/test/velocity/";

    public static void main(String[] args){
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("name", "Velocity");
        context.put("project", "Jakarta");

        StringWriter sw = new StringWriter();
        Template template = Velocity.getTemplate("testtemplate.vm", "utf-8");
        template.merge(context, sw);
        System.out.println("template: " + sw);

        String s = "We are using $project $name to render this.";
        sw = new StringWriter();
        Velocity.evaluate(context, sw, "mystring", s);
        System.out.println("String: " + sw);
    }
}
