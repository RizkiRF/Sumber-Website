package id.web.rizki.sourceinternet;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConnectionInternetTask con1;
    EditText webTarget;
    static TextView hasilSource;
    NetworkInfo infoInternet;

    ConnectivityManager conManager;

    Spinner spiner;

    String url;
    String protocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasilSource = (TextView) findViewById(R.id.hasil_source);
        conManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        infoInternet = conManager.getActiveNetworkInfo();

        spiner = (Spinner) findViewById(R.id.spiner_item);

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0 :
                        protocol = "http://";
                        break;
                    case 1 :
                        protocol = "https://";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void getSource(View view) {
        webTarget = (EditText) findViewById(R.id.web_target);
        url = webTarget.getText().toString();
        System.out.println(url);
        hasilSource.setText("Mengambil data...");
        if( !url.equals("")){
            con1 = new ConnectionInternetTask(this);
            con1.execute(protocol+url);
        }
        else{
            hasilSource.setText("Maaf, URL tidak boleh kosong");
        }

    }
}
