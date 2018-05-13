package bd.com.example.yusuf.emg_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class Registration extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{


    private EditText emp_name,emp_jdate,emp_email,emp_slary,emp_mobile,emp_nid,emp_preaddress,emp_peraddress,emp_id,emp_desg;
    private Spinner emp_bgroup,emp_dept,emp_section,emp_gender;
    private Button submit,view;

    private ImageView image;

    private Bitmap bitmap;

    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationctivity);

        emp_name = (EditText) findViewById(R.id.reg_name);
        emp_jdate = (EditText) findViewById(R.id.reg_joining);
        emp_email = (EditText) findViewById(R.id.reg_email);
        emp_slary = (EditText) findViewById(R.id.reg_salary);
        emp_mobile = (EditText) findViewById(R.id.reg_mobile);
        emp_nid = (EditText) findViewById(R.id.reg_nid);
        emp_preaddress = (EditText) findViewById(R.id.reg_preaddress);
        emp_peraddress= (EditText) findViewById(R.id.reg_peraddress);
        emp_id = (EditText) findViewById(R.id.reg_id);
        emp_desg = (EditText) findViewById(R.id.reg_desg);

        emp_bgroup = (Spinner) findViewById(R.id.reg_blood);
        emp_dept = (Spinner) findViewById(R.id.reg_dept);
        emp_section = (Spinner) findViewById(R.id.reg_section);
        emp_gender = (Spinner) findViewById(R.id.reg_gender);

        image = (ImageView) findViewById(R.id.reg_image);

        submit = (Button) findViewById(R.id.submit);
        view = (Button) findViewById(R.id.view);


        Download_2 bg_group=new Download_2(Registration.this,Config.URL_GET_BRANCH,emp_bgroup);
        Download_2 em_dept=new Download_2(Registration.this,Config.URL_GET_DEPT,emp_dept);
        Download_2 em_sec=new Download_2(Registration.this,Config.URL_GET_SEC,emp_section);
        bg_group.execute();
        em_dept.execute();
        em_sec.execute();

        submit.setOnClickListener(this);
        view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == submit)
        {

            addemployee();
            cleanable();
        }
        if (v == view)
        {
            startActivity(new Intent(Registration.this,ViewAllEmployee.class));
        }
    }

    private void cleanable() {
        emp_id.setText(null);
        emp_name.setText(null);
        emp_desg.setText(null);
        emp_slary.setText(null);
        emp_preaddress.setText(null);
        emp_peraddress.setText(null);
        emp_nid.setText(null);
        emp_mobile.setText(null);
        emp_email.setText(null);

    }

    private void addemployee() {

        final String name = emp_name.getText().toString().trim();
        final String empid = emp_id.getText().toString().trim();
        final String email = emp_email.getText().toString().trim();
        final String desg = emp_desg.getText().toString().trim();
        final String nid = emp_nid.getText().toString().trim();
        final String mobile = emp_mobile.getText().toString().trim();
        final String preAdrs = emp_preaddress.getText().toString().trim();
        final String perAdrs = emp_peraddress.getText().toString().trim();
        final String salary = emp_slary.getText().toString().trim();
        final String date = emp_jdate.getText().toString().trim();
        final String bgroup = emp_bgroup.getSelectedItem().toString().trim();
        final String dept = emp_dept.getSelectedItem().toString().trim();
        final String section = emp_section.getSelectedItem().toString().trim();
        final String gender = emp_gender.getSelectedItem().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String>
        {

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(Registration.this,"Adding..","Wait",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                Toast.makeText(Registration.this,s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_ID,empid);
                params.put(Config.KEY_EMP_EMAIL,email);
                params.put(Config.KEY_EMP_NID,nid);
                params.put(Config.KEY_EMP_MOBILE,mobile);
                params.put(Config.KEY_EMP_PRE_ADDRESS,preAdrs);
                params.put(Config.KEY_EMP_PER_ADDRESS,perAdrs);
                params.put(Config.KEY_EMP_SAL,salary);
                params.put(Config.KEY_EMP_date,date);
                params.put(Config.KEY_EMP_DEPT,dept);
                params.put(Config.KEY_EMP_SECTION,section);
                params.put(Config.KEY_EMP_BGROUP,bgroup);
                params.put(Config.KEY_EMP_GENDER,gender);
                params.put(Config.KEY_EMP_DESG,desg);
               // params.put(Config.KEY_EMP_IMG,img);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                //String res = rh.sendPostRequest(Config.URL_REG, params);
                return res;
            }
        }
        AddEmployee addEmployee = new AddEmployee();
        addEmployee.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,ViewSingleEmployee.class);
        HashMap<String,String> map = (HashMap) parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.TAG_ID,empId);
        startActivity(intent);
    }
}
