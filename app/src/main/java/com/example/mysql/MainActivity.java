package com.example.mysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText ed_name,ed_dept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void reg_data(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void log_in(View view) {

        ed_name=findViewById(R.id.ed_name);
        ed_dept=findViewById(R.id.ed_dept);
        String name=ed_name.getText().toString();
        String dept=ed_dept.getText().toString();
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute("get_info",name,dept);
    }
}
