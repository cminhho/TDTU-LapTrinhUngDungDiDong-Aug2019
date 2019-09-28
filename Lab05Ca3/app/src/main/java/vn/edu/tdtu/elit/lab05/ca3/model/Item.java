package vn.edu.tdtu.elit.lab05.ca3.model;

public class Item {

  private String name;
  private boolean selected;

  public Item(String name) {
    this.name = name;
    this.selected = false;
  }

  public Item(String name, boolean selected) {
    this.name = name;
    this.selected = selected;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
}
