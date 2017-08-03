
$(function(){

    $(".toolbar-item").hover(function() {
    $(this).css({
        position: "relative"
    }).find(".toolbar-item-sub").css({
        width: "150px",
        position: "absolute",
        top: "0px",
        right: "52px",
        padding: "10px"
    }).show()
}
, function() {
    $(this).find(".toolbar-item-sub").hide()
}
);
    //首先将#back-to-top隐藏
    $("#backTop").hide();
    
    //当滚动条的位置处于距顶部200像素以下时，跳转链接出现，否则消失
    $(function(){
    
        $(window).scroll(function(){
            if ($(window).scrollTop()>200){
                $("#backTop").fadeIn();
            }else{
                $("#backTop").fadeOut();
            }
        });
        
        //当点击跳转链接后，回到页面顶部位置
        $("#backTop").click(function(){
            $('body,html').animate({scrollTop:0},500);
            return false;
        });
        
    });
    
});