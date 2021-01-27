package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.TabList;

public class CommonCompontsAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_common_componts);
        findComponentById(ResourceTable.Id_btn_text_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_button_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_textfield_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_image_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_tablist_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_picker_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_datapicker_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_timepicker_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_switch_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_radiocontainer_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_progressbar_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_roundprogressbar_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_toastdialog_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_scrollview_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_listcontainer_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_slider_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_pageslider_demo).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_checkbox_demo).setClickedListener(this);
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
            case ResourceTable.Id_btn_text_demo:
                present(new TextDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_button_demo:
                present(new ButtonDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_textfield_demo:
                present(new TextFieldDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_image_demo:
                present(new ImageDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_tablist_demo:
                present(new TabListDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_picker_demo:
                present(new PickerDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_datapicker_demo:
                present(new DataPickerDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_timepicker_demo:
                present(new TimePickerDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_switch_demo:
                present(new SwitchDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_radiocontainer_demo:
                present(new RadioContainerDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_progressbar_demo:
                present(new ProgressBarDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_roundprogressbar_demo:
                present(new RoundProgressBarDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_toastdialog_demo:
                present(new ToastDialogDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_scrollview_demo:
                present(new ScrollViewDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_listcontainer_demo:
                present(new ListContainerDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_slider_demo:
                present(new SliderDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_pageslider_demo:
                present(new PageSliderDemoAbilitySlice(), new Intent());
                break;
            case ResourceTable.Id_btn_checkbox_demo:
                present(new CheckboxDemoAbilitySlice(), new Intent());
                break;

        }
    }
}
