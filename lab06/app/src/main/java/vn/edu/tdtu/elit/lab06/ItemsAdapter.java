package vn.edu.tdtu.elit.lab06;

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
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<Item> {

  private Context context;
  private View view;
  private ArrayList<Item> items;
  private TextView txtName;
  private CheckBox cbSelected;

  public ItemsAdapter(@NonNull Context context, @NonNull ArrayList<Item> items) {
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
      }
    });
    view.setLongClickable(true);
    return view;
  }

  private void setViewValue(int position) {
    Item item = items.get(position);
    txtName.setText(item.getName());
    cbSelected.setChecked(item.isSelected());
  }

  private void initView() {
    txtName = (TextView) view.findViewById(R.id.txtName);
    cbSelected = (CheckBox) view.findViewById(R.id.cbSelectItem);
  }
}
