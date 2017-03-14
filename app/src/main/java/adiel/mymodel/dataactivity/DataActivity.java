package adiel.mymodel.dataactivity;

import android.content.CursorLoader;
import android.database.Cursor;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import adiel.mymodel.R;
import adiel.mymodel.data.MyDbHelper;
import adiel.mymodel.data.TodosContract;
import static adiel.mymodel.data.TodosContract.*;

public class DataActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private TodosCursorAdapter adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getLoaderManager().initLoader(770,null,this);
        final ListView lv = (ListView) findViewById(R.id.lv);
        adapter = new TodosCursorAdapter(this, cursor, false);
        lv.setAdapter(adapter);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {TodosContract.TodosEntry.COLUMN_TEXT,
                TodosContract.TodosEntry.TABLE_NAME + "." + TodosEntry._ID,
                TodosEntry.COLUMN_CREATED,
                TodosEntry.COLUMN_EXPIRED,
                TodosEntry.COLUMN_DONE,
                TodosContract.CategoriesEntry.TABLE_NAME + "." +
                        TodosContract.CategoriesEntry.COLUMN_DESCRIPTION};

        return new CursorLoader(
                this,
                TodosContract.TodosEntry.CONTENT_URI,
                projection,
                null, null, null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    public void insertToDo(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.createTodo(getContentResolver());
    }
}
