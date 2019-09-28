package vn.edu.tdtu.elit.lab05.ca3.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
import java.util.List;
import vn.edu.tdtu.elit.lab05.ca3.R;
import vn.edu.tdtu.elit.lab05.ca3.model.Item;

public class ItemsAdapter extends ArrayAdapter<Item> {

  private Context context;
  private View view;
  private List<Item> items;
  private TextView txtName;
  private CheckBox cbSelected;
  private ImageView imvIcon;

  public ItemsAdapter(@NonNull Context context, @NonNull List<Item> items) {
    super(context, R.layout.item_row, items);
    this.context = context;
    this.items = items;
  }

  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
    view = layoutInflater.inflate(R.layout.item_row, null);

    initView();
    setViewValue(position);

    cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Item selectedItem = getItem(position);
        selectedItem.setSelected(b);
        Toast.makeText(context,
            selectedItem.getName() + " is selected: " + selectedItem.isSelected(),
            Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
      }
    });

    return view;
  }

  private void setViewValue(int position) {
    Item item = items.get(position);
    txtName.setText(item.getName());
    cbSelected.setChecked(item.isSelected());
    if(item.isSelected()) {
      imvIcon.setImageResource(R.drawable.monitor_icon);
    } else {
      imvIcon.setImageResource(R.drawable.monitor_icon_focus);
    }
  }

  private void initView() {
    txtName = view.findViewById(R.id.txtName);
    cbSelected = view.findViewById(R.id.cbSelectItem);
    imvIcon = view.findViewById(R.id.imvIcon);
  }
}