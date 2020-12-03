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

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    List<ModelSoccerTeams> items;

    public EventAdapter(FragmentActivity activity, List<ModelSoccerTeams> items) {
        this.items = items;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event, parent, false);
        return new EventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        final ModelSoccerTeams data = items.get(position);

        holder.homeScore.setText(data.getHomeGoals());
        holder.awayScore.setText(data.getAwayGoals());
        holder.homeName.setText(data.getHomeTeam());
        holder.awayName.setText(data.getAwayTeam());
        holder.tvdate.setText(data.getDate());
        holder.leagueName.setText(data.getLeague());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvdate, homeScore, awayScore, homeName, awayName, leagueName;

        public ViewHolder(View itemView) {
            super(itemView);
            homeScore = itemView.findViewById(R.id.homeScore);
            awayScore = itemView.findViewById(R.id.awayScore);
            homeName = itemView.findViewById(R.id.homeName);
            awayName = itemView.findViewById(R.id.awayTeam);
            tvdate = itemView.findViewById(R.id.evenDate);
            leagueName = itemView.findViewById(R.id.namaEvent);
        }
    }

}
