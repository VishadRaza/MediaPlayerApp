package com.foodbreak.vishad.mediaplayerapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.foodbreak.vishad.mediaplayerapp.R;
import com.foodbreak.vishad.mediaplayerapp.model.ListDetails;
import com.foodbreak.vishad.mediaplayerapp.model.Model;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Model> models;

    public MovieAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        TextView title = (TextView)convertView.findViewById(R.id.textView);
        TextView about =(TextView) convertView.findViewById(R.id.textView2);
        Model model = models.get(i);
        title.setText((model.getName()));
        about.setText((model.getArtist()));
        return convertView;
    }
}
