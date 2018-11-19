var host = "http://"+window.location.host;
$(function () {
    console.log("注册")
});
$(".registered").on("click",function () {
    console.log("提交"+$("form").serialize())
    var form = getFormJson("#registered")
    var url = host+"/login/saveRegistered.htm"
    $.ajax({
        url:url,
        data:form,
        async:false,
        success:function (data) {
            console.log(data);
            if (data.code==0) {
                window.location.href=host+"/login/login.htm";
            }
        },
        error:function () {

        }
    })
})