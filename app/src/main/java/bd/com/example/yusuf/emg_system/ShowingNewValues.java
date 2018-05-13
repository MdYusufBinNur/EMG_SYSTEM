package bd.com.example.yusuf.emg_system;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowingNewValues extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener{

    private String JSON_String;
    private Object JSON_Object;
    SimpleAdapter adapter;
    private ListView listView;
    private TextView newvaluename,newvalueid,simpleSearch;
    private Button viewDept,viewSec,viewBranch,viewSalary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_new_values);
        listView =(ListView) findViewById(R.id.newdatalist);
        newvaluename =(TextView) findViewById(R.id.newdataname);
        newvaluename =(TextView) findViewById(R.id.newdataid);
        simpleSearch =(EditText) findViewById(R.id.simpleSearch);

        viewDept=(Button) findViewById(R.id.viewdept);
        viewSec=(Button) findViewById(R.id.viewsec);
        viewBranch=(Button) findViewById(R.id.viewbranch);
        viewSalary=(Button) findViewById(R.id.viewsalary);

        viewDept.setOnClickListener(this);
        viewSec.setOnClickListener(this);
        viewBranch.setOnClickListener(this);
        viewSalary.setOnClickListener(this);


        addSearch();

    }
    private void addSearch() {
        simpleSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ShowingNewValues.this.adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void getDept() {
        class GetJason extends AsyncTask<Void,Void,String>
        {
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(ShowingNewValues.this,"Fetchin Data","Wait",false,false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();

                JSON_String = s;
                showemployee();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendGetRequest(Config.URL_GET_DEPT);
                return s;
            }
        }
        GetJason getJason = new GetJason();
        getJason.execute();
    }
    private void getSection() {
        class GetJason extends AsyncTask<Void,Void,String>
        {
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(ShowingNewValues.this,"Fetchin Data","Wait",false,false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                JSON_String = s;
                showSection();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendGetRequest(Config.URL_GET_SEC);
                return s;
            }
        }
        GetJason getJason = new GetJason();
        getJason.execute();
    }
    private void getBranch() {
        class GetJason extends AsyncTask<Void,Void,String>
        {
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(ShowingNewValues.this,"Fetchin Data","Wait",false,false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                JSON_String = s;
                showBranch();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendGetRequest(Config.URL_GET_BRANCH);
                return s;
            }
        }
        GetJason getJason = new GetJason();
        getJason.execute();
    }

    private void showemployee() {
        JSONObject jsonObject =  null;
        ArrayList<HashMap<String ,String >> arrayList = new ArrayList<>();
        try
        {

            jsonObject = new JSONObject(JSON_String);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            for (int i = 0 ;i<=result.length();i++)
            {
                JSONObject jo = result.getJSONObject(i);
                String did = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_DEPT_NAME);
                HashMap<String ,String > employee = new HashMap<>();
                employee.put(Config.TAG_ID,did);

                employee.put(Config.TAG_DEPT_NAME,name);

                arrayList.add(employee);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new SimpleAdapter(ShowingNewValues.this,arrayList,
                R.layout.view_data,
                new String [] {Config.TAG_ID,Config.TAG_DEPT_NAME},
                new int[]{R.id.newdataid,R.id.newdataname});

        listView.setAdapter(adapter);
    }
    private void showSection() {
        JSONObject jsonObject =  null;
        ArrayList<HashMap<String ,String >> arrayList = new ArrayList<>();
        try
        {

            jsonObject = new JSONObject(JSON_String);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            for (int i = 0 ;i<=result.length();i++)
            {
                JSONObject jo = result.getJSONObject(i);
                String did = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_SEC_NAME);
                HashMap<String ,String > employee = new HashMap<>();
                employee.put(Config.TAG_ID,did);

                employee.put(Config.TAG_SEC_NAME,name);

                arrayList.add(employee);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new SimpleAdapter(ShowingNewValues.this,arrayList,
                R.layout.view_data,
                new String [] {Config.TAG_ID,Config.TAG_SEC_NAME},
                new int[]{R.id.newdataid,R.id.newdataname});

        listView.setAdapter(adapter);
    }
    private void showBranch() {
        JSONObject jsonObject =  null;
        ArrayList<HashMap<String ,String >> arrayList = new ArrayList<>();
        try
        {

            jsonObject = new JSONObject(JSON_String);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            for (int i = 0 ;i<=result.length();i++)
            {
                JSONObject jo = result.getJSONObject(i);
                String did = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_BRANCH_NAME);
                HashMap<String ,String > employee = new HashMap<>();
                employee.put(Config.TAG_ID,did);

                employee.put(Config.TAG_BRANCH_NAME,name);

                arrayList.add(employee);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new SimpleAdapter(ShowingNewValues.this,arrayList,
                R.layout.view_data,
                new String [] {Config.TAG_ID,Config.TAG_BRANCH_NAME},
                new int[]{R.id.newdataid,R.id.newdataname});

        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        if (v == viewDept)
        {
            getDept();
        }
        if (v == viewSec)
        {
            getSection();
        }
        if (v == viewBranch)
        {
            getBranch();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
