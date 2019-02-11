package android.com.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyHandler  {


    private static  VolleyHandler volleyHandler;
private static Context context;
    private RequestQueue requestQueue;


    private VolleyHandler(Context context) {
        this.context = context;
        this.requestQueue=getRequestQueue();
    }




    public  static synchronized   VolleyHandler getInstance(Context context)
    {
        if(volleyHandler==null)
        {
            volleyHandler=new VolleyHandler(context);
        }
        return  volleyHandler;
    }


    public  RequestQueue getRequestQueue()
    {
        if(requestQueue ==null)
        {
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToQueue(Request<T> req)
    {
        getRequestQueue().add(req);


    }


}

