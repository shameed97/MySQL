package com.example.mysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void insert(View view) {
        EditText edt_id = findViewById(R.id.edt_id);
        EditText edt_name = findViewById(R.id.edt_name);
        EditText edt_dept = findViewById(R.id.edt_dept);
        BackgroundTask backgroundTask=new BackgroundTask(this);
        String id= edt_id.getText().toString();
        String name= edt_name.getText().toString();
        String dept= edt_dept.getText().toString();
        String method="add_info";
        backgroundTask.execute(method,id,name,dept);
        Log.i("ss",backgroundTask.toString());
        finish();
    }
}
