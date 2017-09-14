package br.com.andresguedes.testing.presentation.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.andresguedes.testing.R;

/**
 * Created by andreguedes on 13/09/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    final TextView textViewBio;
    final TextView textViewName;
    final ImageView imageViewAvatar;

    public UserViewHolder(View itemView) {
        super(itemView);

        imageViewAvatar = itemView.findViewById(R.id.imageview_userprofilepic);
        textViewName = itemView.findViewById(R.id.textview_username);
        textViewBio = itemView.findViewById(R.id.textview_user_profile_info);
    }

}