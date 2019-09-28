package vn.edu.tdtu.elit.lab05.ca3.exercise3;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.tdtu.elit.lab05.ca3.R;
import vn.edu.tdtu.elit.lab05.ca3.model.Item;

public class ComputersAdapter extends ArrayAdapter<Item> {

  private Context context;
  private View view;
  private ArrayList<Item> items;
  private TextView txtName;
  private ImageView imvComputer;
  private RelativeLayout rlComputer;

  public ComputersAdapter(@NonNull Context context, @NonNull ArrayList<Item> items) {
    super(context, R.layout.grid_item_row, items);
    this.context = context;
    this.items = items;
  }

  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
    view = layoutInflater.inflate(R.layout.grid_item_row, null);

    initView();
    setViewValue(position);

    rlComputer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Item item = items.get(position);
        item.setSelected(!item.isSelected());
        Toast.makeText(context, item.getName() + " is selected: " + item.isSelected(),
            Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
      }
    });

    return view;
  }

  private void setViewValue(int position) {
    Item item = items.get(position);
    txtName.setText(item.getName());
    if (item.isSelected()) {
      imvComputer.setImageResource(R.drawable.monitor_icon_focus);
    } else {
      imvComputer.setImageResource(R.drawable.monitor_icon);
    }
  }

  private void initView() {
    txtName = (TextView) view.findViewById(R.id.txtName);
    imvComputer = (ImageView) view.findViewById(R.id.imvComputer);
    rlComputer = (RelativeLayout) view.findViewById(R.id.rlComputer);
  }
}