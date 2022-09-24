layui.use('carousel', function(){
    var carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#change'
        ,width: '40%' //设置容器宽度
        ,arrow: 'always' //始终显示箭头
        ,anim: 'fade' //切换动画方式
        ,interval:'5000'
    });
});

layui.use(
    ['form', 'layedit', 'laydate'],
    function () {
        var form = layui.form;
        var layer = layui.layer;
        console.log('{DEBUG} layui.user...');
    }
);