function searchCourseTable() {
   var course_year = $("#course_year option:selected").text();
   var arr=course_year.split("/");
   var json={Id:$.getUrlParam("id"),type:$.getUrlParam("type"),year:arr[0],semester:arr[1]};
    var table=$("#courseTable");
    for(var i=1;i<=7;i++){
        for(var j=1;j<=7;j++){
            table.find("tr").eq(i).find("td").eq(j).html("");
        }
    }
    $.ajax({
        type: "post",
        url: "/load_selected_courseTable",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data_1,status) {
            var data=data_1.data;
            $("#totalCredit").text(data_1.data[0].gpa);
            console.log(data);
                for(var i=0;i<data.length;i++){
                    table.find("tr").eq(data[i].time).find("td").eq(data[i].weekdata).html(data[i].courseName+" "+data[i].courseNum+"  "+data[i].teacherName+"  <br>"+data[i].classroomId+" "+"1"+"-"+"16周");
                }
        }
    });

}

function search_grade_table(){
    var course_year = $("#g_course_year option:selected").text();
    var arr=course_year.split("/");
    var json={id:$.getUrlParam("id"),type:$.getUrlParam("type"),year:arr[0],semster:arr[1]};
    $.ajax({
        type: "post",
        url: "/student/search/grade",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data_2,status) {
            auto_create_table_grade(data_2.data);
         }

    });
}

function  load_grade(){
    document.getElementById("r").style.display="none";
    document.getElementById("g").style.display="block";

}

function  search_course(){
    document.getElementById("g").style.display="none";
    document.getElementById("r").style.display="block";
}

function displayCurrentTime() {
    var myDate=new Date();
    document.getElementById("t").innerHTML=  myDate.toLocaleDateString();
}



