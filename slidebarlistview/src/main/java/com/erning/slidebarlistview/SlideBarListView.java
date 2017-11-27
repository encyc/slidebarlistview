package com.erning.slidebarlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 会自动显示侧面拼音的listView
 * Created by 二宁 on 17-11-26.
 */
public class SlideBarListView extends ListView {
    private static String TAG = "SlideBarListView";

    //滑动条背景画笔
    private Paint slideBackPaint = new Paint();
    //滑动条文字画笔
    private Paint slideFontPaint = new Paint();

    //窗口宽
    private int windowWidth = 0;
    //窗口高
    private int windowHeight = 0;
    //一个字母占的高度
    private int oneHeight = 0;
    //字体大小
    private int fontSize = 14;
    //外边距
    private int margin = 10;
    //内距
    private int padding = 5;
    //圆角度数
    private int round = 11;

    //背景
    private RectF rect;
    //文字向上偏移量
    private float textBaseY = 0;

    //背景未选中的颜色
    private int color_back_unselect = Color.argb(0,255,255,255);
    //背景选中的颜色
    private int color_back_select = Color.argb(255,51,51,51);
    //文字未选中颜色
    private int color_font_unselect = Color.argb(255,102,102,102);
    //文字选中的颜色
    private int color_font_select = Color.argb(255,255,255,255);

    //数字
    private static char[] number = new char[]{'#','0','1','2','3','4','5','6','7','8','9'};
    //字母
    private static char[] latter = new char[]{'#','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    private boolean isSlideBarTouch = false;

    public SlideBarListView(Context context) {
        super(context);
        init();
    }
    public SlideBarListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public SlideBarListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SlideBarListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        oneHeight = dip2px(getContext(),oneHeight);
        fontSize = dip2px(getContext(),fontSize);
        margin = dip2px(getContext(),margin);
        padding = dip2px(getContext(),padding);
        round = dip2px(getContext(),round);

        slideBackPaint.setColor(color_back_unselect);
        slideFontPaint.setColor(color_font_unselect);
        slideFontPaint.setTextSize(fontSize);
        slideFontPaint.setTypeface(Typeface.create("System", Typeface.BOLD));
        slideFontPaint.setTextAlign(Paint.Align.CENTER);

        rect = new RectF(windowWidth-margin-padding-fontSize-padding,margin,windowWidth-margin,windowHeight-margin+10);

        Paint.FontMetrics fm = slideFontPaint.getFontMetrics();
        float fontHeight = fm.descent - fm.ascent;
        textBaseY = (oneHeight-fontHeight)/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(rect,round,round,slideBackPaint);
        for (int i=0;i<latter.length;i++){
            canvas.drawText(String.valueOf(latter[i]),windowWidth-margin-padding-(fontSize/2),oneHeight*(i+1)+margin-textBaseY,slideFontPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获得空间宽高
        windowWidth = w;
        windowHeight = h;

        //计算一个字所占的高度
        oneHeight = (windowHeight-2*margin)/latter.length;
        Paint.FontMetrics fm = slideFontPaint.getFontMetrics();
        float fontHeight = fm.descent - fm.ascent;
        //计算文字向上偏移量
        textBaseY = (oneHeight-fontHeight)/2;

        //计算背景位置
        rect = new RectF(windowWidth-margin-padding-fontSize-padding,margin,windowWidth-margin,windowHeight-margin);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            if (ev.getX()>=(windowWidth-margin-padding-fontSize-padding) && ev.getX()<=(windowWidth-margin) && ev.getY()>=margin && ev.getY()<=windowHeight-margin){
                isSlideBarTouch = true;

                slideBackPaint.setColor(color_back_select);
                slideFontPaint.setColor(color_font_select);
                invalidate();

                float absHeight = ev.getY() - margin;
                int position = (int) (absHeight/oneHeight);
                if (onSlideBarChangeLintener != null){
                    onSlideBarChangeLintener.OnSlideBarChange(position,latter[position]);
                }
            }else{
                isSlideBarTouch = false;
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_UP){
            if (isSlideBarTouch){
                isSlideBarTouch = false;
                slideBackPaint.setColor(color_back_unselect);
                slideFontPaint.setColor(color_font_unselect);
                invalidate();
                return true;
            }

            isSlideBarTouch = false;

            slideBackPaint.setColor(color_back_unselect);
            slideFontPaint.setColor(color_font_unselect);
            invalidate();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            if (isSlideBarTouch && ev.getY()>=margin && ev.getY()<=windowHeight-margin){
                float absHeight = ev.getY() - margin;
                int position = (int) (absHeight/oneHeight);
                if (position>=latter.length){
                    position = latter.length-1;
                }
                if (onSlideBarChangeLintener != null){
                    onSlideBarChangeLintener.OnSlideBarChange(position,latter[position]);
                }
            }
        }

        return isSlideBarTouch ? true : super.onTouchEvent(ev);
    }

    //滑动条被触发监听器
    public interface OnSlideBarChangeLintener{
        void OnSlideBarChange(int index, char latter);
    }
    private OnSlideBarChangeLintener onSlideBarChangeLintener;
    public void setOnSlideBarChangeLintener(OnSlideBarChangeLintener onSlideBarChangeLintener){
        this.onSlideBarChangeLintener = onSlideBarChangeLintener;
    }

    //setter
    public void setFontSize(int fontSize) {
        this.fontSize = dip2px(getContext(),fontSize);
        invalidate();
    }
    public void setMargin(int margin) {
        this.margin = dip2px(getContext(),margin);
        invalidate();
    }
    public void setPadding(int padding) {
        this.padding = dip2px(getContext(),padding);
        invalidate();
    }
    public void setRound(int round) {
        this.round = dip2px(getContext(),round);
        invalidate();
    }
    public void setColor_back_unselect(int color_back_unselect) {
        this.color_back_unselect = color_back_unselect;
        slideBackPaint.setColor(color_back_unselect);
        invalidate();
    }
    public void setColor_back_select(int color_back_select) {
        this.color_back_select = color_back_select;
        invalidate();
    }
    public void setColor_font_unselect(int color_font_unselect) {
        this.color_font_unselect = color_font_unselect;
        slideFontPaint.setColor(color_font_unselect);
        invalidate();
    }
    public void setColor_font_select(int color_font_select) {
        this.color_font_select = color_font_select;
        invalidate();
    }
}
