var weeks = []
var days = []
function auto_create_table_selected_classroom(data) {
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
            weeks.push(data[i].week)
            days.push(data[i].day)
            t="111";
            var tab="<tr>"+ "<td class='color_1'>"+data[i].classroomId+"</td>";
            tab+="<td class='color_1'>"+data[i].takeDate+"</td>";
            tab+="<td class='color_1'>"+data[i].startTime+"</td>";
            tab+="<td class='color_1'>"+data[i].endTime+"</td>";
            tab+="<td class='color_1'>"+data[i].takeId+"</td>";
            tab+="<td class='color_1'>"+data[i].information+"</td>";
            tab+="<td class='color_1'>"+"成功"+"</td>";
            tab+="<td data-index='"+data[i].week + "' data-index2 = '"+data[i].day + "' class='color_1' onclick='cancel($(this));'><a class='l_select_1' style='text-decoration: none;font-size: medium;font-weight: normal'  href='#'><i class=\"fa fa-times-circle-o\" aria-hidden=\"true\"></i> </a></td>";
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





function  Search_selected_classroom(){

    var json = {
        Id: $.getUrlParam("id"),
        type: $.getUrlParam("type"),
        year:$("#year option:selected").text(),
        week:$("#week option:selected").text(),
        building:$("#building option:selected").text(),
        classroom: $("#classroom_number").val()
    };
    $.ajax({
        type: "post",
        url: "/Search_selected_classroom",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data,status) {
            auto_create_table_selected_classroom(data);
        }
    });

}
function cancel(a) {
    console.log(a.attr("data-index"))

    var table=$("#senfe");
    var tr=table.find("tr").eq(a.parent("tr").index()+1);
    var str=tr.find("td").eq(1).text().split("-");
    var days=DateDiff(tr.find("td").eq(1).text(),"2019-09-02");
    var week=a.attr("data-index");
    var day=a.attr("data-index2");
    console.log("1");
    console.log(str);
    var json={
        takeDate:tr.find("td").eq(1).text(),
        Id: $.getUrlParam("id"),
        type: $.getUrlParam("type"),
        classroomid:tr.find("td").eq(0).text(),
        week:week,
        day:day,
        startTime:tr.find("td").eq(2).text(),
        endTime:tr.find("td").eq(3).text()
    }
    console.log(json);
    $.ajax({
        type: "post",
        url: "/Classroom/cancel/apply",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data,status) {
                  alert("取消成功");
                 window.location.reload();


        }
    });

}
