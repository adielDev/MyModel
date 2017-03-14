package adiel.mymodel;

import android.content.ContentResolver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import adiel.mymodel.data.DatabaseHelper;
import adiel.mymodel.data.MyDbHelper;
import adiel.mymodel.dataactivity.DataActivity;

public class MainActivity extends AppCompatActivity {
    String[] itemname ={
            "Get theatre tickets",
            "Order pizza for tonight",
            "Buy groceries",
            "Running session at 19.30",
            "Call Uncle Sam"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initDb(View view) {
        DatabaseHelper helper = new DatabaseHelper(this);
        helper.getReadableDatabase();
    }

    public void createCategory(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.createCategory(getContentResolver());
    }

    public void readData(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.readData(getContentResolver());
    }

    public void createTodo(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.createTodo(getContentResolver());
    }

    public void updateData(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.updateTodo(getContentResolver());
    }

    public void deleteTodo(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.deleteTodo(getContentResolver());
    }


    public void deleteAllTodo(View view) {
        MyDbHelper myDbHelper = new MyDbHelper();
        myDbHelper.deleteAllTodo(getContentResolver());
    }

    public void openAct(View view) {
        startActivity(new Intent(MainActivity.this, DataActivity.class));
    }
}
