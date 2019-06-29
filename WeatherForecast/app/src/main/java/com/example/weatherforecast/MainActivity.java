package com.example.weatherforecast;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherforecast.HomeFragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    FragmentManager myFragmentManager;
    FragmentTransaction myFragmentTransaction;
    SearchView search;
    ImageView bell;
    SharedPreferences sharedpreferences;
    TextView tv2;
    public static SharedPreferences splogin;
    SharedPreferences prefs = null;
    String name,surName,edit_name,edit_surName;
    TextView txt1;
    public static String myUid;
    private long mLastPress = 0;
    private static long back_pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (SearchView) findViewById(R.id.action_search);


        myFragmentManager = getSupportFragmentManager();
        myFragmentTransaction = myFragmentManager.beginTransaction();
        myFragmentTransaction.replace(R.id.containerView, new HomeFragment()).commit();


    Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

    //setupSearchView();
}

//    private void setupSearchView() {
//
//        search.setIconifiedByDefault(false);
//        search.setOnQueryTextListener(MainActivity.this);
//        search.setSubmitButtonEnabled(true);
//        search.setQueryHint("Search");
//
//        String URL = "http://api.openweathermap.org/data/2.5/weather?q=gwalior&appid=86f6f91706b4d9bf4fcca579293d747c";
//        Toast.makeText(MainActivity.this,URL,Toast.LENGTH_LONG).show();
//        Log.d("ViewUrl=>",URL);
//
//        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
//                Log.d("response", response);
//                try {
//
//                    List list = new ArrayList();
//                    JSONObject object= new JSONObject(response);
//                    //JSONObject main = object.getJSONObject("main");
//                    Iterator<String> mains = object.keys();
//                    while(mains.hasNext()){
//                        Toast.makeText(MainActivity.this,mains.next()+"list",Toast.LENGTH_LONG).show();
//                        Log.d("keys", mains.next());
//                        JSONObject main = object.getJSONObject(mains.next());
//                        Iterator<String> keys = main.keys();
//                        while(keys.hasNext()){
//                            Log.d("values", keys.next());
//                        }
//
//                    }
////                        Toast.makeText(getContext(),object.length()+"length",Toast.LENGTH_LONG).show();
////                        for(int i =1; i<=main.length(); i++)
////                        {
//                    // JSONObject obj = main.getJSONObject(String.valueOf());
////                            Toast.makeText(getContext(),obj+"",Toast.LENGTH_LONG).show();
////                            String humidity = main.getString("humidity");
////                        Toast.makeText(getContext(),humidity+"",Toast.LENGTH_LONG).show();
////
////                            String pressure = main.getString("pressure");
////                            Toast.makeText(getContext(),pressure,Toast.LENGTH_LONG).show();
//                    //}
//
////                        JSONArray arr = obj.getJSONArray("value_slab");
////                        for (int i = 0; i < arr.length(); i++) {
////                            JSONObject job = arr.getJSONObject(i);
////
////                            String slab_id = job.getString("slab_id");
////                            String slab_value = job.getString("slab_value");
////
////                            JSONArray arr1 = job.getJSONArray("taplans");
////                            for (int j = 0; j < arr.length(); j++) {
////                                JSONObject json = arr1.getJSONObject(j);
////                                String id = json.getString("id");
////                                String plan_name = json.getString("plan_name");
////                                //SlabName.add(plan_name);
////                                Toast.makeText(MainActivity.this, plan_name, Toast.LENGTH_LONG).show();
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                if (error.networkResponse == null) {
//                    if (error.getClass().equals(TimeoutError.class)) {
//                        // Show timeout error message
//                        Toast.makeText(MainActivity.this,
//                                "Oops. Timeout error!",
//                                Toast.LENGTH_LONG).show();
//                    }
//                    Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//
//                }}
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> hm = new HashMap<String, String>();
////                    hm.put("Employee_Name",Employee_Name);
////                    hm.put("Employee_Password",Employee_Password);
////                    hm.put("Employee_City",Employee_City);
////                    hm.put("File",File);
////                    hm.put("Email",Email);
//                return hm;
//            }
//        };
//        request.setRetryPolicy(new DefaultRetryPolicy(  DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//        queue.add(request);
//    }


    @Override
    public boolean onQueryTextSubmit(String query) {

//        if(TabLayout_Adapter.results.size() > 0){
//
//
//        }else {
//            Toast.makeText(MainActivity.this, "Result Not Found", Toast.LENGTH_LONG).show();
//        }
//
//                frameLayout = (FrameLayout) findViewById(R.id.containerView);
//                txt1 = new TextView(MainActivity.this);
//                frameLayout.setBackgroundColor(Color.TRANSPARENT);
//
//
//                frameLayout.addView(txt1);
//                txt1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.search_result, 0, 0);
//                txt1.setCompoundDrawablePadding(10);
//                txt1.setText("No Result Found");
//
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                params.gravity = Gravity.CENTER;
//                params.setMargins(10, 10, 10, 10);
//                txt1.setLayoutParams(params);



        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Today.loadData(newText);
//        Tomorrow.loadData(newText);
//        fiveDays.loadData(newText);

        return false;
    }

    /**
     * Hides the soft keyboard
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            View lFocused = getCurrentFocus();
            if (lFocused != null)
                lFocused.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager lInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        lInputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }, 100);//Modified to 100ms to intercept SoftKeyBoard on Android 8 (Oreo) and hide it.
        }
    }
}



