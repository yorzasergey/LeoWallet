package com.xtreme.leowallet.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xtreme.leowallet.R;
import com.xtreme.leowallet.model.DashboardItem;
import com.xtreme.leowallet.model.HeaderItem;
import com.xtreme.leowallet.model.ListItem;

import java.util.List;

public class AccountInfoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ListItem> mListDashboard;
    private View.OnClickListener mOnClickListener;

    public AccountInfoRecyclerViewAdapter(List<ListItem> listDashboard, View.OnClickListener onClickListener, Context context) {
        this.mListDashboard = listDashboard;
        this.mOnClickListener = onClickListener;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ListItem.TYPE_HEADER) {
            return new HeaderRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_header, parent, false));
        }else if(viewType == ListItem.TYPE_WITHOUT_NAME){
            return new EventWithoutServiceRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_without_name, parent, false));
        }else {
            return new EventWithServiceRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {

        int type = getItemViewType(position);

        if (type == ListItem.TYPE_HEADER) {
            HeaderItem header = (HeaderItem) mListDashboard.get(position);
            HeaderRecyclerViewHolder holder = (HeaderRecyclerViewHolder) viewHolder;
            holder.mTextHeader.setText(header.getHeaderName());
            holder.itemView.setTag(ListItem.TYPE_HEADER);
        }else if(type == ListItem.TYPE_WITH_NAME){

            EventWithServiceRecyclerViewHolder holder = (EventWithServiceRecyclerViewHolder) viewHolder;

            DashboardItem dashboardItem = (DashboardItem) mListDashboard.get(position);

            Resources resources = mContext.getResources();
            final int resourceId = resources.getIdentifier(dashboardItem.getItemImageFileName(), "drawable", mContext.getPackageName());
            resources.getDrawable(resourceId);

            holder.mPrevIcon.setImageDrawable(resources.getDrawable(resourceId));
            holder.mPrevIcon.setTag(dashboardItem.getItemService());
            holder.mTextName.setText(dashboardItem.getItemName());
            holder.mTextAccount.setText(dashboardItem.getItemAccount());

            if(dashboardItem.isFavorite()){
                holder.mFavoriteIcon.setImageResource(R.drawable.star_active);
            }

            holder.mPrevIcon.setOnClickListener(mOnClickListener);
            holder.mFavoriteIcon.setOnClickListener(mOnClickListener);

        }else{

            EventWithoutServiceRecyclerViewHolder holder = (EventWithoutServiceRecyclerViewHolder) viewHolder;

            DashboardItem dashboardItem = (DashboardItem) mListDashboard.get(position);

            Resources resources = mContext.getResources();
            final int resourceId = resources.getIdentifier(dashboardItem.getItemImageFileName(), "drawable", mContext.getPackageName());
            resources.getDrawable(resourceId);

            holder.mPrevIcon.setImageDrawable(resources.getDrawable(resourceId));
            holder.mPrevIcon.setTag(dashboardItem.getItemService());
            holder.mTextAccount.setText(dashboardItem.getItemAccount());

            if(dashboardItem.isFavorite()){
                holder.mFavoriteIcon.setImageResource(R.drawable.star_active);
            }

            holder.mPrevIcon.setOnClickListener(mOnClickListener);
            holder.mFavoriteIcon.setOnClickListener(mOnClickListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mListDashboard.get(position).getListItemType();
    }

    @Override
    public int getItemCount() {
        return mListDashboard.size();
    }

    public void addItems(List<ListItem> listDashboard) {
        this.mListDashboard = listDashboard;
        notifyDataSetChanged();
    }

    static class EventWithoutServiceRecyclerViewHolder extends RecyclerView.ViewHolder {

        public ImageView mPrevIcon;
        public TextView mTextAccount;
        public ImageView mFavoriteIcon;

        EventWithoutServiceRecyclerViewHolder(View view) {
            super(view);

            mPrevIcon = (ImageView) itemView.findViewById(R.id.prevIcon);
            mTextAccount = (TextView) itemView.findViewById(R.id.textAccount);
            mFavoriteIcon = (ImageView) itemView.findViewById(R.id.favoriteIcon);
        }
    }

    static class EventWithServiceRecyclerViewHolder extends RecyclerView.ViewHolder {

        public ImageView mPrevIcon;
        public TextView mTextName;
        public TextView mTextAccount;
        public ImageView mFavoriteIcon;

        EventWithServiceRecyclerViewHolder(View view) {
            super(view);

            mPrevIcon = (ImageView) itemView.findViewById(R.id.prevIcon);
            mTextName = (TextView) itemView.findViewById(R.id.textName);
            mTextAccount = (TextView) itemView.findViewById(R.id.textAccount);
            mFavoriteIcon = (ImageView) itemView.findViewById(R.id.favoriteIcon);
        }
    }

    static class HeaderRecyclerViewHolder extends RecyclerView.ViewHolder {

        long _id;
        public TextView mTextHeader;

        HeaderRecyclerViewHolder(View view) {
            super(view);

            mTextHeader = (TextView) itemView.findViewById(R.id.textHeader);
        }
    }
}
