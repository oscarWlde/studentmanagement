
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
        url: "/selected_courseTable",
        data:JSON.stringify(json),
        dataType: "json",
        contentType:"application/json",
        success:function (data_1,status) {
            var data=data_1.data;
            console.log(data);
            for(var i=0;i<data.length;i++){
                table.find("tr").eq(data[i].time).find("td").eq(data[i].weekdata).html(data[i].courseName+" "+data[i].courseNum+"  "+data[i].teacherName+"  <br>"+data[i].classroomId+" "+"1"+"-"+"16周");
            }
        }
    });

}