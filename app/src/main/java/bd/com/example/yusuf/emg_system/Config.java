package bd.com.example.yusuf.emg_system;

/**
 * Created by Yusuf on 06-May-18.
 */

public class Config {
    public static final String URL_ADD="http://192.168.56.1/android/employeemanagement/addEmployee.php";
    public static final String URL_REG="http://192.168.56.1/android/employeemanagement/registration.php";
    public static final String URL_GET_ALL="http://192.168.56.1/android/employeemanagement/getAllEmployee.php";
    public static final String URL_ADD_DEPTNAME="http://192.168.56.1/android/employeemanagement/Admin/Add/addNewDept.php";
    public static final String URL_ADD_SECNAME="http://192.168.56.1/android/employeemanagement/Admin/Add/addNewSec.php";
    public static final String URL_ADD_BRANCHNAME="http://192.168.56.1/android/employeemanagement/Admin/Add/addNewBranch.php";
    public static String urlAddress="http://192.168.56.1/android/employeemanagement/Admin/View/viewDeptInArray.php";

    //public static final String URL_GET_ALL="http://192.168.1.19/android/crudmysql/getAllEMp.php";
    public static final String URL_DELETE_EMP="http://192.168.56.1/android/employeemanagement/deleteEmployee.php?id=";
    public static final String URL_UPDATE_EMP="http://192.168.56.1/android/employeemanagement/updateEmployeeInfo.php";
    public static final String URL_GET_EMP="http://192.168.56.1/android/employeemanagement/getSingleEmployee.php?id=";
    //public static final String URL_ADD="http://192.168.56.1/android/crudmysql/addEMp.php";

    //to get newly added value like dept,section etc
    public static final String URL_GET_DEPT="http://192.168.56.1/android/employeemanagement/Admin/View/viewDept.php";
    public static final String URL_GET_SEC="http://192.168.56.1/android/employeemanagement/Admin/View/viewSection.php";
    public static final String URL_GET_BRANCH="http://192.168.56.1/android/employeemanagement/Admin/View/viewBranch.php";


    //keys that will be used to send the request to php script
    public static final String KEY_ID="id";
    public static final String KEY_EMP_ID="empid";
    public static final String KEY_EMP_EMAIL="email";
    public static final String KEY_EMP_NAME="name";
    public static final String KEY_EMP_DESG="designation";
    public static final String KEY_EMP_SAL="salary";
    public static final String KEY_EMP_date="jdate";
    public static final String KEY_EMP_DEPT="dept";
    public static final String KEY_EMP_SECTION="section";
    public static final String KEY_EMP_BGROUP="bgroup";
    public static final String KEY_EMP_GENDER="gender";
    public static final String KEY_EMP_PER_ADDRESS="peraddress";
    public static final String KEY_EMP_PRE_ADDRESS="preaddress";
    public static final String KEY_EMP_MOBILE="mobile";
    public static final String KEY_EMP_NID="nid";


    public static final String KEY_DEPT_ID="deptid";
    public static final String KEY_DEPT_NAME="deptname";
    public static final String KEY_SEC_NAME="section";
    public static final String KEY_BRANCH_NAME="branch";




    //json tag
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID="id";
    public static final String TAG_EMP_ID="empid";
    public static final String TAG_EMP_EMAIL="email";
    public static final String TAG_NAME="name";
    public static final String TAG_DESG="designation";
    public static final String TAG_SAL="salary";
    public static final String TAG_pre_address="preaddress";
    public static final String TAG_per_address="peraddress";
    public static final String TAG_date="jdate";
    public static final String TAG_GENDER="gender";
    public static final String TAG_EMP_MOBILE="mobile";
    public static final String TAG_EMP_NID="nid";
    public static final String TAG_EMP_DEPT="dept";
    public static final String TAG_EMP_SECTION="section";
    public static final String TAG_EMP_BGROUP="bgroup";

    public static final String TAG_DEPT_NAME="deptname";
    public static final String TAG_SEC_NAME="secname";
    public static final String TAG_BRANCH_NAME="branchname";


}
