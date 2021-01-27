package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.ScrollView;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

public class ScrollViewDemoAbilitySlice extends AbilitySlice {


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_scrollview_demo);
        ScrollView scrollview = (ScrollView) findComponentById(ResourceTable.Id_scrollview);

        //设置回弹效果。
        //scrollView.setReboundEffect(true);

        /*配置回弹效果
        overscrollPercent：过度滚动百分比，默认值40
        overscrollRate：过度滚动率，默认值0.6
        remainVisiblePercent：应保持可见内容的最小百分比，默认值20*/
        //setReboundEffectParams(int overscrollPercent, float overscrollRate, int remainVisiblePercent)
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
