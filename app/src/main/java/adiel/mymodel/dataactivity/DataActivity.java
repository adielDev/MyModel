package adiel.mymodel.dataactivity;

import android.content.CursorLoader;
import android.database.Cursor;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import adiel.mymodel.R;
import adiel.mymodel.data.MyDbHelper;
import adiel.mymodel.data.TodosContract;
import static adiel.mymodel.data.TodosContract.*;

public class DataActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private TodosCursorAdapter adapter;
    Cursor cursor;
    Handler uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getLoaderManager().initLoader(770,null,this);
        getLoaderManager().initLoader(613,null,this);
        final ListView lv = (ListView) findViewById(R.id.lv);
        adapter = new TodosCursorAdapter(this, cursor, false);
        lv.setAdapter(adapter);

    }


    @Override
    public Loader<Cursor> onCreateLoader(final int id, Bundle args) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DataActivity.this, "id"+id, Toast.LENGTH_SHORT).show();
            }
        });

        if(args!=null) {
            Log.d("adiel", "args:" + args.toString());
        }
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
            case 613:
                Log.d("adiel", "onCreateLoader 613");
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
            case 613:
                Log.d("adiel", "613 from onLoadFinished");
                break;

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d("adiel", "from onLoaderReset, id="+loader.getId());
        adapter.swapCursor(null);
    }

    public void insertToDo(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.createTodo(getContentResolver());
    }
}
