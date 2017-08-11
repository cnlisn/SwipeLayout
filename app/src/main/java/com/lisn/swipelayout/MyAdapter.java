package com.lisn.swipelayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    private Context context;

    private SwipeLayout preLayout;//上一个SwipeLayout
    public MyAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return Cheeses.NAMES.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_swipe, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(Cheeses.NAMES[position]);
        final ViewHolder finalHolder = holder;
        holder.tv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(context,Cheeses.NAMES[position]);
                finalHolder.swipelayout.close();
            }
        });

        holder.swipelayout.setOnSwipeStatuChangeListener(new SwipeLayout.OnSwipeStatuChangeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                preLayout=  layout;
            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onSwiping(SwipeLayout layout) {

            }

            @Override
            public void onStartOpen(SwipeLayout layout) {
                //  当前打开 关闭上一个
                if (preLayout != null){
                    preLayout.close();
                }
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }
        });

        return convertView;
    }



    public class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public TextView tv_delete;
        public TextView tv_call;
        public SwipeLayout swipelayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_delete = (TextView) rootView.findViewById(R.id.tv_delete);
            this.tv_call = (TextView) rootView.findViewById(R.id.tv_call);
            this.swipelayout = (SwipeLayout) rootView.findViewById(R.id.swipelayout);
        }

    }
}
