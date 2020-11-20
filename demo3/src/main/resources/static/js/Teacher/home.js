window.onload = function() {
    var select_data = [{
        course_id: "LXY-01",
        course_name: "人工智能",
        academy: "软件学院",
        type: "主修",
        time: "星期二 第六节",
        capcity: "50",
        information: "NULL"
    }, {
        course_id: "LXY-02",
        course_name: "数据库实践",
        academy: "软件学院",
        type: "主修",
        time: "星期三 第六节",
        capcity: "60",
        information: "NULL"
    }, {
        course_id: "LXY-03",
        course_name: "数据结构",
        academy: "软件学院",
        type: "主修",
        time: "星期三 第六节",
        capcity: "80",
        information: "NULL"
    }];
    var selected_course = [{
        check: "1",
        data: [{
            course_id: "LXY-01",
            course_name: "人工智能",
            academy: "软件学院",
            type: "主修",
            time: "星期二 第六节",
            capcity: "50",
            information: "NULL"
        }, {
            course_id: "LXY-03",
            course_name: "数据结构",
            academy: "软件学院",
            type: "主修",
            time: "星期三 第六节",
            capcity: "80",
            information: "NULL"
        }]
    }];
    var course_data = [{
        day: "1",
        time: "1",
        course_id: "LXY-01",
        course_name: "人工智能",
        context: "null"
    }, {
        day: "2",
        time: "1",
        course_id: "LXY-03",
        course_name: "数据库",
        context: "null"
    }];
    var project_data = [{
        project_id: "ZXY-01",
        project_name: "基于爱情的长跑",
        type: "实物类",
        captain: "林新宇",
        number: "2",
        others: "孔德焱",
        state: "1",
        time: "2019"
    }, {
        project_id: "LXY-01",
        project_name: "基于123",
        type: "实物类",
        captain: "林新宇",
        number: "2",
        others: "孔德焱",
        state: "1",
        time: "2019"
    }];
    this.init_select_course_table(select_data);
    this.get_selected_course_table(selected_course[0].data);
    this.set_course_table($("#course_table"), course_data);
    this.set_course_table($("#selected_course_table"), course_data);
    this.project(project_data);
}

function nameFunction() {
    var name = "林新宇";
    document.getElementById("home_head_name").innerHTML = "姓名：" + name;
}

function set_course_table(table, data) {
    //var table = $("#course_table");
    for (var i = 0; i < data.length; i++) {
        table.find("tr").eq(data[i].day).find("td").eq(data[i].time).html(data[i].course_id + "<br>" + data[i].course_name + "<br>" + data[i].context);
        table.find("tr").eq(data[i].day).find("td").eq(data[i].time).attr("onclick", "course_content($(this))");
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
    //alert(in_cell.html());
    var x = in_cell.html().split("<br>");
    alert(x[0]);
}

function project(data) {
    var table = $("#project_table");
    for (var i = 0; i < data.length; i++) {
        var table_content = "<tr>" +
            "<td style='text-align:center;'>" + data[i].project_id + "</td>" +
            "<td style='text-align:center;'>" + data[i].project_name + "</td>" +
            "<td style='text-align:center;'>" + data[i].type + "</td>" +
            "<td style='text-align:center;'>" + data[i].captain + "</td>" +
            "<td style='text-align:center;'>" + data[i].number + "</td>" +
            "<td style='text-align:center;'>" + data[i].others + "</td>" +
            "<td style='text-align:center;'>" + data[i].state + "</td>" +
            "<td style='text-align:center;'>" + data[i].time + "</td>";
        table.append(table_content);
    }
}