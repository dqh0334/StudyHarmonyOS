package com.harmonyos.myapplication.slice;

import com.harmonyos.myapplication.ResourceTable;
import com.harmonyos.myapplication.bean.SampleItem;
import com.harmonyos.myapplication.provider.SampleItemProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.agp.components.ScrollView;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class ListContainerDemoAbilitySlice extends AbilitySlice {


    private ListContainer listContainer;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_listcontainer_demo);
        ScrollView scrollview = (ScrollView) findComponentById(ResourceTable.Id_scrollview);

        initListContainer();
    }
    private void initListContainer() {
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);
        List<SampleItem> list = getData();
        SampleItemProvider sampleItemProvider = new SampleItemProvider(list,this);
        listContainer.setItemProvider(sampleItemProvider);


        listContainer.setItemClickedListener((listContainer, component, position, id) -> {
            SampleItem item = (SampleItem) listContainer.getItemProvider().getItem(position);
            new ToastDialog(getContext())
                    .setText("clicked:"+item.getName())
                    // Toast显示在界面中间
                    .setAlignment(LayoutAlignment.CENTER)
                    .show();
        });

        listContainer.setItemLongClickedListener((listContainer, component, position, id) ->
                {
                    SampleItem item = (SampleItem) listContainer.getItemProvider().getItem(position);
                    new ToastDialog(getContext())
                            .setText("long clicked:" + item.getName())
                            .setAlignment(LayoutAlignment.CENTER)
                            .show();
                    return false;
                }
        );

        //设置ListContainer的布局方向：orientation设置为“horizontal”，表示横向布局；orientation设置为“vertical”，表示纵向布局。默认为纵向布局。
        //listContainer.setOrientation(Component.HORIZONTAL);

        //设置ListContainer的开始和结束偏移量。
        //listContainer.setContentOffSet(32,16);

        //设置回弹效果
        //listContainer.setReboundEffect(true);

        //在开启回弹效果后，可以调用setReboundEffectParams()方法调整回弹效果。
        listContainer.setReboundEffectParams(40,0.6f,20);

        //设置着色器颜色。
        //listContainer.setShaderColor(new Color(Color.getIntColor("#90EE90")));
    }
    private ArrayList<SampleItem> getData() {
        ArrayList<SampleItem> list = new ArrayList<>();
        for (int i = 0; i <= 80; i++) {
            list.add(new SampleItem("Item"+i));
        }
        return list;
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
