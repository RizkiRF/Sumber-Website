package id.web.rizki.sourceinternet;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by RN on 10/22/2017.
 */

public class ConnectionInternetTask extends AsyncTask<String, Void, String> {

    Context ctx;

    ConnectionInternetTask(Context ct){
        ctx = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;

        String s5 = null;
        try {
                URL myUrl = new URL(s1);
                HttpURLConnection conHttp = (HttpURLConnection) myUrl.openConnection();
                conHttp.setReadTimeout(10000);
                conHttp.setConnectTimeout(20000);
                conHttp.setRequestMethod("GET");
                conHttp.connect();

                in = conHttp.getInputStream();
                BufferedReader httpBuff = new BufferedReader(new InputStreamReader(in));
                StringBuilder st = new StringBuilder();
                String line = "";

                while ((line = httpBuff.readLine()) != null) {
                    st.append(line + " \n");
                }

                httpBuff.close();
                in.close();

                return st.toString();
        } catch (Exception e) {
            String s4 = "Maaf, silahkan cek kembali koneksi anda atau URL anda, kemudian coba kembali";
            return s4;
        }

    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.hasilSource.setText(s);
    }
}
