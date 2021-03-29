package org.example.controller;

import lombok.Getter;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //注册一个id为类名首字母小写的bean对象  默认注册一个 单例模式
@Getter
public class LoginController {
    @Autowired
    private LoginService loginService;

}
