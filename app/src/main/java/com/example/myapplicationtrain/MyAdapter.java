package com.example.myapplicationtrain;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private static ClickListener clickListener;

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MyAdapter.clickListener = clickListener;
    }
    /*String[] days = new String[7];
    String[] temperatures = new String[7];
    String[] condition = new String[7];*/

    ArrayList<DetailsData> weatherList;

    ImageView weatherIcon;


    public MyAdapter(ArrayList<DetailsData> weatherList) {
        /*for (int i=0; i<days.length; i++) {
            this.days[i] = days[i];
            this.temperatures[i] = temperatures[i];
            this.condition[i] = condition[i];
        }*/
        this.weatherList = weatherList;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView day, status, temp;

        public MyViewHolder(View v) {
            super(v);
            day = v.findViewById(R.id.day);
            status = v.findViewById(R.id.status);
            temp = v.findViewById(R.id.temp);
            weatherIcon = v.findViewById(R.id.imageView);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
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

        /*String day = days[i+1];
        String temp = temperatures[i+1];
        String cond = condition[i+1];*/
        DetailsData weatherData = weatherList.get(i+1);
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String conditionIcon= weatherData.condition; // conditionIcon = Partly cloudy, Light drizzle, etc
        conditionIcon = conditionIcon.replaceAll("\\s","").toLowerCase(); //conditionIcon -> conditionIcon
        Log.d("cond", conditionIcon);

        CurrentWeather weather  = new CurrentWeather();

        weather.setIcon(conditionIcon, weatherIcon);

        holder.day.setText(weatherData.day);
        holder.temp.setText(weatherData.temperature);
        holder.status.setText(weatherData.condition);

    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return weatherList.size()-1;
    }
}
