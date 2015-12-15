package com.iesebre.dam2.amayor.todos;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by albert on 20/11/15.
 */
public class CustomListAdapter extends BaseAdapter {

    private ArrayList<TodoItem> list;

    private LayoutInflater layoutInflater;

    public Context context;

    public CustomListAdapter(Context context, ArrayList listData) {
        this.context = context;
        this.list = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Do things with the listview
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listitem, null);

            holder = new ViewHolder();
            holder.buttonView = (ToggleButton) convertView.findViewById(R.id.listbutton);
            holder.textView = (TextView) convertView.findViewById(R.id.listtext);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //We set the tag to the TaskItem, so we can find it in callbacks
        holder.buttonView.setTag(list.get(position));
        holder.textView.setTag(list.get(position));

        //Here we set the checkbox of the listview
        if(list.get(position).getColor() == TodoItem.Color.RED) {
            holder.buttonView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.prioritat_alta, 0, 0, 0);
        }

        else if(list.get(position).getColor() == TodoItem.Color.BLUE) {
            holder.buttonView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.prioritat_mitja, 0, 0, 0);
        }

        else if(list.get(position).getColor() == TodoItem.Color.GREEN) {
            holder.buttonView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.prioritat_baixa, 0, 0, 0);
        }

        holder.buttonView.setChecked(list.get(position).getChecked());

        //Padding, 16dp to pixels
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        holder.buttonView.setCompoundDrawablePadding((int)((16 * displayMetrics.density) + 0.5));

        //Set the text of the listview
        holder.textView.setText(list.get(position).getText());

        if(list.get(position).getChecked()){
            //Add strike through, set text to gray
            holder.textView.setPaintFlags(holder.buttonView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textView.setTextColor(Color.GRAY);
        } else {
            //Remove strike through, set text to black
            holder.textView.setPaintFlags(holder.buttonView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textView.setTextColor(Color.BLACK);
        }

        return convertView;
    }

    static class ViewHolder {
        ToggleButton buttonView;
        TextView textView;
    }

}