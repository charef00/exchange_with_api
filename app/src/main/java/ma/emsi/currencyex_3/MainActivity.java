package ma.emsi.currencyex_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText amount;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    Button btn;
    TextView txt;
    ArrayAdapter<CharSequence> model;
    String[] currencies={"MAD","USD","EUR"};
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
        model=ArrayAdapter.createFromResource(this,R.array.currencies, R.layout.item_file);
        spinnerFrom=findViewById(R.id.from_3);
        spinnerTo=findViewById(R.id.to_3);
        btn=findViewById(R.id.btn_3);
        txt=findViewById(R.id.result_3);
        amount=findViewById(R.id.amount_3);
        spinnerFrom.setAdapter(model);
        spinnerTo.setAdapter(model);

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
}