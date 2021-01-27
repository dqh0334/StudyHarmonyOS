package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.element.FrameAnimationElement;

public class AnimatorFrameDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_animation_frame);
        FrameAnimationElement frameAnimationElement = new FrameAnimationElement(getContext(), ResourceTable.Graphic_animation_element);
        findComponentById(ResourceTable.Id_animation_frame).setBackground(frameAnimationElement);
        frameAnimationElement.setOneShot(true);     //设置动画之播放一次
        frameAnimationElement.start();

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
