package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Picker;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;

public class PickerDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_picker_demo);
        Picker picker = (Picker) findComponentById(ResourceTable.Id_test_picker);
        picker.setMinValue(0); // 设置选择器中的最小值
        picker.setMaxValue(6); // 设置选择器中的最大值

        picker.setValueChangedListener((picker1, oldVal, newVal) -> {
            // oldVal:上一次选择的值； newVal：最新选择的值
        });

      /*  picker.setFormatter(new Picker.Formatter() {
            @Override
            public String format(int i) {
                String value;
                switch (i) {
                    case 0:
                        value = "Mon";
                        break;
                    case 1:
                        value = "Tue";
                        break;
                    case 2:
                        value = "Wed";
                        break;
                    case 3:
                        value = "Thu";
                        break;
                    case 4:
                        value = "Fri";
                        break;
                    case 5:
                        value = "Sat";
                        break;
                    case 6:
                        value = "Sun";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + i);
                }
                return value;
            }
        });*/

        picker.setDisplayedData(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"});


    /*  ShapeElement shape = new ShapeElement();
        shape.setShape(ShapeElement.RECTANGLE);
        shape.setRgbColor(RgbColor.fromArgbInt(0xFF40E0D0));
        // 单独设置上边框
        // picker.setDisplayedLinesTopElement(shape);
        // 单独设置下边框
        // picker.setDisplayedLinesBottomElement(shape);
        // 同时设置上下边框
        picker.setDisplayedLinesElements(shape,shape);  */

        //picker.setShaderColor(new Color(Color.getIntColor("#1E90FF")));

        boolean isWheel = picker.isWheelModeEnabled(); // 获取当前是否是选择轮模式
        picker.setWheelModeEnabled(!isWheel);
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
