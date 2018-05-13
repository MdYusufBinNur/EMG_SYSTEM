package bd.com.example.yusuf.emg_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllEmployee extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private String JSON_String;
    private EditText search;
    SimpleAdapter adapter;
    SimpleAdapter spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);
        search = (EditText) findViewById(R.id.search);

        listView=(ListView) findViewById(R.id.listAll);
        listView.setOnItemClickListener(this);
        getJson();
        addSearch();
    }

    private void addSearch() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ViewAllEmployee.this.adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void getJson() {
        class GetJason extends AsyncTask<Void,Void,String>
        {
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(ViewAllEmployee.this,"Fetchin Data","Wait",false,false);

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
                String s = requestHandler.sendGetRequest(Config.URL_GET_ALL);
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
                String id = jo.getString(Config.TAG_EMP_ID);
                String did = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME);
                String desg = jo.getString(Config.TAG_DESG);
                String salary = jo.getString(Config.TAG_SAL);
                HashMap<String ,String > employee = new HashMap<>();
                employee.put(Config.TAG_ID,did);
                employee.put(Config.TAG_EMP_ID,id);
                employee.put(Config.TAG_NAME,name);
                employee.put(Config.TAG_DESG,desg);
                employee.put(Config.TAG_SAL,salary);
                arrayList.add(employee);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new SimpleAdapter(ViewAllEmployee.this,arrayList,
                R.layout.view_all_list,
                new String [] {Config.TAG_EMP_ID,Config.TAG_NAME,Config.TAG_DESG,Config.TAG_SAL},
                new int[]{R.id.empid,R.id.empname,R.id.empdesg,R.id.empsalarty});

        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        Intent intent = new Intent(this,ViewSingleEmployee.class);
        HashMap<String,String> map = (HashMap) parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.TAG_ID,empId);
        startActivity(intent);
    }
}
