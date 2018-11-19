function login(){
    $("")
}

$("#phone").blur(function () {
    console.log($("#phone").val())
})

$("._submit").click(function () {
   var form = getFormJson("#loginForm");
    console.log(form)
})
