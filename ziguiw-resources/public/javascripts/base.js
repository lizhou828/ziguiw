/**
 * Created with IntelliJ IDEA.
 * User: pengcg
 * Date: 13-1-23
 * Time: P.M 12:13
 */

function addFavorite(sURL, sTitle) {
    try {
        window.external.addFavorite(sURL.toString(), sTitle.toString());
    }
    catch (e) {
        try {
            window.sidebar.addPanel(sTitle.toString(), sURL.toString(), "");
        }
        catch (e) {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}

function setHome(vrl) {
    if(document.all){
        document.body.style.behavior='url(#default#homepage)';
        document.body.setHomePage(vrl);
    }
    else if(window.sidebar){
        if(window.netscape){
            try{
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            }
            catch(e){
                alert("您的浏览器未启用[设为首页]功能，开启方法：先在地址栏内输入about:config,然后将项 signed.applets.codebase_principal_support 值该为true即可");
            }
        }
        var prefs=Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
        prefs.setCharPref('browser.startup.homepage',vrl);
    }
}
