/*汉化by lxf qq1140215489*/
(function(){
	var oLanguage={
		"oAria": {
			"sSortAscending": ": 升序排列",
			"sSortDescending": ": 降序排列"
		},
		"oPaginate": {
			"sFirst": "首页",
			"sLast": "末页",
			"sNext": "下页",
			"sPrevious": "上页"
		},
		"sEmptyTable": "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;没有相关记录",
		"sInfo": "第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条",
		"sInfoEmpty": "第 0 到 0 条记录，共 0 条",
		"sInfoFiltered": "",//(从 _MAX_ 条记录中检索)
		"sInfoPostFix": "",
		"sDecimal": "",
		"sThousands": ",",
		"sLengthMenu": "每页显示条数: _MENU_",
		"sLoadingRecords": "正在载入...",
		"sProcessing": '<img width="160" src="/img/sohu-waiting.gif"><div class="mt10" style="font-size: 24px;font-family: \'microsoft yahei\';">正在载入，请稍候...</div>',
		"sSearch": "搜索:",
		"sSearchPlaceholder": "",
		"sUrl": "",
		"sZeroRecords": "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未找到相关记录"
	}
	$.fn.dataTable.defaults.oLanguage=oLanguage;
	//$.extend($.fn.dataTable.defaults.oLanguage,oLanguage)

	//修改其他默认配置
	$.fn.dataTable.defaults.searching=false;
	$.fn.dataTable.defaults.ordering=false;
	//$.fn.dataTable.defaults.lengthChange=false;
	$.fn.dataTable.defaults.pageLength=50;
	//$.fn.dataTable.defaults.sDom='<"pull-right mt5"l>tip'; 这个为什么无效呢
	//"dom": '<"pull-right mt5"l>rt<"pull-left"i>p',//sDom写在通用配置里不管事儿呢？
	//"paging":   false,//"info":     false,

})();
$(function(){
	$(document).on("mousedown",".dataTables_processing",function(){return false;});//禁止点击过快时的选中
});





