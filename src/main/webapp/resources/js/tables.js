/* tablesFn
 * 调用方法：tablesFn('.jobPost',data,{"wrapClass" : "newClass","check":false})
 * parent: 要插入表格的父级元素
 * data: 表格中插入的数据
 * {}：表格格式自定义
*/

function tablesFn(parent,data,option){
	var o = {
		"type" : "simple",  //表格模式：简单"simple", 动态"dynamic"
		"wrapClass" : "",  //表格table自定义className
		"check" : false,  //是否显示选择按钮
		"detail" : false,  //是否显示详情按钮
		"operation" : false,  //是否显示操作按钮
		"detailMsg" : "详细内容展示", //详情DOM结构
		"onready" : null  //dom结果成功后的回调
	}
	var opt = $.extend(o,option);

	var tdNum = data.title.length;  //计算共有几列
	tdNum += opt.check ? 1 : 0;
	tdNum += opt.detail ? 1 : 0;
	tdNum += opt.operation ? 1 : 0;

	var tableDom = '';
	tableDom += '<table id="simple-table" class="table table-bordered table-hover '+  opt.wrapClass +'"><thead><tr>';

	// 表头
	if(opt.check){  //选择按钮
		tableDom += '<th class="center"><label class="pos-rel"><input type="checkbox" class="ace" /><span class="lbl"></span></label></th>';
	}
	if(opt.detail){   //详情按钮
		tableDom += '<th class="detail-col">详情</th>';
	}
	for(var i=0;i<data.title.length;i++){
		tableDom += '<th>'+ data.title[i] +'</th>';
	}
	if(opt.operation){   //操作按钮
		tableDom += '<th>操作</th>';
	}

	tableDom += '</tr></thead><tbody>';

	// 内容部分
	for(var i=0;i<data.data.length;i++){
		tableDom += '<tr>';
		if(opt.check){
			tableDom += '<th class="center"><label class="pos-rel"><input type="checkbox" class="ace" /><span class="lbl"></span></label></th>';
		}
		if(opt.detail){
			tableDom += '<td class="center"><div class="action-buttons"><a href="#" class="green bigger-140 show-details-btn" title="Show Details"><i class="ace-icon fa fa-angle-double-down"></i><span class="sr-only">Details</span></a></div></td>';
		}
		for(var j in data.data[i]){
			tableDom += '<td>'+ data.data[i][j] +'</td>';
		}
		if(opt.operation){   //操作按钮
			tableDom += '<td><div class="hidden-sm hidden-xs btn-group"><button class="btn btn-xs btn-success"><i class="ace-icon fa fa-check bigger-120"></i></button><button class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button><button class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button><button class="btn btn-xs btn-warning"><i class="ace-icon fa fa-flag bigger-120"></i></button></div><div class="hidden-md hidden-lg"><div class="inline pos-rel"><button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto"><i class="ace-icon fa fa-cog icon-only bigger-110"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close"><li><a href="#" class="tooltip-info" data-rel="tooltip" title="View"><span class="blue"><i class="ace-icon fa fa-search-plus bigger-120"></i></span></a></li><li><a href="#" class="tooltip-success" data-rel="tooltip" title="Edit"><span class="green"><i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li><li><a href="#" class="tooltip-error" data-rel="tooltip" title="Delete"><span class="red"><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li></ul></div></div></td>';
		}
		tableDom += '</tr>';
		if(opt.detail){
			tableDom += '<tr class="detail-row"><td colspan="'+ tdNum +'"><div class="center">详情展示内容</div></td></tr>';
		}
	}

	tableDom += '</tbody></table>';

	$(parent).append(tableDom);

	if(opt.detail){
		$('.show-details-btn').on('click', function(e) {
			e.preventDefault();
			$(this).closest('tr').next().toggleClass('open');
			$(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
		});
	}

}