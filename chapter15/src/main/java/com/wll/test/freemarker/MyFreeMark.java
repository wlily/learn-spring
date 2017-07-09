package com.wll.test.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wll on 12/15/15.
 */
public class MyFreeMark {
    public static void main(String[] args) throws IOException {
        MyFreeMark freemark = new MyFreeMark();
        freemark.create();
    }

    private String path = "/home/wll/0-ProjectCode/spring3.x practice/chapter15/src/main/java/com/wll/test/freemarker/";
    private String srcFile = "testfreemark.ftl";
    private String targetFile = path + "test.html";

    /**
     * freemark初始化
     */
    public MyFreeMark() throws IOException {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("utf-8");
        configuration.setDirectoryForTemplateLoading(new File(path));
    }

    private void create(){
        Template t = null;
        try {
            //获取模板信息
            t = configuration.getTemplate(srcFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File outFile = new File(targetFile);
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map map = new HashMap<String, Object>();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Dog", "1111"));
        animals.add(new Animal("Pig", "2222"));

        map.put("animals", animals);
        map.put("user", "XiaoLi");
        try {
            //输出数据到模板中，生成文件。
            t.process(map, out);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * freemark模板配置
     */
    private Configuration configuration;

}
