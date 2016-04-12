package ticwear.design.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * An arc shape drawable that draw with a semi-circle clip.
 *
 * Created by tankery on 4/8/16.
 */
public class ArcDrawable extends Drawable {

    private final Paint mArcPaint = new Paint();
    private final Drawable srcDrawable;
    private final Path mClipPath = new Path();

    public ArcDrawable(int color) {
        this(new ColorDrawable(color));
    }

    public ArcDrawable(@NonNull Drawable drawable) {
        super();
        srcDrawable = drawable;
        mArcPaint.setAntiAlias(true);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        srcDrawable.setColorFilter(colorFilter);
        mArcPaint.setColorFilter(colorFilter);
    }

    @Override
    public void setTintList(ColorStateList tint) {
        srcDrawable.setTintList(tint);
    }

    @Override
    public void setTintMode(@NonNull PorterDuff.Mode tintMode) {
        srcDrawable.setTintMode(tintMode);
    }

    @Override
    public int getOpacity() {
        return srcDrawable.getOpacity();
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        int size = Math.max(getBounds().width(), getBounds().height());
        srcDrawable.setBounds(left, top, size, size);

        mClipPath.reset();
        float radius = size * 0.5f;
        mClipPath.addCircle(left + radius, top + radius, radius, Path.Direction.CW);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();

        if (srcDrawable instanceof ColorDrawable) {
            int color = ((ColorDrawable) srcDrawable).getColor();
            int size = srcDrawable.getBounds().width();
            drawWithColor(canvas, color, size);
        } else {
            drawWithClip(canvas, srcDrawable);
        }

        canvas.restore();
    }

    private void drawWithColor(Canvas canvas, @ColorInt int color, int size) {

        final ColorFilter colorFilter = mArcPaint.getColorFilter();
        if ((color >>> 24) != 0 || colorFilter != null /*|| mTintFilter != null*/) {

            // do not using filter
//            if (colorFilter == null) {
//                mArcPaint.setColorFilter(mTintFilter);
//            }

            mArcPaint.setColor(color);
            Rect rect = getBounds();
            RectF rectF = new RectF(rect);
            rectF.right = rectF.left + size;
            rectF.bottom = rectF.top + size;
            canvas.drawArc(rectF, 180, 180, true, mArcPaint);
            // Restore original color filter.
//            mArcPaint.setColorFilter(colorFilter);
        }
    }

    private void drawWithClip(Canvas canvas, Drawable drawable) {
        canvas.clipRect(getBounds());
        canvas.clipPath(mClipPath);

        drawable.draw(canvas);
    }

    @Override
    public void setAlpha(int alpha) {
        srcDrawable.setAlpha(alpha);
    }

}