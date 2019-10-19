package com.example.admin.lab07.exercise03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thChung on 3/23/2019.
 */

public class ContactDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContactDB";
    private static final String TABLE_CONTACT = "contacts";

    private static final String COLUMN_CONTACT_ID ="id";
    private static final String COLUMN_CONTACT_NAME ="name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_FAVORITE = "favorite";

    public ContactDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "ContactDatabaseHelper.onCreate ... ");
        // Create Contact tables.
        String script = "CREATE TABLE " + TABLE_CONTACT + "("
                + COLUMN_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CONTACT_NAME + " TEXT,"
                + COLUMN_PHONE_NUMBER + " TEXT,"
                + COLUMN_FAVORITE + " INTEGER DEFAULT 0)";

        // execute the script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "ContactDatabaseHelper.onUpgrade ... ");

        // Drop table if exist.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        // create db.
        onCreate(db);
    }

    public void createDefaultContacts()  {
        int count = this.getContactsCount();
        if(count == 0) {
            Contact contact1 = new Contact("Ho Minh Chung", "0906 246 489", 1);
            Contact contact2 = new Contact("Nguyen Van A", "0906 246 489", 1);
            this.addContact(contact1);
            this.addContact(contact2);
        }
    }


    public void addContact(Contact contact) {
        Log.i(TAG, "ContactDatabaseHelper.addContact ... " + contact.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_NAME, contact.getName());
        values.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        values.put(COLUMN_FAVORITE, contact.getFavorite());

        // Insert a new row to table
        db.insert(TABLE_CONTACT, null, values);

        // Close database connection.
        db.close();
    }


    public Contact getContact(int id) {
        Log.i(TAG, "ContactDatabaseHelper.getContact ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACT, new String[] { COLUMN_CONTACT_ID,
                        COLUMN_CONTACT_NAME, COLUMN_PHONE_NUMBER, COLUMN_FAVORITE}, COLUMN_CONTACT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact();
        contact.setContactId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));
        contact.setFavorite(Integer.parseInt(cursor.getString(3)));
        // return contact
        return contact;
    }


    public List<Contact> getAllContacts() {
        Log.i(TAG, "ContactDatabaseHelper.getAllNotes ... " );

        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setContactId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contact.setFavorite(cursor.getInt(3));

                contactList.add(contact);

            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();

        // return note list
        return contactList;
    }

    public int getContactsCount() {
        Log.i(TAG, "ContactDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_CONTACT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateContact(Contact updateNote) {
        Log.i(TAG, "ContactDatabaseHelper.updateContact ... "  + updateNote.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_NAME, updateNote.getName());
        values.put(COLUMN_PHONE_NUMBER, updateNote.getPhoneNumber());
        values.put(COLUMN_FAVORITE, updateNote.getFavorite());

        // updating row
        return db.update(TABLE_CONTACT, values, COLUMN_CONTACT_ID + " = ?",
                new String[]{String.valueOf(updateNote.getContactId())});
    }

    public void deleteContact(Contact contact) {
        Log.i(TAG, "ContactDatabaseHelper.updateNote ... " + contact.getName() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT, COLUMN_CONTACT_ID + " = ?",
                new String[] { String.valueOf(contact.getContactId()) });
        db.close();
    }

    public void deleteAllContacts() {
        Log.i(TAG, "ContactDatabaseHelper.deleteAllContacts ... ");

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_CONTACT);
        db.close();
    }
}
