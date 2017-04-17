var returnCode = {
    "OK": 0
    , "-1": "操作失败，请稍后重试!"
};


function ajaxSuccessCallback(rs, tipsflag, reloadflag) {
    var code = rs.code;
    if (!rs || !code) code = 0;//if(typeof returnCode[""+code] =="undefined") code=0;
    var tip = returnCode["" + code];
    if (code == 10000) {
        $.yfixtips({
            content: tip,
            type: 'success'
        });
    } else if (code == 10510) {
        $.yfixtips(tip + '即将跳转至登录页！');
        setTimeout(function () {
            location.href = "/";
        }, 2000);
    } else {
        $.yfixtips(tip);
    }
    //这里加某个code的特殊处理
}
/*
 case returnCode.OK:
 if (tipsflag && reloadflag) {
 $.yfixtips({
 content : '操作成功，页面即将刷新！',
 type : 'success'
 });
 setTimeout(function() {
 location.reload();
 }, 2600);
 } else if (reloadflag) {
 location.reload();
 } else {
 tipsflag && $.yfixtips({
 content : '操作成功！',
 type : 'success'
 });
 }*/

function ajaxErrorCallback() {
    if (typeof ajaxIsAbort != "undefined" && ajaxIsAbort) {
        $.yfixtips({content: "请求已停止！", type: 'success'});
        ajaxIsAbort = undefined;
    } else {
        $.yfixtips('操作失败，请稍后重试！');
    }
}
