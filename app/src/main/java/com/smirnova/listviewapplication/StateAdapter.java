package com.smirnova.listviewapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {

    private LayoutInflater inflater;
    private int layout;
    private List<State> states;


    public StateAdapter(@NonNull Context context, int resource, @NonNull List<State> objects) {
        super(context, resource, objects);

        this.states = objects;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        // вариант 1
        /*View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView capitalView = view.findViewById(R.id.capital);

        State state = states.get(position);

        flagView.setImageResource(state.getFlagResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getCapital());
         return view;
*/
        // Вапиант 2 view заменяем на convertView

       /* if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        ImageView flagView = convertView.findViewById(R.id.flag);
        TextView nameView = convertView.findViewById(R.id.name);
        TextView capitalView = convertView.findViewById(R.id.capital);

        State state = states.get(position);

        flagView.setImageResource(state.getFlagResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getCapital());

        return convertView;*/

        ViewHolder viewHolder;

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        State state = states.get(position);

        viewHolder.imageView.setImageResource(state.getFlagResource());
        viewHolder.nameView.setText(state.getName());
        viewHolder.capitalView.setText(state.getCapital());

        return convertView;

    }

    private class ViewHolder {
        final ImageView imageView;
        final TextView nameView, capitalView;

        ViewHolder(View view) {
            imageView = view.findViewById(R.id.flag);
            nameView = view.findViewById(R.id.name);
            capitalView = view.findViewById(R.id.capital);
        }
    }
}
