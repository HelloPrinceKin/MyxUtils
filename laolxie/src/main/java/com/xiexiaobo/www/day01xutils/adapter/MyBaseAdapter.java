package com.xiexiaobo.www.day01xutils.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Bob on 2016/10/24.
 */
public abstract class MyBaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> mData;
    public MyBaseAdapter(List<T> data){
        mData = data;
    }
    @Override
    public int getCount() {
        return mData==null?0:mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent) ;
}
