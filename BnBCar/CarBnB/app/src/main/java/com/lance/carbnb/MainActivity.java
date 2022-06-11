package com.lance.carbnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lance.carbnb.Register;

public class MainActivity extends AppCompatActivity {
    private TextView signupBtn;
    private ImageView loginArrow;
    private EditText usernameEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupBtn = (TextView) findViewById(R.id.signup_btn);
        usernameEdit = (EditText) findViewById(R.id.usernameEdt);
        loginArrow = (ImageView) findViewById(R.id.loginArw);
        passwordEdit = (EditText) findViewById(R.id.passwordeEdt);

        loginArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://192.168.0.89:8080/get-users" ;

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                             public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                // textView.setText("Response is: "+ response.substring(0,500));
                                System.out.println("Response: " + response.substring(0,500));
                                usernameEdit.setText(response);
                            }
                        },
                       new Response.ErrorListener(){
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //                textView.setText("That didn't work!");
                                    System.out.println("Error: " + error.getMessage());
                                    usernameEdit.setText(error.getMessage());
                                }
                       });
                // Add the request to the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                usernameEdit.setText("Clicked");

                try {
                    Thread.sleep(3000);
                    queue.add(stringRequest);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               }

    });

//        signupBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),Register.class);
//            }
//        });


//        loginArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getApplicationContext(), Dashboard.class);
//                startActivity(i);
//            }
//        });
//
    }
}
