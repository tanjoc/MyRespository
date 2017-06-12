package cn.edu.spring.injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //创建bean实例
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/spring-config.xml");
        Category category = applicationContext.getBean(Category.class);
        System.out.println(category.toString());
        //设置属性
        category.setCateId(1);
        category.setCateName("书籍");
        //显示结果
        System.out.println(category.toString());

        Product product=applicationContext.getBean(Product.class);
        System.out.println(product.toString());
    }

}