//表格外层dom结构渲染，只需初始化渲染
function tableWrapFn(option){
	var o = {
		"parents" : "",
		"titleHead" : "巧达导入职位"
	}
	var opt = $.extend(o,option);

	var tableWrap = '';
	
	tableWrap += '<div class="table-header">'+ opt.titleHead +'</div>'+
				'<div class="select-newAdd">'+
		          '<div id="table-select" class="table-select">'+
		            '<label>筛选条件：<select name="dynamic-table-select" aria-controls="dynamic-table" class="form-control input-sm"></select></label>'+
		          '</div>'+
		          '<div id="dynamic-table_filter" class="dataTables_filter">'+
		           '<label>搜索条件：<input type="search" class="form-control input-sm" placeholder="" aria-controls="dynamic-table" /></label>'+
		          '</div>'+
		          '<input type="button" value="查找" class="btn btn-xs btn-info" id="btn-table" />'+
		        '</div>'+
                '<div class="clearfix"><div class="pull-right tableTools-container"></div></div>'+
				'<div class="table-body"></div>';
	$(opt.parents).append(tableWrap);
};

//table表格渲染，各次异步请求均需渲染
function tableDomFn(data,option){
	if(data.select && data.select.length != 0){
		var optionDom = '';
		for(var i=0;i<data.select.length;i++){
			optionDom += '<option value="'+ data.select[i] +'">'+ data.select[i] +'</option>'
		}
		$('#table-select select').empty().append(optionDom);
	}

	$('#dynamic-table_wrapper').remove();
	$('.clearfix .pull-right.tableTools-container').html('');

	var o = {
		"wrapClass" : "",  //表格table自定义className
		"check" : true,  //是否显示选择按钮
		"operation" : true,  //是否显示操作按钮
		"curPage" : 1   //请求的页码
	}
	var opt = $.extend(o,option);

	var tableDom = '';
	tableDom += '<table id="dynamic-table" class="table table-striped table-bordered table-hover"><thead><tr>';

	if(opt.check){
		tableDom += '<th class="center"><label class="pos-rel"><input type="checkbox" class="ace" /><span class="lbl"></span></label></th>';
	}

	for(var i=0;i<data.title.length;i++){
		tableDom += '<th data-name='+ data.title[i].name +'>'+ data.title[i].value +'</th>';
	}

	if(opt.operation){
		tableDom += '<th>操作</th>';
	}

	tableDom += '</tr></thead><tbody>';

	// 加字段标识start
	tableDom += '<tr>';

	if(opt.check){
		tableDom += '<td class="center"><label class="pos-rel"><input type="checkbox" class="ace" /><span class="lbl"></span></label></td>';
	}

	for(var i=0;i<data.title.length;i++){
		tableDom += '<td data-name='+ data.title[i].name +'></td>';
	}

	if(opt.operation){   //操作按钮
		tableDom += '<td><div class="hidden-sm hidden-xs action-buttons">'+
							'<a class="blue" href="#"><i class="ace-icon fa fa-search-plus bigger-130"></i></a>'+
							'<a class="green" href="#"><i class="ace-icon fa fa-pencil bigger-130"></i></a>'+
							'<a class="red" href="#"><i class="ace-icon fa fa-trash-o bigger-130"></i></a>'+
						'</div></td>';
	}

	tableDom += '</tr>';
	// 加字段标识end

	tableDom += '</tbody></table>';

	$(".table-body").append(tableDom);

    // 拷贝足够行数
    for(var i=0;i<data.data.length-1;i++){
    	var oTr = $("#dynamic-table tbody tr:eq(0)").clone();
		oTr.appendTo("#dynamic-table tbody");
    }
    // 循环按照每个td的data-name填充数据
    $("#dynamic-table tbody tr").each(function (i,o){
    	$(this).find('td').each(function (j,t){
    		var oKey = $(t).data('name');
    		$(t).html(data.data[i][oKey]);
    	})
    })

	handleTable();

};

