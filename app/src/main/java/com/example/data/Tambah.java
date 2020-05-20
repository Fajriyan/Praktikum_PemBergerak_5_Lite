package com.example.data;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tambah extends Activity {

    DatabaseHelper db;
    EditText data_nama, data_merk, data_harga;
    Button simpan_data;


    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        db = new DatabaseHelper(this);
        data_nama = (EditText) findViewById(R.id.isi_nama);
        data_merk = (EditText) findViewById(R.id.isi_merk);
        data_harga = (EditText) findViewById(R.id.isi_harga);
        simpan_data = (Button) findViewById(R.id.button_simpan);



        try {
            simpan_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String nama = data_nama.getText().toString().trim();
                    String merk = data_merk.getText().toString().trim();
                    String harga = data_harga.getText().toString().trim();

                    try {
                            boolean isInserted = db.insertData(nama, merk, harga);
                            if (isInserted == true) {
                                Toast.makeText(Tambah.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                data_nama.setText("");
                                data_merk.setText("");
                                data_harga.setText("");

                            } else
                                Toast.makeText(Tambah.this, "Gagal Menyimpan", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(Tambah.this, "Inputan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch(Exception e){
            Toast.makeText(Tambah.this, " "+e, Toast.LENGTH_SHORT).show();
        }
    }
}
