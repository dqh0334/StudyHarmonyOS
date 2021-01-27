package com.harmonyos.myapplication;

import com.harmonyos.myapplication.slice.CommonCompontsAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySliceAnimator;
import ohos.aafwk.content.Intent;

public class CommonCompontsAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(CommonCompontsAbilitySlice.class.getName());
        setAbilitySliceAnimator(null);
    }
}
