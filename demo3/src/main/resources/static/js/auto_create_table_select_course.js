function auto_create_table_course(data) {
   var  annexList = new Array();
    var count=0;
   var  page=0;
    var size=10;
   var  last=0;
    var remain=0;
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

            var tab="<tr>"+ "<td class='color_1'>"+data[i].Course_number+"</td>";
            tab+="<td class='color_1'>"+data[i].course_name+"</td>";
            tab+="<td class='color_1'>"+data[i].teacher+"</td>";
            tab+="<td class='color_1'>"+data[i].Academy+"</td>";
            tab+="<td class='color_1'>"+data[i].type+"</td>";
            tab+="<td class='color_1'>"+data[i].time+"</td>";
            tab+="<td class='color_1'>"+data[i].capity+"</td>";
            tab+="<td  class='color_1' onclick='show($(this));'><a class='l_select_1' href='#'>查看详情</a></td>";
            tab+="<td  class='color_1' onclick='select($(this));'><a class='l_select_1' href='#'>选择</a></td>";
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

function select(a) {

    var table=$("#senfe");
    var  Course_number=table.find("tr").eq(a.parent("tr").index()+1).find("td").eq(0).text();
    var date=$("#year option:selected").text();

    var json={
        //Id: $.getUrlParam("id"), type: $.getUrlParam("type"),
        CourseNumber:Course_number,
        data:date
    };
    $.ajax({//选课插入数据库
        type: "post",
        url: "/refresh_inserted_information",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data_1,status) {
            auto_create_table_classroom(data_1);
        }
    });
    var json={
        //Id: $.getUrlParam("id"), type: $.getUrlParam("type"),
        CourseNumber:Course_number,
        data:date
    };
    $.ajax({
        type: "post",
        url: "/refresh_selected_information",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data_2,status) {
           auto_create_table_course_selected(data_2);
        }
    });


}
function show(a) {
    alert(data[a.parent("tr").index()].information);
}


function search_select_course(){
      var year =$("#year option:selected").text();
      var academy =$("#academy option:selected").text();
      var major_type=$("#major_type option:selected").text();
      var courseNmuber=$("#classroom_number").text();

      var json={ id:$.getUrlParam("id"),
          type:$.getUrlParam("type"),
          year:year,
          academy:academy,
          major_type:major_type,
          courseNumber:courseNmuber
      };

    $.ajax({
        type: "post",
        url: "/search_select_course",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data,status) {
            auto_create_table_course(data)
        }
    });

}


$.getUrlParam = function getUrlParam(name) {
    // 构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    // 匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);;
    return null; // 返回参数值
};


