package com.cjkj.jcb_caiyou.adapter.lottery.SSQ;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;
import com.cjkj.jcb_caiyou.util.LotteryAlgorithmUtils;

/**
 * 双色球-篮球 适配器
 * Created by 1 on 2018/1/25.
 */
public class SSQ_BuleBallAdapter extends AbsRecyclerViewAdapter {

    private String[] itemNames = new String[]{
            "01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12", "13", "14", "15", "16"
    };

    private boolean[] itemChoice = new boolean[]{
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false
    };

    public SSQ_BuleBallAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public void setItemChoice(int index) {
        if (itemChoice[index]) {
            itemChoice[index] = false;
        } else {
            itemChoice[index] = true;
        }
        notifyDataSetChanged();
    }

    /**
     * 摇一摇随机出n个篮球
     */
    public void Random() {
        for (int i = 0; i <= itemChoice.length - 1; i++) {
            itemChoice[i] = false;
        }
        int[] random = LotteryAlgorithmUtils.randomCommon(0, itemNames.length+1, 1);
        StringBuffer buffer = new StringBuffer();
        for (int index : random) {
            itemChoice[index-1] = true;
        }
        notifyDataSetChanged();
    }

    /**
     * 获取蓝球选择个数
     * @return
     */
    public int getBallCount(){
        int count =0;
        for(boolean b : itemChoice){
            if(b) count ++;
        }
        return count;
    }

    /**
     * 清空已选中的球
     */
    public void clear(){
        for (int i = 0; i <= itemChoice.length - 1; i++) {
            itemChoice[i] = false;
        }
        notifyDataSetChanged();
    }

    /**
     * 获取选中的球
     * @return
     */
    public String getBalls(){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < itemChoice.length ; i++) {
            if(itemChoice[i]){
                buffer.append(itemNames[i]+" ");
            }
        }
        return buffer.toString();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(
                LayoutInflater.from(getContext()).inflate(R.layout.item_lottery_blueball, parent, false));
    }


    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mItemText.setText(itemNames[position]);
            if (itemChoice[position]) {
                itemViewHolder.mItemText.setTextColor(getContext().getResources().getColor(R.color.white));
                itemViewHolder.mItemText.setBackground(getContext().getResources().getDrawable(R.drawable.lottery_buleball_xz));
            } else {
                itemViewHolder.mItemText.setTextColor(getContext().getResources().getColor(R.color.blue));
                itemViewHolder.mItemText.setBackground(getContext().getResources().getDrawable(R.drawable.lottery_ball_wxz));
            }
        }
        super.onBindViewHolder(holder, position);
    }


    @Override
    public int getItemCount() {
        return itemNames.length;
    }


    public class ItemViewHolder extends ClickableViewHolder {

        public TextView mItemText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemText = $(R.id.itemText);
        }
    }
}
