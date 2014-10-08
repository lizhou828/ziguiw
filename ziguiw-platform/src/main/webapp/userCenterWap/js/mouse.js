/**
* Highlighted when the mouse to move
* @param obj
*/
function over(obj) {
$(obj).addClass("highlight");
}
/**
* cancel Highlighted when the mouse to out
* @param obj
*/
function out(obj) {
$(obj).removeClass("highlight");
}
/**
*batch checkbox delete
* @param obj
*/
function checkChange(obj) {
// var $tr = $(obj).parent().parent();
// if ($tr.hasClass("selected")) {
// $tr.removeClass('selected').find(':checkbox').attr('checked', false);
// }
// else {
// $tr.addClass('selected').find(':checkbox').attr('checked', true);
// }
// var c = $(":checkbox[checked]");
// var len = c.length;
// if (len > 0) {
// document.getElementById("deleteAll").removeAttribute("disabled");
// }
// else {
// document.getElementById("deleteAll").disabled = true;
// }
}