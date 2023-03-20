package ma.emsi.currencyex_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText amount;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private RequestQueue queue;
    private StringRequest string_request;
    ArrayList<String> currencies=new ArrayList<>();
    Button btn;
    TextView txt;
    ArrayAdapter<String> model;
    //String[] currencies={"MAD","USD","EUR"};
    String[] sybmol={"Dh","$","€"};
    double[][] exchange=
            {
                    {1,0.097,0.090},
                    {10.34,1,0.93},
                    {11.07,1.07,1}
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrencies();

        spinnerFrom=findViewById(R.id.from_3);
        spinnerTo=findViewById(R.id.to_3);
        btn=findViewById(R.id.btn_3);
        txt=findViewById(R.id.result_3);
        amount=findViewById(R.id.amount_3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int rst=Integer.parseInt(amount.getText().toString());
                int f=Integer.parseInt(spinnerFrom.getSelectedItemId()+"");
                int t=Integer.parseInt(spinnerTo.getSelectedItemId()+"");

                double cal=rst*exchange[f][t];
                txt.setText(cal+" "+sybmol[t]);
            }
        });

    }

    public void getCurrencies()
    {
        String url="http://10.0.2.2/url/";
        queue= Volley.newRequestQueue(getApplicationContext());
        string_request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject json=new JSONObject(response);
                    JSONArray jsonCurrencies=json.getJSONArray("currencies");
                    for (int i = 0; i <jsonCurrencies.length() ; i++)
                    {
                        JSONObject Jsoncode=jsonCurrencies.getJSONObject(i);
                        String code=Jsoncode.getString("code");
                        currencies.add(code);
                    }
                    model=new ArrayAdapter<String>(getApplicationContext(), R.layout.item_file,currencies);
                    spinnerFrom.setAdapter(model);
                    spinnerTo.setAdapter(model);

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.i("json size:","Error :" + e.toString());
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("json error","Error : "+error.toString());
            }
        });
        queue.add(string_request);
    }
}