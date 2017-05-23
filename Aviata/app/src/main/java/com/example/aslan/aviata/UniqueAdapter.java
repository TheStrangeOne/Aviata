package com.example.aslan.aviata;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Aslan on 02.04.2017.
 */
public class UniqueAdapter<T> extends ArrayAdapter<T> {
    public UniqueAdapter(Context context, int resource) {
        super(context, resource);
    }

    public UniqueAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public UniqueAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    public UniqueAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public UniqueAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    public UniqueAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return super.getView(position, convertView, parent);
    }
}
