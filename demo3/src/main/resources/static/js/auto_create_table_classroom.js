function auto_create_table_classroom(data) {
    annexList = new Array();
    count=0;
    page=0;
    size=13;
    last=0;
    remain=0;
    $(function(){
        //第二种： 动态创建表格的方式，使用动态创建dom对象的方式
        //清空所有的子节点
        $("#group_one").empty();
        //自杀
        // $("#J_TbData").remove();
        last=Math.floor(data.length/size);
        $("#total_page").text(last+1);
        remain=data.length%size;

            tab=tab+"</tr>";for( var i = 0; i < data.length; i++ ) {

                var tab="<tr>"+ "<td class='color_1'>"+data[i].name+"</td>";
                for(var j = 0; j < 49; j++){

                    tab=tab+select_color(data[i].state_1[j]);
                }
            if (count < size) {
                //第一页显示的数量
                $("#group_one").append(tab);
                $("#prePage" ).attr("disabled","disabled");
                $("#nextPage").attr("disabled","disabled");
            } else {
                //恢复点击
              $("#nextPage").removeAttr("disabled");
            }
            count++;
            //保存到数组中
            var annex = new String();
            annex=tab;
            annexList.push(annex);
        }

        $("#currentPage").text(page+1);
         currentPage=page+1;
    });
    if(next==1){

        //上一页

        next=0;
        $("#prePage").click( function () {
            page--;
            paging();
        });

        //下一页
        $("#nextPage").click( function () {
            page++;
            paging();
        });
        //首页
        $("#firstPage").click( function () {
            page=0;
            paging();

        });
        //末页
        $("#lastPage").click( function () {
            page=last;
            paging();

        });
        //指定页
        $("#jump").click( function () {
            page=$("#aim_page").val()-1;
            paging();

        });    //分页
        function paging() {
            var tab ="";
            var curentNumber = page*size;
            var length = curentNumber+size;
            //当前页数
            var currentPage = page+1;
            for(var i = curentNumber; i < length; i++ ) {
                // console.log(annexList[i]);
                if(typeof(annexList[i]) == "undefined") {
                    break;
                }
                tab +=annexList[i];
            }
            if(page==last) {
                //到了最后一页不可以点击
                $("#nextPage" ).attr("disabled","disabled");
            } else {
                //恢复点击
                $("#nextPage").removeAttr("disabled");
            }

            if(page==0) {
                //到了第一页不可以点击
                $("#prePage" ).attr("disabled","disabled");
            } else {
                $("#prePage").removeAttr("disabled");
            }
            //填充到表格
            $("#group_one").html(tab);
            //显示当前页数
            $("#currentPage").text(currentPage);
        }
    }
}



function apply_classroom(a) {
    $('#apply_classroom').window('open');
}
function show(a) {
    alert(showData[a.parent("tr").index()+(currentPage-1)*size].information[a.index()-1]);
    // document.getElementById("dialog_2").style.display="block";
    // document.getElementById("information").innerText=showData[a.parent("tr").index()].information[a.index()+1];
}

 function  select_color(data) {

    if(data=="0"){
       return "<td onclick='apply_classroom($(this));'  class='color_1'></td>";
    }else if(data=="1"){
      return "<td onclick='show($(this));' class='color_2'></td>";
    }
    else if(data=="2"){
        return "<td onclick='show($(this));' class='color_3'></td>";
    }else if(data=="3"){
        return "<td onclick='show($(this));' class='color_4'></td>";
    }else{
        return "<td onclick='show($(this));' class='color_5'></td>";
    }
}
function ok(){
    document.getElementById("dialog_2").style.display="none";
    document.getElementById("tm").style.display="none";
    return false;
}
function ok1(){
    document.getElementById("dialog_1").style.display="none";
    document.getElementById("tm").style.display="none";
    return false;
}



function open_w(){

    var str= $("#r_data").val().split("-");
    var days=DateDiff( $("#r_data").val(),"2019-09-02");//计算天数
    var week=Math.floor(days/7);//第几周
    var day=days%7+1;//星期几
    month=str[1];
    if(month>=9||month<=1){
        semester=2;
    }else {
        semester=1;
    }

    var json = {
        id: $("#stuid").val(),
        type: $.getUrlParam("type"),
        classroomid: $("#buliding").val()+$("#classid").val(),
        data: $("#r_data").val(),
        week:week,
        day:day,
        starttime: $("#starttime").val(),
        endtime: $("#endtime").val(),
        information: $("#inf").val(),
        semester: semester,
        year:str[0]
    };
    var fla=1;
    if( $("#starttime").val()>$("#endtime").val()){
        alert("开始节次不能大于结束节次");
    }else {
        for(var i=0;i<showData.length;i++){
            var str=$("#buliding").val()+$("#classid").val();
            if(showData[i].name==str){

                var s=parseInt($("#starttime").val());
                var m=parseInt(day);
                var e=parseInt($("#endtime").val());

                for(var j=s+(m-1)*7-1;j<=e+(m-1)*7-1;j++){
                    if(showData[i].state_1[j]!="0"){
                         alert(showData[i].name+"已被申请");
                         fla=0;
                         break;
                    }
                }
            }
        }
        if(fla){
            $.ajax({
                type: "post",
                url: "/applyClassroom",
                data:JSON.stringify(json),
                dataType: "json",
                contentType:"application/json",
                success:function (data,status) {
                    alert("申请成功");
                    load_classroom();
                }
            });
            $("#apply_classroom").window('close');
        }

    }

}
function close_w(){

    $("#apply_classroom").window('close');
}

function load_classroom(){

    showData=[];
    //获取本地时间
    var str=$("#year option:selected").text().split("/");
    var str1=1+parseInt($("#week option:selected").val());
    var json= {id: $.getUrlParam("id"),
        type: $.getUrlParam("type"),
        year:str[0],
        semester:str[1],
        week:str1,
        buildingName:$("#building option:selected").text(),
    };
    $.ajax({
        type: "post",
        url: "/load_Classroom",
        data: JSON.stringify(json),
        dataType: "json",
        contentType: "application/json",
        success: function (data, status) {
            showData=data.data;
            console.log(data.data);
            auto_create_table_classroom(data.data);
        }
    });
}
