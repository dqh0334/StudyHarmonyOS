package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        findComponentById(ResourceTable.Id_btn_common_componts).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_common_layout).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_animation).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_fraction).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_media).setClickedListener(this);
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
            case ResourceTable.Id_btn_common_componts:
                // 通过Intent中的OperationBuilder类构造operation对象，指定设备标识（空串表示当前设备）、应用包名、Ability名称
                Operation operationCommonComponts = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.harmonyos.myapplication")
                        .withAbilityName("com.harmonyos.myapplication.CommonCompontsAbility")
                        .build();
                // 把operation设置到intent中
                intent.setOperation(operationCommonComponts);
                startAbility(intent);
                break;

            case ResourceTable.Id_btn_common_layout:
                Operation operationCommonLayout = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.harmonyos.myapplication")
                        .withAbilityName("com.harmonyos.myapplication.CommonLayoutAbility")
                        .build();
                intent.setOperation(operationCommonLayout);
                startAbility(intent);
                break;
            case ResourceTable.Id_btn_animation:
                Operation operationAnimation = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.harmonyos.myapplication")
                        .withAbilityName("com.harmonyos.myapplication.AnimationAbility")
                        .build();
                intent.setOperation(operationAnimation);
                startAbility(intent);
                break;
            case ResourceTable.Id_btn_fraction:
                Operation operationFraction = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.harmonyos.myapplication")
                        .withAbilityName("com.harmonyos.myapplication.TestFractionAbility")
                        .build();
                intent.setOperation(operationFraction);
                startAbility(intent);
                break;
            case ResourceTable.Id_btn_media:
                Operation operationMedia = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.harmonyos.myapplication")
                        .withAbilityName("com.harmonyos.myapplication.MediaAbility")
                        .build();
                intent.setOperation(operationMedia);
                startAbility(intent);
                break;
        }
    }
}
