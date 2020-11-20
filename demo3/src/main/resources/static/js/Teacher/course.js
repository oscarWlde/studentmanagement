window.onload = function() {

};


function set_course_table(table, data) {
    //var table = $("#course_table");
    for (var i = 0; i < data.length; i++) {
        table.find("tr").eq(data[i].day).find("td").eq(data[i].starttime).html(data[i].CourseNum+ "<br>" + data[i].CourseName + "<br>" + data[i].classroomId);
        table.find("tr").eq(data[i].day).find("td").eq(data[i].starttime).attr("onclick", "course_content($(this))");
        //table.find("tr").eq(data[i].day).find("td").eq(data[i].time).attr("id", data[i].course_id);
    }
}

function init_select_course_table(select_data) {
    var table = $("#select_table");
    for (var i = 0; i < select_data.length; i++) {
        var table_content = "<tr class='course_item'>" +
            "<td style='text-align:center;'><button style='width: 100%;' onclick='select($(this));'>选择</button></td>" +
            "<td>" + select_data[i].course_id + "</td>" +
            "<td>" + select_data[i].course_name + "</td>" +
            "<td>" + select_data[i].academy + "</td>" +
            "<td>" + select_data[i].type + "</td>" +
            "<td>" + select_data[i].time + "</td>" +
            "<td>" + select_data[i].capcity + "</td>" +
            "<td style='text-align:center;'><button style='width: 100%;' onclick='select($(this));'>查看详情</button></td>";
        table.append(table_content);
    }
}


function get_selected_course_table(selected_data) {
    var table = $("#selected_table");
    for (var i = 0; i < selected_data.length; i++) {
        var table_content = "<tr class='course_item'>" +
            "<td style='text-align:center;'><button style='width: 100%;' onclick='select($(this));'>退课</button></td>" +
            "<td>" + selected_data[i].course_id + "</td>" +
            "<td>" + selected_data[i].course_name + "</td>" +
            "<td>" + selected_data[i].academy + "</td>" +
            "<td>" + selected_data[i].type + "</td>" +
            "<td>" + selected_data[i].time + "</td>" +
            "<td>" + selected_data[i].capcity + "</td>" +
            "<td style='text-align:center;'><button style='width: 100%;' onclick='select($(this));'>查看详情</button></td>";
        table.append(table_content);
    }
}

function course_content(in_cell) {
    var x = in_cell.html().split("<br>");
    window.location.href = "/Teacher/CourseMore?id="+$.getUrlParam("id")+"&type="+$.getUrlParam("type")+"&courseNum="+x[0]+"&courseName="+x[1];
}

