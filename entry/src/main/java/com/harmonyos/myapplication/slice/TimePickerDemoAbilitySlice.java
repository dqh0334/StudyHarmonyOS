package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import com.harmonyos.myapplication.util.LogUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.DatePicker;
import ohos.agp.components.Text;
import ohos.agp.components.TimePicker;
import ohos.hiviewdfx.HiLog;
import ohos.nfc.tag.TagInfo;

public class TimePickerDemoAbilitySlice extends AbilitySlice {

    private static final String TAG = TimePickerDemoAbilitySlice.class.getName();

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_timepicker_demo);

        TimePicker timePicker = (TimePicker) findComponentById(ResourceTable.Id_time_picker);

        //获取时间
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        int second = timePicker.getSecond();

        //设置时间
        timePicker.setHour(19);
        timePicker.setMinute(18);
        timePicker.setSecond(12);

        timePicker.setTimeChangedListener(new TimePicker.TimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute, int second) {
                LogUtils.info(TAG,"hour :"+hour+"  minute :"+minute+"  second :"+second);
            }
        });

        /*//隐藏小时的显示
        timePicker.showHour(false);
        //隐藏分钟
        timePicker.showMinute(false);
        //设置小时selector无法滚动选择
        timePicker.enableHour(false);
        //设置分钟selector无法滚动
        timePicker.enableMinute(false);
        //设置秒selector无法滚动
        timePicker.enableSecond(false);*/
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
