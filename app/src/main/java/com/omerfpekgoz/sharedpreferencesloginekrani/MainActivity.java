package com.omerfpekgoz.sharedpreferencesloginekrani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//Shared Preference telefondaki bir uygulamaya kullanıcı adı ve şifre girip çıkış yapmadan tekrar giriş yaparsak direk giriş sağlayacaktır
//Örneğin;instagram ,twitter
//Uygulama silinirse kullanıcı bilgileri de silinecektir

public class MainActivity extends AppCompatActivity {

    private EditText txtKullaniciAdi;
    private EditText txtSifre;
    private Button btnGiris;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtKullaniciAdi = findViewById(R.id.txtKullaniciAdi);
        txtSifre = findViewById(R.id.txtSifre);
        btnGiris = findViewById(R.id.btnGiris);

        sp = getSharedPreferences("girisBilgi", MODE_PRIVATE);
        editor = sp.edit();

        userName = sp.getString("username", "kullanıcı adı yok");
        password = sp.getString("password", "sifre yok");


        //Kullanıcı daha önce giriş yaptı ise bilgileri kayıt olacak
        //Çıkış yapmaz ise bilgiler silinmeyecek
        //İkinci kez girdiğinde direk ikinci ekrana yönlendirilecek
        if (userName.equals("admin") && password.equals("123")) {
            startActivity(new Intent(MainActivity.this, IkinciEkran.class));
            finish();
        }


        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtKullaniciAdi.getText().toString().equals("admin") && txtSifre.getText().toString().equals("123")) {

                    editor.putString("username", txtKullaniciAdi.getText().toString());
                    editor.putString("password", txtSifre.getText().toString());
                    editor.commit();

                    startActivity(new Intent(MainActivity.this, IkinciEkran.class));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Kullanıcı Adı ve ya Şifre Hatalı", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
