package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.ComponentState;
import ohos.agp.components.RadioButton;
import ohos.agp.components.RadioContainer;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.StateElement;

public class RadioContainerDemoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_radiocontainer_demo);

        RadioContainer radioContainer = (RadioContainer) findComponentById(ResourceTable.Id_radio_container);
        for (int i = 0; i < 4; i++){
            ((RadioButton) radioContainer.getComponentAt(i)).setButtonElement(createStateElement());
        }

        //根据ID设置指定RadioButton为选定状态。
        //radioContainer.mark(ResourceTable.Id_radio_button_1);

        //清除RadioContainer中所有RadioButton的选定状态。
        //radioContainer.cancelMarks();

        Text answer = (Text) findComponentById(ResourceTable.Id_text_checked);
        radioContainer.setMarkChangedListener((radioContainer1, index) -> {
            switch (index) {
                case 0:
                    answer.setText("[A]");
                    break;
                case 1:
                    answer.setText("[B]");
                    break;
                case 2:
                    answer.setText("[C]");
                    break;
                case 3:
                    answer.setText("[D]");
                    break;
            }
        });
    }

    private StateElement createStateElement() {
        ShapeElement elementButtonOn = new ShapeElement();
        elementButtonOn.setRgbColor(RgbPalette.RED);
        elementButtonOn.setShape(ShapeElement.OVAL);

        ShapeElement elementButtonOff = new ShapeElement();
        elementButtonOff.setRgbColor(RgbPalette.WHITE);
        elementButtonOff.setShape(ShapeElement.OVAL);

        StateElement checkElement = new StateElement();
        checkElement.addState(new int[]{ComponentState.COMPONENT_STATE_CHECKED}, elementButtonOn);
        checkElement.addState(new int[]{ComponentState.COMPONENT_STATE_EMPTY}, elementButtonOff);
        return checkElement;
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
