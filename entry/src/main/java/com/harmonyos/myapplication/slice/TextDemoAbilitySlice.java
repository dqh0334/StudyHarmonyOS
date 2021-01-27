package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

public class TextDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_text_demo);
        Text text1 = (Text) findComponentById(ResourceTable.Id_text1);
        // 设置自动调整规则
        text1.setAutoFontSizeRule(30, 100, 1);
        // 设置点击一次增多一个"T"
        text1.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component Component) {
                text1.setText(text1.getText() + "T");
            }
        });

        Text text2 = (Text) findComponentById(ResourceTable.Id_text2);
        //设置跑马灯效果
        text2.setTruncationMode(Text.TruncationMode.AUTO_SCROLLING);
        //设置文本的自动滚动计数。无效滚动
        text2.setAutoScrollingCount(Text.AUTO_SCROLLING_FOREVER);
        //启动跑马灯效果
        text2.startAutoScrolling();
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
