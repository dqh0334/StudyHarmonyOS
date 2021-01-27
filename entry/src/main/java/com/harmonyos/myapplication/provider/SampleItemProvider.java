package com.harmonyos.myapplication.provider;

import com.harmonyos.myapplication.ResourceTable;
import com.harmonyos.myapplication.bean.SampleItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;
import java.util.List;
public class SampleItemProvider extends RecycleItemProvider {
    private List<SampleItem> list;
    private AbilitySlice slice;
    public SampleItemProvider(List<SampleItem> list, AbilitySlice slice) {
        this.list = list;
        this.slice = slice;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Component getComponent(int position, Component convertComponent, ComponentContainer componentContainer) {
        Component cpt = convertComponent;
        if (cpt == null) {
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_item_sample, null, false);
        }
        SampleItem sampleItem = list.get(position);
        Text text = (Text) cpt.findComponentById(ResourceTable.Id_item_index);
        text.setText(sampleItem.getName());
        return cpt;
    }
}
