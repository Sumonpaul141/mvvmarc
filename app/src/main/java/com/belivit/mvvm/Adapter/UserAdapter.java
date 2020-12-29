package com.belivit.mvvm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belivit.mvvm.Model.User;
import com.belivit.mvvm.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;
    private Context ctx;

    public UserAdapter(List<User> users, Context ctx) {
        this.users = users;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.phone.setText(user.getPhone());
        holder.address.setText(user.getAddress().getCity());
        holder.company.setText(user.getCompany().getName());
        holder.email.setText(user.getEmail());
        holder.name.setText(user.getName());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, phone, company, address, email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userImage);
            name = itemView.findViewById(R.id.userNameTv);
            phone = itemView.findViewById(R.id.userPhoneTv);
            company = itemView.findViewById(R.id.userCompanyTv);
            address = itemView.findViewById(R.id.userAddressTv);
            email = itemView.findViewById(R.id.userEmailTv);
        }
    }
}
