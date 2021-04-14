<%--
  Created by IntelliJ IDEA.
  User: LightseaBlue
  Date: 2020/1/20
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<body>

<div class="main-navigation" style="margin:0;padding:0; margin-left: 351px; list-style-type:none;font-family:Arial, LucidaGrande, Lucida Sans, Arial;color:#FFFFFF">
    <ul>
        <li><a href="http://www.lanrenzhijia.com">首页</a>
<%--            <ul>--%>
<%--                <li><a href="http://www.lanrenzhijia.com">Sub Menu Item 1</a></li>--%>
<%--                <li><a href="#">注意</a></li>--%>
<%--                <li><a href="#">Sub Menu Item 3</a></li>--%>
<%--                <li><a href="#">Sub Menu Item 4</a></li>--%>
<%--                <li><a href="#">Sub Menu Item 5</a></li>--%>
<%--            </ul>--%>
        </li>
        <li>
            <a href="http://www.lanrenzhijia.com">发现</a>
            <ul>
                <li><a href="#">有声书</a></li>
                <li><a href="#">音乐</a></li>
                <li><a href="#">教育</a></li>
                <li><a href="#">知识</a></li>
                <li><a href="#">生活</a></li>
            </ul>
        </li>
        <li>
            <a href="#">我的</a>
            <ul>
                <li><a href="#">Sub Menu Item 1</a></li>
                <li><a href="#">Sub Menu Item 2</a></li>
                <li><a href="http://www.lanrenzhijia.com">Sub Menu Item 3</a></li>
                <li><a href="#">Sub Menu Item 4</a></li>
                <li><a href="#">Sub Menu Item 5</a></li>
            </ul>
        </li>
        <li>
            <a href="http://www.lanrenzhijia.com">登陆</a>
            <ul>
<%--                <li><a href="#">Sub Menu Item 1</a></li>--%>
<%--                <li><a href="#">Sub Menu Item 2</a></li>--%>
            </ul>
        </li>
    </ul>
    <div class="search">
        <input type="text" name="search" class="search-input" />
        <input type="submit" name="search" value="" class="search-btn" />
    </div>
</div><!--main-navigation end-->




<script type="text/javascript">
    $(document).ready(function() {

        $(".main-navigation li").hover(function(){
            var itemwidth = $(this).width(); /* Getting the LI width */
            $(this).prepend("<div class='hover'></div>"); /* Inserting a blank div into within li above the <a> tag*/
            $(this).find("div").fadeIn('10000').css({ 'width' : itemwidth}); /* Using the itemwidth for the div to display properly*/
            $(this).find("ul").fadeIn('1000').slideDown('10000').css("display", "block");

        },function(){
            $(this).find("div").slideUp('1000').fadeOut('1000');/* sliding up and fading out the hover div */
            $(this).find("div").remove();/* removing the <div> code from html at every mouseout event*/
            $(this).find("ul").fadeOut('1000'); /* fading out the sub menu */

        });

        $(".search-input").focus(function(){
            $(this).animate({width:'180px'}, 500); /* on focus, increasing the input width of search to left side*/
        });

        $(".search-input").focusout(function(){
            $(this).animate({width:'117px'}, 500);  /* on focus, decreasing the input width of search to left side*/
        });

    });
</script>

</body>