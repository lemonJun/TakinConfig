//分页
function page(option){
	var pages = {
		pageList : function(option){
			var o = {
				parents : '#grid-pager',  //分页dom插入的父元素
				curPage : '',     //当前页码
				totalPage : '',    //是否有下一页
				query : '',       //其它参数
				callBackFn : ''     //回调
			};
			var opt = $.extend(o,option);
			$('.pageList').remove();
			pages.pageDom(opt);
			pages.addEvent(opt);
		},
		pageDom : function (opt){
			var nextPage = (opt.curPage < opt.totalPage) ? true : false;
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

			if(!nextPage){
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