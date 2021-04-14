<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<!-- 头部滚动 -->
<div class="mainbanner">
    <div class="mainbanner_window">
        <ul id="slideContainer">
            <li><a href="#"><img src="img/bj1.jpg" width="1920"
                                 height="500"/></a></li>
            <li><a href="#"><img src="images/2.jpg" width="1920"
                                 height="500"/></a></li>
            <li><a href="#"><img src="images/3.jpg" width="1920"
                                 height="500"/></a></li>
            <li><a href="#"><img src="images/4.jpg" width="1920"
                                 height="500"/></a></li>
        </ul>
    </div>
    <ul class="mainbanner_list">
        <li><a href="javascript:void(0);">1</a></li>
        <li><a href="javascript:void(0);">2</a></li>
        <li><a href="javascript:void(0);">3</a></li>
        <li><a href="javascript:void(0);">4</a></li>
    </ul>
</div>

<!-- 头部滚动结束 -->


<!--中部开始-->

<div class="con_sy">

    <!--中部1开始-->

    <div class="con_sy1">

        <!--选项卡切换开始-->

        <div class="xxk_sy">

            <!--代码开始-->

            <script>
                <!--
                /*第一种形式 第二种形式 更换显示样式*/
                function setTab(name, cursel, n) {
                    for (i = 1; i <= n; i++) {
                        var menu = document.getElementById(name + i);
                        var con = document.getElementById("con_" + name
                            + "_" + i);
                        menu.className = i == cursel ? "hover" : "";
                        con.style.display = i == cursel ? "block" : "none";
                    }
                }

                 -->
            </script>


            <div id="Tab1">
                <div class="Menubox">
                    <ul>
                        <c:forEach items="${bigType }" var="item" varStatus="i">

                            <c:if test="${i.index==0 }">
                                <li id="one1" onmouseover="setTab('one',1,4)" class="hover">
                                        ${item.btname }</li>
                            </c:if>

                            <c:if test="${i.index!=0 }">
                                <li id="one${i.index+1 }"
                                    onmouseover="setTab('one',${i.index+1},4)">${item.btname }</li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <a style="float: right; margin-right: 10px;cursor: pointer;">查看全部>></a>
                </div>


                <div class="Contentbox">


                    <c:forEach items="${allType }" var="item" varStatus="i">
                        <c:if test="${i.index==0}">
                            <div id="con_one_1" class="hover" style="width: 972px;height: 276px">
                        </c:if>
                        <c:if test="${i.index!=0}">
                            <div id="con_one_${i.index+1}" style="display: none; width: 972px;height: 276px" >
                        </c:if>

                        <c:forEach items="${item}" var="item1" varStatus="j">

                            <c:if test="${j.index%5==0}">
                                <ul style=" float: left; margin-left: 10px; margin-right: 30px">
                            </c:if>
                            <li style="font-size: 35px;margin-bottom: 10px;margin-left: 10px;margin-top: 5px;cursor: pointer;">
                                <a>${item1.tname}</a>
                            </li>
                            <c:if test="${j.index%5==4||j.last}">
                                </ul>
                            </c:if>

                        </c:forEach>

                        <img src="${bigType[i.index].btimg}"
                             style="height: 267px; width: 446px;right: 587px; position: absolute; clear: both">

                        <c:if test="${i.index==0}">
                            </div>
                        </c:if>
                        <c:if test="${i.index!=0}">
                            </div>
                        </c:if>

                    </c:forEach>


                </div>
            </div>

            <!--代码结束-->

        </div>
        <!--选项卡切花结束-->


        <!--猜你喜欢开始-->

        <div class="jfdh_sy">
            <div class="jfdh_bt">
                猜你喜欢
                <span id="changeYouLike" onclick="changeYouLike()"
                      style="float: right; margin-right: 20px;cursor: pointer;">
                    <img src="img/Refresh.png" height="" width="">
                    换一换
                </span>
            </div>

            <div id="youLike">
                <c:forEach items="${youLike }" var="item">
                    <div class="jfdh1">
                        <div class="jflp2">
                            <a href="javascript:void(0)"><img src="${item.bphoto }" onclick="playMusic(${item.bid})"
                                                              width="66" height="66"/></a>
                        </div>
                        <div class="jflp3">
                                ${item.bname }<br/> 播讲：${item.uname }<br/> <a href="#">查看详情</a>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>

        <!--猜你喜欢结束-->
    </div>
    <!--中部1结束-->


    <!--中部开始-->
    <c:forEach items="${allBigtype }" var="item" varStatus="i">
        <div class="con_dm">
            <div class="con_dmbt">
                <span class="dt2_2">${item.btname }</span> <span
                    class="dt2_3"> <a href="tplist.html">查看更多>></a>
					</span>
            </div>


            <div
                    style="font-size: 22px; margin-top: 8px; width: 235px; height: 23px; text-align: center;">${item.btname }排行榜
            </div>
            <div class="con2_dtk">

                <!--左侧开始-->

                <div class="con2_zc">
                    <div id="wrap${i.index+1 }">
                        <ul>

                            <c:forEach items="${allTopbook[i.index] }" var="item1"
                                       varStatus="j">

                                <li>
                                    <h2>
                                        <span class="szmc">${item1.bname }</span>
                                    </h2>
                                    <a href="javascript:void (0)">
                                        <div class="movielist">
                                            <img src="${item1.bphoto }" onclick="playMusic(${item1.bid})"/>
                                            <dl>
                                                <dt>${item1.bname }</dt>
                                                <dd>
                                                    <span style="font-size: 5px">演播：</span>${item1.uname }
                                                </dd>
                                            </dl>
                                        </div>
                                    </a>
                                </li>
                            </c:forEach>

                        </ul>

                    </div>
                </div>
                <!--左侧1结束-->

                <!--右侧开始-->
                <div class="con2_yc">
                    <div class="hdpic">
                        <dl>

                            <c:forEach items="${allBook[i.index] }" var="item2">
                                <dd>
                                    <a href="javascript:void(0) ">
                                        <img src="${item2.bphoto}" width="184" height="278"/>
                                        <div class="pictitle" style="height:278px; width: 184px">
                                            <div class="zz">
                                                <span style="color: white;">${item2.bname }</span><br/>
                                                <span style="color: white; z-index:999; font-size: 18px;">演播：${item2.uname }</span>
                                                <img id="yonPlay${item2.bid}" onclick="playMusic(${item2.bid})"
                                                     style="height: 50px;width: 50px; background: white;border-radius: 45px;  position: absolute; bottom: 30px; "
                                                     src="img/play.png">
                                            </div>
                                        </div>
                                        <div class="picshine">
                                        </div>
                                    </a>
                                </dd>

                            </c:forEach>

                        </dl>
                    </div>
                </div>
                <!--右侧结束-->
            </div>
        </div>
        <!--中部结束-->
    </c:forEach>


