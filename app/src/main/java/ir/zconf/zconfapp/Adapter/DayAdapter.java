package ir.zconf.zconfapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

import ir.zconf.zconfapp.Entity.SubjectEntity;
import ir.zconf.zconfapp.R;

public class DayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int ITEM_VIEW_TYPE_BREAK = 0;
    private static final int ITEM_VIEW_TYPE_SPEAK = 1;

    private Context context;
    private ArrayList<SubjectEntity> subjects;

    public DayAdapter(Context context, ArrayList<SubjectEntity> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    public boolean isBreak(int position) {
        if (subjects.get(position).getType().equals("break")) {
            return true;
        } else {
            return false;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView speaker;
        public TextView time;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) itemView.findViewById(R.id.title);
            speaker = (TextView) itemView.findViewById(R.id.speaker);
            time = (TextView) itemView.findViewById(R.id.time);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public class ViewHolderBreak extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;
        ImageView image;

        public ViewHolderBreak(View v) {
            super(v);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_BREAK:
                View viewBreak = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_break, parent, false);
                return new ViewHolderBreak(viewBreak);

            case ITEM_VIEW_TYPE_SPEAK:
                View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_subject, parent, false);
                return new ViewHolder(view);

            default:
                View view1 = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_subject, parent, false);
                return new ViewHolder(view1);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isBreak(position)) {
            ViewHolderBreak viewHolderBreak = (ViewHolderBreak) holder;

            Picasso.with(context)
                    .load(subjects.get(position).getImage())
                    .placeholder(R.mipmap.zconf)
                    .into(viewHolderBreak.image);

            viewHolderBreak.title.setText(subjects.get(position).getTitle());
            viewHolderBreak.time.setText(subjects.get(position).getTime());

        } else {
            ViewHolder viewHolder = (ViewHolder) holder;
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.GRAY)
                    .borderWidthDp(1)
                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();

            Picasso.with(context)
                    .load(subjects.get(position).getImage())
                    .fit()
                    .transform(transformation)
                    .placeholder(R.mipmap.zconf)
                    .into(viewHolder.image);

            viewHolder.title.setText(subjects.get(position).getTitle());
            viewHolder.speaker.setText(subjects.get(position).getSpeaker());
            viewHolder.time.setText(subjects.get(position).getTime());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isBreak(position))
            return ITEM_VIEW_TYPE_BREAK;
        else return ITEM_VIEW_TYPE_SPEAK;
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}
