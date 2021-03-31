package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HTTP响应json数据，前后端统一约定的json数据
 * 响应状态码都是200，进入Ajax的success来使用
 * {success:true,data:xxx}
 * {success:false,code:xxx,message:xxx}
 */
@Getter
@Setter
@ToString
public class JSONResponse { //返回给前端用

    //业务操作是否成功的标识
    private boolean success;
    //业务操作的消息码，一般来说，出现错误时的错误码才有意义，给开发人员定位问题
    private String code;
    //业务操作的错误消息：给用户看的信息
    private String message;
    //业务数据：业务操作成功时，给前端Ajax success方法使用，解析响应json数据渲染网页内容
    private Object data;

}
