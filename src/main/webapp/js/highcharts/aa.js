//初始化界面
$(function () {
    year();
    $('#btn_search').click(function() {
        createStaties();
        initTableData();
    });
});

var MONTH=[];
var BasicNumber=[];

// var year;
// $('#year').combobox({
//     onSelect: function(record){
//         year=record.text;
//     }
// });
//查询数据
function createStaties(){
    MONTH=[];
    BasicNumber=[];

    alert(year);
    $.ajax({
        url : path+'/cao/depotItem/salesTrans.do',
        data : {
            'year':$('#year').val()
        },
        dataType : 'json',
        type : 'post',
        async : false,
        success : function(data) {
            console.log(data);
            $.each(data, function(idx, elem) {
                MONTH.push(elem.month+"月");
                BasicNumber.push(elem.basicnumber);
            });

        }
    });
}

function year(){
    var dd = new Date();
    var currentYear = dd.getFullYear();
    var size = currentYear - 1950 + 1;
    var items=[];
    items.push({
        "id":"",
        "text":"--请选择--",
        "selected":true
    });
    for( var i=0; i<size; i++ ){
        var yearOld = currentYear-i;
        items.push({
            "id":yearOld,
            "text":yearOld
        });
    }
    $('#year').combobox({
        valueField:'id',
        textField:'text',
        data:items
    });
    // alert($('#year').val()+"year");
}

// JS 代码
function initTableData() {
    var chart = new Highcharts.Chart({
        chart: {
            type: 'line',
            renderTo: 'container'
        },
        title: {
            text: '月平均销售额'
        },
        xAxis: {
            categories: MONTH
        },
        yAxis: {
            title: {
                text: '销售额'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    // 开启数据标签
                    enabled: true
                },
                // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                enableMouseTracking: false
            }
        },
        series: [{
            name: '全部商品',
            data: BasicNumber
        }]
    });
}