</div>

<!--中部结束-->
<script>
    $("#gdt").scroll(function () {
        var $this = $(this),
            viewH = $(this).height(),//可见高度
            contentH = $(this).get(0).scrollHeight,//内容高度
            scrollTop = $(this).scrollTop();//滚动高度
        if (contentH - viewH - scrollTop <= 0) { //到达底部100px时,加载新内容
            // if(scrollTop/(contentH -viewH)>=0.95){ //到达底部100px时,加载新内容
            // if(scrollTop == (contentH -viewH)){
            var bid = $('#music').attr("bid");
            var thisPage = $('.listMusic').attr("page");
            $.post("playMusic.action", {
                op: "findMusicList",
                bid: bid,
                thisPage: thisPage
            }, function (data) {
                if (data == 2) {
                    return;
                } else {
                    appendList(data);
                }
            })
        }
    });


    function changeYouLike() {
        $.post("main.action", {
            op: "changeYouLike"
        }, function (data) {
            $('#youLike').html("");
            for (var i = 0; i < data.length; i++) {
                $('#youLike').append(
                    "<div class=\"jfdh1\">\n" +
                    "<div class=\"jflp2\">\n" +
                    "    <a href=\"javascript:void(0)\"><img src=\"" + data[i].bphoto + "\"  onclick=\"playMusic(" + data[i].bid + ")\" width=\"66\" height=\"66\"/></a>\n" +
                    "</div>\n" +
                    "<div class=\"jflp3\">\n" +
                    data[i].bname + "<br/> 播讲：" + data[i].uname + "<br/> <a href=\"#\">查看详情</a>\n" +
                    "</div>\n" +
                    "</div>"
                )
            }
        });
    }

    function switchMusic(bid, biid) {
        $.post("playMusic.action", {
            op: "switchMusic",
            biid: biid,
            bid: bid
        }, function (data) {
            setSrc(data);
        });
    }

    function setSrc(data) {
        $('.listMusic').css("background", "");
        $("#list" + data.biid).css("background", "url(images/nav-bg.jpg)");
        $('#music').attr("src", data.address);
        $('#nextMusic').attr("onclick", "switchMusic(" + data.bid + "," + data.nextbiid + ")");
        $('#precMusic').attr("onclick", "switchMusic(" + data.bid + "," + data.precbiid + ")");
    }

    function appendList(data) {
        var num = data.thisPage + 1;
        for (var i = 0; i < data.list.length; i++) {
            if (i % 2 == 0) {
                $('#listShow').append('<hr><li class="listMusic" id="list' + data.list[i].biid + '" style="font-size: 25px; color: cornflowerblue;" >' +
                    '<a href="javascript:switchMusic(' + data.list[i].bid + ',' + data.list[i].biid + ')" style="color: cornflowerblue;">' + (i + num) + '.&nbsp' + data.list[i].biname + '</a>' +
                    // '<span style="float: left">' +
                    // data.list[i].date +
                    // '</span>' +
                    '</li>');
            } else {
                $('#listShow').append('<hr><li class="listMusic" id="list' + data.list[i].biid + '" style="font-size: 25px; color: #ff69b4;">' +
                    '<a href="javascript:switchMusic(' + data.list[i].bid + ',' + data.list[i].biid + ')" style="color: hotpink;">' + (i + num) + '.&nbsp' + data.list[i].biname + '</a>' +
                    '</li>');
            }
        }
        $('.listMusic').attr("page", data.thisPage);
    }

    function playMusic(bid, uid) {
        var ispause = $('#music').attr("bid");
        $.post("playMusic.action", {
            op: "Play",
            bid: bid,
            uid: uid
        }, function (data) {
            if (ispause == data.bid) {
                if ($('#music')[0].paused) {
                    $('#music')[0].play();
                    $('#yonPlay' + bid).attr("src", "img/stop.png");
                } else {
                    $('#music')[0].pause();
                    // $('#yonPlay'+bid).attr("src","img/play.png");
                }
            } else {
                $('#yonPlay' + ispause).attr("src", "img/play.png");
                $('#music').attr("onpause", "isSleep(" + data.bid + ")");
                $('#music').attr("onplay", "isPlay(" + data.bid + ")");
                $('#playimg').attr("src", data.photo);
                $('#music').attr("bid", data.bid);
                $('#yonPlay' + bid).attr("src", "img/stop.png");

                $('#listShow').html('');
                appendList(data);

                setSrc(data);
            }
        });
    }
</script>

<%--找主页js到index.js--%>

</body>
<%@ include file="bottom.jsp" %>
