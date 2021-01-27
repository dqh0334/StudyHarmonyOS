package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import com.harmonyos.myapplication.util.LogUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.agp.graphics.SurfaceOps;
import ohos.app.Environment;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.global.resource.RawFileEntry;
import ohos.media.common.Source;
import ohos.media.player.Player;
import ohos.security.SystemPermission;

import javax.net.ssl.StandardConstants;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class PlayerDemoAbilitySlice extends AbilitySlice implements Component.ClickedListener {

    private static final String TAG = "PlayerDemoAbilitySlice";

    private StackLayout rootPlayerLayout;
    private Slider playerProgress;
    private Button btnStartPlayer;
    private Button btnStartPause;
    private Button btnStartStop;
    private Button btnStartNext;
    private Button btnPlayerAudio;
    private SurfaceProvider surfaceView;
    private static Player sPlayer;
    private static boolean isFinished = true;
    private static boolean isPause = false;
    private static List<String> mMediaLists = new ArrayList<>();
    private TaskDispatcher uiTaskDispatcher;
    private Runnable runnable;

    {
        mMediaLists.add("test_video1.mp4");
        mMediaLists.add("test_video2.mp4");
        mMediaLists.add("test_audio.mp3");
    }

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);

        super.setUIContent(ResourceTable.Layout_ability_player_demo);

        getWindow().setTransparent(true);
        getWindow().setBackgroundColor(new RgbColor(0xffffffff));

        rootPlayerLayout = (StackLayout) findComponentById(ResourceTable.Id_player_layout);
        playerProgress = (Slider) findComponentById(ResourceTable.Id_player_progress);
        btnStartPlayer = (Button) findComponentById(ResourceTable.Id_btn_player_start);
        btnStartPause = (Button) findComponentById(ResourceTable.Id_btn_player_pause);
        btnStartStop = (Button) findComponentById(ResourceTable.Id_btn_player_stop);
        btnStartNext = (Button) findComponentById(ResourceTable.Id_btn_player_next);
        btnPlayerAudio = (Button) findComponentById(ResourceTable.Id_btn_player_audio);

        btnStartPlayer.setClickedListener(this);
        btnStartPause.setClickedListener(this);
        btnStartStop.setClickedListener(this);
        btnStartNext.setClickedListener(this);
        btnPlayerAudio.setClickedListener(this);


        if (sPlayer != null) {
            sPlayer.stop();
        } else {
            sPlayer = new Player(this);
        }
        sPlayer.setPlayerCallback(mPlayerCallback);
        isFinished = true;

        uiTaskDispatcher = getUITaskDispatcher();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (sPlayer != null) {
                    playerProgress.setProgressValue(sPlayer.getCurrentTime() * 100 / sPlayer.getDuration());
                }
                if (isFinished) {
                    return;
                }
                uiTaskDispatcher.delayDispatch(this::run, 200);
            }
        };
        playerProgress.setValueChangedListener(new Slider.ValueChangedListener() {
            @Override
            public void onProgressUpdated(Slider slider, int i, boolean b) {

            }

            @Override
            public void onTouchStart(Slider slider) {
                LogUtils.info(TAG, "onTouchStart       " + slider);
                isFinished = true;
            }

            @Override
            public void onTouchEnd(Slider slider) {
                int progress = slider.getProgress();
                if (sPlayer != null && sPlayer.isNowPlaying()) {
                    sPlayer.rewindTo(sPlayer.getDuration() * progress * 10);
                    isFinished = false;
                    uiTaskDispatcher.delayDispatch(runnable, 200);
                }
            }
        });

    }

    private void addSurfaceView() {
        if (surfaceView != null) {
            rootPlayerLayout.removeComponent(surfaceView);
        }
        playerProgress.setProgressValue(0);
        StackLayout.LayoutConfig layoutConfig = new StackLayout.LayoutConfig(StackLayout.LayoutConfig.MATCH_PARENT, StackLayout.LayoutConfig.MATCH_PARENT);
        surfaceView = new SurfaceProvider(this);
        surfaceView.pinToZTop(false);
        surfaceView.getSurfaceOps().get().addCallback(new SurfaceOps.Callback() {
            @Override
            public void surfaceCreated(SurfaceOps surfaceOps) {
                sPlayer.setSurfaceOps(surfaceOps);
                sPlayer.enableScreenOn(true);       //屏幕常亮
            }

            @Override
            public void surfaceChanged(SurfaceOps surfaceOps, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceOps surfaceOps) {

            }
        });
        surfaceView.setLayoutConfig(layoutConfig);
        rootPlayerLayout.addComponent(surfaceView, 0);
    }

    @Override
    public void onActive() {
        super.onActive();
        if (sPlayer != null && !sPlayer.isNowPlaying()) {
            sPlayer.play();
        }
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
        if (sPlayer != null) {
            sPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (sPlayer != null) {
            sPlayer.stop();
            sPlayer.release();
            sPlayer = null;
        }
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_btn_player_start:
                if (sPlayer != null && sPlayer.isNowPlaying()) {
                    return;
                }
                if (!isPause) {
                    isFinished = false;
                    uiTaskDispatcher.delayDispatch(runnable, 200);
                    addSurfaceView();
                    loadVideoSource(0);
                }
                boolean play = sPlayer.play();
                LogUtils.info(TAG, "" + play);
                break;
            case ResourceTable.Id_btn_player_pause:
                if (sPlayer != null && sPlayer.isNowPlaying()) {
                    sPlayer.pause();
                }
                isPause = true;
                break;
            case ResourceTable.Id_btn_player_stop:
                if (sPlayer != null) {
                    sPlayer.stop();
                }
                isPause = false;
                isFinished = true;
                break;
            case ResourceTable.Id_btn_player_next:
                isFinished = false;
                uiTaskDispatcher.delayDispatch(runnable, 200);
                if (sPlayer != null) {
                    sPlayer.stop();
                    sPlayer.reset();
                    addSurfaceView();
                    loadVideoSource(1);
                    sPlayer.play();
                }
                break;
            case ResourceTable.Id_btn_player_audio:
                isFinished = false;
                uiTaskDispatcher.delayDispatch(runnable, 200);
                if (sPlayer != null) {
                    sPlayer.stop();
                    sPlayer.reset();
                    loadVideoSource(2);
                    sPlayer.play();
                }
                break;
        }
    }

    private Player.IPlayerCallback mPlayerCallback = new Player.IPlayerCallback() {
        @Override
        public void onPrepared() {
            LogUtils.info(TAG, "onPrepared");
        }

        @Override
        public void onMessage(int i, int i1) {
            LogUtils.info(TAG, "onMessage  i:  " + i + "  i1:  " + i1);
        }

        @Override
        public void onError(int i, int i1) {
            LogUtils.info(TAG, "onError  i:  " + i + "  i1:  " + i1);
        }

        @Override
        public void onResolutionChanged(int i, int i1) {
            LogUtils.info(TAG, "onResolutionChanged  i:  " + i + "  i1:  " + i1);
        }

        @Override
        public void onPlayBackComplete() {
            LogUtils.info(TAG, "onPlayBackComplete ");
            isFinished = true;
            isPause = false;
        }

        @Override
        public void onRewindToComplete() {
            LogUtils.info(TAG, "onRewindToComplete ");
        }

        @Override
        public void onBufferingChange(int i) {
            LogUtils.info(TAG, "onBufferingChange " + i);
        }

        @Override
        public void onNewTimedMetaData(Player.MediaTimedMetaData mediaTimedMetaData) {
            LogUtils.info(TAG, "onNewTimedMetaData ");
        }

        @Override
        public void onMediaTimeIncontinuity(Player.MediaTimeInfo mediaTimeInfo) {
            LogUtils.info(TAG, "onMediaTimeIncontinuity ");
        }
    };

    public void loadVideoSource(int index) {

        String videoPath = getExternalCacheDir().getPath() + "/test/" + mMediaLists.get(index);
        File videoFile = new File(videoPath);

        if (!videoFile.exists()) {
            videoFile.mkdirs();
            String testVideo = "entry/resources/rawfile/" + mMediaLists.get(index);
            RawFileEntry entry = getContext().getResourceManager().getRawFileEntry(testVideo);
            try {
                Files.copy(entry.openRawFile(), new File(videoPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fileInputStream = null;
        FileDescriptor fileDescriptor = null;
        try {
            fileInputStream = new FileInputStream(videoFile);
            fileDescriptor = fileInputStream.getFD();
            Source source = new Source(fileDescriptor);

            if (source != null) {
                sPlayer.setSource(source);
                sPlayer.prepare();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
