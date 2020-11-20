
function  onSmText() {

    var tip_1=$("#tip_1");
    var tip_2=$("#tip_2");
    var tip_3=$("#tip_3");
    var user=$("#user");
    var psw=$("#psw");
    var person=$("input[name='a']:checked");
    var tag=0;
    tip_1.css("display","none");
    tip_2.css("display","none")
    tip_3.css("display","none")
    if(user.val()==""){
        tip_1.css("display","inline");
        tag=1;
    }
    if(psw.val()=="" ){
        tip_2.css("display","inline");
        tag=1;
    }
    if(person.val()==null){
        tip_3.css("display","inline");
        tag=1;
    }
    if(tag==1){
        return false;
    }else {
        var json={name:user.val(),psw:psw.val(),person:person.val()};
        $.ajax({
            type: "post",
            url: "/login/check",
            data:JSON.stringify(json),
            dataType: "json",
            contentType:"application/json",
            success:function (data,status) {
             alert("1");
             if("true"==data.check){//验证成功 跳转
                  alert("1");
                  alert(person.val());
                 if(person.val()=="student"){
                     window.location.href = "/Home?id="+user.val()+"&type="+person.val();
                 }
                 else if(person.val()=="teacher") {
                     window.location.href = "/Teacher/Home?id="+user.val()+"&type="+person.val();
                 }
                 else if (person.val()=="manager"){
                     window.location.href = "/A_Home?id="+user.val()+"&type="+person.val();
                 }
             }
             else{
                 alert("用户不存在或者密码错误")
             }
            }
        });
        return false;
    }

}