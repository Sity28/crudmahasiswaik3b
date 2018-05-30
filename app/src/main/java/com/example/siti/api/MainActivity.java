package com.example.siti.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.siti.api.adapter.MahasiswaAdapter;
import com.example.siti.api.model.Mahasiswa;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //nama java yg dibuat + nama pemanggilan
        Mahasiswa mahasiswa1 = new Mahasiswa();
        mahasiswa1.setNama("Ruka");
        mahasiswa1.setNama("3.34.15.1.21");
        mahasiswa1.setEmail("rukanarumi777@gmail.com");
        mahasiswa1.setFoto("R.drawable.s");

        Mahasiswa mahasiswa2 = new Mahasiswa(
                "Ruka",
                "3.34.15.1.21",
                "rukanarumi777@gmail.com",
                "R.drawable.s"
        );


        ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
        mahasiswas.add( mahasiswa1);
        mahasiswas.add(mahasiswa2);

        ImageView ivCobaGambar = (ImageView) findViewById(R.id.tv_img_coba);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try{
                URL url = new URL("http://piscum.photos/200/300?image=1");
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                ivCobaGambar.setImageBitmap(bmp);
            } catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }



        //Adapter
        String[] nama = new String[]{"Ruka", "Dandeli", "Narumi", "Siti"}; //menampilkan data statis
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nama);


        MahasiswaAdapter mahasiswaAdapter = new MahasiswaAdapter(this, R.layout.item_mahasiswa,
                mahasiswas);



        //activity (menampilkan data)
        ListView lvDaftarNama = (ListView) findViewById(R.id.list_mahasiswa);

        lvDaftarNama.setAdapter(mahasiswaAdapter);



    }
}
