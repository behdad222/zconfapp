package ir.zconf.zconfapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.zconf.zconfapp.Entity.PictureEntity;
import ir.zconf.zconfapp.R;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PictureEntity> pictures;

    public PictureAdapter(Context context, ArrayList<PictureEntity> pictures) {
        this.context = context;
        this.pictures = pictures;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView description;

        public ViewHolder(View view) {
            super(view);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_picture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load(pictures.get(position).getUrl())
                .placeholder(R.mipmap.zconf).into(holder.image);

        holder.description.setText(pictures.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }
}
