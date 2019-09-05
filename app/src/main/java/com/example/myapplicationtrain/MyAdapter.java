package com.example.myapplicationtrain;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    String[] days = new String[7];
    String[] temperatures = new String[7];
    String[] condition = new String[7];

    ImageView weatherIcon;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView day,status,temp;
        public MyViewHolder(View v) {
            super(v);
            day=  v.findViewById(R.id.day);
            status=  v.findViewById(R.id.status);
            temp=  v.findViewById(R.id.temp);
            weatherIcon =  v.findViewById(R.id.imageView);
        }
    }

    public MyAdapter(String[] days, String[] temperatures, String[] condition) {
        for (int i=0; i<days.length; i++) {
            this.days[i] = days[i];
            this.temperatures[i] = temperatures[i];
            this.condition[i] = condition[i];
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent , int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.customlist, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(contactView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        String day = days[i+1];
        String temp = temperatures[i+1];
        String cond = condition[i+1];
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String conditionIcon= cond; // conditionIcon = Partly cloudy, Light drizzle, etc
        conditionIcon = conditionIcon.replaceAll("\\s","").toLowerCase(); //conditionIcon -> conditionIcon
        Log.d("cond", conditionIcon);

        CurrentWeather weather  = new CurrentWeather();

        weather.setIcon(conditionIcon, weatherIcon);

        holder.day.setText(day);
        holder.temp.setText(temp);
        holder.status.setText(cond);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return days.length-1;
    }
}
