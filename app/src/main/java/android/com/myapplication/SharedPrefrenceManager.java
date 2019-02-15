package android.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefrenceManager {

    private  static  final String FILENAME="INSTAGRAMLOGIN";
private  static  final String USERNAME="username";
public  static final  String EMAIL="email";
public static  final String ID="id";

private static SharedPrefrenceManager sharedPrefrenceManager;
private static Context context;


private  SharedPrefrenceManager(Context context)
{
    this.context=context;
}

    public static synchronized SharedPrefrenceManager getInstance(Context context){



        if(sharedPrefrenceManager == null)
            sharedPrefrenceManager = new SharedPrefrenceManager(context);
        return sharedPrefrenceManager;
    }

    public void storeUserData(User user)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("FILENAME",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(USERNAME,user.getUsername());
        editor.putString(EMAIL,user.getEmail());
        editor.putInt(ID,user.getId());
        editor.apply();
    }

    public boolean isUserLoggedIn()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(USERNAME,null)!=null)
        {
            return  true;
        }
        return  false;

    }
    public  void logUserOut()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent( context,LoginActivity.class));

    }
    public  User getUserData()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        User user=new User(sharedPreferences.getInt(ID,-1),sharedPreferences.getString(USERNAME,null),sharedPreferences.getString(EMAIL,null));

        return  user;
    }

}
