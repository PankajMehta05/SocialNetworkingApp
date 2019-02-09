package android.com.myapplication;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Sign_up extends AppCompatActivity {

    LinearLayout loginContainer;
    AnimationDrawable animationDrawable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginContainer=findViewById(R.id.login_container);
        animationDrawable= (AnimationDrawable) loginContainer.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);



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

