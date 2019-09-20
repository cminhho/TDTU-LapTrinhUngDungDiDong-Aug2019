package com.tdt.lab04.exercise2;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tdt.lab04.R;

import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<String> {
    private Context context;
    private View view;
    private ArrayList<String> items;
    private TextView txtName;
    private Button btnRemove;

    public ItemsAdapter(@NonNull Context context, @NonNull ArrayList<String> items) {
        super(context, R.layout.item_row, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        view = layoutInflater.inflate(R.layout.item_row,null);

        // lookup views
        txtName = view.findViewById(R.id.txtName);
        btnRemove = view.findViewById(R.id.btnRemove);

        setViewValue(position);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = items.get(position);
                Toast.makeText(context, item + " is removed!", Toast.LENGTH_LONG).show();
                items.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    private void setViewValue(int position) {
        String item = items.get(position);
        txtName.setText(item);
    }
}
