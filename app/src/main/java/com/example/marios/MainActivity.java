package com.example.marios;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, DialogInterface.OnClickListener {
    private EditText editTextTextPassword, editTextTextEmailAddress;
    private Button buttonLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnLongClickListener(this);

        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
        String email = sp.getString("email", "");
        String password = sp.getString("password", "");

        if (!email.equals("") && !password.equals("")) {
            editTextTextEmailAddress.setText(email);
            editTextTextPassword.setText(password);

        }

    }


    public void login(View view) {
        Intent intent = new Intent(this, About.class);
        if (!editTextTextEmailAddress.getText().toString().equals("")) {
            //saving email and password of user in a local file for future use
            //create sp file
            SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
            //open editor for editing
            SharedPreferences.Editor editor = sp.edit();
            //write the wanted settings
            editor.putString("email", editTextTextEmailAddress.getText().toString());
            editor.putString("password", editTextTextPassword.getText().toString());
            //save and close file
            editor.commit();
            intent.putExtra("email", editTextTextEmailAddress.getText().toString());
            startActivity(intent);
        }

    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUPActivity.class);
        startActivity(intent);
    }

    //clears email and password input on long click by user
    @Override
    public boolean onLongClick(View view) {
        editTextTextEmailAddress.setText("");
        editTextTextPassword.setText("");
        return true;
    }


    public void  onClick(DialogInterface dialog,int which){
        if (which==dialog.BUTTON_POSITIVE){
            super.onBackPressed();
            dialog.cancel();
        }
        if (which==dialog.BUTTON_NEGATIVE){
            dialog.cancel();
        }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?");
        builder.setCancelable(false);
        builder.setPositiveButton("YES",this);
        builder.setNegativeButton("NO",this);
        AlertDialog dialog=builder.create();
        dialog.show();

    }
}