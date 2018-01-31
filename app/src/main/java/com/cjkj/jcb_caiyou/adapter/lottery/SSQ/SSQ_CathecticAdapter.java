package com.cjkj.jcb_caiyou.adapter.lottery.SSQ;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cjkj.jcb_caiyou.R;
import com.cjkj.jcb_caiyou.adapter.helper.AbsRecyclerViewAdapter;
import com.cjkj.jcb_caiyou.entity.lottery.SSQ.SSQEntity;
import com.cjkj.jcb_caiyou.ui.is_native.lottery.SSQ.SSQ_CathecticActivity;
import com.sflin.csstextview.CSSTextView;
import java.util.ArrayList;
import java.util.List;

/**
 * 双色球投注适配器
 * Created by 1 on 2018/1/29.
 */
public class SSQ_CathecticAdapter extends AbsRecyclerViewAdapter {

    public static final int ITEM_TYPE_HEADER = 1;
    public static final int ITEM_TYPE_FOOTER = 2;

//    public interface OnItemDeleListener{
//        void onItemDele(int position);
//    }
//    private OnItemDeleListener mItemDeleListener;
//    public void setOnItemDeleListener(OnItemDeleListener mItemDeleListener){
//        this.mItemDeleListener = mItemDeleListener;
//    }

    private List<SSQEntity> mDatas = new ArrayList<>();

    public void setInfo(List<SSQEntity> datas) {
        this.mDatas = datas;
    }

    /**
     * 添加单条数据
     *
     * @param position
     * @param entity
     */
    public void addData(int position, SSQEntity entity) {
        mDatas.add(position, entity);
        notifyItemInserted(position);//通知演示插入动画
        notifyItemRangeChanged(position, mDatas.size() - position);//通知数据与界面重新绑定
    }

    /**
     * 添加多条数据
     *
     * @param position
     * @param List<SSQEntity>
     */
    public void addData(int position, List<SSQEntity> list) {
        mDatas.addAll(position, list);
        notifyItemInserted(position);//通知演示插入动画
        notifyItemRangeChanged(position, mDatas.size() - position);//通知数据与界面重新绑定
    }

    /**
     * 移除单条数据
     *
     * @param holder
     */
    public void removeData(ClickableViewHolder holder) {
        mDatas.remove(holder.getAdapterPosition());
        notifyItemRemoved(holder.getAdapterPosition());
        ((SSQ_CathecticActivity)getContext()).Calculation();
    }

    /**
     * 清空数据
     */
    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
        ((SSQ_CathecticActivity)getContext()).Calculation();
    }

    /**
     * 获取总注数
     *
     * @return
     */
    public int getZhuAllCount() {
        int count = 0;
        for (SSQEntity bean : mDatas) {
            count = count + bean.getZhuCount();
        }
        return count;
    }

    public List<SSQEntity> getmDatas(){
        return mDatas;
    }

    public SSQ_CathecticAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
//        if (viewType == ITEM_TYPE_HEADER) {
//            return new HeaderHolder(LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
//        } else
        if (viewType == ITEM_TYPE_FOOTER) {
            return new FootHolder(LayoutInflater.from(getContext()).inflate(R.layout.footer_ssq_cathectic, parent, false));
        } else {
            return new ItemViewHolder(
                    LayoutInflater.from(getContext()).inflate(R.layout.item_ssq_cathectic, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final ClickableViewHolder holder, int position) {
//        if (holder instanceof HeaderHolder) {
//            HeaderHolder headerHolder = (HeaderHolder) holder;
//           // headerHolder.textViewHeader.setText("");
//        } else
        if (holder instanceof FootHolder) {
            FootHolder footHolder = (FootHolder) holder;
            footHolder.layout_deleAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearData();
                }
            });
        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mItemText.setText(mDatas.get(position).getRedBall() + "  " + mDatas.get(position).getBuleBall());
            itemViewHolder.mItemText.setTextArrColor("  " + mDatas.get(position).getBuleBall(), getContext().getResources().getColor(R.color.blue));
            itemViewHolder.mItem_content.setText(mDatas.get(position).getZhuCount() + "注" + mDatas.get(position).getMoney() + "元");
            itemViewHolder.mItemImageDele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeData(holder);
                }
            });
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        //添加了Footer,固position加1，要不然会少一条数据
        return mDatas == null ? 0 : mDatas.size() + 1;
    }

    /*根据位置来返回不同的item类型*/
    @Override
    public int getItemViewType(int position) {
//        if (position == 0) {
//            return ITEM_TYPE_HEADER;
//        } else
        if (position + 1 == getItemCount()) {
            return ITEM_TYPE_FOOTER;
        } else
            return 0;
    }

    /*头部Item*/
    class HeaderHolder extends ClickableViewHolder {
        public TextView textViewHeader;

        public HeaderHolder(View itemView) {
            super(itemView);
            textViewHeader = (TextView) $(android.R.id.text1);
        }
    }

    /*底部Item*/
    class FootHolder extends ClickableViewHolder {
        public LinearLayout layout_deleAll;

        public FootHolder(View itemView) {
            super(itemView);
            layout_deleAll = (LinearLayout) $(R.id.layout_deleAll);
        }
    }

    public class ItemViewHolder extends ClickableViewHolder {

        public CSSTextView mItemText;
        public ImageView mItemImageDele;
        public TextView mItem_content;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemText = $(R.id.mItemText);
            mItemImageDele = $(R.id.item_dele);
            mItem_content = $(R.id.item_content);
        }
    }
}
