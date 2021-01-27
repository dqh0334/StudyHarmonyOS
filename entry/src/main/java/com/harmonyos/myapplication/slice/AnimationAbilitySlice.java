package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;

public class AnimationAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_animation);
        findComponentById(ResourceTable.Id_btn_animator_frame_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_animator_value_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_animator_property_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_animator_group_demo).setClickedListener(this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        Intent intent = new Intent();
        switch (component.getId()) {
            case ResourceTable.Id_btn_animator_frame_demo:
                present(new AnimatorFrameDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_animator_value_demo:
                present(new AnimatorValueDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_animator_property_demo:
                present(new AnimatorPropertyDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_animator_group_demo:
                present(new AnimatorGroupDemoAbilitySlice(), new Intent());
                break;
        }
    }
}
