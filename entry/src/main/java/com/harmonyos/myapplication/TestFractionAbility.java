package com.harmonyos.myapplication;

import com.harmonyos.myapplication.slice.AnimationAbilitySlice;
import com.harmonyos.myapplication.slice.FractionDemoAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;

public class TestFractionAbility extends FractionAbility {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(FractionDemoAbilitySlice.class.getName());

    }
}
