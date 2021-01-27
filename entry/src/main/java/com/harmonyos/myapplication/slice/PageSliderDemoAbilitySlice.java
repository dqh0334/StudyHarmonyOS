package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Rect;

import java.util.ArrayList;

public class PageSliderDemoAbilitySlice extends AbilitySlice{

    private int mCurrentPosition = 0;
    private PageSlider pageSlider;
    private PageSliderIndicator pageSliderIndicator;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_pageslider_demo);
        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_pageslider);
        pageSliderIndicator = (PageSliderIndicator) findComponentById(ResourceTable.Id_pagesliderindicator);

        initPageSlider();
        initPageSliderIndicator();
    }

    private void initPageSliderIndicator() {
        pageSliderIndicator.setViewPager(pageSlider);

        ShapeElement normalElement = new ShapeElement();
        normalElement.setShape(ShapeElement.OVAL);
        normalElement.setBounds(new Rect(0,0,20,20));
        normalElement.setShaderType(ShapeElement.LINEAR_GRADIENT_SHADER_TYPE);
        normalElement.setRgbColor(RgbPalette.BLUE);

        ShapeElement selectElement = new ShapeElement();
        selectElement.setShape(ShapeElement.OVAL);
        selectElement.setBounds(new Rect(0,0,20,20));
        selectElement.setShaderType(ShapeElement.LINEAR_GRADIENT_SHADER_TYPE);
        selectElement.setRgbColor(RgbPalette.RED);

        pageSliderIndicator.setItemElement(normalElement,selectElement);
        pageSliderIndicator.setItemOffset(30);

    }

    private void initPageSlider() {
        LayoutScatter layoutScatter = LayoutScatter.getInstance(getContext());
        DependentLayout layoutOne = (DependentLayout) layoutScatter.parse(ResourceTable.Layout_page_one, null, false);
        DependentLayout layoutTwo = (DependentLayout) layoutScatter.parse(ResourceTable.Layout_page_two, null, false);
        DependentLayout layoutThree = (DependentLayout) layoutScatter.parse(ResourceTable.Layout_page_three, null, false);

        ArrayList<Component> pageView = new ArrayList();
        pageView.add(layoutOne);
        pageView.add(layoutTwo);
        pageView.add(layoutThree);

        pageSlider.setProvider(new PageSliderProvider() {
            @Override
            public int getCount() {
                return pageView.size();
            }

            @Override
            public Object createPageInContainer(ComponentContainer componentContainer, int i) {
                componentContainer.addComponent(pageView.get(i));
                return pageView.get(i);
            }

            @Override
            public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
                componentContainer.removeComponent(pageView.get(i));
            }

            @Override
            public boolean isPageMatchToObject(Component component, Object o) {
                return component == o;
            }
        });
        pageSlider.setCurrentPage(mCurrentPosition);
        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {

            }

            @Override
            public void onPageSlideStateChanged(int i) {

            }

            @Override
            public void onPageChosen(int i) {

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
