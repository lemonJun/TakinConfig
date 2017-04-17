/*
 * @author yelin yelin@sohu-inc.com
 * @date 2013-09-23
 * @brief 实现页面请求等待以及超时错误的浮动提示
 * 共用class: float_tips，载入中class:loading_tips，错误class:error_tips，成功class:success_tips
 */
;(function(w, d, undefined){
    if(!d.getElementsByClassName){
        d.getElementsByClassName = function(className, element){
            var children = (element || d).getElementsByTagName('*');
            var elements = new Array();
            for (var i=0; i<children.length; i++){
                var child = children[i];
                var classNames = child.className.split(' ');
                for (var j=0; j<classNames.length; j++){
                    if (classNames[j] == className){
                    elements.push(child);
                    break;
                    }
                }
            }
            return elements;
        };
    }
    var Tips = function(type){
        //var inited, ele, st;
        this.inited = null;
        this.ele = null;
        this.st = null;
        var self = this;
        this.show = function(msg, time){
            if(this.inited){
            	this.hide();
            }
            this.ele = d.createElement('div');
            //ie6 has bug when using the subsequence code, what is the fuck!!!
            //ele.setAttribute('class', type + ' float_tips');
            this.ele.className = type + ' float_tips';
            if( type === 'loading_tips' ){
            	this.ele.innerHTML = '<span class="loading"></span>' + ( msg ? msg : '' );
            	d.body.appendChild(this.ele);
                this.inited = true;
                return;
            }
            this.ele.innerHTML = msg ? msg : '';
            d.body.appendChild(this.ele);
            this.inited = true;
            var t = time ? time : 3;
            this.st = setTimeout(function(){
                d.body.removeChild(self.ele);
                clearTimeout(self.st);
                self.st = null;
                self.inited = null;
                self.ele = null;
            }, t * 1000);
        };
        this.hide = function(){
            if(this.ele) {
                d.body.removeChild(this.ele);
                clearTimeout(this.st);
                this.st = null;
                this.inited = null;
                this.ele = null;
            }
        };
        //return {show : show, hide : hide};
    };
    w.loadingTips = new Tips('loading_tips');
    w.successTips = new Tips('success_tips');
    w.errorTips = new Tips('error_tips');
})(window, document);