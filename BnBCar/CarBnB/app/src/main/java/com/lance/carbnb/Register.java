package com.lance.carbnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;


public class Register extends AppCompatActivity{
    LinearLayout login;
    EditText emailSignUp, userNameSignUp, pwSignUp;
    ImageView arrowSignUp;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = (LinearLayout) findViewById(R.id.login);
        emailSignUp = (EditText) findViewById(R.id.emailSignUp);
        userNameSignUp = (EditText) findViewById(R.id.userSignUp);
        pwSignUp = (EditText) findViewById(R.id.pasSignUp);
        arrowSignUp = (ImageView) findViewById(R.id.arwSignUp);

        //listener
        arrowSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validating if the text field is empty or not.
                if (emailSignUp.getText().toString().isEmpty() || pwSignUp.getText().toString().isEmpty() || userNameSignUp.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Please enter all the values", Toast.LENGTH_SHORT).show();
                }

                String url = "http://192.168.1.71:8080/add-user";

                StringRequest stringRequest =  new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                // textView.setText("Response is: "+ response.substring(0,500));
                                System.out.println("Response: " + response);
                                emailSignUp.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error: "+error.getMessage());
                        emailSignUp.setText(error.getMessage());
                    }
                    protected Map<String, String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("id" , "8");
                        params.put("email", emailSignUp.getText().toString());
                        params.put("username", userNameSignUp.getText().toString());
                        params.put("password", pwSignUp.getText().toString());
                        return params;
                    }
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> headers = new HashMap<String, String>();
                        headers.put("Content-Type","application/json");
                        return headers;
                    }

                });
                requestQueue = Volley.newRequestQueue(Register.this);
                requestQueue.add(stringRequest);

            }
        });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}

