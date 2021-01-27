package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.components.Component;

public class AnimatorValueDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_animation_value);
        AnimatorValue animatorValue = new AnimatorValue();
        //动画时间
        animatorValue.setDuration(2000);
        //延时启动
        animatorValue.setDelay(1000);
        //重复次数+1
        animatorValue.setLoopedCount(2);
        //回弹加速器
        animatorValue.setCurveType(Animator.CurveType.BOUNCE);
        Component button = findComponentById(ResourceTable.Id_animation_value);

        animatorValue.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float value) {
                button.setContentPosition((int) (1080 * value), button.getContentPositionY());
                button.setAlpha(value);
            }
        });

        animatorValue.setStateChangedListener(new Animator.StateChangedListener() {
            float contentPositionX = 0.0f;
            @Override
            public void onStart(Animator animator) {
               contentPositionX = button.getContentPositionX();
            }

            @Override
            public void onStop(Animator animator) {

            }

            @Override
            public void onCancel(Animator animator) {

            }

            @Override
            public void onEnd(Animator animator) {
                button.setContentPosition(contentPositionX,button.getContentPositionY());
            }

            @Override
            public void onPause(Animator animator) {

            }

            @Override
            public void onResume(Animator animator) {

            }
        });

        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                animatorValue.start();
                getUITaskDispatcher().delayDispatch(new Runnable() {
                    @Override
                    public void run() {
                        // animator.cancel();//取消动画并返回初始状态
                        // animator.stop();//在当前状态下停止动画
                    }
                },1000);
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }


}
