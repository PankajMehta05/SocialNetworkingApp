package android.com.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.com.myapplication.URLS.login_api;

public class LoginActivity extends AppCompatActivity {


    LinearLayout loginContainer;
    ProgressDialog progressDialog;
    AnimationDrawable animationDrawable;
    Button login_btn;

    EditText username_et, password_et;
    TextView sign_up_btn, forgot_password_btn;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginContainer = findViewById(R.id.login_container);
        animationDrawable = (AnimationDrawable) loginContainer.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        login_btn = findViewById(R.id.login_btn);

        username_et = findViewById(R.id.user_name);
        password_et = findViewById(R.id.user_password);
        progressDialog = new ProgressDialog(this);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        forgot_password_btn = findViewById(R.id.forgot_pass_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent signUpIntent = new Intent(LoginActivity.this, Sign_up.class);
                startActivity(signUpIntent);

            }
        });
        forgot_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void logIn() {

        progressDialog.setMessage("Please wait");
        progressDialog.setTitle("Log In");
        progressDialog.show();

        final String username = username_et.getText().toString();
        final String password = password_et.getText().toString();

        if (TextUtils.isEmpty(username)) {
            username_et.setError("Please fill this field");
            username_et.requestFocus();
            progressDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(password)) {

            password_et.setError("Please fill this field");
            password_et.requestFocus();
            progressDialog.dismiss();
            return;

        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLS.login_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (!jsonObject.getBoolean("error")) {
                        progressDialog.dismiss();
                        JSONObject jsonObjectUser = jsonObject.getJSONObject("user");
                        User user = new User(jsonObjectUser.getInt("id"), jsonObjectUser.getString("email"), jsonObjectUser.getString("username"));


                        //store data in shared prefrence
                        // let user log in


                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    } else {
                        Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }

            //My Changes
            //abc
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> authanticationVariables = new HashMap<>();
                authanticationVariables.put("username", username);
                authanticationVariables.put("password", password);

                return authanticationVariables;
            }
        };
        VolleyHandler.getInstance(getApplicationContext()).addToQueue(stringRequest);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
       boolean isUserLoggedIn= SharedPrefrenceManager.getInstance((getApplicationContext())).isUserLoggedIn();
       if(isUserLoggedIn)
       {
           startActivity(new Intent(LoginActivity.this,MainActivity.class));

       }
       else
       {

       }
    }
}
