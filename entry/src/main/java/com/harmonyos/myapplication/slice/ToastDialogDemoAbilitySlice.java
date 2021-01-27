package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

public class ToastDialogDemoAbilitySlice extends AbilitySlice {


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_toastdialog_demo);
        findComponentById(ResourceTable.Id_btn_dialog_1).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                new ToastDialog(getContext())
                        .setText("This is a ToastDialog")
                        .show();
            }
        });findComponentById(ResourceTable.Id_btn_dialog_2).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                new ToastDialog(getContext())
                        .setText("This is a ToastDialog displayed in the middle")
                        .setAlignment(LayoutAlignment.CENTER)
                        .show();
            }
        });findComponentById(ResourceTable.Id_btn_dialog_3).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                DirectionalLayout toastLayout = (DirectionalLayout) LayoutScatter.getInstance(ToastDialogDemoAbilitySlice.this)
                        .parse(ResourceTable.Layout_layout_toast, null, false);
                new ToastDialog(getContext())
                        .setComponent(toastLayout)
                        .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT)
                        .setAlignment(LayoutAlignment.CENTER)
                        .show();
            }
        });findComponentById(ResourceTable.Id_btn_dialog_4).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                DirectionalLayout layout = (DirectionalLayout) LayoutScatter.getInstance(ToastDialogDemoAbilitySlice.this)
                        .parse(ResourceTable.Layout_layout_toast_and_image, null, false);
                new ToastDialog(getContext())
                        .setComponent(layout)
                        .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT)
                        .setAlignment(LayoutAlignment.CENTER)
                        .show();
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
