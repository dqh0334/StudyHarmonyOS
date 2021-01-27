package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.DatePicker;
import ohos.agp.components.Picker;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;

public class DataPickerDemoAbilitySlice extends AbilitySlice {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_datapicker_demo);
        // 获取DatePicker实例
        DatePicker datePicker = (DatePicker) findComponentById(ResourceTable.Id_date_pick);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        //设置当前日期
        datePicker.updateDate(2021, 8, 8);


    /*    datePicker.setYearFixed(true);
        datePicker.setMonthFixed(true);
        datePicker.setDayFixed(true);*/

        Text selectedDate = (Text) findComponentById(ResourceTable.Id_text_date);
        datePicker.setValueChangedListener(
                new DatePicker.ValueChangedListener() {
                    @Override
                    public void onValueChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        selectedDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                }
        );

 /*       datePicker.setSelectedTextSize(40);
        datePicker.setSelectedTextColor(new Color(Color.getIntColor("#FFA500")));
        datePicker.setOperatedTextColor(new Color(Color.getIntColor("#00FFFF")));
        datePicker.setSelectedNormalTextMarginRatio(10.0f);
        datePicker.setWheelModeEnabled(true);
        ShapeElement shape = new ShapeElement();
        shape.setShape(ShapeElement.RECTANGLE);
        shape.setRgbColor(RgbColor.fromArgbInt(0xFF9370DB));
        datePicker.setDisplayedLinesElements(shape,shape);
        datePicker.setShaderColor(new Color(Color.getIntColor("#00CED1")));*/
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
