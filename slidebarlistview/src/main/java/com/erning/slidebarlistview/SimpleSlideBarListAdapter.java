package com.erning.slidebarlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * 别的我不管，里面只能放String
 * Created by 二宁 on 17-11-26.
 */
public class SimpleSlideBarListAdapter extends BaseAdapter {
    protected Context context;
    protected List<String> dataList;
    public SimpleSlideBarListAdapter(Context context, List<String> dataList){
        this.context = context;
        this.dataList = dataList;
        Collections.sort(this.dataList, new StringComparator());
    }
    @Override
    public int getCount() {
        return dataList.size();
    }
    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if (view == null){
            v = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,viewGroup,false);
        }else{
            v = view;
        }

        TextView txt = v.findViewById(android.R.id.text1);
        txt.setText(dataList.get(i));

        return v;
    }
    public void scroll(SlideBarListView view,char position){
        if (position == '#')
            view.setSelection(0);
        for(int i=0;i<dataList.size();i++){
            try {
                if (PinyinDemo.ToFirstChar(dataList.get(i).substring(0,1)).toUpperCase().toCharArray()[0] == position){
                    view.setSelection(i);
                    return;
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
    }
}