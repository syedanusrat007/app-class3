package com.cse.mist.demopapps1;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cse.mist.demopapps1.adapter.FlowerAdapter;
import com.cse.mist.demopapps1.model.FlowerObj;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView reCycleView;
    private RecyclerView.LayoutManager layoutManager;
    private FlowerAdapter adapter;
    private List<FlowerObj> fList =   new ArrayList<FlowerObj>();
    private static final String URL_DATA = "http://services.hanselandpetal.com/feeds/flowers.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        intUi();

    }

    private void intUi() {

        reCycleView = (RecyclerView) findViewById(R.id.reCycleView);
        reCycleView.setHasFixedSize(true);
        reCycleView.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(context);
        reCycleView.setLayoutManager(layoutManager);


        loadFlowerData();

    }

    private void loadFlowerData() {

        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Loading data . . . ");
        pd.show();


        StringRequest strRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.dismiss();
                try {

                    JSONArray ar = new JSONArray(response);
                    Log.e("Array Length",""+ar.length());

                    for(int i =0;i<ar.length();i++){
                        JSONObject obj = ar.getJSONObject(i);
                        FlowerObj flower = new FlowerObj();
                        flower.setProductId(obj.getInt("productId"));
                        flower.setCategory(obj.getString("category"));
                        flower.setName(obj.getString("name"));
                        flower.setInstructions(obj.getString("instructions"));
                        flower.setPhoto(obj.getString("photo"));
                        flower.setPrice(obj.getDouble("price"));
                        fList.add(flower);
                    }



                    adapter = new FlowerAdapter(fList,context);
                    reCycleView.setAdapter(adapter);

                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Log.e("errorMessage",""+error.getMessage());
                        Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                })

     /*   {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,username);
                params.put(KEY_PASSWORD,password);
                params.put(KEY_EMAIL, email);
                return params;
            }

        }*/

                ;

        RequestQueue rQue = Volley.newRequestQueue(context);
        rQue.add(strRequest);

    }
}
