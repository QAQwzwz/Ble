package com.srtianxia.bleattendance.ui.teacher.dataanalysis.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by zia on 2018/4/23.
 */
public class CircleDataView extends android.support.v7.widget.AppCompatTextView {

    private boolean isLog = true;
    private Paint paint;
    private int COLOR_DARK, COLOR_WHITE, COLOR_BLACK, COLOR_TEXT, COLOR_BLUE, COLOR_GREEN, COLOR_CIRCLE;
    private String centerText = "0 / 0";
    private final int mainTextSize = 110;
    private final int subTextSize = 45;
    private Rect rect = new Rect();

    public CircleDataView(Context context) {
        super(context);
        init();
    }

    public CircleDataView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void update(final int count, final int toltleCount) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, count);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int c = (int) animation.getAnimatedValue();
                centerText = c + " / " + toltleCount;
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int startColor = Color.RED;
            int endColor = (COLOR_GREEN - startColor) * count / toltleCount / 2 + startColor;
            ValueAnimator colorAnimator = ValueAnimator.ofArgb(startColor, endColor);
            colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    COLOR_CIRCLE = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
            colorAnimator.setDuration(2500);
            colorAnimator.start();
        }


    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        COLOR_DARK = COLOR_CIRCLE = Color.parseColor("#e2e2e2");
        COLOR_WHITE = Color.parseColor("#ffffff");
        COLOR_BLACK = Color.parseColor("#000000");
        COLOR_TEXT = Color.parseColor("#aeaeae");
        COLOR_BLUE = Color.parseColor("#1599e6");
        COLOR_GREEN = Color.GREEN;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //外围圆圈
        paint.setColor(COLOR_CIRCLE);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        canvas.drawCircle(centerX, centerY, getWidth() / 2, paint);
        paint.setColor(COLOR_WHITE);
        canvas.drawCircle(centerX, centerY, getWidth() / 2 - 20, paint);

        //文字
        paint.setColor(COLOR_BLACK);
//        paint.setStrokeWidth(160);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(mainTextSize);

        paint.getTextBounds(centerText, 0, centerText.length(), rect);
        float startX = getWidth() / 2 - rect.width() / 2;
        float startY = getHeight() / 2 + rect.height() / 2;
        canvas.drawText(centerText, startX, startY, paint);

        String topText = "打卡人数/应到人数";
        paint.getTextBounds(topText, 0, topText.length(), rect);
        startX = getWidth() - rect.width() / 2;
        startY = getHeight() / 2 - rect.height() / 2 - 30;
        paint.setColor(COLOR_TEXT);
        paint.setTextSize(subTextSize);
        canvas.drawText(topText, startX, startY, paint);

        String bottomText = "打卡明细>";
        paint.getTextBounds(bottomText, 0, bottomText.length(), rect);
        startX = getWidth() / 2 - rect.width() / 2;
        startY = getHeight() / 2 - rect.height() / 2 + 150;
        paint.setColor(COLOR_BLUE);
        canvas.drawText(bottomText, startX, startY, paint);
    }

    private void log(String msg) {
        if (isLog)
            Log.e("CircleDataView", msg);
    }
}
