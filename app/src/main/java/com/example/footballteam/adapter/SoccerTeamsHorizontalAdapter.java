package com.example.footballteam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballteam.R;
import com.example.footballteam.model.ModelSoccerTeams;

import java.util.List;

public class SoccerTeamsHorizontalAdapter extends RecyclerView.Adapter<SoccerTeamsHorizontalAdapter.ViewHolder> {

    List<ModelSoccerTeams> items;

    public interface onSelectData {
    }

    public SoccerTeamsHorizontalAdapter(FragmentActivity activity, List<ModelSoccerTeams> items, onSelectData xSelectData) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_film_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelSoccerTeams data = items.get(position);

        holder.homeName.setText(data.getHomeTeam());
        holder.awayName.setText(data.getAwayTeam());

        String time = data.getTime();
        String date = data.getDate();

        if (isNullOrEmpty(time) || time == "null") {
            holder.tvTime.setText("Upcoming...");

        } else {
            holder.tvTime.setText(time);

        }
        if (isNullOrEmpty(date) || date == "null") {
            holder.tvdate.setText("Upcoming...");

        } else {
            holder.tvdate.setText(date);

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvdate;
        TextView homeName;
        TextView awayName;
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);

            homeName = itemView.findViewById(R.id.home_name);
            awayName = itemView.findViewById(R.id.away_name);
            tvdate = itemView.findViewById(R.id.tvdate);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return false;
        return true;
    }

}
