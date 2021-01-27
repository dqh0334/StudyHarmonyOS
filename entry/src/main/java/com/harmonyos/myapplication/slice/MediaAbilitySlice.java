package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.service.WindowManager;

public class MediaAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_media);

        getWindow().setTransparent(true);
        getWindow().setLayoutAlignment(LayoutAlignment.BOTTOM);
        WindowManager.LayoutConfig layoutConfig = getWindow().getLayoutConfig().get();
        layoutConfig.width = 1000;
        layoutConfig.height = 1200;
        getWindow().setLayoutConfig(layoutConfig);

        findComponentById(ResourceTable.Id_btn_player_video).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_take_picture).setClickedListener(this);
        findComponentById(ResourceTable.Id_stackLayout).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                terminateAbility();
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

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_btn_player_video:
                present(new PlayerDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_take_picture:
                present(new TakePictureAbilitySlice(), new Intent());
                break;
        }
    }
}
