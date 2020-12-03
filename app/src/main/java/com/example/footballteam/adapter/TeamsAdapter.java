package com.example.footballteam.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.footballteam.R;
import com.example.footballteam.model.ModelSoccerTeams;

import java.util.List;


public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

     List<ModelSoccerTeams> items;
     TeamsAdapter.onSelectData onSelectData;
     Context mContext;

    public interface onSelectData {
        void onSelected(ModelSoccerTeams modelMovie);
    }

    public TeamsAdapter(Context context, List<ModelSoccerTeams> items, TeamsAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_film, parent, false);
        return new TeamsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamsAdapter.ViewHolder holder, int position) {
        final ModelSoccerTeams data = items.get(position);

        holder.tvTitle.setText(data.getTeamName());
        holder.tvSubTitle.setText(data.getSubName());
        holder.tvRealeseDate.setText(data.getYears());
        holder.tvCounrty.setText(data.getCountry()+", ");
        holder.tvDesc.setText(data.getDescription());

        holder.imgYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://"+data.getYoutube());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
        holder.imgWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+data.getWebsite()));
                mContext.startActivity(intent);
            }
        });
        holder.imgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://"+data.getTwitter());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
        holder.imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://"+data.getInstagram());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
        holder.imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://"+data.getFacebook());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });


        Glide.with(mContext)
                .load(data.getLogoTeams())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image)
                        .transform(new RoundedCorners(16)))
                .into(holder.imgPhoto);

        holder.cvFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvFilm;
        public ImageView imgPhoto ,imgYoutube, imgTwitter, imgWebsite, imgInstagram, imgFacebook;
        public TextView tvTitle, tvSubTitle;
        public TextView tvRealeseDate, tvCounrty;
        public TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            cvFilm = itemView.findViewById(R.id.cvFilm);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
            tvRealeseDate = itemView.findViewById(R.id.tvRealeseDate);
            tvCounrty = itemView.findViewById(R.id.tvCountry);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            imgFacebook = itemView.findViewById(R.id.imgFacebook);
            imgInstagram = itemView.findViewById(R.id.imgInstagram);
            imgTwitter = itemView.findViewById(R.id.imgTwitter);
            imgWebsite = itemView.findViewById(R.id.imgWebsite);
            imgYoutube = itemView.findViewById(R.id.imgYoutube);
        }
    }

}
