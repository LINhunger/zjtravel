$(function(){
    // 轮播图开始
    var left = $('.content_middle .btnLeft');//获取左点击
    var right = $('.content_middle .btnRight');//获取右点击
    var aSmall = $('.content_middle .table a');
    var aLi = $('.content_middle ul li');
    var iNow = 0;
    // 左点击
    left.click(function(){
        iNow--;
        // 判断回流
        if(iNow<0){
            iNow=5;
        }
        aLi.eq(iNow).siblings().stop().animate({
            opacity:0

        },800);
        aLi.eq(iNow).stop().animate({
            opacity:1

        },800);
        aSmall.eq(iNow).addClass('small_active').siblings().removeClass('small_active');
    });
    // 右点击切换
    right.click(function(){
        iNow++;
        if(iNow>5){
            iNow=0;
        }
        aLi.eq(iNow).siblings().stop().animate({
            opacity:0

        },800);
        aLi.eq(iNow).stop().animate({
            opacity:1

        },800);
        aSmall.eq(iNow).addClass('small_active').siblings().removeClass('small_active');

    });

    //手动切换
    aSmall.mouseover(function(){
        var n = $(this).index();
//        var iNow = $(this).index();
//        alert(iNow);
        iNow = n;
        aLi.eq(iNow).siblings().stop().animate({
            opacity:0

        },800);
        aLi.eq(iNow).stop().animate({
            opacity:1

        },800);
        aSmall.eq(iNow).addClass('small_active').siblings().removeClass('small_active');

    });
    // 封装函数体
    function move1(){
        aLi.eq(iNow).siblings().stop().animate({
            opacity:0

        },800);
        aLi.eq(iNow).stop().animate({
            opacity:1
        },800);

        aSmall.eq(iNow).addClass('small_active').siblings().removeClass('small_active');
    }

    // 定个定时器的初始值

    function run2(){
        iNow++;
        if(iNow>5){
            iNow=0;
        }
        move1();
    }

// 定时器
    timer = setInterval(run2,800);



//当鼠标划入，停止轮播图切换
    $(".content_middle").hover(function(){
        clearInterval(timer);
    },function(){
        timer = setInterval(run2,800);
    })
})