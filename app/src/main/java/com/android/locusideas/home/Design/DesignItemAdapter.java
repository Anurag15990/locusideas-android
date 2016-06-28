package com.android.locusideas.home.design;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.locusideas.core.ui.widgets.TextViewPlus;
import com.android.locusideas.home.design.models.DesignItem;
import com.locusideas.locusideas.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 19/06/16.
 */

class DesignItemAdapter extends RecyclerView.Adapter<DesignItemAdapter.ViewHolder>{

    private final DesignItemsAdapterCallbacks designItemsAdapterCallbacks;
    private List<DesignItem> designItems = new ArrayList<>();

    private Context appContext;

    DesignItemAdapter(Context appContext, DesignItemsAdapterCallbacks designItemsAdapterCallbacks){
        this.appContext = appContext;
        this.designItemsAdapterCallbacks = designItemsAdapterCallbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View designItemView = inflater.inflate(R.layout.view_design_item, parent, false);
        return new ViewHolder(designItemView, designItemsAdapterCallbacks);
    }

    @Override
    public void onBindViewHolder(ViewHolder designItemHolder, int position) {
        DesignItem designItem = designItems.get(position);

        updateAvatar(designItemHolder.avatar,designItem.getAvatar());
        updateViewBasedOnLikeStatus(designItemHolder.actionLike, designItem.isLiked());
        updateItemImage(designItemHolder.image, designItem.getImage());
        updateTitle(designItemHolder.title, designItem.getTitle());
        updateSubTitle(designItemHolder.subTitle, designItem.getSubTitle());
    }

    private void updateSubTitle(TextViewPlus subTitle, String subTitleString) {
        subTitle.setText(subTitleString);
    }

    private void updateTitle(TextViewPlus title, String titleString) {
        title.setText(titleString);
    }

    private void updateItemImage(ImageView image, String imageRes) {

    }

    private void updateAvatar(ImageView avatarView, String avatar){

    }

    private void updateViewBasedOnLikeStatus(ImageView actionLikeView, boolean likeStatus){
        int resId = likeStatus ? R.drawable.ic_item_liked_24dp : R.drawable.ic_item_not_liked_24dp;
        actionLikeView.setImageDrawable(ResourcesCompat.getDrawable(appContext.getResources(), resId, null));
    }

    public DesignItem getItemAt(int position){
        return designItems.get(position);
    }

    /**
     * Call to reflect changes made to item
     * @param designItem
     */
    void updateView(DesignItem designItem){
        int position = designItems.indexOf(designItem);
        designItems.remove(position);
        designItems.add(position, designItem);
        notifyItemChanged(position);
    }

    void initializeWithItems(List<DesignItem> designItems){
        this.designItems = designItems;
        notifyDataSetChanged();
    }

    /**
     *
     * @param designItems
     * @param position
     */
    void insertItems(List<DesignItem> designItems, int position){
        this.designItems.addAll(position, designItems);
        notifyItemRangeInserted(position, designItems.size());
    }

    @Override
    public int getItemCount() {
        if (designItems.size() == 0){
            designItemsAdapterCallbacks.onAdapterEmpty();
        }
        return designItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ActionCallbacks actionCallbacks;
        @BindView(R.id.design_item_title_avatar)
        ImageView avatar;

        @BindView(R.id.design_item_image)
        ImageView image;

        @BindView(R.id.design_item_title)
        TextViewPlus title;

        @BindView(R.id.design_item_subtitle)
        TextViewPlus subTitle;

        @BindView(R.id.design_item_action_like)
        ImageView actionLike;

        ViewHolder(View itemView, ActionCallbacks actionCallbacks) {
            super(itemView);
            this.actionCallbacks = actionCallbacks;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.design_item_action_like)
        public void onClickActionLike(){
            actionCallbacks.onActionLikeClick(getAdapterPosition());
        }

        @Override
        public void onClick(View v) {
            actionCallbacks.onItemClick(getAdapterPosition());
        }

    }

    interface DesignItemsAdapterCallbacks extends ActionCallbacks{
        void onAdapterEmpty();
    }

    interface ActionCallbacks{
        void onItemClick(int itemPosition);
        void onActionLikeClick(int itemPosition);
    }

}
