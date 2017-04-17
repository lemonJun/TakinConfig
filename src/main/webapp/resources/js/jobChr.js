/* 后端提示json示例：
 * data.title为表头的标题，按顺序排列
 * data.data为表格内容，按title的顺序排列*/
var data = {
	"code": 0,
	"data":[{"日期":"2016-05-20","免费新增":"1000","付费新增":"2000","免费合计":"200000","付费合计":"250000","总计":"450000"},
	        {"日期":"2016-05-21","免费新增":"1100","付费新增":"2100","免费合计":"200100","付费合计":"250100","总计":"450200"},
	        {"日期":"2016-05-22","免费新增":"900","付费新增":"1200","免费合计":"200050","付费合计":"250300","总计":"450350"},
	        {"日期":"2016-05-23","免费新增":"1050","付费新增":"2200","免费合计":"200200","付费合计":"250140","总计":"450340"},
	        {"日期":"2016-05-24","免费新增":"950","付费新增":"1600","免费合计":"200650","付费合计":"251200","总计":"451850"},
	        {"日期":"2016-05-25","免费新增":"1300","付费新增":"1890","免费合计":"201000","付费合计":"250900","总计":"451900"},
	        {"日期":"2016-05-26","免费新增":"1650","付费新增":"2230","免费合计":"201500","付费合计":"251600","总计":"453100"}],
	"title":["日期","免费新增","付费新增","免费合计","付费合计","总计"],   
	"msg":"success"
}


jobChr();
function jobChr(){
	fn(data);
	function fn(data){

		tablesFn('.table_jobChr',data);

	};
	/*$.ajax({
		url: "",  //请求后端地址
		dataType: "json",
		param: "",
		success: function(data){
			if(data.code == 0){
				tablesFn('.jobChrBox',data,{"detailMsg":detailMsg});
			}else{
				alert('网络错误，请稍后再试！');
			}
		},
		error: function (){
			alert('网络错误，请稍后再试！');
		}
	})*/
};


var myChart = echarts.init(document.getElementById('echart_jobChr'));
option = {
    title: {
        text: '堆叠区域图'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['免费新增','付费新增']
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['2016-05-20','2016-05-21','2016-05-22','2016-05-23','2016-05-24','2016-05-25','2016-05-26']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'免费新增',
            type:'line',
            stack: '总量',
            areaStyle: {normal: {}},
            data:[1000, 1100, 900, 1050, 950, 1300, 1650]
        },
        {
            name:'付费新增',
            type:'line',
            stack: '总量',
            areaStyle: {normal: {}},
            data:[2000, 2100, 1200, 2200, 1600, 1890, 2230]
        }
    ]
};
myChart.setOption(option);

