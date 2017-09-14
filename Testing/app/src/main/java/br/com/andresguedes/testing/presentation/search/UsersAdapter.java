package br.com.andresguedes.testing.presentation.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.andresguedes.testing.R;
import br.com.andresguedes.testing.data.remote.model.User;

/**
 * Created by andreguedes on 13/09/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final Context context;
    private List<User> items;

    public UsersAdapter(Context context, List<User> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = items.get(position);
        holder.textViewBio.setText(user.getBio());
        if (user.getName() != null) {
            holder.textViewName.setText(user.getLogin() + " - " + user.getName());
        } else {
            holder.textViewName.setText(user.getLogin());
        }
        Picasso.with(context).load(user.getAvatarUrl()).into(holder.imageViewAvatar);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    void setItems(List<User> githubUserList) {
        this.items = githubUserList;
        notifyDataSetChanged();
    }

}