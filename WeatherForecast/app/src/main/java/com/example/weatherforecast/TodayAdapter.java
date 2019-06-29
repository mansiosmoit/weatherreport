package com.example.weatherforecast;



/**
 * Created by Windows7 on 06-07-2018.
 */

/**
 * Created by Windows7 on 06-07-2018.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
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
//import com.example.weatherforecast.R;
//import com.example.weatherforecast.Today;
import com.example.weatherforecast.model.CardviewModel;


import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class TodayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    public int num = 1;
    Activity activity;
    public static ArrayList<CardviewModel> filterResult;
    ArrayList<CardviewModel> myItem = new ArrayList<>();
    HashSet<String>list=new HashSet<String>();
    String tenderlist;
    SharedPreferences pref;
    SharedPreferences.Editor ed;
    String value;
    Context context;
    String work,state,tid,pac,date,statename;
    String tenderArray;
    CardviewModel item;
    public static HashSet<String> tenderId=new HashSet<>();
    public static ArrayList<CardviewModel> results;

    public TodayAdapter(RecyclerView recyclerView, Activity activity, ArrayList<CardviewModel> items) {
        this.activity = activity;
        this.myItem = items;
        getFilter();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.viewed_item_layout, parent, false);
            return new ItemView(view);

        }
        return null;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                results = new ArrayList<CardviewModel>();
                if (filterResult == null)

                    filterResult = myItem;


                if (constraint != null) {
                    if (filterResult != null && filterResult.size() > 0) {
                        for (final CardviewModel tender : filterResult) {
                            if (tender.getTime().toLowerCase().contains(constraint.toString())
                                    || tender.getWind().toLowerCase().contains(constraint.toString())
                                    || tender.getPressure().toLowerCase().contains(constraint.toString())
                                    || tender.getHumidity().toLowerCase().contains(constraint.toString()))
                                results.add(tender);
                        }

                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                myItem = (ArrayList<CardviewModel>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemViewType(int position) {
        return myItem.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
//
//    public void setLoadMore(LoadMore loadMore) {
//        this.loadMore = loadMore;
//    }





    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position, List<Object> payloads) {


        if (holder instanceof ItemView) {
            item = myItem.get(position);
            final ItemView viewHolder = (ItemView) holder;



            if(item.getTime().equals("null") || item.getTime().equals("")){
                viewHolder.tvtime.setText("NA");
            }else{
                viewHolder.tvtime.setText(item.getTime());
                Log.d("item.getTime()",item.getTime());
            }

            if(item.getWind().equals("null") || item.getWind().equals("")){
                viewHolder.tvwind.setText("NA");
            }else{
                viewHolder.tvwind.setText(item.getWind());
            }

            if(item.getPressure().equals("null") || item.getPressure().equals("")){
                viewHolder.tvpressure.setText("NA");
            }else{
                viewHolder.tvpressure.setText(item.getPressure());
            }

            if(item.getHumidity().equals("null") || item.getHumidity().equals("")){
                viewHolder.tvhumidity.setText("NA");
            }else{
                viewHolder.tvhumidity.setText(item.getHumidity());
            }

//            if(item.getDate().equals("null") || item.getDate().equals("")){
//                viewHolder.tvDate.setText("NA");
//            }else{
//                viewHolder.tvDate.setText(item.getDate());
//            }

//            if(item.getView_date().equals("null") || item.getView_date().equals("")){
//                viewHolder.tvId2.setText("NA");
//            }else{
//                viewHolder.tvId2.setText(item.getView_date());
//            }
//            if(item.getView_on().equals("null") || item.getView_on().equals("")){
//                viewHolder.tvId1.setText("NA");
//            }else{
//                viewHolder.tvId1.setText(item.getView_on());
//            }

//            if(item.getTenderarea().equals("null") || item.getTenderarea().equals("")){
//                viewHolder.tvAreaWork.setText("NA");
//            }else{
//                viewHolder.tvAreaWork.setText(item.getTenderarea());
//            }

//            if(item.getKeyword_name().equals("null") || item.getKeyword_name().equals("")){
//                viewHolder.tvKeyword.setText("NA");
//            }else{
//                viewHolder.tvKeyword.setText(item.getKeyword_name());
//            }



            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                Context context;
                @Override
                public void onClick(final View v) {
                   // myItem.get(position).setSelected(true);


                    Gson gson = new Gson();
                    tenderlist = gson.toJson(list);

                    work = item.getTime();
                    statename = item.getWind();
                    date = item.getPressure();
                    pac = item.getHumidity();




                //    String url =GlobalClass.Ip+"mobile_controller/recent_view_tenders?mobile_request=1&user_id="+ RelevantTender.userId+"&tender_ids=" + tenderlist;
String url = "";

                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            Log.d("response", response);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error.networkResponse == null) {
                                if (error.getClass().equals(TimeoutError.class)) {
                                    // Show timeout error message
                                    Toast.makeText(v.getContext(),
                                            "Something Went Wrong, Please try Again After Sometime",
                                            Toast.LENGTH_LONG).show();
                                }
                                Toast.makeText(v.getContext(), "Something Went Wrong, Please try Again After Sometime", Toast.LENGTH_LONG).show();

                            }
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> hm = new HashMap<String, String>();
                            hm.put("tid", tenderlist);
                            return hm;
                        }
                    };
                    request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestQueue queue = Volley.newRequestQueue(v.getContext());
                    queue.add(request);
                }

               // private void onResume() {
//                    list.add(myItem.get(position).getWind());
//                }


                });

            }

        }



    @Override
    public int getItemCount() {


        return myItem.size();
    }


    class ItemView extends RecyclerView.ViewHolder {

        TextView tvtime;
        TextView tvwind;
        TextView tvpressure;
        TextView tvhumidity;
        //TextView tvPac;
        ImageView eye,share;
        //ImageView like;
       // TextView tvDate;
        CardView cardView;
        Context context;
        //TextView tvKeyword;
       // TextView tvAreaWork;
        TextView tvId2;
        TextView tvId1;




        public ItemView(View itemView) {
            super(itemView);

            tvtime=(TextView)itemView.findViewById(R.id.tvtime);
            tvwind=(TextView)itemView.findViewById(R.id.tvwind);
            tvpressure=(TextView)itemView.findViewById(R.id.tvpressure);
            tvhumidity=(TextView)itemView.findViewById(R.id.tvhumidity);
//            tvPac=(TextView)itemView.findViewById(R.id.tvPac);
//            tvKeyword=(TextView)itemView.findViewById(R.id.tvKeyword);
//            tvDate=(TextView)itemView.findViewById(R.id.tvDate);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
//            eye=(ImageView)itemView.findViewById(R.id.eye);
//            share=(ImageView)itemView.findViewById(R.id.share);
            //like=(ImageView)itemView.findViewById(R.id.like);
//            ;tvAreaWork=(TextView)itemView.findViewById(R.id.tvAreaWork)
//            tvId1=(TextView)itemView.findViewById(R.id.tvId1);
//            tvId2=(TextView)itemView.findViewById(R.id.tvId2);



        }
    }
}




