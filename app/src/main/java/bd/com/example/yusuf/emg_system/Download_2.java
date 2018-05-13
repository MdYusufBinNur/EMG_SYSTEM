package bd.com.example.yusuf.emg_system;

/**
 * Created by Yusuf on 13-May-18.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Download_2 extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    Spinner sp;
    ProgressDialog pd;

    public Download_2(Context c, String urlAddress, Spinner sp) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.sp = sp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Downloading..");
        pd.setMessage("Retrieving data...Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.retrieveData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        sp.setAdapter(null);

        if(s != null)
        {

            ParseSec parseSec = new ParseSec(c,s,sp);
            parseSec.execute();
            Parser_2 p=new Parser_2(c,s,sp);
            p.execute();
            ParseBranch parseBranch = new ParseBranch(c,s,sp);
            parseBranch.execute();


        }else {
            Toast.makeText(c,"No data retrieved", Toast.LENGTH_SHORT).show();
        }
    }

    private String retrieveData()
    {
        HttpURLConnection con= Connector_2.connect(urlAddress);
        if(con==null)
        {
            return null;
        }
        try
        {
            InputStream is=con.getInputStream();

            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer receivedData=new StringBuffer();

            while ((line=br.readLine()) != null)
            {
                receivedData.append(line+"\n");
            }

            br.close();
            is.close();

            return receivedData.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
