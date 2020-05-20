package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbhlper;
    ListView nomer, nama_list, merk_list, harga_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbhlper = new DatabaseHelper(this);
        nomer = (ListView) findViewById(R.id.nomer_list);
        nama_list = (ListView) findViewById(R.id.nama_list);
        merk_list = (ListView) findViewById(R.id.merk_list);
        harga_list = (ListView) findViewById(R.id.harga_list);

        ArrayList<String>nomermu = new ArrayList<>();
        ArrayList<String>namamu = new ArrayList<>();
        ArrayList<String>merkmu = new ArrayList<>();
        ArrayList<String>hargamu = new ArrayList<>();

        Cursor data = dbhlper.getAllData();
        if(data.getCount()==0){
            Toast.makeText(List.this, "Data Kosong", Toast.LENGTH_SHORT).show();
        }else{

            while (data.moveToNext()){

                nomermu.add(data.getString(0));
                namamu.add(data.getString(1));
                merkmu.add(data.getString(2));
                hargamu.add(data.getString(3));

                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,nomermu);
                ListAdapter listAdapterNama = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,namamu);
                ListAdapter listAdapterMerk = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,merkmu);
                ListAdapter listAdapterHarga = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,hargamu);

                nomer.setAdapter(listAdapter);
                nama_list.setAdapter(listAdapterNama);
                merk_list.setAdapter(listAdapterMerk);
                harga_list.setAdapter(listAdapterHarga);

            }
        }

    }
}
