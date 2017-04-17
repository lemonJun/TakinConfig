$(function (){
	$("#topDateTime").html(getNowDate());
	leftmenuBind();
	logOutBind();
});

/*全局常量*/
var progressHtml='<div class="progress"><div class="progress-bar progress-bar-striped active" style="width: 5%"></div></div>';

/*全局函数*/
//leftmenu交互
function leftmenuBind(){
	$(".leftmenu-list li>a").click(function(){
		var box=$(this).parent("li");
		var ul=$(this).next("ul");
		if(ul.length>0) {
			if(box.hasClass("menu-open")){
				ul.slideUp(200,function(){
					box.removeClass("menu-open");
				});
			}else{
				ul.slideDown(200,function(){
					box.addClass("menu-open");
				});			
			}
		}else{//换成刷新
			//if(box.hasClass("menu-on")) return false;
			//box.addClass("menu-on");
		}
	});
}
//注销退出
function logOutBind(){
	$(document).on("click","#logOut",function(){
		var userId=$(this).attr("userId");
		bootbox.dialog({
			message: "确定要退出么？",
			buttons: {
				cancel:{label: "取 消"},
				ok: {
					label: "退 出",
					className: "btn-danger",
					callback: function(rs) {
						if (rs) logOut(userId);
					}
				}
			}
		});
	});
}
//退出
function logOut(id){
	$.ajax({
	    url:"/user/to_logout",
	    method:"post",
	    data:{userid:id},
	    dataType:"json",
	    success:function(rs){
	    	ajaxSuccessCallback(rs);
	    	if(rs.code!=returnCode.OK) return false;
	    	location.href='http://sns-sso.sce.sohuno.com/logout';
	    },
	    error:function(){
			ajaxErrorCallback();
	    },
	    complete:function(){}		    
	});	
}
//获取当前日期
function getNowDate(){
	var o=new Date();
	return o.getFullYear()+"-"+(o.getMonth()+1)+"-"+o.getDate();
}
//自媒体头像error替换操作
function avatarReplace(o){
	o.name=$(o).attr("src");
	o.onerror=null;
	o.src="/static/img/avatar_error.png";
	o.title="头像载入失败";
}
//延时执行
function delayDo(fn,time){
	if(typeof time=="undefined") time=0;
    var tt=setTimeout(function(){			        	
    	fn();
    	clearTimeout(tt);
    },time);
}

//cookie操作
function getCookie(name) {   
	var ck=document.cookie;
	var index = ck.indexOf(name + "=");  
	if (index == -1) return null;  
	index = ck.indexOf("=", index) + 1;  
	var endstr = ck.indexOf(";", index);  
	if (endstr == -1) endstr = ck.length;  
	return unescape(ck.substring(index, endstr));  
}  
function setCookie(name, value) { 
	if(value!=null){
		document.cookie=name + "=" + escape(value) + ";";
	}else{
		var ck=document.cookie;
		var reg=new RegExp(name+"=.*?;","g");
		document.cookie=ck.replace(reg,"");		
	}
}
//加载小狐狸loading//样式和分页保持一致 详见jquery.dataTables-config.js 以后可以统一成一个
function globalLoadingTip(pSelector,toggle){
	if(typeof toggle!="undefined" && toggle=="hide"){
		$("#globalLoadingTipBox").hide();
	}else{
		if($("#globalLoadingTipBox").length==1) {
			$("#globalLoadingTipBox").show();
			return;
		}
		var s='<div id="globalLoadingTipBox" class="dataTables_processing"> <img width="160" src="/img/sohu-waiting.gif"><div class="mt10" style="font-size: 24px;font-family: \'microsoft yahei\';">正在载入，请稍候...</div> </div>';
		$(pSelector).append(s);
	}
}

