package com.xiexiaobo.www.day01xutils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiexiaobo.www.day01xutils.R;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by Bob on 2016/10/24.
 */
public class MyAdapter extends MyBaseAdapter<String> {
    private static String TAG = MyAdapter.class.getSimpleName();
    private List<String> mData;
    private LayoutInflater mInflater;
    public MyAdapter(List<String> data, Context context) {
        super(data);
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
           if (convertView==null){
               convertView =  mInflater.inflate(R.layout.item_test,null);
               holder = new ViewHolder(convertView);
               //x.view().inject(holder,convertView);
               convertView.setTag(holder);
           }else {
               holder = (ViewHolder) convertView.getTag();
           }
        // 赋值
        holder.mTextView.setText(mData.get(position));

        return convertView;
    }
    public static class ViewHolder{
        @ViewInject(R.id.tv_item_test)
        private TextView mTextView;
        public ViewHolder (View itemView){
            mTextView = (TextView) itemView.findViewById(R.id.tv_item_test);
        }
    }
}
