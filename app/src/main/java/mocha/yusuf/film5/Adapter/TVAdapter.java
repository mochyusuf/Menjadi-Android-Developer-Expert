package mocha.yusuf.film5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import mocha.yusuf.film5.Activity.DetailTV;
import mocha.yusuf.film5.BuildConfig;
import mocha.yusuf.film5.Model.TVModel;
import mocha.yusuf.film5.R;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.ViewHolder>{
    private Context context;
    private ArrayList<TVModel> tvs;

    public void setTvs(ArrayList<TVModel> tvs) {
        this.tvs = tvs;
    }

    public TVAdapter(Context context) {
        this.context = context;
        tvs = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_description;
        private ImageView tv_image;
        private RelativeLayout tv;

        ViewHolder(View view) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_description = view.findViewById(R.id.tv_description);
            tv_image = view.findViewById(R.id.tv_image);
            tv = view.findViewById(R.id.tv);
        }
    }

    @NonNull
    @Override
    public TVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_tv, viewGroup, false);
        return new TVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TVAdapter.ViewHolder holder, int position) {
        final TVModel tv = this.tvs.get(position);
        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.API_IMAGE +tv.getPoster_path())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.tv_image);
        holder.tv_title.setText(tv.getName());
        holder.tv_description.setText(tv.getOverview());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveWithObjectIntent = new Intent(context, DetailTV.class);
                moveWithObjectIntent.putExtra(DetailTV.EXTRA_TV, tvs.get(holder.getAdapterPosition()));
                context.startActivity(moveWithObjectIntent);
            }
        });
    }

    public void _notifyItemRangeRemoved(int i,int size){
        notifyItemRangeRemoved(i,size);
    }

    @Override
    public int getItemCount() {
        return this.tvs.size();
    }
}
