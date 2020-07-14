package com.omerfpekgoz.sharedpreferencesloginekrani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IkinciEkran extends AppCompatActivity {

    private Button btnCikis;
    private TextView txtCikti;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ikinci_ekran);

        btnCikis = findViewById(R.id.btnCikis);
        txtCikti = findViewById(R.id.txtCikti);

        sp = getSharedPreferences("girisBilgi", MODE_PRIVATE);
        editor = sp.edit();

        userName = sp.getString("username", "kullanıcı adı yok");
        password = sp.getString("password", "sifre yok");

        txtCikti.setText("Kullanıcı Adı : " + userName + " " + "Şifre : " + password);

        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    //Çıkış Yapıldığında kullanıcı adı ve şifre yi sıfırlayacak ve MainScreen e gönderecek
                editor.remove("username");
                editor.remove("password");
                editor.commit();

                startActivity(new Intent(IkinciEkran.this, MainActivity.class));
                finish();

            }
        });
    }

}
