package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * http响应json数据，前后端约定统一json格式
 * 响应状态码都是200 进入ajax的success来使用
 * { success:true,data:xxx}   正确
 * {success:false,code:xxx,message:xxx}  错误
 *
 */
@Getter
@Setter
@ToString
public class JSONResponse {
    //业务是否操作成功
    private boolean success;  //默认false
    //业务操作的消息码，一般来说，出现错误时的错误码才有意义，给开发人员看——定位问题
    private String code;
    //业务操作的错误消息：给用户看的信息
    private String message;
    //业务数据：业务操作成功时，给前端ajax success 方法使用，解析响应json数据,渲染网页内容
    private Object data;


}
