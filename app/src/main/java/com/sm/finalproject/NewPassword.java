package com.sm.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewPassword extends Activity {
    EditText edtPassword,edtConfirmPassword;
    boolean isShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        ControlBind();
        findViewById(R.id.imgShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    isShow = false;
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    isShow = true;
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        findViewById(R.id.imgShow1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    isShow = false;
                    edtConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    isShow = true;
                    edtConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        findViewById(R.id.btnRegistration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //todo Password validatation
            if (edtPassword.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            } else {
                //todo ConfirmPassword validatation
                if (edtConfirmPassword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_ConfirmPassword), Toast.LENGTH_SHORT).show();
                } else {
                    if (!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString())) {
                        Toast.makeText(getApplicationContext(), getString(R.string.Password_is_not_match), Toast.LENGTH_SHORT).show();
                    } else {
                        //todo Registration validatation
                        Toast.makeText(NewPassword.this, R.string.Submit1, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                }
            }
        }
    });
}
private void ControlBind() {
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
    }
}