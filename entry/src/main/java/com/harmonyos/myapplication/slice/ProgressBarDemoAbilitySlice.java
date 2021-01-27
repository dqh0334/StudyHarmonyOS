package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.Component;
import ohos.agp.components.DatePicker;
import ohos.agp.components.ProgressBar;
import ohos.agp.components.TabList;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;

import java.util.ArrayList;
import java.util.List;

public class ProgressBarDemoAbilitySlice extends AbilitySlice {


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_progressbar_demo);
        ProgressBar progressBar = (ProgressBar) findComponentById(ResourceTable.Id_progressbar);

        //设置当前进度
        //progressBar.setProgressValue(60);

        //设置最大和最小值
        //progressBar.setMaxValue(400);
        //progressBar.setMinValue(0);


        //设置ProgressBar分割线
       // progressBar.enableDividerLines(true);
        //progressBar.setDividerLinesNumber(5);

        //设置ProgressBar分割线颜色
        progressBar.setDividerLineColor(Color.MAGENTA);
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
