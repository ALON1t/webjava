package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString

//sql对应的Java类
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String nickname;
    private Date createTime;
    private Integer viewCount;
    private Integer userId;
}
