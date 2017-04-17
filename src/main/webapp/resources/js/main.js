//设置iframe的高度
iFrameHeight();
//默认显示首页
defaulNav();
//切换iframe跳转链接
navSrc();

function iFrameHeight(){
    var iHeight = $(window).height() - $('#navbar').height() - 75;
    $('#myFrame').attr('height',iHeight);
}

function defaulNav(){
    $('#sidebar .index').parent('li').addClass('active');
    $('#myFrame').attr('src','index.html');
}

function navSrc(){
    $('#sidebar').find('a').off().on('click', function(){
        var _this = this;
        if($(_this).hasClass('dropdown-toggle')){
            return;
        }
        var className = $(_this).attr('class');
        var ifSrc = className+'.html';

        level(_this, ifSrc);
    })

    function level(obj,src,num){
        $('#sidebar').find('li').removeClass('active').removeClass('open');
        $(obj).parent('li').parents('li').addClass('active open');
        $(obj).parent('li').addClass('active');
        $('#myFrame').attr('src', src);
    }
}