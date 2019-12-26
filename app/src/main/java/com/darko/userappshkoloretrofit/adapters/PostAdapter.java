package com.darko.userappshkoloretrofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darko.userappshkoloretrofit.R;
import com.darko.userappshkoloretrofit.datamodel.UserPosts;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<UserPosts> postsList;

    public PostAdapter(List<UserPosts> postsList) {
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflateView = layoutInflater.inflate(R.layout.single_card_item,parent,false);
        return new PostViewHolder(inflateView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        UserPosts currentUserPosts = postsList.get(position);

        holder.textViewUserId.setText(String.valueOf(currentUserPosts.getUserId()));
        holder.textViewPostId.setText(String.valueOf(currentUserPosts.getId()));
        holder.textViewPostTitle.setText(currentUserPosts.getTitle());
        holder.textViewPostBody.setText(currentUserPosts.getBody());

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewUserId,textViewPostId,textViewPostTitle,textViewPostBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUserId = itemView.findViewById(R.id.text_view_id);
            textViewPostId = itemView.findViewById(R.id.text_view_name);
            textViewPostTitle= itemView.findViewById(R.id.text_view_username);
            textViewPostBody = itemView.findViewById(R.id.text_view_street);
        }
    }
}