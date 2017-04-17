/*
    @author yelin yelin@sohu-inc.com 有空重构下lxf
    @brief : this plugin is based on jquery, inject the jquery namespace with 
            5 veriable : ydialograndomvalue, yfixtipstimer, fn.ydialog, fn.yfixtips, fn.ytips
            notice : fn.ydialog needs the outer css support, specified in sohu cloudscape project. 
                     fn.yfixtips and fn.ytips has no dependencies except jquery and can be used freely
*/
;(function($){
    $.yfixtipstimer = null;
    $.yfixtips = function( opts ){
        if( $.yfixtipstimer ){
            clearTimeout($.yfixtipstimer);
            $.yfixtipstimer = null;
            $('.yfixtips-element').remove();
        }
        var opt;
        var defaultSettings = {
            title : '提示消息'
            ,content : '这里是提示内容...'
            ,time : 3
            ,type : 'error'
            ,lock : false
            ,width : 200
            ,position : 'right-bottom'
        };
        if(typeof opts === 'string'){
            opt = $.extend({}, defaultSettings, {content : opts});
        }else{
            opt = $.extend({}, defaultSettings, opts);
        }
        var positionStr = '';
        switch(opt.position){
            case 'left-top':
                positionStr = 'left:0;top:0px;';
                break;
            case 'left-bottom':
                positionStr = 'left:0;bottom:0;';
                break;
            case 'right-top':
                positionStr = 'right:0;top:0;';
                break;
            case 'center':
                positionStr = 'left:50%;top:100px;margin-left:-' + opt.width/2 + 'px;';
                break;
            case 'right-bottom':
            default:
                positionStr = 'right:0;bottom:0;';
                break;
        }
        function createHTML(){
            var str = '';
            str +=    '<div class="yfixtips yfixtips-element" style="z-index: 3001;background:#fff;border:1px solid #999;border-radius:5px 5px 0 0;position:fixed;'+ positionStr +'width:'+ opt.width +'px;display:none;">'
                        + '<div class="ftips-header" style="color:#fff;position:relative;height:30px;line-height:30px;border-bottom: 1px solid #aaa;background:'+ getColor() +';">'
                            + '<div style="text-indent: 1em;font-weight:600;">'+ opt.title +'</div>'
                            + '<a class="yfixtips-close" href="javascript:;" style="position:absolute;right:5px;top:0px;text-decoration:none;" title="关闭">'
                                + '<i style="font-size:18px;font-weight:800;font-style:normal;color:#fff;padding:0 4px;">x</i>'
                            + '</a>'
                        + '</div>'
                        + '<div class="ftips-body" style="min-height:120px;">'
                            +'<div style="font-size:13px;padding:10px 10px;">'
                                + '<p style="">'+ opt.content +'</p>'
                            +'</div>'
                        + '</div>'
                    + '</div>'
            return str;
        }
        var overlayHTML  = [
                            '<div class="overlay yfixtips-element" style="position:fixed;_position: absolute; top: 0px; left: 0px; background: #000; opacity: 0.3; z-index: 1000; display: block; width: 100%; height: 100%;filter:alpha(opacity=30);">',
                                '<iframe width="100%" height="100%" frameborder="0" src="javascript:false" style="position:absolute;top:0;left:0;z-index:1;"></iframe>',
                                '<div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; z-index: 2; background: #000;"></div>',
                            '</div>'
                            ].join('');
        function getColor(){
            var color = '#900';
            switch(opt.type){
                case 'success':
                    color = '#1FBBA6';
                    break;
                case 'warn':
                    color = '#844';
                    break;
                case 'normal':
                    color = '#666';
                    break;
                case 'error':
                default:
                    break;
            }
            return color;
        }
        var elHTML = createHTML();
        var el = $( elHTML );
        var overlayElement = opt.lock ? $(overlayHTML) : '';
        $(document.body).append(overlayElement).append(el);
        el.slideDown(600);
        $('.yfixtips-close').on('click', function(){
            $.yfixtipstimer && clearTimeout($.yfixtipstimer);
            el.slideUp(600, function(){
                $('.yfixtips-element').remove();
            });
        });
        $.yfixtipstimer = setTimeout(function(){
            $.yfixtipstimer && clearTimeout($.yfixtipstimer);
            el.slideUp(600, function(){
                $('.yfixtips-element').remove();
            });
        }, opt.time * 1000);
        el.on('mouseenter', function(){
            $.yfixtipstimer && clearTimeout($.yfixtipstimer);
        }).on('mouseleave', function(){
            $.yfixtipstimer = setTimeout(function(){
                $.yfixtipstimer && clearTimeout($.yfixtipstimer);
                el.slideUp(600, function(){
                    $('.yfixtips-element').remove();
                });
            }, opt.time * 1000);
        });
    };

    $.fn.ytips = function(opts){
        var self = this;
        var defaultSettings = {
            content : self.data('title') ? self.data('title') : '提示消息内容。'
        };
        var opt = $.extend({}, defaultSettings, opts);
        function createHTML( left, top ){
            var str = '';
            str +=    '<div class="ytips-element" style="width:180px;height:28px;z-index: 1100;background:#fff;border:1px solid #999;border-radius:3px;position:absolute;left:'+left+';top'+top+';">'
                        + '<div class="ytips-body" style="">'
                            +'<div style="font-size:13px;padding:5px 10px;">'
                                + '<p style="text-align:center;">'+ opt.content +'</p>'
                            +'</div>'
                        + '</div>'
                    + '</div>'
            return str;
        }
        this.on('mouseenter', function(e){
            var left = (e.pageX-90) + 'px';
            var top = (e.pageY-34) + 'px';
            var el = $(createHTML(left, top));
            $(document.body).append(el);
        }).on('mousemove', function(e){
            var left = (e.pageX-90) + 'px';
            var top = (e.pageY-34) + 'px';
            $('.ytips-element').css({
                left : left,
                top : top
            });
        }).on('mouseleave', function(e){
            $('.ytips-element').remove();
        });
    }
})(jQuery || $);
