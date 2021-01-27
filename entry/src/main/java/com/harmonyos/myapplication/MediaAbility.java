package com.harmonyos.myapplication;

import com.harmonyos.myapplication.slice.MediaAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.security.SystemPermission;

public class MediaAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        requestPermissionsFromUser(new String[]{SystemPermission.CAMERA}, 0);
        super.setMainRoute(MediaAbilitySlice.class.getName());

    }
}
