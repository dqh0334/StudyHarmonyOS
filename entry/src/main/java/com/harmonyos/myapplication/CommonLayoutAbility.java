package com.harmonyos.myapplication;

import com.harmonyos.myapplication.slice.CommonLayoutSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;

public class CommonLayoutAbility extends Ability implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(CommonLayoutSlice.class.getName());

    }

    @Override
    public void onClick(Component component) {

    }
}
