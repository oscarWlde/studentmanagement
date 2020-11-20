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

        for( var i = 0; i < data.length; i++ ) {
            var tab="<tr>"+ "<td class='color_1'>"+data[i].projectId+"</td>";
            tab+="<td class='color_1'>"+data[i].projectName+"</td>";
            tab+="<td class='color_1'>"+data[i].type+"</td>";
            tab+="<td class='color_1'>"+data[i].teacherId+"</td>";
            tab+="<td class='color_1'>"+data[i].other+"</td>";
            tab+="<td class='color_1'>"+data[i].state+"</td>";
            tab+="<td class='color_1'>"+data[i].applytime+"</td>";
            tab+="<td  class='color_1' onclick='look($(this));'><a class='l_select_1' href='#'>查看详情</a></td>"
            tab=tab+"</tr>";
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


    });

//上一页
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

    });
    //分页
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

function look(a) {
    alert(Proceedata[a.parent("tr").index()].projectName);
}
function search_project_process(){
    var year=$("#year option:selected").val();
    var state=$("#state option:selected").val();
    alert(state);
    var json={ Id: $.getUrlParam("id"), type: $.getUrlParam("type"),
        year:year,state:state};
    console.log(json);
    $.ajax({
        type: "post",
        url: "/student/search_project",
        data: JSON.stringify(json),
        dataType: "json",
        contentType: "application/json",
        success: function (data_1, status) {
            console.log(data_1);
            var data=[];
            for(var i=0;i<data_1.length;i++){
                var ojson={};
                ojson.projectId=data_1[i].inf[i].projectId;
                ojson.projectName=data_1[i].inf[i].projectName;
                ojson.type=data_1[i].inf[i].projectType;
                ojson.other="";
                ojson.teacherId=data_1[i].inf[i].teacherName;
                ojson.applytime=data_1[i].inf[i].applytime;
                ojson.state=change(data_1[i].inf[i].state,data_1[i].inf[i].mylevel);
                for(var j=0;j<data_1[i].inf.length;j++){
                    if(j==data_1[i].inf.length-1){
                        ojson.other+=data_1[i].inf[j].stuId;
                    }else {
                        ojson.other+=data_1[i].inf[j].stuId+"/";
                    }
                }
                console.log(ojson);
                data.push(ojson);
                console.log(data);
            }
            console.log(data);
            Proceedata=data;
            auto_create_table_classroom(data);
        }
    });
    function change( s,l) {
        var  str="";
        var arr=["","院级","校级","国家级","未通过"]
        if(s==0){
            str="未审核";
        }else if(s==1){
            str="审核通过";
        }else if(s=="open"){

            str="开题答辩/"+arr[l];
        }
        else if(s=="mid"){
            str="中期答辩/"+arr[l];
        } else {
            str="结题答辩/"+arr[l];
        }
        return str;
    }
}
