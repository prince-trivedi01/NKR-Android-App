package nkr.collegeproject.com.nkr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import Helper.InputValidation;
import Modal.Student;
import Modal.Teacher;
import SQL.StudentDB;
import SQL.TeacherDB;

/**
 * Created by Prince on 18-04-2018.
 */

public class StudentLogin extends AppCompatActivity implements View.OnClickListener {


    private final AppCompatActivity activity = StudentLogin.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutSUserId;
    private TextInputLayout textInputLayoutSPassword;

    private TextInputEditText textInputEditTextStudentUserId;
    private TextInputEditText textInputEditTextStudentPassword;

    private AppCompatButton appCompatStudentLogin;

    private AppCompatButton appCompatStudentAttendSubmit;

    private InputValidation inputValidation;
    private StudentDB StudentDatabase;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewsl);

        textInputLayoutSUserId = (TextInputLayout) findViewById(R.id.textInputLayoutStudentReUserId);
        textInputLayoutSPassword = (TextInputLayout) findViewById(R.id.textInputLayoutStudentRePassword);

        textInputEditTextStudentUserId = (TextInputEditText) findViewById(R.id.textInputEditTextReStudentReUserId);
        textInputEditTextStudentPassword = (TextInputEditText) findViewById(R.id.textInputEditTextStudentRePassword);

        appCompatStudentLogin = (AppCompatButton) findViewById(R.id.appCompatButtonStudentLogin);

        appCompatStudentAttendSubmit = (AppCompatButton) findViewById(R.id.appCompatButtonAttendanceSubmit);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatStudentLogin.setOnClickListener(this);
        appCompatStudentAttendSubmit.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        StudentDatabase = new StudentDB(activity);
        inputValidation = new Helper.InputValidation(activity);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.appCompatButtonStudentLogin:
                verifyFromSQLite();
                break;
            case R.id.appCompatButtonAttendanceSubmit:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), TeacherSignIn.class);
                startActivity(intentRegister);
                break;
        }
    }


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
   private Context context;

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentUserId, textInputLayoutSUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextUserId(textInputEditTextStudentUserId, textInputLayoutSUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentPassword, textInputLayoutSPassword, getString(R.string.error_message_userid))) {
            return;
        }

        if (StudentDatabase.checkStudent(textInputEditTextStudentUserId.getText().toString().trim(),
                textInputEditTextStudentPassword.getText().toString().trim())) {


            Toast.makeText(this,"Login Successfully Attendance Added ",Toast.LENGTH_LONG ).show();


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_user_password), Snackbar.LENGTH_LONG).show();
        }

    }





}
