# slidebarlistview
演示：<br/>
<image src="img/img.gif" /><br/>
暂时没有仓库，下载slidebarlistview作为Module使用<br/>
你需要：<br/>
allprojects {<br/>
　repositories {<br/>
　　...<br/>
　　maven { url "https://jitpack.io" }<br/>
　}<br/>
}<br/>
<br/>
使用了：pinyin4j:2.5.0<br/>
<br/>
<br/>
使用方法：<br/>
<com.erning.slidebarlistview.SlideBarListView<br/>
　android:id="@+id/list"<br/>
　android:layout_width="match_parent"<br/>
　android:layout_height="match_parent" /><br/>
 <br/>
java:<br/>
setFontSize：设置字体大小(dp)<br/>
setMargin：设置外边距(dp)<br/>
setPadding：设置内边距(dp)<br/>
setRound：设置圆角弧度(dp)<br/>
setColor_back_unselect：设置没有选中时的背景颜色(dp)<br/>
setColor_back_select：设置选中时的背景颜色(dp)<br/>
setColor_font_unselect：设置没有选中时的文字颜色(dp)<br/>
setColor_font_select：设置选中时的文字颜色(dp)<br/>
<br/>
一个Adapter继承自SimpleSlideBarListAdapter，也可以直接使用SimpleSlideBarListAdapter(context，List\<String>)<br/>
如果继承SimpleSlideBarListAdapter只需重写getView()即可<br/>
