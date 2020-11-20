function auto_create_table_grade(data) {
    console.log(data);
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
            var tab="<tr>"+ "<td class='color_1' style='font-size: medium'>"+data[i].courseId+"</td>";
            tab=tab+"<td class='color_1' style='font-size: medium'>"+data[i].courseName+"</td>";
            tab=tab+"<td class='color_1' style='font-size: medium'>"+data[i].teacherName+"</td>";
            tab=tab+"<td class='color_1' style='font-size: medium'>"+data[i].credit+"</td>";
            tab=tab+"<td class='color_1' style='font-size: medium'>"+data[i].pgrade+"</td>";
            tab=tab+"<td class='color_1' style='font-size: medium'>"+data[i].grade+"</td>";
            tab=tab+"<td class='color_1' style='font-size: medium'>"+data[i].totalgrade+"</td>";
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
if(next==1) {
        //上一页
        next = 0;
//上一页
        $("#prePage").click(function () {
            page--;
            paging();
        });

        //下一页
        $("#nextPage").click(function () {
            page++;
            paging();
        });
        //首页
        $("#firstPage").click(function () {
            page = 0;
            paging();

        });
        //末页
        $("#lastPage").click(function () {
            page = last;
            paging();

        });
        //指定页
        $("#jump").click(function () {
            page = $("#aim_page").val() - 1;
            paging();

        });

        //分页
        function paging() {
            var tab = "";
            var curentNumber = page * size;
            var length = curentNumber + size;
            //当前页数
            var currentPage = page + 1;
            for (var i = curentNumber; i < length; i++) {
                // console.log(annexList[i]);
                if (typeof (annexList[i]) == "undefined") {
                    break;
                }
                tab += annexList[i];
            }
            if (page == last) {
                //到了最后一页不可以点击
                $("#nextPage").attr("disabled", "disabled");
            } else {
                //恢复点击
                $("#nextPage").removeAttr("disabled");
            }

            if (page == 0) {
                //到了第一页不可以点击
                $("#prePage").attr("disabled", "disabled");
            } else {
                $("#prePage").removeAttr("disabled");
            }
            //填充到表格
            $("#g_group_one").html(tab);
            //显示当前页数
            $("#currentPage").text(currentPage);
        }


    }
}