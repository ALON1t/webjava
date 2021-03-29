package org.example;

import org.example.controller.LoginController;
import org.example.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        //根据Spring配置文件路径创建容器：应用上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        LoginController loginController = (LoginController) context.getBean("loginController");
        System.out.println(loginController);
        //验证loginController依赖注入的loginService是否为容器中的Bean对象
        LoginService loginService = context.getBean(LoginService.class);
        System.out.println(loginController.getLoginService() == loginService);
        //关闭容器
        ((ClassPathXmlApplicationContext) context).close();
    }
}