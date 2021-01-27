package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import com.harmonyos.myapplication.util.LogUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.agp.graphics.Surface;
import ohos.agp.graphics.SurfaceOps;
import ohos.agp.utils.Rect;
import ohos.app.Context;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.media.camera.CameraKit;
import ohos.media.camera.device.Camera;
import ohos.media.camera.device.CameraConfig;
import ohos.media.camera.device.CameraStateCallback;
import ohos.media.camera.device.FrameConfig;
import ohos.media.camera.params.Metadata;
import ohos.media.image.ImageReceiver;
import ohos.media.image.ImageSource;
import ohos.media.image.common.ImageFormat;
import ohos.media.image.common.PixelFormat;
import ohos.media.image.common.Size;

import java.io.*;
import java.util.Optional;

public class TakePictureAbilitySlice extends AbilitySlice{

    private EventHandler cameraEventHandler;
    private static final String TAG = "TakePictureAbilitySlice";
    private SurfaceProvider cameraPreview;
    private Image imgPicture;
    private Camera cameraDevice;


    private Context context;
    private String picturePath;

    // 定义生成图片文件名
    private String pictureName = "picture_001.jpg";


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_take_picture);

        getWindow().setTransparent(true);
        getWindow().setBackgroundColor(new RgbColor(0xffffffff));

        picturePath = getExternalCacheDir().getPath() + "/";
        LogUtils.info(TAG,picturePath);
        context = this;
        imgPicture = (Image) findComponentById(ResourceTable.Id_preview_picture);
        cameraPreview = (SurfaceProvider) findComponentById(ResourceTable.Id_camera_preview);
        cameraPreview.pinToZTop(false);
        findComponentById(ResourceTable.Id_btn_take_picture).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                capture();
            }
        });
        cameraPreview.getSurfaceOps().get().addCallback(new SurfaceCallBack());
        cameraEventHandler = new EventHandler(EventRunner.create("TakePictureAbilitySlice"));
        takePictureInit();
    }

    private class SurfaceCallBack implements SurfaceOps.Callback{

        @Override
        public void surfaceCreated(SurfaceOps surfaceOps) {
            openCamera();
        }

        @Override
        public void surfaceChanged(SurfaceOps surfaceOps, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceOps surfaceOps) {

        }
    }

    private final class CameraStateCallbackImpl extends CameraStateCallback {

        private Surface previewSurface;

        @Override
        public void onCreated(Camera camera) {
            // 创建相机设备
            previewSurface = cameraPreview.getSurfaceOps().get().getSurface();

            CameraConfig.Builder cameraConfigBuilder = camera.getCameraConfigBuilder();
            if (cameraConfigBuilder == null) {
                LogUtils.info(TAG, "onCreated cameraConfigBuilder is null");
                return;
            }
            // 配置预览的Surface
            cameraConfigBuilder.addSurface(previewSurface);
            // 配置拍照的Surface
            cameraConfigBuilder.addSurface(imageReceiver.getRecevingSurface());
            try {
                // 相机设备配置
                camera.configure(cameraConfigBuilder.build());
            } catch (IllegalArgumentException e) {
                LogUtils.info(TAG, "Argument Exception");
            } catch (IllegalStateException e) {
                LogUtils.info(TAG, "State Exception");
            }
        }

        @Override
        public void onConfigured(Camera camera) {
            // 获取预览配置模板
            FrameConfig.Builder framePreviewConfigBuilder = camera.getFrameConfigBuilder(Camera.FrameConfigType.FRAME_CONFIG_PREVIEW);
            // 配置预览Surface
            framePreviewConfigBuilder.addSurface(previewSurface);
            framePreviewConfigBuilder.setAeMode(Metadata.AfMode.AF_MODE_CONTINUOUS,new Rect());
            // 启动循环帧捕获
            camera.triggerLoopingCapture(framePreviewConfigBuilder.build());


            cameraDevice = camera;

        }

        @Override
        public void onPartialConfigured(Camera camera) {
            // 当使用了addDeferredSurfaceSize配置了相机，会接到此回调
        }

        @Override
        public void onReleased(Camera camera) {
            // 释放相机设备
            if (camera != null){
                camera.release();
                camera = null;
            }
        }
    }

    private void capture() {
        // 获取拍照配置模板
        FrameConfig.Builder framePictureConfigBuilder = cameraDevice.getFrameConfigBuilder(Camera.FrameConfigType.FRAME_CONFIG_PICTURE);
        // 配置拍照Surface
        framePictureConfigBuilder.addSurface(imageReceiver.getRecevingSurface());
        // 配置拍照其他参数
        framePictureConfigBuilder.setImageRotation(270);

        // 启动单帧捕获(拍照)
        cameraDevice.triggerSingleCapture(framePictureConfigBuilder.build());

    }

    private void openCamera() {
        CameraKit cameraKit = CameraKit.getInstance(TakePictureAbilitySlice.this);
        if (cameraKit == null){
            LogUtils.info(TAG,"获取cameraKit失败");
            terminateAbility();
        }
        String[] cameraIds = cameraKit.getCameraIds();
        String cameraId = null;
        int length = cameraIds.length;
        if (length < 0){
            LogUtils.info(TAG,"cameraIds size is 0");
            terminateAbility();
        }else if (length >=2){
            cameraId = cameraIds[1];
        }else {
            cameraId = cameraIds[0];
        }

        // 相机创建和相机运行时的回调
        CameraStateCallbackImpl cameraStateCallback = new CameraStateCallbackImpl();
        if(cameraStateCallback ==null) {
            LogUtils.info(TAG, "cameraStateCallback is null");
        }
        cameraKit.createCamera(cameraId,cameraStateCallback,cameraEventHandler);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    // 图像帧数据接收处理对象
    private ImageReceiver imageReceiver;
    // 拍照支持分辨率
    private Size pictureSize;

    // 单帧捕获生成图像回调Listener
    private final ImageReceiver.IImageArrivalListener imageArrivalListener = new ImageReceiver.IImageArrivalListener() {
        @Override
        public void onImageArrival(ImageReceiver imageReceiver) {
            LogUtils.info(TAG,"onImageArrival ---------------------------------");
            File myFile = new File(picturePath, pictureName); // 创建图片文件
            ImageSaver imageSaver = new ImageSaver(imageReceiver.readNextImage(), myFile); // 创建一个读写线程任务用于保存图片
            cameraEventHandler.postTask(imageSaver); // 执行读写线程任务生成图片
        }
    };

    // 保存图片, 图片数据读写，及图像生成见run方法
    class ImageSaver implements Runnable {
        private final ohos.media.image.Image myImage;
        private final File myFile;

        ImageSaver(ohos.media.image.Image image, File file) {
            myImage = image;
            myFile = file;
        }

        @Override
        public void run() {
            ohos.media.image.Image.Component component = myImage.getComponent(ImageFormat.ComponentType.JPEG);
            byte[] bytes = new byte[component.remaining()];
            component.read(bytes);
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(myFile);
                output.write(bytes); // 写图像数据
                LogUtils.info(TAG,  "bytes.length------------------"+bytes.length);
                output.flush();
            } catch (IOException e) {
                LogUtils.info(TAG,  "save picture occur exception!"+e.getLocalizedMessage());
            } finally {
                myImage.release();
                if (output != null) {
                    try {
                        output.close(); // 关闭流
                    } catch (IOException e) {
                        LogUtils.info(TAG,  "image release occur exception!");
                    }
                }
            }

            ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
            sourceOptions.formatHint = "image/jpg";
            ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
            decodingOptions.desiredPixelFormat = PixelFormat.ARGB_8888;
            File myFile = new File(picturePath, pictureName);

            try {
                FileInputStream fileInputStream = new FileInputStream(myFile);
                ImageSource imageSource = ImageSource.create(fileInputStream, sourceOptions);

                getUITaskDispatcher().asyncDispatch(new Runnable() {
                    @Override
                    public void run() {
                        imgPicture.setPixelMap(Optional.ofNullable(imageSource.createPixelmap(decodingOptions)).get());
                    }
                });
            } catch (FileNotFoundException e) {
                LogUtils.info(TAG,  "FileNotFoundException   "+e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }
    private void takePictureInit() {
        imageReceiver = ImageReceiver.create(1080,1080, ImageFormat.JPEG, 1); // 创建ImageReceiver对象，注意creat函数中宽度要大于高度；5为最大支持的图像数，请根据实际设置。
        imageReceiver.setImageArrivalListener(imageArrivalListener);
    }

}

/*/storage/emulated/0/Android/data/com.harmonyos.myapplication/cache/picture/*/
