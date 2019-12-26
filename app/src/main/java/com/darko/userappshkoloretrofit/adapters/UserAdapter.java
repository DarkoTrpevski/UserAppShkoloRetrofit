package com.darko.userappshkoloretrofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darko.userappshkoloretrofit.R;
import com.darko.userappshkoloretrofit.datamodel.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userArrayList) {
        this.userList = userArrayList;
    }

    private Listener listener;
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener {
        void onUserClick(int position);
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflateView = layoutInflater.inflate(R.layout.single_card_item, parent, false);
        return new UserViewHolder(inflateView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = userList.get(position);

        holder.textViewId.setText(String.valueOf(currentUser.getId()));
        holder.textViewName.setText(currentUser.getName());
        holder.textViewUserName.setText(currentUser.getUsername());

        holder.textViewStreet.setText(currentUser.getAddress().getStreet());
        holder.textViewCity.setText(currentUser.getAddress().getCity());
        holder.textViewZipcode.setText(currentUser.getAddress().getZipcode());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewId, textViewName, textViewUserName, textViewStreet, textViewCity, textViewZipcode;

        public UserViewHolder(@NonNull View itemView, final Listener listener) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.text_view_id);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewUserName = itemView.findViewById(R.id.text_view_username);

            textViewStreet = itemView.findViewById(R.id.text_view_street);
            textViewCity = itemView.findViewById(R.id.text_view_city);
            textViewZipcode = itemView.findViewById(R.id.text_view_zipcode);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onUserClick(position);
                        }
                    }
                }
            });
        }
    }
}
