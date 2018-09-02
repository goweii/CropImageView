package per.goweii.android.cropimageview;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import per.goweii.cropimageview.CropImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CropImageView crop_image_view;
    private DisplayMetrics displayMetrics;
    private int size;

    private boolean isImageH = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayMetrics = getResources().getDisplayMetrics();

        crop_image_view = findViewById(R.id.crop_image_view);
        findViewById(R.id.tv_center).setOnClickListener(this);
        findViewById(R.id.tv_center_left).setOnClickListener(this);
        findViewById(R.id.tv_center_right).setOnClickListener(this);
        findViewById(R.id.tv_center_top).setOnClickListener(this);
        findViewById(R.id.tv_center_bottom).setOnClickListener(this);
        findViewById(R.id.tv_left_top).setOnClickListener(this);
        findViewById(R.id.tv_left_bottom).setOnClickListener(this);
        findViewById(R.id.tv_right_top).setOnClickListener(this);
        findViewById(R.id.tv_right_bottom).setOnClickListener(this);
        findViewById(R.id.tv_anim_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crop_image_view.setAutoMove(true);
            }
        });
        findViewById(R.id.tv_anim_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crop_image_view.setAutoMove(false);
            }
        });
        findViewById(R.id.tv_anim_w).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (size == 0) {
                    size = crop_image_view.getLayoutParams().width;
                }
                ValueAnimator valueAnimator = ValueAnimator.ofInt(size, displayMetrics.widthPixels, size);
                valueAnimator.setDuration(1000);
                valueAnimator.setInterpolator(new DecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = (Integer) animator.getAnimatedValue();
                        crop_image_view.getLayoutParams().width = value;
                        crop_image_view.requestLayout();
                    }
                });
                valueAnimator.start();
            }
        });
        findViewById(R.id.tv_anim_w_h).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (size == 0) {
                    size = crop_image_view.getLayoutParams().width;
                }
                ValueAnimator valueAnimator = ValueAnimator.ofInt(size, displayMetrics.widthPixels, size);
                valueAnimator.setDuration(1000);
                valueAnimator.setInterpolator(new DecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = (Integer) animator.getAnimatedValue();
                        crop_image_view.getLayoutParams().width = value;
                        crop_image_view.getLayoutParams().height = value;
                        crop_image_view.requestLayout();
                    }
                });
                valueAnimator.start();
            }
        });
        findViewById(R.id.tv_anim_h).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (size == 0) {
                    size = crop_image_view.getLayoutParams().width;
                }
                ValueAnimator valueAnimator = ValueAnimator.ofInt(size, displayMetrics.widthPixels, size);
                valueAnimator.setDuration(1000);
                valueAnimator.setInterpolator(new DecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = (Integer) animator.getAnimatedValue();
                        crop_image_view.getLayoutParams().height = value;
                        crop_image_view.requestLayout();
                    }
                });
                valueAnimator.start();
            }
        });
        crop_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isImageH = !isImageH;
                if (isImageH){
                    crop_image_view.setImageResource(R.mipmap.img_h);
                } else {
                    crop_image_view.setImageResource(R.mipmap.img_v);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_center:
                crop_image_view.setCropType(CropImageView.CropType.CENTER);
                break;
            case R.id.tv_center_left:
                crop_image_view.setCropType(CropImageView.CropType.CENTER_LEFT);
                break;
            case R.id.tv_center_right:
                crop_image_view.setCropType(CropImageView.CropType.CENTER_RIGHT);
                break;
            case R.id.tv_center_top:
                crop_image_view.setCropType(CropImageView.CropType.CENTER_TOP);
                break;
            case R.id.tv_center_bottom:
                crop_image_view.setCropType(CropImageView.CropType.CENTER_BOTTOM);
                break;
            case R.id.tv_left_top:
                crop_image_view.setCropType(CropImageView.CropType.TOP_LEFT);
                break;
            case R.id.tv_left_bottom:
                crop_image_view.setCropType(CropImageView.CropType.BOTTOM_LEFT);
                break;
            case R.id.tv_right_top:
                crop_image_view.setCropType(CropImageView.CropType.TOP_RIGHT);
                break;
            case R.id.tv_right_bottom:
                crop_image_view.setCropType(CropImageView.CropType.BOTTOM_RIGHT);
                break;
        }
    }
}
