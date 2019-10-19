package com.example.admin.lab07.exercise03;

/**
 * Created by thChung on 3/23/2019.
 */
public class Contact {

  private int id;
  private String name;
  private String phoneNumber;
  private String imgAddress;
  private int favorite;

  public Contact() {
  }

  public Contact(String name) {
    this.name = name;
    this.favorite = 0;
    this.phoneNumber = "0906 246 489";
  }

  public Contact(String name, boolean selected) {
    this.name = name;
    this.favorite = 0;
    this.phoneNumber = "0906 246 489";
  }

  public Contact(String name, String phoneNumber, int favorite) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.favorite = favorite;
  }

  public Contact(int id, String name, String phoneNumber) {
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImgAddress() {
    return imgAddress;
  }

  public void setImgAddress(String imgAddress) {
    this.imgAddress = imgAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getFavorite() {
    return favorite;
  }

  public void setFavorite(int favorite) {
    this.favorite = favorite;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Contact contact = (Contact) o;

    if (id != contact.id) {
      return false;
    }
    if (favorite != contact.favorite) {
      return false;
    }
    if (name != null ? !name.equals(contact.name) : contact.name != null) {
      return false;
    }
    if (phoneNumber != null ? !phoneNumber.equals(contact.phoneNumber)
            : contact.phoneNumber != null) {
      return false;
    }
    return imgAddress != null ? imgAddress.equals(contact.imgAddress) : contact.imgAddress == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
    result = 31 * result + (imgAddress != null ? imgAddress.hashCode() : 0);
    result = 31 * result + favorite;
    return result;
  }

  @Override
  public String toString() {
    return "Contact{" +
            "contactId=" + id +
            ", name='" + name + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", imgAddress='" + imgAddress + '\'' +
            ", favorite=" + favorite +
            '}';
  }
}
