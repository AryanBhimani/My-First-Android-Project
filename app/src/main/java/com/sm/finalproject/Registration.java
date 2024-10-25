package com.sm.finalproject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Registration extends Activity {
    EditText edtUsername,emailId,edtMob_No,edtPassword,edtConfirmPassword,edtemail_ID;
    boolean isShow;
    Spinner spinner;
    String StrGender = "";
    RadioButton rbMale, rbFemale;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<String> ALCountry = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ControlBind();
        rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrGender = "Male";
                rbMale.setChecked(true);
                rbFemale.setChecked(false);
            }
        });
        rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrGender = "Female";
                rbMale.setChecked(false);
                rbFemale.setChecked(true);
            }
        });
        ALCountry.add("Select Country *");
        ALCountry.add("India");
        ALCountry.add("USA");
        ALCountry.add("UK");
        ALCountry.add("Canada");
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ALCountry);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String StrCoutry = spinner.getSelectedItem().toString();
                Toast.makeText(Registration.this, StrCoutry, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        findViewById(R.id.txtLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
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
                if (edtUsername.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_Username), Toast.LENGTH_SHORT).show();
                } else {
                    //todo mob_no validatation
                    if (edtMob_No.getText().toString().length() != 10) {
                        Toast.makeText(getApplicationContext(), getString(R.string.enter_Mob_No), Toast.LENGTH_SHORT).show();
                    } else {
                        if (StrGender.equals("")) {
                            Toast.makeText(Registration.this, R.string.select_gender, Toast.LENGTH_SHORT).show();
                        } else if (spinner.getSelectedItemPosition() == 0) {
                            Toast.makeText(Registration.this, R.string.select_country, Toast.LENGTH_SHORT).show();
                        } else {
                            //todo email_ID validatation
                            if (edtemail_ID.getText().toString().trim().length() == 0) {
                                Toast.makeText(getApplicationContext(), getString(R.string.Empty_Email_address),Toast.LENGTH_SHORT).show();
                            } else {
                                //todo email_ID validatation
                                if (edtemail_ID.getText().toString().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
                                } else {
                                    //todo email_ID validatation
                                    if (!edtemail_ID.getText().toString().trim().matches(emailPattern)) {
                                        Toast.makeText(getApplicationContext(), getString(R.string.Invalidemailaddress), Toast.LENGTH_SHORT).show();
                                    } else {
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
                                                    Toast.makeText(Registration.this, R.string.Ragistration, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    void ControlBind() {
        spinner = findViewById(R.id.spinner);
        edtUsername = findViewById(R.id.edtUsername);
        edtMob_No = findViewById(R.id.edtMob_No);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        edtemail_ID = findViewById(R.id.edtemail_ID);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
    }
}
