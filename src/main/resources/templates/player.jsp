<body>
<div style="position: fixed; bottom: 0px; width: 100%; height: 54px; background:#f1f3f4; z-index: 999999;">
			<span style="height: 54px; width: 10px; position: absolute; left: 534px; top:17px">
				<a id="precMusic" href="javascript:void (0)"><img src="images/prev.png"
                                                                  style="width: 20px; height: 20px;"/></a>
			</span>
    <span style="height: 54px; width: 10px; position: absolute; left: 565px; top: 17px;">
				<a id="nextMusic" href="javascript:void(0)"><img src="images/next.png"
                                                                 style="width: 20px; height: 20px;"/></a>
			</span>
    <audio id="music" controls="controls" preload="metadata" onended="isFinish()" onplay="isPlay()" autoplay="autoplay"
           style="outline: none; width: 700px; margin-left: 600px;">

    </audio>
    <span style="position: absolute; bottom: 18px;"><a href="javascript:showList()"><img
            src="images/list.png"></a></span>
    <span style="position: absolute; right: 525px;"><img id="playimg" src="img/Triple.png"
                                                         style="width: 54px; height: 54px;"></span>
</div>
<div id="list"
     style="overflow:hidden; height:200px; width: 850px; position: fixed; left: 530px; bottom: 54px;z-index: 99999; background: url(/images/play_bg.png); background-size: contain;"
     hidden="hidden">
    <div id="gdt" style="overflow: scroll; width: 870px; height:220px;">
        <ul id="listShow">
        </ul>
    </div>
</div>
<script>
    //播放
    function isPlay(bid) {
        $('#yonPlay' + bid).attr("src", "img/stop.png");
    }

    //暂停
    function isSleep(bid) {
        $('#yonPlay' + bid).attr("src", "img/play.png");
    }

    //结束
    function isFinish() {
        $('#nextMusic').click();
    }

    function showList() {
        var ishidden = $('#list').attr('hidden');
        if (ishidden == "hidden") {
            $('#list').prop('hidden', false);
        } else {
            $('#list').attr("hidden", 'hidden');
        }
    }
</script>

</body>