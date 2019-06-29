package com.example.weatherforecast;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherforecast.model.CardviewModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Today extends Fragment {
    ArrayList<CardviewModel> items = new ArrayList<>();

    public static TodayAdapter myAdapter;
    ArrayList<String> temp = new ArrayList<>();
    String id, data;
    ArrayList<String> arr = new ArrayList<>();
    String URL;
    String idData;
    String tid;
    ArrayList<String> myVal = new ArrayList<>();
    int i = 0;
    Bitmap loadingIcon;
    public static RecyclerView recyclerView;
    private final int visibleThreshold = 1;
    Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


         View rootView = inflater.inflate(R.layout.today_layout, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new TodayAdapter(recyclerView, getActivity(), items);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        System.out.println("Recycle View Add Adapter");

       // recyclerView.setHasFixedSize(true);
//        if (rootView != null)
//            rootView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    InputMethodManager lInputManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                    lInputManager.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
//                }
//            }, 100);//Modified to 100ms to intercept SoftKeyBoard on Android 8 (Oreo) and hide it.
        //Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        toolbar.setVisibility(View.GONE);



        jsonFatchTenderData();


//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0) {
//                    // Recycle view scrolling down...
//
//                    if(recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) == false){
//
//                        View toastView = getLayoutInflater().inflate(R.layout.activity_toast_custom_view, null);
//                        Toast toast = new Toast(context);
//                        toast.setView(toastView);
//                        toast.setDuration(Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0,0);
//                        toast.show();
//
//                        pageCount = pageCount + 10;
//                        Log.d("PageCount",pageCount+"");
//                        jsonFatchTenderData();
//
//                    }
//
//                }
//            }
//        });
//
//              openLoading();

        return rootView;
    }

    private void jsonFatchTenderData() {
        try {

             idData = "86f6f91706b4d9bf4fcca579293d747c";
            URL = "http://api.openweathermap.org/data/2.5/weather?q=gwalior&appid=idData";
            Toast.makeText(getContext(),URL,Toast.LENGTH_LONG).show();
            Log.d("ViewUrl=>",URL);

            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                    Log.d("response", response);
                    try {

                        List list = new ArrayList();
                        JSONObject object= new JSONObject(response);
                        JSONObject main = object.getJSONObject("main");

                        JSONObject wind = object.getJSONObject("wind");



                        Toast.makeText(getContext(),object.length()+"length",Toast.LENGTH_LONG).show();
//                        for(int i =1; i<=main.length(); i++)
//                        {

                            //String humidity = main.getString("humidity");
                        //Toast.makeText(getContext(),humidity+"",Toast.LENGTH_LONG).show();

                            String pressure = main.getString("pressure");
                            Toast.makeText(getContext(),pressure+"pressure",Toast.LENGTH_LONG).show();
                        String humidity = main.getString("humidity");
                        Toast.makeText(getContext(),humidity+"humidity",Toast.LENGTH_LONG).show();

                        String speed = wind.getString("speed");
                       Toast.makeText(getContext(),speed+"speed",Toast.LENGTH_LONG).show();
                        String timezone = object.getString("timezone");
                       Toast.makeText(getContext(),timezone+"timezone",Toast.LENGTH_LONG).show();



                        CardviewModel data = new CardviewModel();
                        data.setTime(timezone);
                        data.setWind(speed);
                        data.setPressure(pressure);
                        data.setHumidity(humidity);

                        items.add(data);

                        myAdapter.notifyItemRangeChanged(0,myAdapter.getItemCount());
                        myAdapter.notifyDataSetChanged();





//                        JSONArray arr = obj.getJSONArray("value_slab");
//                        for (int i = 0; i < arr.length(); i++) {
//                            JSONObject job = arr.getJSONObject(i);
//
//                            String slab_id = job.getString("slab_id");
//                            String slab_value = job.getString("slab_value");
//
//                            JSONArray arr1 = job.getJSONArray("taplans");
//                            for (int j = 0; j < arr.length(); j++) {
//                                JSONObject json = arr1.getJSONObject(j);
//                                String id = json.getString("id");
//                                String plan_name = json.getString("plan_name");
//                                //SlabName.add(plan_name);
//                                Toast.makeText(MainActivity.this, plan_name, Toast.LENGTH_LONG).show();

                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if (error.networkResponse == null) {
                        if (error.getClass().equals(TimeoutError.class)) {
                            // Show timeout error message
                            Toast.makeText(getContext(),
                                    "Oops. Timeout error!",
                                    Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }}
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> hm = new HashMap<String, String>();
                    hm.put("appid",idData);
//                    hm.put("Employee_Password",Employee_Password);
//                    hm.put("Employee_City",Employee_City);
//                    hm.put("File",File);
//                    hm.put("Email",Email);
                    return hm;
                }
            };
            request.setRetryPolicy(new DefaultRetryPolicy(  DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(request);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void loadData(String newText) {
        if(myAdapter != null) {
            myAdapter.getFilter().filter(newText);
            recyclerView.setAdapter(myAdapter);

        }}

}


