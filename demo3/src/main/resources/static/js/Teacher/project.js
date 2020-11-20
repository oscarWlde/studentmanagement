window.onload = function() {

    var project_data = [{
        project_id: "ZXY-01",
        project_name: "基于71546跑",
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
    this.project(project_data);
}

function nameFunction() {
    var name = "林新宇";
    document.getElementById("home_head_name").innerHTML = "姓名：" + name;
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