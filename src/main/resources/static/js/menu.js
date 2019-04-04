// 左边菜单
$(".leftsidebar_box dt").css({"background-color":"#f9f3d7"});
$(function(){
    $(".leftsidebar_box dd").hide();
    $(".leftsidebar_box dt").click(function(){
        $(".leftsidebar_box dt").css({"background-color":"#f9f3d7})
        $(this).css({"background-color": "#f9f3d7"});
        $(this).parent().find('dd').removeClass("menu_chioce");
        $(".menu_chioce").slideUp();
        $(this).parent().find('dd').slideToggle();
        $(this).parent().find('dd').addClass("menu_chioce");
    });
})