package per.goweii.cropimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 可自由指定自适应裁剪的对齐点
 *
 * @author Cuizhen
 * @date 2018/8/13-上午11:07
 */
public class CropImageView extends AppCompatImageView {

    protected final Context context;
    private int cropType;

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(attrs);
    }

    public void setCropType(@Type int cropType) {
        this.cropType = cropType;
        invalidate();
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CropImageView);
        cropType = typedArray.getInt(R.styleable.CropImageView_crop_type, 0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        initMatrix();
        Matrix matrix = getImageMatrix();
        if (matrix == null) {
            drawable.draw(canvas);
        } else {
            final int saveCount = canvas.getSaveCount();
            canvas.save();
            canvas.concat(matrix);
            getDrawable().draw(canvas);
            canvas.restoreToCount(saveCount);
        }
    }

    private void initMatrix() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Matrix matrix = getImageMatrix();
        if (matrix == null) {
            return;
        }

        final int dWidth = drawable.getIntrinsicWidth();
        final int dHeight = drawable.getIntrinsicHeight();

        final int vWidth = getWidth();
        final int vHeight = getHeight();

        float percentX = 0.5f;
        float percentY = 0.5f;

        switch (cropType) {
            default:
                break;
            case CropType.CENTER:
                percentX = 0.5f;
                percentY = 0.5f;
                break;
            case CropType.CENTER_LEFT:
                percentX = 0.0f;
                percentY = 0.5f;
                break;
            case CropType.CENTER_RIGHT:
                percentX = 1.0f;
                percentY = 0.5f;
                break;
            case CropType.CENTER_TOP:
                percentX = 0.5f;
                percentY = 0.0f;
                break;
            case CropType.CENTER_BOTTOM:
                percentX = 1.0f;
                percentY = 0.5f;
                break;
            case CropType.TOP_LEFT:
                percentX = 0.0f;
                percentY = 0.0f;
                break;
            case CropType.BOTTOM_LEFT:
                percentX = 0.0f;
                percentY = 1.0f;
                break;
            case CropType.TOP_RIGHT:
                percentX = 1.0f;
                percentY = 0.0f;
                break;
            case CropType.BOTTOM_RIGHT:
                percentX = 1.0f;
                percentY = 1.0f;
                break;
        }

        float scale;
        float dx = 0, dy = 0;

        if (dWidth * vHeight > vWidth * dHeight) {
            scale = (float) vHeight / (float) dHeight;
            dx = (vWidth - dWidth * scale) * percentX;
        } else {
            scale = (float) vWidth / (float) dWidth;
            dy = (vHeight - dHeight * scale) * percentY;
        }

        matrix.setScale(scale, scale);
        matrix.postTranslate(Math.round(dx), Math.round(dy));
    }

    public interface CropType {
        int CENTER = 0;
        int CENTER_LEFT = 1;
        int CENTER_RIGHT = 2;
        int CENTER_TOP = 3;
        int CENTER_BOTTOM = 4;
        int TOP_LEFT = 5;
        int TOP_RIGHT = 6;
        int BOTTOM_LEFT = 7;
        int BOTTOM_RIGHT = 8;
    }

    @IntDef({CropType.CENTER,
            CropType.CENTER_LEFT, CropType.CENTER_RIGHT, CropType.CENTER_TOP, CropType.CENTER_BOTTOM,
            CropType.TOP_LEFT, CropType.BOTTOM_LEFT, CropType.TOP_RIGHT, CropType.BOTTOM_RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }
}
