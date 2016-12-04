package com.example.hirurg.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends ActionBarActivity {
    private ListView listView;
    private DB db;
    private SimpleCursorAdapter scAdapter;
    private Cursor cursor;
    private static final int CM_DELETE_ID = 1;
    private static final int CM_EDIT_ID = 2;
    private static final String TAG = "MYLOG";

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate started");
        db = new DB(this);
        db.open();
        Log.d(TAG, "onCreate db created");
        listView = (ListView) findViewById(android.R.id.list);

        cursor = db.getAllData();
//		startManagingCursor(cursor);
        String[] from = new String[] { DB.COLUMN_FN, DB.COLUMN_LN, DB.COLUMN_PHONE };
        int[] to = new int[] { R.id.listFN, R.id.listLN, R.id.listPhone};
        scAdapter = new SimpleCursorAdapter(this, R.layout.list, cursor, from,
                to);
        listView.setAdapter(scAdapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this,
                        ContactActivity.class);
                intent.putExtra("row_id", id);

                startActivity(intent);

            }

        });
        Log.d(TAG, "onCreate finished");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem itemToHide;

        if (Properties.properties.get(Properties.ORDER_BY) == Properties.FIRST_NAME) {
            itemToHide = menu.findItem(R.id.sort_by_first_name);
        } else {
            itemToHide = menu.findItem(R.id.sort_by_last_name);
        }
        itemToHide.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_contact) {
            Intent intent = new Intent(this, EditorActivity.class);
            startActivity(intent);
        }
        if (id == R.id.fill_in) {
            DBFiller.fillDB(this);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        if (id == R.id.del_all) {
            db.deleteAll();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        if (id == R.id.sort_by_first_name) {
            Properties.properties.put(Properties.ORDER_BY, Properties.FIRST_NAME);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        if (id == R.id.sort_by_last_name) {
            Properties.properties.put(Properties.ORDER_BY, Properties.LAST_NAME);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_EDIT_ID, 0, R.string.edit);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete);
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item
                    .getMenuInfo();
            db.delRec(acmi.id);
            cursor.requery();
            return true;
        } else if (item.getItemId() == CM_EDIT_ID){
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item
                    .getMenuInfo();
            Intent intent = new Intent(this, EditorActivity.class);
            intent.putExtra("row_id", acmi.id);

            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
