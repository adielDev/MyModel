package adiel.mymodel.customloader;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import adiel.mymodel.R;
import adiel.mymodel.data.MyDbHelper;
import adiel.mymodel.data.TodosContract;
import adiel.mymodel.dataactivity.TodosCursorAdapter;
import static adiel.mymodel.data.TodosContract.*;

public class CustomLoaderAct extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private TodosCursorAdapter adapter;
    MyCursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loader);
        getSupportLoaderManager().initLoader(770,null,this);
        final ListView lv = (ListView) findViewById(R.id.lv);
        adapter = new TodosCursorAdapter(this, myCursor, false);
        lv.setAdapter(adapter);

    }


    public void insertToDo(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.createTodo(getContentResolver());
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("adiel", "onCreateLoader 770");
        String[] projection = {TodosContract.TodosEntry.COLUMN_TEXT,
                TodosContract.TodosEntry.TABLE_NAME + "." + TodosEntry._ID,
                TodosEntry.COLUMN_CREATED,
                TodosEntry.COLUMN_EXPIRED,
                TodosEntry.COLUMN_DONE,
                TodosContract.CategoriesEntry.TABLE_NAME + "." +
                        TodosContract.CategoriesEntry.COLUMN_DESCRIPTION};

        CursorLoader cursorLoader = new CursorLoader(
                this,
                TodosEntry.CONTENT_URI,
                projection,
                null, null, null);
        switch (id) {
            case 770:
                return cursorLoader;
            default:
                Log.d("adiel", "onCreateLoader default");
                return null;

        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()){

            case 770:
                Log.d("adiel", "770 from onLoadFinished");
                adapter.swapCursor(data);
                break;

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d("adiel", "from onLoaderReset, id="+loader.getId());
        adapter.swapCursor(null);
    }

}
