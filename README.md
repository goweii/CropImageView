# CropImageView
可自由指定自适应裁剪对齐点的ImageView

[GitHub主页](https://github.com/goweii/CropImageView)

[Demo下载](https://github.com/goweii/CropImageView/raw/master/app/release/app-release.apk)



# 截图

![](https://raw.githubusercontent.com/goweii/CropImageView/master/picture/crop_image_view_demo.gif?raw=true)



# 集成方式

1. 在项目根目录的**build.gradle**添加仓库地址

```java
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```

2. 在项目app目录的**build.gradle**添加依赖

[最新版请看](https://github.com/goweii/CropImageView/releases)

从1.2.1版本开始，版本号前不加v，引用时需要注意。

```java
dependencies {
	implementation 'com.github.goweii:CropImageView:1.2.1'
}
```

# 使用

### 布局文件

```java
<per.goweii.cropimageview.CropImageView
    android:id="@+id/crop_image_view"
    android:layout_width="200dp"
    android:layout_height="200dp"
    android:src="@mipmap/img_h"
    app:crop_type="center"
    app:crop_auto_move="true"
    app:crop_scale="2"
    app:crop_percent_x="0.2"
    app:crop_percent_y="0.2"/>
```

### 代码调用

```java
// 设置对齐点
crop_image_view.setCropType(CropImageView.CropType.CENTER);
// 设置对齐点(取值为0~1)
crop_image_view.setCropPercent(0.5f， 0.5f)；
// 开启自动移动
crop_image_view.setAutoMove(true);
```

### 常用方法

- #### setSmoothMoveAnimDuration(@IntRange(from = 0) long smoothMoveAnimDuration)

  设置切换焦点时移动时长

- #### setSmoothMoveAnimInterpolator(TimeInterpolator smoothMoveAnimInterpolator)

  设置切换焦点时移动的时间插值器

- #### setCropType(@Type int cropType)

  设置焦点类型

- #### setCropPercent(@FloatRange(from = 0, to = 1) final float percentX, @FloatRange(from = 0, to = 1) final float percentY)

  设置焦点位于图片的位置

- #### clearCropPercent()

  清空焦点比例

- #### setAutoMove(boolean autoMove)

  设置自动移动动画开启关闭

- #### setAutoMoveDuration(int autoMoveDuration)

  设置自动移动动画一次到边界的移动时长

- #### setCropScale(@FloatRange(from = 1) float cropScale)

  设置裁剪的缩放比例，
