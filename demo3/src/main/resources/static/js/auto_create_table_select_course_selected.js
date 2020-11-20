
function auto_create_table_course_selected(data) {
    var annexList = new Array();
    var count=0;
    var page=0;
   var size=10;
   var  last=0;
   var  remain=0;
    $(function(){
        //第二种： 动态创建表格的方式，使用动态创建dom对象的方式
        //清空所有的子节点
        $("#group_one_1").empty();
        //自杀
        // $("#J_TbData").remove();
        last=Math.floor(data.length/size);
        $("#total_page_1").text(last+1);
        remain=data.length%size;
        for( var i = 0; i < data.length; i++ ) {

            var tab="<tr>"+ "<td class='color_1'>"+data[i].Course_number+"</td>";
            tab+="<td class='color_1'>"+data[i].course_name+"</td>";
            tab+="<td class='color_1'>"+data[i].teacher+"</td>";
            tab+="<td class='color_1'>"+data[i].Academy+"</td>";
            tab+="<td class='color_1'>"+data[i].type+"</td>";
            tab+="<td class='color_1'>"+data[i].time+"</td>";
            tab+="<td class='color_1'>"+data[i].capity+"</td>";
            tab+="<td  class='color_1' onclick='show($(this));'><a class='l_select_1' href='#'>查看详情</a></td>";
            tab+="<td  class='color_1' onclick='delect($(this));'><a class='l_select_1' href='#'>删除</a></td>";
            tab=tab+"</tr>";
            if (count < size) {
                //第一页显示的数量
                $("#group_one_1").append(tab);
                $("#prePage_1" ).attr("disabled","disabled");
                $("#nextPage_1").attr("disabled","disabled");
            } else {
                //恢复点击
                $("#nextPage_1").removeAttr("disabled");
            }
            count++;
            //保存到数组中
            var annex = new String();
            annex=tab;
            annexList.push(annex);
        }
        $("#currentPage_1").text(page+1);
    });

//上一页
    $("#prePage_1").click( function () {
        page--;
        paging();
    });

    //下一页
    $("#nextPage_1").click( function () {
        page++;
        paging();
    });
    //首页
    $("#firstPage_1").click( function () {
        page=0;
        paging();

    });
    //末页
    $("#lastPage_1").click( function () {
        page=last;
        paging();

    });
    //指定页
    $("#jump_1").click( function () {
        page=$("#aim_page_1").val()-1;
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
            $("#nextPage_1" ).attr("disabled","disabled");
        } else {
            //恢复点击
            $("#nextPage_1").removeAttr("disabled");
        }

        if(page==0) {
            //到了第一页不可以点击
            $("#prePage_1" ).attr("disabled","disabled");
        } else {
            $("#prePage_1").removeAttr("disabled");
        }
        //填充到表格
        $("#group_one_1").html(tab);
        //显示当前页数
        $("#currentPage_1").text(currentPage);
    }
}
function show(a) {
    alert(data[a.parent("tr").index()].information);
}
function delect(a) {
    var table=$("#senfe_1");
    var  Course_number=table.find("tr").eq(a.parent("tr").index()+1).find("td").eq(0).text();
    var date=$("#year option:selected").text();

    var json={
        //Id: $.getUrlParam("id"), type: $.getUrlParam("type"),
        CourseNumber:Course_number,
        data:date
    };
    $.ajax({
        type: "post",
        url: "/refresh_deleted_information",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data_2,status) {
            auto_create_table_course_selected(data_2);
        }
    });
    var json={
        //Id: $.getUrlParam("id"), type: $.getUrlParam("type"),
        CourseNumber:Course_number,
        data:date
    };
    $.ajax({
        type: "post",
        url: "/refresh_select_information",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data,status) {
            auto_create_table_course(data);
        }
    });




}