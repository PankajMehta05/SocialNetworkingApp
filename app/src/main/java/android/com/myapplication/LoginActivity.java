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
    AnimationDrawable animationDrawable;
    Button login_btn;

    EditText username_et,password_et;
    TextView sign_up_btn,forgot_password_btn;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginContainer=findViewById(R.id.login_container);
        animationDrawable= (AnimationDrawable) loginContainer.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
login_btn=findViewById(R.id.login_btn);

        username_et=findViewById(R.id.user_name);
        password_et=findViewById(R.id.user_password);
        progressBar=new ProgressBar(this);
        sign_up_btn=findViewById(R.id.sign_up_btn);
        forgot_password_btn=findViewById(R.id.forgot_pass_btn);


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
                Intent signUpIntent=new Intent(LoginActivity.this,Sign_up.class);
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

       String username=username_et.getText().toString();
       String password=password_et.getText().toString();

       if(TextUtils.isEmpty(username))

       {
           username_et.setError("Please fill this field");
           username_et.requestFocus();
           return;
       }
       if(TextUtils.isEmpty(password))
        {

            password_et.setError("Please fill this field");
            password_et.requestFocus();
            return;

        }

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLS.login_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

            //My Changes
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

if(animationDrawable!=null && animationDrawable.isRunning())
{
 animationDrawable.start();
}
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(animationDrawable!=null&&animationDrawable.isRunning())
        {
            animationDrawable.stop();
        }
    }

}
