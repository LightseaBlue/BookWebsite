$(function () {

    $('.mainbanner').each(function () {
        var $_root = $(this);
        var $window_b = $_root.find('.mainbanner_window');
        var $list = $_root.find('.mainbanner_list');
        var $items = $list.children();
        var $window_ul = $window_b.find('#slideContainer');
        var count = $items.length;
        var item_size = 1920;
        var dur_ms = 960;
        var autoplay_interval = 7680;
        var cur_idx = 0;
        var fix_idx = function (_idx) {
            if (_idx < 0)
                return
            (count - 1);
            if (_idx >= count)
                return 0;
            return _idx;
        }

        var goto = function (_idx) {
            var idx = fix_idx(_idx);
            $items.eq(idx).addClass('active').siblings().removeClass('active');
            if (cur_idx != idx) {
                var offset_x = -idx * item_size;
                $window_ul.stop().animate({'left': offset_x}, dur_ms);
                cur_idx = idx;
            }
        }

        $items.each(function (index, element) {
            var $cur_item = $(this);
            var $cur_a = $cur_item.find('a');
            $cur_a.data('index', index);
            $cur_a.click(function () {
                var index = $(this).data('index');
                goto(index);
                return false;
            });
        });

        var autoplay_flag = true;

        window.setInterval(function () {
            if (autoplay_flag) {
                goto(cur_idx + 1);
            }
        }, autoplay_interval);

        $_root.hover(function () {
            autoplay_flag = false;
        }, function () {
            autoplay_flag = true;
        });

        goto(0);
    });


    //下面开始为主页js
    window.onload = function () {

        var oWrap1 = document.getElementById('wrap1');
        var aLi1 = oWrap1.getElementsByTagName('li');
        var aH21 = oWrap1.getElementsByTagName('h2');
        var aBox1 = oWrap1.getElementsByTagName('div');

        for (var i = 0; i < aLi1.length; i++) {
            var iNow = 0;
            aLi1[i].index = i;
            aLi1[i].onmouseover = function () {
                aH21[iNow].style.display = 'block';
                aBox1[iNow].style.display = 'none';

                aH21[this.index].style.display = 'none';
                aBox1[this.index].style.display = 'block';

                iNow = this.index;
            };

        }

        var oWrap2 = document.getElementById('wrap2');
        var aLi2 = oWrap2.getElementsByTagName('li');
        var aH22 = oWrap2.getElementsByTagName('h2');
        var aBox2 = oWrap2.getElementsByTagName('div');

        for (var i = 0; i < aLi2.length; i++) {
            var iNow = 0;
            aLi2[i].index = i;
            aLi2[i].onmouseover = function () {
                aH22[iNow].style.display = 'block';
                aBox2[iNow].style.display = 'none';

                aH22[this.index].style.display = 'none';
                aBox2[this.index].style.display = 'block';

                iNow = this.index;
            };

        }

        var oWrap3 = document.getElementById('wrap3');
        var aLi3 = oWrap3.getElementsByTagName('li');
        var aH23 = oWrap3.getElementsByTagName('h2');
        var aBox3 = oWrap3.getElementsByTagName('div');

        for (var i = 0; i < aLi3.length; i++) {
            var iNow = 0;
            aLi3[i].index = i;
            aLi3[i].onmouseover = function () {
                aH23[iNow].style.display = 'block';
                aBox3[iNow].style.display = 'none';

                aH23[this.index].style.display = 'none';
                aBox3[this.index].style.display = 'block';

                iNow = this.index;
            };

        }

        var oWrap4 = document.getElementById('wrap4');
        var aLi4 = oWrap4.getElementsByTagName('li');
        var aH24 = oWrap4.getElementsByTagName('h2');
        var aBox4 = oWrap4.getElementsByTagName('div');

        for (var i = 0; i < aLi4.length; i++) {
            var iNow = 0;
            aLi4[i].index = i;
            aLi4[i].onmouseover = function () {
                aH24[iNow].style.display = 'block';
                aBox4[iNow].style.display = 'none';

                aH24[this.index].style.display = 'none';
                aBox4[this.index].style.display = 'block';

                iNow = this.index;
            };
        }

        var oWrap5 = document.getElementById('wrap5');
        var aLi5 = oWrap5.getElementsByTagName('li');
        var aH25 = oWrap5.getElementsByTagName('h2');
        var aBox5 = oWrap5.getElementsByTagName('div');

        for (var i = 0; i < aLi5.length; i++) {
            var iNow = 0;
            aLi5[i].index = i;
            aLi5[i].onmouseover = function () {
                aH25[iNow].style.display = 'block';
                aBox5[iNow].style.display = 'none';

                aH25[this.index].style.display = 'none';
                aBox5[this.index].style.display = 'block';

                iNow = this.index;
            };

        }

    };

    $(document).ready(
        //鼠标移入图片显示文字
        function () {
            $(".hdpic dd a").hover(
                function () {
                    $(this).find(".picshine").css("background", "url(images/play_bg.png)");
                    $(this).find(".picshine").css("background-size", "cover");
                    $(this).find(".picshine").stop();
                    $(this).find(".picshine").css(
                        "background-position", "-235px 0");
                    $(this).find(".picshine").animate({
                        backgroundPosition: '235px 0'
                    }, 500);
                    $(this).find(".pictitle").stop().animate({
                        left: '0px'
                    }, {
                        queue: false,
                        duration: 450
                    });
                    //	移除效果
                }, function () {
                    $(this).find(".picshine").css("background", "");
                    $(this).find(".picshine").css("background-size", "");
                    $(this).find(".pictitle").stop().animate({
                        left: '-1135px'
                    }, {
                        queue: false,
                        duration: 200
                    });
                });

        });
})