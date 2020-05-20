package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Updet extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbhlper;
    ListView nomer, nama_list, merk_list, harga_list;
    EditText aidi, up_nama, up_merk, up_harga, id_hapus;
    Button ubah,hapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updet);


        dbhlper = new DatabaseHelper(this);
        nomer = (ListView) findViewById(R.id.nomer_list);
        nama_list = (ListView) findViewById(R.id.nama_list);
        merk_list = (ListView) findViewById(R.id.merk_list);
        harga_list = (ListView) findViewById(R.id.harga_list);

        aidi = (EditText) findViewById(R.id.id_utama);
        up_nama = (EditText) findViewById(R.id.updet_nama);
        up_merk = (EditText) findViewById(R.id.updet_merk);
        up_harga = (EditText) findViewById(R.id.updet_harga);
        ubah = (Button) findViewById(R.id.button_updet);

        id_hapus = (EditText) findViewById(R.id.id_delete);
        hapus = (Button) findViewById(R.id.button_delete);

        ArrayList<String>nomermu = new ArrayList<>();
        ArrayList<String>namamu = new ArrayList<>();
        ArrayList<String>merkmu = new ArrayList<>();
        ArrayList<String>hargamu = new ArrayList<>();

        Cursor data = dbhlper.getAllData();
        if(data.getCount()==0){
            Toast.makeText(Updet.this, "Data Masih Kosong", Toast.LENGTH_SHORT).show();
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

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        boolean isUpdate = dbhlper.updateData(aidi.getText().toString(),
                                up_nama.getText().toString(),up_merk.getText().toString(),up_harga.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(Updet.this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Updet.this, "Gagal Update Data", Toast.LENGTH_SHORT).show();
                    }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = dbhlper.deleteData(id_hapus.getText().toString());
                if (deletedRows > 0) {
                    Toast.makeText(Updet.this, "Berhasil Menghapus Data", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(Updet.this, "Gagal Menghapus Data", Toast.LENGTH_LONG).show();
            }
        });


    }
}
