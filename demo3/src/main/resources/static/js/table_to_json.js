// // table_id：需要转化成json的表格id，返回json数组
// function htmlToJson(table_id){
//     var table = angular.element('#'+table_id);
//     var data = [];
//
//     if(!table.length){
//         return data;
//     }
//
//     var vtable = table.clone();
//
//     var thead = [];
//     // 这里表头如果是用的td可以在find里面写成 tr:first-child td
//     angular.forEach(vtable.find('th'),function(value,index){
//         thead.push(value.innerHTML)
//     });
//
//
//     // 补全表格，去除rowspan colspan
//     angular.forEach(vtable.find('tr'),function(trv,tri){
//         angular.forEach(angular.element(trv).children(),function(tdv,tdi){
//             if(tdv.getAttribute('rowspan')){
//                 var rowspan = parseInt(tdv.getAttribute('rowspan')) || 1;
//                 tdv.removeAttribute('rowspan');
//                 var current_tr = angular.element(trv);
//                 var rowspan_node = null;
//
//                 while (rowspan > 1) {
//                     rowspan_node = angular.element(tdv).clone();
//                     current_tr.next().children().eq(tdi).before(rowspan_node);
//                     current_tr = current_tr.next();
//                     rowspan--;
//                 }
//             }
//             if(tdv.getAttribute('colspan')){
//                 var colspan = parseInt(tdv.getAttribute('colspan')) || 1;
//                 tdv.removeAttribute('colspan');
//                 var colspan_node = null;
//
//                 while (colspan > 1) {
//                     colspan_node = angular.element(tdv).clone();
//                     angular.element(tdv).after(colspan_node);
//                     colspan--;
//                 }
//             }
//         });
//     });
//
//     // 获取表格数据json数组
//     angular.forEach(vtable.find('tr'), function (trv, tri) {
//         var row_data = {};
//         angular.forEach(angular.element(trv).children(), function (tdv, tdi) {
//             row_data[thead[tdi]] = tdv.innerText;
//         });
//         data.push(row_data);
//     });
//
//     // 假设第一行是表头
//     return data.slice(1);
// }
//
// // data：需要转化成html表格的json数组，返回生成的table节点
// function jsonToHtml(data){
//     var thead = '';
//     angular.forEach(Object.keys(data[0]),function(value,index){
//         thead += "<th>" + value + "</th>"
//     });
//     thead = "<tr>" + thead + "</tr>";
//
//     var row = "";
//     var tbody = '';
//     angular.forEach(data, function (value, index) {
//         row = "";
//         angular.forEach(Object.keys(value), function (v, i) {
//             row += "<td>" + value[v] + "</td>";
//         });
//         row = "<tr>" + row + "</tr>";
//         tbody += row;
//     });
//
//     var table = document.createElement('table');
//     table.innerHTML = thead + tbody;
//     return table;
// }
// // 对第一列合并相同项，传入table里面的字符串对象，返回合并后的字符串对象（今天搞得太晚了，还真是朝9晚9的生活啊，先吃完饭在来优化吧，饿的了脑子飞了）
// function firstColMerge(str) {
//     var table = document.createElement('table');
//     table.innerHTML = str;
//     $scope.table = angular.element(table);
//
//     var rowspan = 1;
//     var last_td = document.createElement('td');
//     angular.forEach($scope.table.find('tr td:first-child'), function (value, key) {
//         if (last_td.innerHTML === value.innerHTML) {
//             value.style.display = "none";
//             rowspan++;
//             last_td.setAttribute("rowspan", rowspan);
//         } else {
//             last_td = value;
//             rowspan = 1;
//         }
//     });
//
//     return $scope.table.html();
// }
