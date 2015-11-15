package com.jackie.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editUsrname;
    private EditText editPassword;
    private Button btnLogin;
    private CheckBox cbPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCollector.addActivity(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
        editUsrname = (EditText) findViewById(R.id.editUsrname);
        editPassword = (EditText) findViewById(R.id.editPassword);
        cbPass = (CheckBox) findViewById(R.id.cbPass);
        boolean isRemember = pref.getBoolean("isRemember", false);
        if (isRemember) {
            editUsrname.setText(pref.getString("usrname", ""));
            editPassword.setText(pref.getString("password", ""));
        }
        btnLogin = (Button) findViewById(R.id.btnLLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrname = editUsrname.getText().toString();
                String password = editPassword.getText().toString();
                if (usrname.equals("admin") && password.equals("admin")) {
                    if (cbPass.isChecked()) {
                        editor.putBoolean("isRemember", true);
                        editor.putString("usrname", usrname);
                        editor.putString("password", password);

                    } else {
                        editor.clear();
                    }
                    //一定要记得commit
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
