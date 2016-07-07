package com.android.locusideas.home.projects;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.locusideas.core.ui.widgets.TextViewPlus;
import com.android.locusideas.home.projects.models.Project;
import com.bumptech.glide.Glide;
import com.locusideas.locusideas.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 19/06/16.
 */

class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder>{

    private ProjectsAdapterCallbacks projectsAdapterCallbacks;
    private List<Project> projects = new ArrayList<>();
    private Context appContext;

    ProjectsAdapter(Context appContext){
        this.appContext = appContext;
    }

    public void setProjectsAdapterCallbacks(ProjectsAdapterCallbacks projectsAdapterCallbacks) {
        this.projectsAdapterCallbacks = projectsAdapterCallbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View projectView = inflater.inflate(R.layout.view_project, parent, false);
        return new ViewHolder(projectView, projectsAdapterCallbacks);
    }

    @Override
    public void onBindViewHolder(ViewHolder projectViewHolder, int position) {
        Project project = projects.get(position);

        updateAvatar(projectViewHolder.avatar,project.getOwner().getPicture().getUrl());
        //updateViewBasedOnLikeStatus(projectViewHolder.actionLike, project.isLiked());
        updateItemImage(projectViewHolder.image, project.getMedias().getInitial().get(0).getMedia().getUrl());
        updateTitle(projectViewHolder.title, project.getTitle());
        //updateSubTitle(projectViewHolder.subTitle, project.getSubTitle());
    }

    private void updateSubTitle(TextViewPlus subTitle, String subTitleString) {
        subTitle.setText(subTitleString);
    }

    private void updateTitle(TextViewPlus title, String titleString) {
        title.setText(titleString);
    }

    private void updateItemImage(ImageView image, String imageRes) {
        Glide.with(appContext).load(imageRes).into(image);
    }

    private void updateAvatar(ImageView avatarView, String avatar){
        Glide.with(appContext).load(avatar).into(avatarView);
    }

    private void updateViewBasedOnLikeStatus(ImageView actionLikeView, boolean likeStatus){
        int resId = likeStatus ? R.drawable.ic_item_liked_24dp : R.drawable.ic_item_not_liked_24dp;
        actionLikeView.setImageDrawable(ResourcesCompat.getDrawable(appContext.getResources(), resId, null));
    }

    public Project getItemAt(int position){
        return projects.get(position);
    }

    /**
     * Call to reflect changes made to item
     * @param project
     */
    void updateView(Project project){
        int position = projects.indexOf(project);
        projects.remove(position);
        projects.add(position, project);
        notifyItemChanged(position);
    }

    void initializeWithItems(List<Project> projects){
        this.projects = projects;
        notifyDataSetChanged();
    }

    /**
     *
     * @param projects
     * @param position
     */
    void insertItems(List<Project> projects, int position){
        this.projects.addAll(position, projects);
        notifyItemRangeInserted(position, projects.size());
    }

    @Override
    public int getItemCount() {
        if (projects.size() == 0){
            projectsAdapterCallbacks.onAdapterEmpty();
        }
        return projects.size();
    }

    public void onDestroy(){
        projectsAdapterCallbacks.onDestroy();
        projectsAdapterCallbacks = null;
        appContext = null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ActionCallbacks actionCallbacks;
        @BindView(R.id.project_title_avatar)
        ImageView avatar;

        @BindView(R.id.project_image)
        ImageView image;

        @BindView(R.id.project_title)
        TextViewPlus title;

        @BindView(R.id.project_subtitle)
        TextViewPlus subTitle;

        @BindView(R.id.project_action_like)
        ImageView actionLike;

        ViewHolder(View itemView, ActionCallbacks actionCallbacks) {
            super(itemView);
            this.actionCallbacks = actionCallbacks;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.project_action_like)
        public void onClickActionLike(){
            actionCallbacks.onActionLikeClick(getAdapterPosition());
        }

        @Override
        public void onClick(View v) {
            actionCallbacks.onItemClick(getAdapterPosition());
        }

    }

    interface ProjectsAdapterCallbacks extends ActionCallbacks{
        void onAdapterEmpty();
        void onDestroy();
    }

    interface ActionCallbacks{
        void onItemClick(int itemPosition);
        void onActionLikeClick(int itemPosition);
    }

}
