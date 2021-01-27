package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorProperty;
import ohos.agp.components.Component;
import ohos.agp.components.element.ShapeElement;

public class AnimatorPropertyDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_animation_property);
        Component button = findComponentById(ResourceTable.Id_animation_property);
        AnimatorProperty animatorProperty = button.createAnimatorProperty();
        animatorProperty.moveFromX(50).moveToX(1000).rotate(90).setDuration(2500).setDelay(500).setLoopedCount(5);
        animatorProperty.setTarget(button);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                button.setBackground(new ShapeElement(AnimatorPropertyDemoAbilitySlice.this,ResourceTable.Graphic_background_text_field2));
               animatorProperty.start();
            }
        });

        animatorProperty.setStateChangedListener(new Animator.StateChangedListener() {
            @Override
            public void onStart(Animator animator) {

            }

            @Override
            public void onStop(Animator animator) {

            }

            @Override
            public void onCancel(Animator animator) {

            }

            @Override
            public void onEnd(Animator animator) {
                animatorProperty.reset();
            }

            @Override
            public void onPause(Animator animator) {

            }

            @Override
            public void onResume(Animator animator) {

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