function handleTable() {
	//initiate dataTables plugin
	var myTable = 
	$('#dynamic-table')
	//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
	.DataTable( {
		bAutoWidth: false,
		/*"aoColumns": [
		  { "bSortable": false },
		  null, null,null, null, null, null,
		  { "bSortable": false }
		],*/
		"aaSorting": [],
		"bFilter":false,
		"bPaginate":false,
		
		
		//"bProcessing": true,
        //"bServerSide": true,
        //"sAjaxSource": "http://127.0.0.1/table.php"	,

		//,
		//"sScrollY": "200px",
		//"bPaginate": true,  //页码
		"bLengthChange" : false

		//"sScrollX": "100%",
		//"sScrollXInner": "120%",
		//"bScrollCollapse": true,
		//Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
		//you may want to wrap the table inside a "div.dataTables_borderWrap" element

		//"iDisplayLength": 50


		/*select: {
			style: 'multi'
		}*/
    } );

	$.fn.dataTable.Buttons.swfPath = ""; //in Ace demo ../components will be replaced by correct assets path
	$.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';
	
	new $.fn.dataTable.Buttons( myTable, {
		buttons: [
		  {
			"extend": "colvis",
			"text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
			"className": "btn btn-white btn-primary btn-bold",
			columns: ':not(:first):not(:last)'
		  },
		  {
			"extend": "copy",
			"text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
			"className": "btn btn-white btn-primary btn-bold"
		  },
		  {
			"extend": "csv",
			"text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
			"className": "btn btn-white btn-primary btn-bold"
		  },
		  {
			"extend": "excel",
			"text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
			"className": "btn btn-white btn-primary btn-bold"
		  },
		  {
			"extend": "pdf",
			"text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
			"className": "btn btn-white btn-primary btn-bold"
		  },
		  {
			"extend": "print",
			"text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
			"className": "btn btn-white btn-primary btn-bold",
			autoPrint: false,
			message: 'This print was produced using the Print button for DataTables'
		  }		  
		]
	} );
	myTable.buttons().container().appendTo( $('.tableTools-container') );
	
	//style the message box
	var defaultCopyAction = myTable.button(1).action();
	myTable.button(1).action(function (e, dt, button, config) {
		defaultCopyAction(e, dt, button, config);
		$('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
	});
	
	
	var defaultColvisAction = myTable.button(0).action();
	myTable.button(0).action(function (e, dt, button, config) {
		
		defaultColvisAction(e, dt, button, config);
		
		
		if($('.dt-button-collection > .dropdown-menu').length == 0) {
			$('.dt-button-collection')
			.wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
			.find('a').attr('href', '#').wrap("<li />")
		}
		$('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
	});

	
	myTable.on( 'select', function ( e, dt, type, index ) {
		if ( type === 'row' ) {
			$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', true);
		}
	} );
	myTable.on( 'deselect', function ( e, dt, type, index ) {
		if ( type === 'row' ) {
			$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', false);
		}
	} );

	/////////////////////////////////
	//table checkboxes
	$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
	
	//select/deselect all rows according to table header checkbox
	$('#dynamic-table > thead > tr > th input[type=checkbox], #dynamic-table_wrapper input[type=checkbox]').eq(0).on('click', function(){
		var th_checked = this.checked;//checkbox inside "TH" table header
		
		$('#dynamic-table').find('tbody > tr').each(function(){
			var row = this;
			if(th_checked) myTable.row(row).select();
			else  myTable.row(row).deselect();
		});
	});
	
	//select/deselect a row when the checkbox is checked/unchecked
	$('#dynamic-table').on('click', 'td input[type=checkbox]' , function(){
		var row = $(this).closest('tr').get(0);
		if(this.checked) myTable.row(row).deselect();
		else myTable.row(row).select();
	});

	$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
		e.stopImmediatePropagation();
		e.stopPropagation();
		e.preventDefault();
	});

}

//分页
function page(option){
	var pages = {
		pageList : function(option){
			var o = {
				parents : '.table-body',  //分页dom插入的父元素
				curPage : '',     //当前页码
				nextPage : '',    //是否有下一页
				query : '',       //其它参数
				callBackFn : ''     //回调
			};
			var opt = $.extend(o,option);
			$('.pageList').remove();
			pages.pageDom(opt);
			pages.addEvent(opt);
		},
		pageDom : function (opt){
			var str = '<div class="pageList">'+
					  '<input type="button" value="上一页" class="prev" />'+
		              '<span>'+ opt.curPage +'</span>'+
		              '<input type="button" value="下一页" class="next" />'+
		          '</div>';

			$(opt.parents).append(str);
			if(opt.curPage == 1){
				$('.prev').addClass('disabled').attr('disabled','true');
			}else{
				$('.prev').attr('data-page', parseInt(opt.curPage) - 1);
			}

			if(opt.nextPage == 1){
				$('.next').addClass('disabled').attr('disabled','true');
			}else{
				$('.next').attr('data-page', parseInt(opt.curPage) + 1);
			}
		},
		addEvent : function (opt){
			$('.pageList input').off().on('click', function (e){
				if($(this).hasClass('disabled')){
					return; 
				}else{
					var param = $.extend(opt,{"curPage" : $(this).data('page'),})
					if(param.callBackFn && typeof param.callBackFn == "function"){
						param.callBackFn({
							current:$(this).data("page")
						})
					}
					$('.pageList').remove();
					pages.pageDom(param);
					pages.addEvent(param);
				}
			})
		}
	}

	pages.pageList(option);

}