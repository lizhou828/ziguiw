$(function () {
    $("#chooseAll").click(function () {//全选
        $("#dangan_tab :checkbox").attr("checked", true);
    });
    $("#chooseNone").click(function () {//全不选
        $("#dangan_tab :checkbox").attr("checked", false);
    });
    $("#switchChoose").click(function () {//反选
        $("#dangan_tab :checkbox").each(function () {
            $(this).attr("checked", !$(this).attr("checked"));
        });
    });
});