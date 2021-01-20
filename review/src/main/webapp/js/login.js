function login(form) {
    //校验用户名密码，
    if(form.oninvalid){//校验不通过，提示、不提交
        // alert("用户名为空")
        return false;
    }

    /**
     * ajax请求，需要抓包查看内容：
     * 1.请求：url，method，Content-Type（请求数据类型）, data（请求数据）
     * 2.响应：status，Content-Type（响应数据类型），data（响应体数据）
     */
    $.ajax({
        url: "../data/login.ok.json",//静态json文件模拟后端servlet返回
        // url: "../data/login.error.json",//静态json文件模拟后端servlet返回
        type: "POST",
        success: function (r) {
            if(r.ok){//r={ok: true}
                //登录操作，用户名密码校验通过
                // alert("登录成功，跳转页面")
                $("#login_error").hide();
                //相对路径写法，是以引入js的html文件作为相对路径的参照点（login.html--->main.html）
                window.location.href = "main.html";
            }else{//r={ok:false, code: xxx, msg: xxx}
                // alert("错误码："+r.code+"\n错误消息："+r.msg)
                $("#login_error").html(r.msg);
                $("#login_error").show();
            }
        }
    })
    return false;
}
//     //校验用户名、密码，如果通过 表单提交
//     var xhr = new XMLHttpRequest();
//
//     xhr.onload = function () {
//         // 输出接收到的文字数据
//         alert(xhr.responseText)
//         //document.getElementById("demo").innerHTML=xhr.responseText;
//     }
//
//     xhr.onerror = function () {
//         alert("请求出错")
//         //document.getElementById("demo").innerHTML="请求出错";
//     }
//
// // 发送异步 POST 请求 第三个参数 async：是否是发起异步请求
//     xhr.open("POST", "../data/login.ok.json", true);
//     xhr.send();
//     return false;
