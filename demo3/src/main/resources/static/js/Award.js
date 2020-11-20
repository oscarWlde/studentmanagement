function auto_create_table_classroom(data) {
    $(function(){
        //第二种： 动态创建表格的方式，使用动态创建dom对象的方式
        //清空所有的子节点
        $("#group_one").empty();
        for( var i = 0; i < data.length; i++ ) {
           var  tab="<tr>"+ "<td class='color_1'>"+data[i].awardId+"</td>";
            tab+= "<td class='color_1'>"+data[i].awardName+"</td>";
            tab+= "<td class='color_1'>"+data[i].content+"</td>";
            tab+= "<td class='color_1'>"+data[i].description+"</td>";
            tab+="<td class='color_1'>"+data[i].ratio+"</td>";
            tab+="<td  class='color_1' onclick='select($(this));'><a class='l_select_1' style='text-decoration: none;font-size: medium;font-weight: normal'  href='#'><i class='fa fa-arrow-circle-up' aria-hidden='true'></i></a></td>";
            tab=tab+"</tr>";
            $("#group_one").append(tab);
         }
    });
}
function select(a) {
    //
    AwardNum=AwardData[a.parent("tr").index()].awardId;
    AwardName=AwardData[a.parent("tr").index()].awardName;
    var content=AwardData[a.parent("tr").index()].content;
    $("#AwardName").textbox('setValue',AwardName);
    $("#AwardNum").textbox('setValue',AwardNum);
    $("#content").textbox('setValue',content);
    $("#stuId").textbox('setValue',$.getUrlParam("id"));
    $("#apply_classroom").window('open');
}

function  load_grade(){
    document.getElementById("r").style.display="none";
    document.getElementById("g").style.display="block";
    var json={
        Id:$.getUrlParam("id")
    };
    $.ajax({
        type: "post",
        data:JSON.stringify(json),
        url: "/student/award",
        dataType: "json",
        contentType: "application/json",
        success: function (data, status) {
            console.log(data.data);
            AwardData=data.data;
            auto_create_table_grade(data.data);
        }
    });

}
function  search_course(){
    document.getElementById("g").style.display="none";
    document.getElementById("r").style.display="block";
    $.ajax({
        type: "post",
        url: "/student/allaward",
        dataType: "json",
        contentType: "application/json",
        success: function (data, status) {
            console.log(data.data);
            AwardData=data.data
            auto_create_table_classroom(data.data);
        }
    });
}


function auto_create_table_grade(data) {
    annexList = new Array();
    count=0;
    page=0;
    size=10;
    last=0;
    remain=0;
    $(function(){
        //第二种： 动态创建表格的方式，使用动态创建dom对象的方式
        //清空所有的子节点
        $("#g_group_one").empty();
        //自杀
        // $("#J_TbData").remove();
        last=Math.floor(data.length/size);
        $("#total_page").text(last+1);
        remain=data.length%size;
        for( var i = 0; i < data.length; i++ ) {
           if(data[i].state=="0"){
               data[i].state="待审核";
           }else if(data[i].state=="1"){
               data[i].state="审核通过";
           }else {
               data[i].state="审核失败";
           }

            var tab="<tr>"+ "<td class='color_1'>"+data[i].awardId+"</td>";
            tab=tab+"<td class='color_1'>"+data[i].awardName+"</td>";
            tab=tab+"<td class='color_1'>"+data[i].content+"</td>";
            tab=tab+"<td class='color_1'>"+data[i].time+"</td>";
            tab=tab+"<td class='color_1'>"+data[i].state+"</td>";
            tab=tab+"</tr>";
            if (count < size) {
                //第一页显示的数量
                $("#g_group_one").append(tab);
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
        $("#g_group_one").html(tab);
        //显示当前页数
        $("#currentPage").text(currentPage);
    }



}
