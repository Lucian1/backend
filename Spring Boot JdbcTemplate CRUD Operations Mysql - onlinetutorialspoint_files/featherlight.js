/**
 * arvfl - ultra slim jQuery lightbox
 * Version 1.7.8 - http://noelboss.github.io/arvfl/
 *
 * Copyright 2017, Noël Raoul Bossart (http://www.noelboss.com)
 * MIT Licensed.
 * Name changed to avoid conflict with other arvfl based plugins
 **/
!function(e){"use strict"
function t(e,n){if(!(this instanceof t)){var r=new t(e,n)
return r.open(),r}this.id=t.id++,this.setup(e,n),this.chainCallbacks(t._callbackChain)}function n(e,t){var n={}
for(var r in e)r in t&&(n[r]=e[r],delete e[r])
return n}function r(e,t){var n={},r=new RegExp("^"+t+"([A-Z])(.*)")
for(var i in e){var o=i.match(r)
if(o){n[(o[1]+o[2].replace(/([A-Z])/g,"-$1")).toLowerCase()]=e[i]}}return n}if(void 0===e)return void("console"in window&&window.console.info("Too much lightness, arvfl needs jQuery."))
if(e.fn.jquery.match(/-ajax/))return void("console"in window&&window.console.info("arvfl needs regular jQuery, not the slim version."))
var i=[],o=function(t){return i=e.grep(i,function(e){return e!==t&&e.$instance.closest("body").length>0})},a={allowfullscreen:1,frameborder:1,height:1,longdesc:1,marginheight:1,marginwidth:1,name:1,referrerpolicy:1,scrolling:1,sandbox:1,src:1,srcdoc:1,width:1},s={keyup:"onKeyUp",resize:"onResize"},c=function(n){e.each(t.opened().reverse(),function(){if(!n.isDefaultPrevented()&&!1===this[s[n.type]](n))return n.preventDefault(),n.stopPropagation(),!1})},l=function(n){if(n!==t._globalHandlerInstalled){t._globalHandlerInstalled=n
var r=e.map(s,function(e,n){return n+"."+t.prototype.namespace}).join(" ")
e(window)[n?"on":"off"](r,c)}}
t.prototype={constructor:t,namespace:"arvfl",targetAttr:"data-arvfl",variant:null,resetCss:!1,background:null,openTrigger:"click",closeTrigger:"click",filter:null,root:"body",openSpeed:250,closeSpeed:250,closeOnClick:"background",closeOnEsc:!0,closeIcon:"&#10005;",loading:"",persist:!1,otherClose:null,beforeOpen:e.noop,beforeContent:e.noop,beforeClose:e.noop,afterOpen:e.noop,afterContent:e.noop,afterClose:e.noop,onKeyUp:e.noop,onResize:e.noop,type:null,contentFilters:["jquery","image","html","ajax","iframe","text"],setup:function(t,n){"object"!=typeof t||t instanceof e!=!1||n||(n=t,t=void 0)
var r=e.extend(this,n,{target:t}),i=r.resetCss?r.namespace+"-reset":r.namespace,o=e(r.background||['<div class="'+i+'-bg">&nbsp;</div>','<div class="'+i+"-loading "+i+'">','<div class="'+i+'-content">','<a class="'+i+"-close-icon "+r.namespace+'-close" aria-label="Close">',r.closeIcon,"</a>",'<div class="'+r.namespace+'-inner">'+r.loading+"</div>","</div>","</div>"].join("")),a="."+r.namespace+"-close"+(r.otherClose?","+r.otherClose:"")
return r.$instance=o.clone().addClass(r.variant),r.$instance.on(r.closeTrigger+"."+r.namespace,function(t){if(!t.isDefaultPrevented()){var n=e(t.target);("background"===r.closeOnClick&&n.is("."+r.namespace)||"anywhere"===r.closeOnClick||n.closest(a).length)&&(r.close(t),t.preventDefault())}}),this},getContent:function(){if(!1!==this.persist&&this.$content)return this.$content
var t=this,n=this.constructor.contentFilters,r=function(e){return t.$currentTarget&&t.$currentTarget.attr(e)},i=r(t.targetAttr),o=t.target||i||"",a=n[t.type]
if(!a&&o in n&&(a=n[o],o=t.target&&i),o=o||r("href")||"",!a)for(var s in n)t[s]&&(a=n[s],o=t[s])
if(!a){var c=o
if(o=null,e.each(t.contentFilters,function(){return a=n[this],a.test&&(o=a.test(c)),!o&&a.regex&&c.match&&c.match(a.regex)&&(o=c),!o}),!o)return"console"in window&&window.console.error("arvfl: no content filter found "+(c?' for "'+c+'"':" (no target specified)")),!1}return a.process.call(t,o)},setContent:function(t){return this.$instance.removeClass(this.namespace+"-loading"),this.$instance.toggleClass(this.namespace+"-iframe",t.is("iframe")),this.$instance.find("."+this.namespace+"-inner").not(t).slice(1).remove().end().replaceWith(e.contains(this.$instance[0],t[0])?"":t),this.$content=t.addClass(this.namespace+"-inner"),this},open:function(t){var n=this
if(n.$instance.hide().appendTo(n.root),!(t&&t.isDefaultPrevented()||!1===n.beforeOpen(t))){t&&t.preventDefault()
var r=n.getContent()
if(r)return i.push(n),l(!0),n.$instance.fadeIn(n.openSpeed),n.beforeContent(t),e.when(r).always(function(e){n.setContent(e),n.afterContent(t)}).then(n.$instance.promise()).done(function(){n.afterOpen(t)})}return n.$instance.detach(),e.Deferred().reject().promise()},close:function(t){var n=this,r=e.Deferred()
return!1===n.beforeClose(t)?r.reject():(0===o(n).length&&l(!1),n.$instance.fadeOut(n.closeSpeed,function(){n.$instance.detach(),n.afterClose(t),r.resolve()})),r.promise()},resize:function(e,t){if(e&&t){this.$content.css("width","").css("height","")
var n=Math.max(e/(this.$content.parent().width()-1),t/(this.$content.parent().height()-1))
n>1&&(n=t/Math.floor(t/n),this.$content.css("width",e/n+"px").css("height",t/n+"px"))}},chainCallbacks:function(t){for(var n in t)this[n]=e.proxy(t[n],this,e.proxy(this[n],this))}},e.extend(t,{id:0,autoBind:"[data-arvfl]",defaults:t.prototype,contentFilters:{jquery:{regex:/^[#.]\w/,test:function(t){return t instanceof e&&t},process:function(t){return!1!==this.persist?e(t):e(t).clone(!0)}},image:{regex:/\.(png|jpg|jpeg|gif|tiff?|bmp|svg)(\?\S*)?$/i,process:function(t){var n=this,r=e.Deferred(),i=new Image,o=e('<img src="'+t+'" alt="" class="'+n.namespace+'-image" />')
return i.onload=function(){o.naturalWidth=i.width,o.naturalHeight=i.height,r.resolve(o)},i.onerror=function(){r.reject(o)},i.src=t,r.promise()}},html:{regex:/^\s*<[\w!][^<]*>/,process:function(t){return e(t)}},ajax:{regex:/./,process:function(t){var n=e.Deferred(),r=e("<div></div>").load(t,function(e,t){"error"!==t&&n.resolve(r.contents()),n.fail()})
return n.promise()}},iframe:{process:function(t){var i=new e.Deferred,o=e("<iframe/>"),s=r(this,"iframe"),c=n(s,a)
return o.hide().attr("src",t).attr(c).css(s).on("load",function(){i.resolve(o.show())}).appendTo(this.$instance.find("."+this.namespace+"-content")),i.promise()}},text:{process:function(t){return e("<div>",{text:t})}}},functionAttributes:["beforeOpen","afterOpen","beforeContent","afterContent","beforeClose","afterClose"],readElementConfig:function(t,n){var r=this,i=new RegExp("^data-"+n+"-(.*)"),o={}
return t&&t.attributes&&e.each(t.attributes,function(){var t=this.name.match(i)
if(t){var n=this.value,a=e.camelCase(t[1])
if(e.inArray(a,r.functionAttributes)>=0)n=new Function(n)
else try{n=JSON.parse(n)}catch(e){}o[a]=n}}),o},extend:function(t,n){var r=function(){this.constructor=t}
return r.prototype=this.prototype,t.prototype=new r,t.__super__=this.prototype,e.extend(t,this,n),t.defaults=t.prototype,t},attach:function(t,n,r){var i=this
"object"!=typeof n||n instanceof e!=!1||r||(r=n,n=void 0),r=e.extend({},r)
var o,a=r.namespace||i.defaults.namespace,s=e.extend({},i.defaults,i.readElementConfig(t[0],a),r),c=function(a){var c=e(a.currentTarget),l=e.extend({$source:t,$currentTarget:c},i.readElementConfig(t[0],s.namespace),i.readElementConfig(a.currentTarget,s.namespace),r),u=o||c.data("arvfl-persisted")||new i(n,l)
"shared"===u.persist?o=u:!1!==u.persist&&c.data("arvfl-persisted",u),l.$currentTarget.blur&&l.$currentTarget.blur(),u.open(a)}
return t.on(s.openTrigger+"."+s.namespace,s.filter,c),c},current:function(){var e=this.opened()
return e[e.length-1]||null},opened:function(){var t=this
return o(),e.grep(i,function(e){return e instanceof t})},close:function(e){var t=this.current()
if(t)return t.close(e)},_onReady:function(){var t=this
t.autoBind&&(e(t.autoBind).each(function(){t.attach(e(this))}),e(document).on("click",t.autoBind,function(n){if(!n.isDefaultPrevented()){t.attach(e(n.currentTarget))(n)}}))},_callbackChain:{onKeyUp:function(t,n){return 27===n.keyCode?(this.closeOnEsc&&e.arvfl.close(n),!1):t(n)},beforeOpen:function(t,n){return e(document.documentElement).addClass("with-arvfl"),this._previouslyActive=document.activeElement,this._$previouslyTabbable=e("a, input, select, textarea, iframe, button, iframe, [contentEditable=true]").not("[tabindex]").not(this.$instance.find("button")),this._$previouslyWithTabIndex=e("[tabindex]").not('[tabindex="-1"]'),this._previousWithTabIndices=this._$previouslyWithTabIndex.map(function(t,n){return e(n).attr("tabindex")}),this._$previouslyWithTabIndex.add(this._$previouslyTabbable).attr("tabindex",-1),document.activeElement.blur&&document.activeElement.blur(),t(n)},afterClose:function(n,r){var i=n(r),o=this
return this._$previouslyTabbable.removeAttr("tabindex"),this._$previouslyWithTabIndex.each(function(t,n){e(n).attr("tabindex",o._previousWithTabIndices[t])}),this._previouslyActive.focus(),0===t.opened().length&&e(document.documentElement).removeClass("with-arvfl"),i},onResize:function(e,t){return this.resize(this.$content.naturalWidth,this.$content.naturalHeight),e(t)},afterContent:function(e,t){var n=e(t)
return this.$instance.find("[autofocus]:not([disabled])").focus(),this.onResize(t),n}}}),e.arvfl=t,e.fn.arvfl=function(e,n){return t.attach(this,e,n),this},e(document).ready(function(){t._onReady()})}(jQuery)
