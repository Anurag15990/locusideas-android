package com.android.locusideas.home.projects.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.locusideas.home.projects.models.ProjectMedia;
import com.bumptech.glide.Glide;
import com.locusideas.locusideas.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 07/07/16.
 */

public class ProjectMediaAdapter extends RecyclerView.Adapter<ProjectMediaAdapter.ViewHolder> {

    List<ProjectMedia> projectMedias = new ArrayList<>();
    Context context;

    @Override
    public ProjectMediaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View projectMedia = inflater.inflate(R.layout.view_project_media, parent, false);
        return new ViewHolder(projectMedia);
    }

    @Override
    public void onBindViewHolder(ProjectMediaAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(projectMedias.get(position).getMedia().getUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return projectMedias.size();
    }

    public void addProjectMedia(List<ProjectMedia> projectMedias){
        this.projectMedias = projectMedias;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.media_image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
