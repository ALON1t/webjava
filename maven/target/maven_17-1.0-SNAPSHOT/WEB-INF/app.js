function load() {
    alert("load OK")

}
$(function () {//jquery的方式：body加载完成之后执行的代码
   alert("load OK")
    //
    //方法的传入参数，是json格式对象
    let data = {
       username:"abc",
       password:"123"
    }
    $.ajax({
        type: "POST",//请求方法
        url: "some.php",//请求路径
        contentType:"application/json",//请求数据类型，默认：application/json
        data: "name=John&location=Boston",//请求数据，如果数据类型是json，需要将json
        success: function(msg){
            alert( "Data Saved: " + msg );
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            // 通常 textStatus 和 errorThrown 之中
            // 只有一个会包含信息
            this; // 调用本次AJAX请求时传递的options参数
        }
    });
})