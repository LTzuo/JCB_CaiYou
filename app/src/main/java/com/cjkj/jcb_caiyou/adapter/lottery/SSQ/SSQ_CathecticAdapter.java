package com.cjkj.jcb_caiyou.adapter.lottery.SSQ;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;
import com.cjkj.jcb_caiyou.entity.lottery.SSQ.SSQEntity;
import com.sflin.csstextview.CSSTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 双色球投注适配器
 * Created by 1 on 2018/1/29.
 */
public class SSQ_CathecticAdapter extends AbsRecyclerViewAdapter {

    private List<SSQEntity> mDatas = new ArrayList<>();

    public void setInfo(List<SSQEntity> datas) {
        this.mDatas = datas;
    }

    public SSQ_CathecticAdapter(RecyclerView recyclerView){
        super(recyclerView);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(
                LayoutInflater.from(getContext()).inflate(R.layout.item_ssq_cathectic, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mItemText.setText(mDatas.get(position).getRedBall()+" "+mDatas.get(position).getBuleBall());
            itemViewHolder.mItemText.setTextArrColor(" "+mDatas.get(position).getBuleBall(),getContext().getResources().getColor(R.color.blue));
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0:mDatas.size();
    }

    public class ItemViewHolder extends ClickableViewHolder {

        public CSSTextView mItemText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemText = $(R.id.mItemText);
        }
    }
}
