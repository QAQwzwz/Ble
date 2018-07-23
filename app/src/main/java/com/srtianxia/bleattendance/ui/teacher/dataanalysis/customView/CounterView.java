package com.srtianxia.bleattendance.ui.teacher.dataanalysis.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zia on 2018/5/9.
 */
public class CounterView extends android.support.v7.widget.AppCompatTextView {

    private Paint paint;
    private String x = "0", y = "0", z = "0";
    private static final String l1 = "缺勤";
    private static final String l2 = "迟到";
    private static final String l3 = "外勤";
    private final int numberSize = 100;
    private final int textSize = 60;
    private final int DARK = Color.parseColor("#333333");
    private Rect rect = new Rect();

    public CounterView(Context context) {
        super(context);
        init();
    }

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void load(int x, int y, int z) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 10);
        valueAnimator.addUpdateListener(animation -> {
            int t = (int) animation.getAnimatedValue();
            this.x = String.valueOf(x * t / 10);
            this.y = String.valueOf(y * t / 10);
            this.z = String.valueOf(z * t / 10);
            invalidate();
        });
        valueAnimator.setDuration(1500);
        valueAnimator.start();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(DARK);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth() / 3;
        int h = getHeight() / 2;
        int wc = w / 2;
        paint.setTextSize(textSize);
        paint.getTextBounds(l1, 0, l1.length(), rect);
        canvas.drawText(l1, wc - rect.width() / 2, h - h / 4, paint);
        paint.getTextBounds(l2, 0, l2.length(), rect);
        canvas.drawText(l2, w + wc - rect.width() / 2, h - h / 4, paint);
        paint.getTextBounds(l3, 0, l3.length(), rect);
        canvas.drawText(l3, 2 * w + wc - rect.width() / 2, h - h / 4, paint);
        paint.setTextSize(numberSize);
        paint.getTextBounds(x, 0, x.length(), rect);
        canvas.drawText(x, wc - rect.width() / 2, h + h / 2, paint);
        paint.getTextBounds(y, 0, y.length(), rect);
        canvas.drawText(y, w + wc - rect.width() / 2, h + h / 2, paint);
        paint.getTextBounds(z, 0, z.length(), rect);
        canvas.drawText(z, 2 * w + wc - rect.width() / 2, h + h / 2, paint);
    }
}
