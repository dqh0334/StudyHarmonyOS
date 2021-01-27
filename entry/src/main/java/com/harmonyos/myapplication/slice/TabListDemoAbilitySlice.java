package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.TabList;
import ohos.agp.components.Text;

import java.util.ArrayList;
import java.util.List;

public class TabListDemoAbilitySlice extends AbilitySlice {


    private List<String> tablists = new ArrayList();
    {
        tablists.add("推荐");
        tablists.add("关注");
        tablists.add("喜欢");
        tablists.add("视频");
        tablists.add("娱乐");
        tablists.add("体育");
        tablists.add("科技");
    }

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_tablist_demo);
        TabList tabList = (TabList) findComponentById(ResourceTable.Id_tab_list);

        for (int i = 1; i < tablists.size(); i++) {
            TabList.Tab tab = tabList.new Tab(getContext());
            tab.setText(tablists.get(i));
           // tab.setMinWidth(64);
          //  tab.setPadding(12, 0, 12, 0);
//            tabList.addTab(tab, i+1); // 1表示位置
            tabList.addTab(tab);
        }
        tabList.setOrientation(Component.HORIZONTAL); //可以设置横向
       // tabList.setOrientation(Component.VERTICAL); //可以设置横向
        TabList.Tab tab = tabList.new Tab(getContext());
        tab.setText(tablists.get(0));
        // tab.setMinWidth(64);
        //  tab.setPadding(12, 0, 12, 0);
          tabList.addTab(tab, 0); // 1表示位置
        tabList.selectTabAt(0);
        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                // 当某个Tab从未选中状态变为选中状态时的回调
            }

            @Override
            public void onUnselected(TabList.Tab tab) {
                // 当某个Tab从选中状态变为未选中状态时的回调
            }

            @Override
            public void onReselected(TabList.Tab tab) {
                // 当某个Tab已处于选中状态，再次被点击时的状态回调
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
