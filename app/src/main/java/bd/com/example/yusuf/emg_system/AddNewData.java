package bd.com.example.yusuf.emg_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddNewData extends AppCompatActivity implements View.OnClickListener {

    private EditText addDept,addSec,addBranch;
    private Button addDeptbtn,addSecbtn,addBranchbtn,viewDept,viewSec,viewBranch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_data);

        addDept = (EditText) findViewById(R.id.addDept);
        addSec = (EditText) findViewById(R.id.addSection);
        addBranch = (EditText) findViewById(R.id.addBranch);

        addDeptbtn = (Button) findViewById(R.id.addDeptButton);
        addSecbtn = (Button) findViewById(R.id.addSectionBtn);
        addBranchbtn = (Button) findViewById(R.id.addBranchButton);

        viewDept  = (Button) findViewById(R.id.viewDeptButton);


        viewDept.setOnClickListener(this);
        addDeptbtn.setOnClickListener(this);
        addSecbtn.setOnClickListener(this);
        addBranchbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == viewDept )
        {

            startActivity(new Intent(AddNewData.this,ShowingNewValues.class));
        }

        if (v == addDeptbtn && addDept == null)
        {

            Toast.makeText(this,"Please Fill the required Information",Toast.LENGTH_SHORT).show();

        }
        else if (v==addDeptbtn && addDept != null)
        {
            addDeparment();
            clean();
        }
        if (v == addSecbtn)
        {

            addSection();
            clean();
        }
        if (v == addBranchbtn)
        {

            addBranch();
            clean();
        }
    }

    private void clean() {
        addDept.setText(null);
        addSec.setText(null);
        addBranch.setText(null);
    }

    private void addDeparment() {
        final String dept_name = addDept.getText().toString().trim();

        class AddDept extends AsyncTask<Void,Void,String>
        {
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog =ProgressDialog.show(AddNewData.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                Toast.makeText(AddNewData.this,s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_DEPT_NAME,dept_name);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_DEPTNAME, params);
                return res;
            }
        }

        AddDept addDept =  new AddDept();
        addDept.execute();
    }
    private void addSection() {
        final String dept_name = addSec.getText().toString().trim();

        class AddDept extends AsyncTask<Void,Void,String>
        {
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog =ProgressDialog.show(AddNewData.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                Toast.makeText(AddNewData.this,s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_SEC_NAME,dept_name);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_SECNAME, params);
                return res;
            }
        }

        AddDept addDept =  new AddDept();
        addDept.execute();
    }
    private void addBranch() {
        final String dept_name = addBranch.getText().toString().trim();

        class AddDept extends AsyncTask<Void,Void,String>
        {
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog =ProgressDialog.show(AddNewData.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                Toast.makeText(AddNewData.this,s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_BRANCH_NAME,dept_name);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_BRANCHNAME, params);
                return res;
            }
        }

        AddDept addDept =  new AddDept();
        addDept.execute();
    }
}
