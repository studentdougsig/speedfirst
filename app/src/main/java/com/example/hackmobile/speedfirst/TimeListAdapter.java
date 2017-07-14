package com.example.hackmobile.speedfirst;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.ViewHolder> {
    private String LOG_TAG = "TimeListAdapter";
    private Context context;
    private FartlekWorkout dataSource;

    public TimeListAdapter(Context context, FartlekWorkout fartlekWorkout) {
        this.context = context;
        this.dataSource = fartlekWorkout;
    }

    public FartlekWorkout getDataSource() {
        return dataSource;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public TextView speedTextView;
        public TextView durationTextView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            speedTextView = (TextView)v.findViewById(R.id.speedText);
            durationTextView = (TextView)v.findViewById(R.id.durationText);
        }
    }

    @Override
    public TimeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fartlek_interval_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        // calculate size of dp margins in px
        Resources r = holder.mView.getResources();
        int smallMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics());
        int bigMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics());

        // increase top margin for first item in list
        if (position == 0) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)holder.mView.getLayoutParams();
            params.setMargins(bigMargin, bigMargin, bigMargin, smallMargin);
        }
        // increase bottom margin for last item in list
        else if (position == dataSource.getList().size()-1) {
            // increase bottom margin
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)holder.mView.getLayoutParams();
            params.setMargins(bigMargin, smallMargin, bigMargin, bigMargin);
        }
        // set default margins for all other list items
        else {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)holder.mView.getLayoutParams();
            params.setMargins(bigMargin, smallMargin, bigMargin, smallMargin);
        }

        // get URI to image
        if (dataSource.getList().size() > 0) {
            final FartlekInterval interval = dataSource.getList().get(position);
            holder.speedTextView.setText(interval.getSpeed());
            holder.durationTextView.setText(""+interval.getDuration());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSource.getList().size();
    }

    public void setDataSource(FartlekWorkout fartlekWorkout) {
        this.dataSource = fartlekWorkout;
    }

}