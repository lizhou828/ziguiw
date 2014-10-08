/*
Copyright 2012, KISSY UI Library v1.30dev
MIT Licensed
build time: Jun 11 22:09
*/
KISSY.add("overlay/aria",function(c,d){function e(){}e.ATTRS={aria:{view:!0}};e.prototype={__bindUI:function(){var a=this,b=a.get("el");if(a.get("aria"))b.on("keydown",function(b){b.keyCode===d.KeyCodes.ESC&&(a.hide(),b.halt())})}};return e},{requires:["event"]});
KISSY.add("overlay/ariaRender",function(c,d){function e(){}function a(a){var c=a.keyCode,e=this.get("el");if(c==g){var c=b(a.target),d=this.__ariaArchor;if(c.equals(e)&&a.shiftKey)d[0].focus(),a.halt();else if(c.equals(d)&&!a.shiftKey)e[0].focus(),a.halt();else if(c.equals(e)||e.contains(c))return;a.halt()}}var b=d.all,g=d.KeyCodes.TAB;e.prototype={__renderUI:function(){var a=this.get("el"),e=this.get("header");this.get("aria")&&(a.attr("role","dialog"),a.attr("tabindex",0),e.attr("id")||e.attr("id",
c.guid("ks-dialog-header")),a.attr("aria-labelledby",e.attr("id")),this.__ariaArchor=b("<div tabindex='0'></div>").appendTo(a))},__bindUI:function(){var c=this;if(c.get("aria")){var b=c.get("el"),e;c.on("afterVisibleChange",function(d){d.newVal?(e=b[0].ownerDocument.activeElement,b[0].focus(),b.attr("aria-hidden","false"),b.on("keydown",a,c)):(b.attr("aria-hidden","true"),b.detach("keydown",a,c),e&&e.focus())})}}};return e},{requires:["node"]});
KISSY.add("overlay/base",function(c,d,e,a){function b(a){return c.require("component/uibase/"+a)}return d.Controller.extend([b("contentbox"),b("position"),b("loading"),b("align"),b("close"),b("resize"),b("mask"),a],{},{ATTRS:{focusable:{value:!1},closable:{value:!1},handleMouseEvents:{value:!1},visibleMode:{value:"visibility"},xrender:{value:e}}},{xclass:"overlay",priority:10})},{requires:["component","./overlayRender","./effect"]});
KISSY.add("overlay/dialog",function(c,d,e,a){function b(a){return c.require("component/uibase/"+a)}return d.extend([b("stdmod"),b("drag"),b("constrain"),a],{},{ATTRS:{closable:{value:!0},handlers:{valueFn:function(){var a=this;return[function(){return a.get("view").get("header")}]}},xrender:{value:e}}},{xclass:"dialog",priority:20})},{requires:["overlay/base","overlay/dialogRender","./aria"]});
KISSY.add("overlay/dialogRender",function(c,d,e){return d.extend([c.require("component/uibase/stdmodrender"),e])},{requires:["./overlayRender","./ariaRender"]});
KISSY.add("overlay/effect",function(c,d,e){function a(){}function b(a){var b=a.get("el"),d=c.all,b=b[0].cloneNode(!0);b.style.visibility="";b.style.overflow="hidden";b.className+=" "+a.get("prefixCls")+"overlay-ghost";var g;if(g=a.get("body"))a=e.get(".ks-stdmod-body",b),d(a).css({height:g.height(),width:g.width()}),a.innerHTML="";return d(b)}function g(a,e){a.__effectGhost&&a.__effectGhost.stop(1);var d=a.get("el"),g=c.all,f=a.get("effect"),h=g(f.target),g=f.duration,h=c.mix(h.offset(),{width:h.width(),
height:h.height()}),j=c.mix(d.offset(),{width:d.width(),height:d.height()}),k=b(a),i=f.easing;k.insertAfter(d);d.hide();e?(f=h,h=j):f=j;k.css(f);a.__effectGhost=k;k.animate(h,{duration:g,easing:i,complete:function(){a.__effectGhost=null;k.remove();d.show()}})}function f(a,b){var c=a.get("el"),e=a.get("effect"),d=e.effect||i,f=e.target;if(d!=i||f)if(f)g(a,b);else{var f=e.duration,e=e.easing,j=b?1:0;c.stop(1,1);c.css({visibility:"visible",display:m[j]});c[d+l[d][j]](f,function(){c.css({display:m[0],
visibility:b?"visible":"hidden"})},e)}}var i="none",l={fade:["Out","In"],slide:["Up","Down"]},m=["block",i];a.ATTRS={effect:{value:{effect:"",target:null,duration:0.5,easing:"easeOut"},setter:function(a){var b=a.effect;c.isString(b)&&!l[b]&&(a.effect="")}}};a.prototype={__bindUI:function(){var a=this;a.on("hide",function(){f(a,0)});a.on("show",function(){f(a,1)})}};return a},{requires:["anim","dom"]});
KISSY.add("overlay",function(c,d,e,a,b,g){d.Render=e;a.Render=b;d.Dialog=a;c.Dialog=a;d.Popup=g;return c.Overlay=d},{requires:["overlay/base","overlay/overlayRender","overlay/dialog","overlay/dialogRender","overlay/popup"]});KISSY.add("overlay/overlayRender",function(c,d,e){function a(a){return c.require("component/uibase/"+a)}return e.Render.extend([a("contentboxrender"),a("positionrender"),a("loadingrender"),6===d.ie?a("shimrender"):null,a("closerender"),a("maskrender")])},{requires:["ua","component"]});
KISSY.add("overlay/popup",function(c,d,e){return d.extend({initializer:function(){var a=this;a.get("trigger")&&("mouse"===a.get("triggerType")?(a._bindTriggerMouse(),a.on("afterRenderUI",function(){a._bindContainerMouse()})):a._bindTriggerClick())},_bindTriggerMouse:function(){var a=this,b=a.get("trigger"),d;a.__mouseEnterPopup=function(b){a._clearHiddenTimer();d=c.later(function(){a._showing(b);d=e},1E3*a.get("mouseDelay"))};c.each(b,function(b){c.one(b).on("mouseenter",a.__mouseEnterPopup)});a._mouseLeavePopup=
function(){d&&(d.cancel(),d=e);a._setHiddenTimer()};c.each(b,function(b){c.one(b).on("mouseleave",a._mouseLeavePopup)})},_bindContainerMouse:function(){this.get("el").on("mouseleave",this._setHiddenTimer,this).on("mouseenter",this._clearHiddenTimer,this)},_setHiddenTimer:function(){var a=this;a._hiddenTimer=c.later(function(){a._hiding()},1E3*a.get("mouseDelay"))},_clearHiddenTimer:function(){this._hiddenTimer&&(this._hiddenTimer.cancel(),this._hiddenTimer=e)},_bindTriggerClick:function(){var a=this;
a.__clickPopup=function(b){b.halt();if(a.get("toggle"))a[a.get("visible")?"_hiding":"_showing"](b);else a._showing(b)};c.each(a.get("trigger"),function(b){c.one(b).on("click",a.__clickPopup)})},_showing:function(a){this.set("currentTrigger",c.one(a.target));this.show()},_hiding:function(){this.set("currentTrigger",e);this.hide()},destructor:function(){var a=this,b,c=a.get("trigger");c&&(a.__clickPopup&&c.each(function(b){b.detach("click",a.__clickPopup)}),a.__mouseEnterPopup&&c.each(function(b){b.detach("mouseenter",
a.__mouseEnterPopup)}),a._mouseLeavePopup&&c.each(function(b){b.detach("mouseleave",a._mouseLeavePopup)}));(b=a.get("el"))&&b.detach("mouseleave",a._setHiddenTimer,a).detach("mouseenter",a._clearHiddenTimer,a)}},{ATTRS:{trigger:{setter:function(a){c.isString(a)&&(a=c.all(a));return a}},triggerType:{value:"click"},currentTrigger:{},mouseDelay:{value:0.1},toggle:{value:!1}}},{xclass:"popup",priority:20})},{requires:["./base"]});
