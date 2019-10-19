package com.example.admin.lab07.exercise03;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.lab07.R;

import java.util.List;

/**
 * Created by thChung on 3/23/2019.
 */

public class ItemPhoneItemAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private TextView txtItemName;
    private List<Contact> items;
    private ImageView imvImage;
    private TextView txtPhoneNumber;
    private ImageView imvFavorite;
    private ContactDatabaseHelper contactDatabaseHelper;

    public ItemPhoneItemAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
        super(context, R.layout.contact_item_row, objects);
        this.context = context;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.contact_item_row, null);

        contactDatabaseHelper = new ContactDatabaseHelper(context);

        // init view
        txtItemName = (TextView) convertView.findViewById(R.id.txtItemName);
        txtPhoneNumber = (TextView) convertView.findViewById(R.id.txtPhoneNumber);
        imvImage = (ImageView) convertView.findViewById(R.id.imvImage);
        imvFavorite = (ImageView) convertView.findViewById(R.id.imvFavorite);

        // get current value
        final Contact currentValue = items.get(position);

        // bind data to view
        txtItemName.setText(currentValue.getName());
        txtPhoneNumber.setText(currentValue.getPhoneNumber());
        imvImage.setImageResource(R.drawable.boy);
        int favorite = currentValue.getFavorite();
        setFavoriteImageResource(favorite);

        imvFavorite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int favorite = currentValue.getFavorite() == 0 ? 1 : 0;
                currentValue.setFavorite(favorite);
                setFavoriteImageResource(favorite);
                contactDatabaseHelper.updateContact(currentValue);
                notifyDataSetChanged();
            }
        });

        convertView.setLongClickable(true);
        return convertView;
    }

    private void setFavoriteImageResource(int favorite) {
        if(favorite == 1){
            imvFavorite.setImageResource(R.drawable.star);
        } else {
            imvFavorite.setImageResource(R.drawable.unstar);
        }
    }
}
