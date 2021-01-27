package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;

public class CommonLayoutSlice extends AbilitySlice implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_common_layout);
        findComponentById(ResourceTable.Id_btn_dependentlayoutt_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_directionallayout_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_stacklayout_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_tablelayout_demo).setClickedListener(this);
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
            case ResourceTable.Id_btn_dependentlayoutt_demo:
                present(new DependentLayoutDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_directionallayout_demo:
                present(new DirectionalLayoutDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_stacklayout_demo:
                present(new StackLayoutDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_tablelayout_demo:
                present(new TableLayoutDemoAbilitySlice(), new Intent());
                break;
        }
    }
}
