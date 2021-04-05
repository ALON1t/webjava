/**
 * ajax
 *   作用：若是不用ajax，使用表单提交的话，页面会跳转
 *   1.表单提交：同步的方式执行
 *           html页面，同步请求，是以修改当前页面的url来发起请求
 *           无论如何页面会刷新
 *    2.ajax请求：异步的方式执行
 *           类似多线程并行并发的执行
 *           （1）当前页面该怎样执行就怎样执行
 *           （2）ajax请求：
 *               等待：包括发送请求，接收响应
 *               响应回调；接收到响应，执行回调方法（成功回调，失败回调）
 *  ajax请求，如果servlet响应的数据格式为text/html，会发生什么？
 *    答：不管响应数据格式是什么，页面都不会刷新，都是在接收到响应以后，执行成功的回调
 *
 *  发送ajax请求后，如果没有满足预期业务需求，应该怎么办？（面试问题）
 *    1.抓包看请求：看url,method,Content-Type,data
 *    2.后端调试： 打断点 debug
 *               看打印内容
 *               如果抛异常，检查堆栈信息
 *    3.抓包看响应:看状态码，Content-Type,data
 *
 */
//ajax请求
$(function () { //页面加载完成之后执行function代码
    //jquery里，使用$("#id")通过元素id获取某个页面元素
    //.submit绑定事件
    $("#login_form").submit(function () {
        //ajax自己发请求  异步
        $.ajax({
            url:"../login",  //请求的服务路径
            type:"post",    //请求方法
            //contentType:"", //请求的数据类型：请求头ContentType,默认表单格式，json需要指定为json
            data:$("#login_form").serialize(), //请求数据：使用序列化表单的数据
            dataType:"json", //响应的数据类型，使用json要指定
            success:function (r) {//响应体json字符串，会再jquery里解析为方法参数
                if (r.success) {
                    //前端页面url直接跳转到某个路径
                    window.location.href="../jsp/articleList.jsp";
                } else {
                    //弹出提示框
                    alert("错误码：" + r.code +"\n错误消息："+r.message);
                }
            }
        })

        //统一不执行默认的表单提交  点击登录按钮不跳转
        return false;
    })
})