package com.justin.demo.util;


import io.netty.util.internal.ResourcesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 操作properties文件的工具类(此工具类的file都是src目录下的properties文件，编译之后在build目录下)
 *
 * @author QiaoLiQiang
 * @time 2018年11月3日下午12:05:32
 */
public class PropertiesFileUtils {
    private static final Logger log = LoggerFactory.getLogger(PropertiesFileUtils.class);

    /**
     * 构造函数私有化
     */
    private PropertiesFileUtils() {

    }

    /**
     * 保存或更新properties文件中的key
     *
     * @param fileName
     * @param key
     * @param value
     */
    public static void saveOrUpdateProperty(String fileName, String key, String value) {
        Properties properties = new Properties();
        InputStream inputStream;
        OutputStream outputStream;
        try {
            String path = ResourcesUtil.class.getClassLoader().getResource(fileName).getPath();
            log.debug("path -> {}", path);
            inputStream = new FileInputStream(new File(path));
            properties.load(inputStream);
            properties.setProperty(key, value);

            // 保存到文件中(如果有的话会自动更新，没有会创建)
            outputStream = new FileOutputStream(new File(path));
            properties.store(outputStream, "");

            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            log.error("saveOrUpdateProperty error", e);
        } catch (IOException e) {
            log.error("saveOrUpdateProperty error", e);
        }
    }

    /**
     * 获取Properties
     *
     * @param fileName
     * @param key
     * @return
     */
    public static String getPropertyValue(String fileName, String key) {
        Properties properties = new Properties();
        InputStream inputStream;
        String value = "";
        try {
            String path = PropertiesFileUtils.class.getClassLoader().getResource(fileName).getPath();
            log.info("path -> {}", path);
            inputStream = new FileInputStream(new File(path));
            properties.load(inputStream);

            value = properties.getProperty(key);
            // 保存到文件中(如果有的话会自动更新，没有会创建)
            inputStream.close();
        } catch (FileNotFoundException e) {
            log.error("saveOrUpdateProperty error", e);
        } catch (IOException e) {
            log.error("saveOrUpdateProperty error", e);
        }
        return value;
    }

    /**
     * 获取Properties
     *
     * @param fileName
     * @return
     */
    public static Properties getProperties(String fileName) {
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            String path = PropertiesFileUtils.class.getClassLoader().getResource(fileName).getPath();
            log.info("path -> {}", path);
            inputStream = new FileInputStream(new File(path));
            properties.load(inputStream);

            inputStream.close();
        } catch (FileNotFoundException e) {
            log.error("saveOrUpdateProperty error", e);
        } catch (IOException e) {
            log.error("saveOrUpdateProperty error", e);
        }
        return properties;
    }

    /**
     * 获取Properties
     *
     * @param fileName
     * @return
     */
    public static Properties removeProperty(String fileName, String key) {
        Properties properties = new Properties();
        InputStream inputStream;
        OutputStream outputStream;
        try {
            String path = PropertiesFileUtils.class.getClassLoader().getResource(fileName).getPath();
            log.info("path -> {}", path);
            inputStream = new FileInputStream(new File(path));
            properties.load(inputStream);
            log.info("properties -> {}", properties);
            if (properties != null && properties.containsKey(key)) {
                log.info("remove key:{}", key);
                properties.remove(key);
            }

            // 保存到文件中(将properties保存到文件)
            outputStream = new FileOutputStream(new File(path));
            properties.store(outputStream, "");

            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            log.error("saveOrUpdateProperty error", e);
        } catch (IOException e) {
            log.error("saveOrUpdateProperty error", e);
        }
        return properties;
    }

    public static void main(String[] args) {
        // 保存三个 最后一个相当于更新
        PropertiesFileUtils.saveOrUpdateProperty("rule.properties", "a", "aaa");
        PropertiesFileUtils.saveOrUpdateProperty("rule.properties", "b", "bbb");
        PropertiesFileUtils.saveOrUpdateProperty("rule.properties", "c", "ccc");
        PropertiesFileUtils.saveOrUpdateProperty("rule.properties", "a", "AAA");

        // 获取所有的properties
        Properties properties = PropertiesFileUtils.getProperties("rule.properties");
        System.out.println(properties);

        // 删除a
        PropertiesFileUtils.removeProperty("rule.properties", "a");

        // 获取所有的properties
        Properties properties1 = PropertiesFileUtils.getProperties("rule.properties");
        System.out.println(properties1);

        List<String> entitlement = Arrays.asList(properties.getProperty("entitlement").split(","));
        for (String s : entitlement) {
            System.out.println(properties.getProperty(s));
        }

    }
}
