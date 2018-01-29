package com.cjkj.jcb_caiyou.adapter.indicatordialog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cjkj.jcb_caiyou.adapter.indicatordialog.IndicatorDialogViewHolder;

/**
 * Created by 1 on 2018/1/26.
 * 底部指示器弹出列表单选对话框适配器
 */
public abstract  class IndicatorDialogAdapter extends RecyclerView.Adapter<IndicatorDialogViewHolder> {


    @Override
    public IndicatorDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndicatorDialogViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(final IndicatorDialogViewHolder holder, final int position) {


        if (clickable()) {
            holder.getConvertView().setClickable(true);
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(v, position);
                }
            });
        }

        onBindView(holder, holder.getLayoutPosition());

    }

    public abstract void onBindView(IndicatorDialogViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {
        return getLayoutID(position);
    }


    public abstract int getLayoutID(int position);

    public abstract boolean clickable();

    public void onItemClick(View v, int position) {
    }



}