package id.hike.apps.android_mpos_mumu.features.kamera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

public class KTPViewPort extends ViewGroup {
    public KTPViewPort(Context context) {
        super(context);
    }

    public KTPViewPort(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public KTPViewPort(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public KTPViewPort(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        int halfHeight = getHeight() / 2;
        int halfWeight = getWidth() / 2;

        int paddingX = 32;
        int paddingY = halfHeight / 2;

        int left = paddingX;
        int top = paddingY;
        int right = getWidth() - left;
        int bottom = (int) (paddingY + (getWidth() * 0.6));

        int viewportCornerRadius = 8;

        Paint eraser = new Paint();
        eraser.setAntiAlias(true);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));


        RectF rect = new RectF((float)left, (float)top, right, bottom);
        canvas.drawRoundRect(rect, (float) viewportCornerRadius, (float) viewportCornerRadius, eraser);

        Paint paint = new Paint();
        paint.setTextSize(55);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.WHITE);

        canvas.drawText("Pastikan KTP berada di dalam kotak area", left, bottom + 100, paint);

    }
}
