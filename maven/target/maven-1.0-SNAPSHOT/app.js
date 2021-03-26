//内容还没有加载完就执行该方法
// function load() {
//     alert("load ok");
// }

//ajax
//内容加载完了才执行该方法
$(function () { //jquery加载方式
    alert("load ok");
    //自己发起网络请求
    //Jquery的ajax方法，是以异步方式发起http网络请求，格式 键:值
    //方法的传入参数，是json格式对象
    let data = { //使用let定义变量
        username:"abc",
        password:"123"
    };
    $.ajax({
        type:"POST", //请求方法 get / post
        url:"data/login.json", //请求路径
        contentType:"application/json", //请求的数据类型(此处以json方式请求)，默认:application/x-www-form-urlencoded
        data:JSON.stringify(data),  //请求数据,如果数据类型是json,需要将json对象转化为字符串
        success:function (r) { //响应状态码200的回调方法
            if (r.success) {
                console.log(r.data1.name);
                //alert(JSON.stringify(r));
            } else {
                alert("success=false");
            }
            //alert(JSON.stringify(r));
        },error:function (jqXHR,textStatus,errorThrown) {//4XX，5XX状态码的错误回调
            console.log("jqXHR={\n"+jqXHR.status+",\n"+jqXHR.statusText+",\n"+jqXHR.responseText
            +"},\ntextStatus="+textStatus+",\nerrorThrown="+errorThrown);
        }
        //失败的函数 error
    });
});