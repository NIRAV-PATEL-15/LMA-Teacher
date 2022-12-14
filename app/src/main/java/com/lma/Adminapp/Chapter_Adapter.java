package com.lma.Adminapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Chapter_Adapter extends RecyclerView.Adapter<Chapter_Adapter.ViewHolder> {
    private ArrayList<Chapter_Model> chapter_models_array;
    private Context context;
    int lastpos = -1;
    private ChapterClick chapterClick;

    public Chapter_Adapter(ArrayList<Chapter_Model> chapter_models_array, Context context, ChapterClick chapterClick) {
        this.chapter_models_array = chapter_models_array;
        this.context = context;
        this.chapterClick = chapterClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chapter_card,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        setAnimation(holder.itemView,position);
        Chapter_Model chapter_model = chapter_models_array.get(position);
        holder.title.setText(chapter_model.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chapterClick.onChapterClick(position);
            }
        });
    }
    private void setAnimation(View itemView, int position) {
        if(position > lastpos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastpos = position;
        }
    }

    @Override
    public int getItemCount() {
        return chapter_models_array.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.chp_name);
        }
    }
    public interface ChapterClick{
        void onChapterClick(int position);
    }
}
