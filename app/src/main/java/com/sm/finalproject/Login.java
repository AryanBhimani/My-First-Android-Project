package com.sm.finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;
public class Login extends Activity {
    EditText edtMob_No, edtPassword;
    boolean isShow;
    Login login;
    int userData,modePrivate;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ControlBindd();
        if (getSharedPreferences("UserData", 0).getString("isLogin", "").equals("True")) {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            if (edtMob_No.getText().toString().length() != 10) {
                Toast.makeText(Login.this, R.string.enter_Mob_No, Toast.LENGTH_SHORT).show();
            } else if (edtPassword.getText().toString().trim().length() == 0) {
                Toast.makeText(Login.this, getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(getApplicationContext(), Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(Login.this, R.string.Login1, Toast.LENGTH_SHORT).show();
                SharedPreferences sharedpreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("isLogin", "True");
                editor.commit();
                startActivity(i);
                finish();
            }
        });
        findViewById(R.id.imgShow).setOnClickListener(v -> {
            if (!isShow) {
                isShow = true;
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                isShow = false;
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
        findViewById(R.id.ForgotPassword).setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        });
        findViewById(R.id.txtRegistration).setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Registration.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        });
    }
    SharedPreferences getSharedPreferences(Login login, int userData, int modePrivate) {
        this.login = login;
        this.userData = userData;
        this.modePrivate = modePrivate;
        return null;
    }
    void ControlBindd() {
        edtMob_No = findViewById(R.id.edtMob_No);
        edtPassword = findViewById(R.id.edtPassword);
    }
}