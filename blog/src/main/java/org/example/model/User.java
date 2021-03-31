package org.example.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
//sql对应的Java类
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private boolean sex;
    private Date birthday;
    private String head;
}
