package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import com.harmonyos.myapplication.fraction.TestFraction;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.ability.fraction.FractionManager;
import ohos.aafwk.ability.fraction.FractionScheduler;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Rect;
import ohos.app.Context;

import java.util.ArrayList;

public class FractionDemoAbilitySlice extends AbilitySlice {

    private int mCurrentPosition = -1;
    private Context context;
    private StackLayout fractionLayout;
    private TestFraction fractionOne;
    private TestFraction fractionTwo;
    private TestFraction fractionThree;
    private TestFraction fractionFour;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_fraction_demo);
        context = this;

        fractionLayout = (StackLayout) findComponentById(ResourceTable.Id_fractionlayout);
        RadioContainer radioContainer = (RadioContainer) findComponentById(ResourceTable.Id_rc_fraction);

        radioContainer.mark(0);
        selectFraction(0);

        radioContainer.setMarkChangedListener(new RadioContainer.CheckedStateChangedListener() {
            @Override
            public void onCheckedChanged(RadioContainer radioContainer, int index) {
                selectFraction(index);
            }
        });

        RadioButton checkboxOne = (RadioButton) findComponentById(ResourceTable.Id_rd_fraction_1);
        RadioButton checkboxTwo = (RadioButton) findComponentById(ResourceTable.Id_rd_fraction_2);
        RadioButton checkboxThree = (RadioButton) findComponentById(ResourceTable.Id_rd_fraction_3);
        RadioButton checkboxFour = (RadioButton) findComponentById(ResourceTable.Id_rd_fraction_4);
        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setShape(ShapeElement.OVAL);
        shapeElement.setBounds(new Rect(0,0,0,0));
        checkboxOne.setButtonElement(shapeElement);
        checkboxTwo.setButtonElement(shapeElement);
        checkboxThree.setButtonElement(shapeElement);
        checkboxFour.setButtonElement(shapeElement);
    }

    private void selectFraction(int index) {
        if (index == mCurrentPosition) {
            return;
        }

        FractionScheduler fractionScheduler = getFractionManager().startFractionScheduler();
        hideAllFraction(fractionScheduler);

        switch (index) {
            case 0:
                if (fractionOne == null) {
                    fractionOne = new TestFraction(context, 0);
                    fractionScheduler.add(fractionLayout.getId(), fractionOne);
                } else {
                    fractionScheduler.show(fractionOne);
                }
                break;
            case 1:
                if (fractionTwo == null) {
                    fractionTwo = new TestFraction(context, 1);
                    fractionScheduler.add(fractionLayout.getId(), fractionTwo);
                } else {
                    fractionScheduler.show(fractionTwo);
                }
                break;
            case 2:
                if (fractionThree == null) {
                    fractionThree = new TestFraction(context, 2);
                    fractionScheduler.add(fractionLayout.getId(), fractionThree);
                } else {
                    fractionScheduler.show(fractionThree);
                }
                break;
            case 3:
                if (fractionFour == null) {
                    fractionFour = new TestFraction(context, 3);
                    fractionScheduler.add(fractionLayout.getId(), fractionFour);
                } else {
                    fractionScheduler.show(fractionFour);
                }
                break;
        }
        fractionScheduler.submit();
    }

    private void hideAllFraction(FractionScheduler fractionScheduler) {
        if (fractionOne != null) {
            fractionScheduler.hide(fractionOne);
        }
        if (fractionTwo != null) {
            fractionScheduler.hide(fractionTwo);
        }
        if (fractionThree != null) {
            fractionScheduler.hide(fractionThree);
        }
        if (fractionFour != null) {
            fractionScheduler.hide(fractionFour);
        }
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private FractionManager getFractionManager() {
        Ability ability = getAbility();
        if (ability instanceof FractionAbility) {
            FractionAbility fractionAbility = (FractionAbility) ability;
            return fractionAbility.getFractionManager();
        }
        return null;
    }
}
