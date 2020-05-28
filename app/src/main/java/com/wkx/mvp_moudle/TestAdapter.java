package com.wkx.mvp_moudle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wkx.base.TestInfo;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    Context context;
    List<TestInfo.DatasBean> dataInfos = new ArrayList<>();


    public TestAdapter(Context context) {
        this.context = context;
    }


    public void setDataInfos(List<TestInfo.DatasBean> dataInfos) {
        this.dataInfos.addAll(dataInfos);
        Log.d(TAG, "setDataInfos: " + dataInfos.size());
        notifyDataSetChanged();
    }

    private static final String TAG = "TestAdapter";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recycle_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TestInfo.DatasBean datasBean = dataInfos.get(i);

        viewHolder.textView.setText(datasBean.getTitle());
        Glide.with(context).load(datasBean.getThumbnail()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img_show);
            textView = itemView.findViewById(R.id.tv_title_show);
        }
    }
}
