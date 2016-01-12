package com.example.floatingbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by ABHISHEK on 01/04/2016.
 */
public class FloatingButton extends ImageView {
    private static final String TAG = FloatingButton.class.getName();



    boolean isStartScaling;
    private int mImageResId = R.drawable.menu;

    public int getDrwableResId() {
        return mImageResId;
    }

    ;

    public FloatingButton(Context context) {
        super(context);

    }

    public FloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FloatingButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        if (isStartScaling)
            startScaling();

    }

    private void startScaling() {

        final int oldWidth = getWidth();
        final int oldHeight = getHeight();

        ScaleAnimation animation = new ScaleAnimation(1, 0f, 1, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.initialize(0, 0, oldWidth, oldHeight);
        animation.setDuration(200);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(1);
        postDelayed(new Runnable() {
            @Override
            public void run() {

                setImageBitmap(null);
                setImageIntoView(mImageResId);
            }
        }, 200);

        startAnimation(animation);
        isStartScaling = false;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            int width = w;
            int height = h;
            try {
                Bitmap bitmap = ((BitmapDrawable) this.getDrawable()).getBitmap();
                Bitmap bit = getResizedBitmap(bitmap, width, height);
                Bitmap roundBits = getRoundedShape(bit, width);
                setImageBitmap(roundBits);
            } catch (RuntimeException x) {
                Log.d(TAG, "User Image Exception : " + width + height);
            }
        }
    }

    void initScaling() {
        isStartScaling = true;
        invalidate();
    }

    void setImageIntoView(int resId) {
        Bitmap originalBitmap = BitmapFactory
                .decodeResource(getContext().getResources(), resId);
        setImageBitmap(originalBitmap);
        Bitmap bitmap = ((BitmapDrawable) FloatingButton.this.getDrawable()).getBitmap();
        int width = bitmap.getWidth();
        Bitmap roundBits = getRoundedShape(originalBitmap, width);
        setImageBitmap(roundBits);
    }

    public void setImage(int resID) {
        switch (mImageResId){
            case R.drawable.menu:

                RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(200);
                rotate.setInterpolator(new LinearInterpolator());
                startAnimation(rotate);
                break;
        }
        mImageResId = resID;
        initScaling();
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = bm;
        // "RECREATE" THE NEW BITMAP
        if (!bm.isRecycled()) {
            resizedBitmap = Bitmap.createBitmap(
                    bm, 0, 0, width, height, matrix, false);

            bm.recycle();
        }
        return resizedBitmap;
    }

    public static Bitmap getRoundedShape(Bitmap scaleBitmapImage, int width) {
        // TODO Auto-generated method stub
        Bitmap targetBitmap = Bitmap.createBitmap(width,
                width, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) width - 1) / 2,
                ((float) width - 1) / 2,
                (Math.min(((float) width),
                        ((float) width)) / 2),
                Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(scaleBitmapImage,
                new Rect(0, 0, scaleBitmapImage.getWidth(),
                        scaleBitmapImage.getHeight()),
                new Rect(0, 0, width,
                        width), null);
        return targetBitmap;
    }
}
