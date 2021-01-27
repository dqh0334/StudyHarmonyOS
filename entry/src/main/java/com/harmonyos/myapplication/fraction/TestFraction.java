package com.harmonyos.myapplication.fraction;

import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.utils.TextAlignment;
import ohos.app.Context;

import java.util.List;

public class TestFraction extends Fraction {

    private final String TAG = TestFraction.class.getSimpleName();

    private int index;
    private Context context;

    public TestFraction(Context context,int index){
        this.context = context;
        this.index = index;
    }

    public static TestFraction create(Context context,int index){
        return new TestFraction(context,index);
    }

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        StackLayout rootLayout = new StackLayout(this);
        rootLayout.setWidth(ComponentContainer.LayoutConfig.MATCH_PARENT);
        rootLayout.setHeight(ComponentContainer.LayoutConfig.MATCH_PARENT);

        Text text = new Text(this);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("第"+index+"页");
        text.setLayoutConfig(new ComponentContainer.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT,ComponentContainer.LayoutConfig.MATCH_PARENT));
        text.setTextSize(50,Text.TextSizeType.FP);
        rootLayout.addComponent(text);
        return rootLayout;
    }
}

