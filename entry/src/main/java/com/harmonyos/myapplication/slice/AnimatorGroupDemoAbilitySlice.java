package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorGroup;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class AnimatorGroupDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_animator_group);
        Button btnAnimatorGroupOne = (Button) findComponentById(ResourceTable.Id_btn_animator_group_one);
        Button btnAnimatorGroupTwo = (Button) findComponentById(ResourceTable.Id_btn_animator_group_two);
        Button btnAnimatorGroupThree = (Button) findComponentById(ResourceTable.Id_btn_animator_group_three);
        Button btnAnimatorGroupFour = (Button) findComponentById(ResourceTable.Id_btn_animator_group_four);


        AnimatorValue animatorValueOne = new AnimatorValue();
        //动画时间
        animatorValueOne.setDuration(2000);
        //延时启动
        //animatorValueOne.setDelay(1000);
        //回弹加速器
        animatorValueOne.setCurveType(Animator.CurveType.BOUNCE);

        animatorValueOne.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float value) {
                btnAnimatorGroupOne.setContentPosition(btnAnimatorGroupOne.getContentPositionX(),(int) (1000 * value));
                btnAnimatorGroupOne.setAlpha(value);
            }
        });
        AnimatorValue animatorValueTwo = new AnimatorValue();
        //动画时间
        animatorValueTwo.setDuration(2000);
        //延时启动
        //animatorValueTwo.setDelay(1000);
        //回弹加速器
        animatorValueTwo.setCurveType(Animator.CurveType.BOUNCE);

        animatorValueTwo.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float value) {
                btnAnimatorGroupTwo.setContentPosition(btnAnimatorGroupTwo.getContentPositionX(),(int) (1000 * value));
                btnAnimatorGroupTwo.setAlpha(value);
            }

        });

        AnimatorValue animatorValueThree = new AnimatorValue();
        //动画时间
        animatorValueThree.setDuration(2000);
        //延时启动
        //animatorValueThree.setDelay(1000);
        //回弹加速器
        animatorValueThree.setCurveType(Animator.CurveType.BOUNCE);

        animatorValueThree.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float value) {
                btnAnimatorGroupThree.setContentPosition(btnAnimatorGroupThree.getContentPositionX(),(int) (1000 * value));
                btnAnimatorGroupThree.setAlpha(value);
            }
        });

        AnimatorValue animatorValueFour = new AnimatorValue();
        //动画时间
        animatorValueFour.setDuration(2000);
        //延时启动
       // animatorValueFour.setDelay(1000);
        //回弹加速器
        animatorValueFour.setCurveType(Animator.CurveType.BOUNCE);

        animatorValueFour.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float value) {
                btnAnimatorGroupFour.setContentPosition(btnAnimatorGroupFour.getContentPositionX(),(int) (1000 * value));
                btnAnimatorGroupFour.setAlpha(value);
            }
        });

        findComponentById(ResourceTable.Id_btn_animator_group_type_one).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                AnimatorGroup animatorGroup = new AnimatorGroup();
                // 4个动画按顺序播放
                animatorGroup.runSerially(animatorValueOne, animatorValueTwo, animatorValueThree, animatorValueFour);
                animatorGroup.start();
            }
        });
        findComponentById(ResourceTable.Id_btn_animator_group_type_two).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                AnimatorGroup animatorGroup = new AnimatorGroup();
                // 4个动画同时播放
                animatorGroup.runParallel(animatorValueOne, animatorValueTwo, animatorValueThree, animatorValueFour);
                animatorGroup.start();
            }
        });
        findComponentById(ResourceTable.Id_btn_animator_group_type_three).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                AnimatorGroup animatorGroup = new AnimatorGroup();
                AnimatorGroup.Builder animatorGroupBuilder = animatorGroup.build();
                // 4个动画同时播放
                animatorGroupBuilder.addAnimators(animatorValueOne).addAnimators(animatorValueTwo, animatorValueThree).addAnimators(animatorValueFour);
                animatorGroup.start();
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
