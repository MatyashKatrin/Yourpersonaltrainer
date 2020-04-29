package com.example.user.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RekordsAdapter extends RecyclerView.Adapter<RekordsAdapter.MyViewHolder> {

    private List<Rekord> rekordsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, date, distance;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.rekordId);
            date = (TextView) view.findViewById(R.id.rekordDate);
            distance = (TextView) view.findViewById(R.id.rekordDistance);
        }
    }


    public RekordsAdapter(List<Rekord> rekordsList) {
        this.rekordsList = rekordsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rekord_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Rekord rekord = rekordsList.get(position);

        holder.id.setText(rekord.getId());
        holder.date.setText(rekord.getDate());
        holder.distance.setText(rekord.getDistance());
    }

    @Override
    public int getItemCount() {
        return rekordsList.size();
    }
}

