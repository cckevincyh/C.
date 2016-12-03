package com.cc.cairou.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.cc.cairou.R;

/**
 * Created by Benson on 2015/12/11.
 */
public class TypeItemAdapter extends ArrayAdapter<String> {
    private int resourceId;
    public TypeItemAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position);

        
        if (position == getCount() - 1) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.last_item, null);
            TextView itemName = (TextView) view.findViewById(R.id.last_item_name);
            itemName.setText(item);
            itemName.getPaint().setFakeBoldText(true);
            return view;
        }
      
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView itemName = (TextView) view.findViewById(R.id.type_item_name);
        itemName.setText(item);
        
        if (position == MemoActivity.selectedTypeItemNum) {
            view.setBackgroundResource(R.color.colorRightList);
        }
        return view;
    }

}
