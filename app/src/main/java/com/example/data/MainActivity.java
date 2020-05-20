package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void updet(View view){
        Intent intent = new Intent(this, Updet.class);
        startActivity(intent);
    }
    public void list(View view){
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }
    public void tambah(View view){
        Intent intent = new Intent(this, Tambah.class);
        startActivity(intent);
    }

}
