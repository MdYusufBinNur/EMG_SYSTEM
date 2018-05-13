package bd.com.example.yusuf.emg_system;

/**
 * Created by Yusuf on 13-May-18.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseBranch extends AsyncTask<Void,Void,Integer>{
    Context c;
    String data;
    ArrayList names=new ArrayList();
    Spinner sp;

    public ParseBranch(Context c, String data, Spinner sp) {
        this.c = c;
        this.data = data;
        this.sp = sp;
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer==1)
        {
            ArrayAdapter adapter=new ArrayAdapter(c,android.R.layout.simple_list_item_1,names);
            sp.setAdapter(adapter);

        }else {
/*
            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
*/
        }
    }

    private int parse()
    {
        try
        {
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;


            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String name=jo.getString("branchname");
                names.add(name);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
