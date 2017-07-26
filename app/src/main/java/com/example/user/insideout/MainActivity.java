package com.example.user.insideout;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {


    public DBAdapter db;
    EditText userName, password;
    Button logInButton;

    private ImageView logo;
   // private Button startButton;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo= (ImageView)findViewById(R.id.frontLogo); //logo image

        //2 editText
        userName= (EditText)findViewById(R.id.editTextEmail);
        password= (EditText)findViewById(R.id.editTextPassword);

        logInButton = (Button)findViewById(R.id.startButton);


        new AsyncLoadDB().execute();


      /*  logInButton = (Button)findViewById(R.id.startButton); //start button
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(i1);
                finish();
            }
        });*/
    }

    //inner class
    public class AsyncLoadDB extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            db = new DBAdapter(MainActivity.this);
            db.open();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            logInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nameTxt = userName.getText().toString();
                    String passwordTxt = password.getText().toString();
                    if(passwordTxt.length() > 0 && nameTxt.length() > 0){

                        if(db.check(nameTxt, passwordTxt)) {
                            startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong Information", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(MainActivity.this, "User name, password required", Toast.LENGTH_SHORT).show();
                    }
                }
            });

          /*  register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1= new Intent(Splash.this, RegisterActivity.class);
                    startActivity(i1);
                }
            });*/
        }

    }


}
