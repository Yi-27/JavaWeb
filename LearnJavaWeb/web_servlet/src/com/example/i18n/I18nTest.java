package com.example.i18n;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Yi-27
 * @create 2020-11-05 14:55
 */
public class I18nTest {

    public static void main(String[] args) {

        // 获取系统默认的语言国家信息
        Locale locale = Locale.getDefault();
        System.out.println(locale); // zh_CN

        // 获取中文的常量Locale对象
        System.out.println(Locale.CHINA);
        // 获取英文，美国的常量Locale对象
        System.out.println(Locale.US);

    }

    @Test
    public void testI18n(){
        // 获取需要的Locale对象
        Locale locale = Locale.CHINA;
        // 通过制定basename和Locale对象，读取 相应的配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);

        System.out.println("用户名：" + bundle.getString("username"));
        System.out.println("密码：" + bundle.getString("password"));
        System.out.println("性别：" + bundle.getString("sex"));
        System.out.println("年龄：" + bundle.getString("age"));
    }

}
