package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.Text;

public class StackLayoutDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_stacklayout_demo);
        ComponentContainer stackLayout = (ComponentContainer) findComponentById(ResourceTable.Id_stack_layout);

        Text textFirst = (Text) findComponentById(ResourceTable.Id_text_blue);

        textFirst.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                stackLayout.moveChildToFront(component);
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
