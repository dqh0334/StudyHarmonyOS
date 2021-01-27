package com.harmonyos.myapplication;

import com.harmonyos.myapplication.slice.AnimationAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class AnimationAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(AnimationAbilitySlice.class.getName());
    }
}
