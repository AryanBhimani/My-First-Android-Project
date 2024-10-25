package com.sm.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends Activity {
    EditText edtMob_No,edtemail_ID;
    boolean isShow;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ControlBind();

        findViewById(R.id.btnRegistration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo mob_no validatation
                if (edtMob_No.getText().toString().length() != 10) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_Mob_No), Toast.LENGTH_SHORT).show();
                } else {
                    //todo email_ID validatation
                    if (edtemail_ID.getText().toString().trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), getString(R.string.Empty_Email_address), Toast.LENGTH_SHORT).show();
                    } else {
                        //todo email_ID validatation
                        if (edtemail_ID.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
                        } else {
                            //todo email_ID validatation
                            if (!edtemail_ID.getText().toString().trim().matches(emailPattern)) {
                                Toast.makeText(getApplicationContext(), getString(R.string.Invalidemailaddress), Toast.LENGTH_SHORT).show();
                            } else {
                                //todo Registration validatation
                                Toast.makeText(ForgotPassword.this, R.string.Submit1, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), NewPassword.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                }
            }
        });
    }
    private void ControlBind() {
        edtMob_No = findViewById(R.id.edtMob_No);
        edtemail_ID = findViewById(R.id.edtemail_ID);
    }
}