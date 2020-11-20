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
            tab+="<td class='color_1'>"+data[i].projectType+"</td>";
            tab+="<td class='color_1'>"+data[i].teacherName+"</td>";
            tab+="<td class='color_1'>"+data[i].academyName+"</td>";
            tab+="<td class='color_1'>"+data[i].studentNum+"</td>";
            tab+="<td  class='color_1' onclick='select($(this));'><a class='l_select_1' style='text-decoration: none;' href='#'><i class='fa fa-arrow-circle-up' aria-hidden='true'></i></a></td>";
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
        currentPage=page+1;


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
        currentPage = page+1;
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
    projectId=Projectdata[a.parent("tr").index()+size*(currentPage-1)].projectId;
    $("#projectId").textbox('setValue',projectId);
    $("#apply_classroom").window('open');
}
function  onSmText(){
    var number=$("#number").val();
    var name=$("#name").val();
    var sex=$("input[name='sex']:checked").val();
    var tel=$("#tel").val();
    var sf=$("input[name='sf']:checked").val();

    document.getElementById("number_1").style.display="none";
    document.getElementById("name_1").style.display="none";
    document.getElementById("sex_1").style.display="none";
    document.getElementById("tel_1").style.display="none";
    document.getElementById("sf_1").style.display="none";
    var tag=1;
    if(number==""){
      document.getElementById("number_1").style.display="inline";
      tag=0;
    }
    if(name==""){
        document.getElementById("name_1").style.display="inline";
        tag=0;
    }
    if(sex==null){
        document.getElementById("sex_1").style.display="inline";
        tag=0;
    }
    if(tel==""){
        document.getElementById("tel_1").style.display="inline";
        tag=0;
        tag=0;
    }
    if(sf==null){
        document.getElementById("sf_1").style.display="inline";
        tag=0;
    }
    if(tag==0){

    }
    else{
        document.getElementById("dialog_1").style.display="none";
        document.getElementById("tm").style.display="none";
        var json={Id: $.getUrlParam("id"), type: $.getUrlParam("type"),
            number:number,name:name,sex:sex, tel:tel,sf:sf};

        //adat={
        //   check:"true";
        // }
        $.ajax({
            type: "post",
            url: "/select_project",
            data:JSON.stringify(json),
            dataType: "json",
            contentType:"application/json",
            success:function (data,status) {
                document.getElementById("dialog_1").style.display="none";
                document.getElementById("tm").style.display="none";
            }
        });
    }

    return false;
}
function  search_Project(){

    var project_name=$("#project_name").val();
    var academy=$("#Academy option:selected").val();
    var year=$("#year option:selected").text();

    var json={Id: $.getUrlParam("id"), type: $.getUrlParam("type"),
        project_name:project_name,academyName:academy,year:year};

    $.ajax({
        type: "post",
        url: "/student/ssssssearchacademy",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data,status) {
            Projectdata=data.data;
            auto_create_table_classroom(data.data);
        }
    });

}
function ok() {
    document.getElementById("dialog_1").style.display="none";
    document.getElementById("tm").style.display="none";
}