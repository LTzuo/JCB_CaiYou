package com.cjkj.jcb_caiyou.adapter.lottery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;

/**
 * Created by 1 on 2018/1/24.
 */
public class LotteryAdapter extends  AbsRecyclerViewAdapter {
    private String[] itemNames = new String[]{
            "双色球", "3D", "七乐彩",
            "大乐透", "排列三", "排列五",
            "七星彩", "竞彩足球", "更多彩种",

    };

    private int[] itemIcons = new int[]{
            R.drawable.icon_ssq, R.drawable.icon_3d,
            R.drawable.icon_7lc, R.drawable.icon_dlt,
            R.drawable.icon_pl3, R.drawable.icon_pl5,
            R.drawable.icon_7xc,R.drawable.icon_zqjc,
            R.drawable.icon_morelottery
    };


    public LotteryAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }


    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(
                LayoutInflater.from(getContext()).inflate(R.layout.item_home_lottery, parent, false));
    }


    @Override
    public void onBindViewHolder(AbsRecyclerViewAdapter.ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mItemIcon.setImageResource(itemIcons[position]);
            itemViewHolder.mItemText.setText(itemNames[position]);
        }
        super.onBindViewHolder(holder, position);
    }


    @Override
    public int getItemCount() {
        return itemIcons.length;
    }


    private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

        ImageView mItemIcon;
        TextView mItemText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemIcon = $(R.id.item_icon);
            mItemText = $(R.id.item_title);
        }
    }
}