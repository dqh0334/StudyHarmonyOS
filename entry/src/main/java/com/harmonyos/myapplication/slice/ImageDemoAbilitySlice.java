package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;

public class ImageDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_image_demo);
        Image image = (Image) findComponentById(ResourceTable.Id_image_switch);
        image.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                image.setPixelMap(ResourceTable.Media_test);
                image.setEnabled(false);
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
