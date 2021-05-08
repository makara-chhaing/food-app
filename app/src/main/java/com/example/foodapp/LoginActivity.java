package com.example.foodapp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.foodapp.databasehelper.Database;
import com.example.foodapp.util.Util;

public class LoginActivity extends AppCompatActivity {

    TextView username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Util.db == null){
            Log.d(Util.DEBUG, "Create Database");
            Util.db = new Database(this,null);
        }

        username = findViewById(R.id.login_et_username);
        password = findViewById(R.id.login_et_password);
    }

    public void login(View v){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        long result;
        if(!(user.equals("") || pass.equals(""))){
            result = Util.db.fetchUser(user,pass);
            Log.d(Util.DEBUG, "resutl: " + result);
            if(result > 0){
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }else {
                Toast.makeText(this, "Incorrect Username or Password!", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
        }

    }

    public void signUp(View v){
        startActivity(new Intent(this, SignupActivity.class));
    }
}