package bd.com.example.yusuf.emg_system;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewSingleEmployee extends AppCompatActivity implements View.OnClickListener{

    private EditText emp_name,emp_jdate,emp_email,emp_slary,emp_mobile,emp_nid,emp_preaddress,emp_peraddress,emp_id,emp_desg;
    private Spinner emp_bgroup,emp_dept,emp_section,emp_gender;
    private Button update,delete;
    private TextView dbid;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id = intent.getStringExtra(Config.KEY_ID);
        setContentView(R.layout.activity_view_single_employee);

        dbid = (TextView) findViewById(R.id.dbid);
        emp_name = (EditText) findViewById(R.id.reg_namee);
        emp_jdate = (EditText) findViewById(R.id.reg_joiningg);
        emp_email = (EditText) findViewById(R.id.reg_emaill);
        emp_slary = (EditText) findViewById(R.id.reg_salaryy);
        emp_mobile = (EditText) findViewById(R.id.reg_mobilee);
        emp_nid = (EditText) findViewById(R.id.reg_nidd);
        emp_preaddress = (EditText) findViewById(R.id.reg_preaddresss);
        emp_peraddress= (EditText) findViewById(R.id.reg_peraddresss);
        emp_id = (EditText) findViewById(R.id.reg_idd);
        emp_desg = (EditText) findViewById(R.id.reg_desgg);

        emp_bgroup = (Spinner) findViewById(R.id.reg_bloodd);
        emp_dept = (Spinner) findViewById(R.id.reg_deptt);
        emp_section = (Spinner) findViewById(R.id.reg_sectionn);
        emp_gender = (Spinner) findViewById(R.id.reg_genderr);

        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        getEmployee();
    }

    @Override
    public void onClick(View v) {

        if (v == update)
        {
            updateEmployee();
        }
        if (v == delete)
        {
            confirmDeleteEmployee();

        }
    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void,Void,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewSingleEmployee.this,"Fetchin Data","Wait",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler res = new RequestHandler();
                String s = res.sendGetRequestParam(Config.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee getEmployee = new GetEmployee();
        getEmployee.execute();
    }

    private void showEmployee(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_NAME);
            String salalry = c.getString(Config.TAG_SAL);
            String desg = c.getString(Config.TAG_DESG);
            String email = c.getString(Config.TAG_EMP_EMAIL);
            String nid = c.getString(Config.TAG_EMP_NID);
            String preAdd = c.getString(Config.TAG_pre_address);
            String perAdd = c.getString(Config.TAG_per_address);
            String bgroup = c.getString(Config.TAG_EMP_BGROUP);
            String section = c.getString(Config.TAG_EMP_SECTION);
            String dept = c.getString(Config.TAG_EMP_DEPT);
            String jdate = c.getString(Config.TAG_date);
            String gender = c.getString(Config.TAG_GENDER);
            String mobile = c.getString(Config.TAG_EMP_MOBILE);
            String did = c.getString(Config.TAG_ID);
            String empid = c.getString(Config.TAG_EMP_ID);

          dbid.setText(did);
          emp_id.setText(empid);
          emp_name.setText(name);
          emp_email.setText(email);
          emp_jdate.setText(jdate);
          emp_mobile.setText(mobile);
          emp_nid.setText(nid);
          emp_preaddress.setText(preAdd);
          emp_peraddress.setText(perAdd);
          emp_desg.setText(desg);
          emp_slary.setText(salalry);

          emp_bgroup.setSelected(Boolean.parseBoolean(bgroup));
          emp_gender.setSelected(Boolean.parseBoolean(gender));
          emp_dept.setSelected(Boolean.parseBoolean(dept));
          emp_section.setSelected(Boolean.parseBoolean(section));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void updateEmployee(){
        final String empname = emp_name.getText().toString().trim();
        final String empdesg = emp_desg.getText().toString().trim();
        final String empsalary = emp_slary.getText().toString().trim();
        final String empid = emp_id.getText().toString().trim();
        final String empemail = emp_email.getText().toString().trim();
        final String empjdate = emp_jdate.getText().toString().trim();
        final String empmobile = emp_mobile.getText().toString().trim();
        final String empnid = emp_nid.getText().toString().trim();
        final String emppreaddress = emp_preaddress.getText().toString().trim();
        final String empperaddress = emp_peraddress.getText().toString().trim();
        final String empbgroup = emp_bgroup.getSelectedItem().toString().trim();
        final String empsection = emp_section.getSelectedItem().toString().trim();
        final String empdept = emp_dept.getSelectedItem().toString().trim();
        final String empgender = emp_gender.getSelectedItem().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewSingleEmployee.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewSingleEmployee.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_ID,id);
                hashMap.put(Config.KEY_EMP_NAME,empname);
                hashMap.put(Config.KEY_EMP_DESG,empdesg);
                hashMap.put(Config.KEY_EMP_SAL,empsalary);
                hashMap.put(Config.KEY_EMP_ID,empid);
                hashMap.put(Config.KEY_EMP_EMAIL,empemail);
                hashMap.put(Config.KEY_EMP_date,empjdate);
                hashMap.put(Config.KEY_EMP_MOBILE,empmobile);
                hashMap.put(Config.KEY_EMP_NID,empnid);
                hashMap.put(Config.KEY_EMP_PRE_ADDRESS,emppreaddress);
                hashMap.put(Config.KEY_EMP_PER_ADDRESS,empperaddress);
                hashMap.put(Config.KEY_EMP_BGROUP,empbgroup);
                hashMap.put(Config.KEY_EMP_DEPT,empdept);
                hashMap.put(Config.KEY_EMP_SECTION,empsection);
                hashMap.put(Config.KEY_EMP_GENDER,empgender);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_UPDATE_EMP,hashMap);
                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }


    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewSingleEmployee.this, "Deleting...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewSingleEmployee.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this employee?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(ViewSingleEmployee.this,ViewAllEmployee.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
