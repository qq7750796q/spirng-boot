var host = "http://"+window.location.host;
function login(){
    $("")
}

$("#phone").blur(function () {
    console.log($("#phone").val())
})

$("._submit").click(function () {
   var form = getFormJson("#loginForm");
    var url = host+"/login/enter.htm"
    $.ajax({
        url:url,
        data:form,
        async:false,
        success:function (data) {
            console.log(data);
            if (data.code==0) {
                window.location.href=host+"/tologin/test.htm";
            }
        },
        error:function () {

        }
    })
})
