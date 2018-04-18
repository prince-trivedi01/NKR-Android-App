package nkr.collegeproject.com.nkr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import Helper.InputValidation;
import Modal.Student;
import SQL.StudentDB;

/**
 * Created by Prince on 18-04-2018.
 */
public class StudentRegistration extends AppCompatActivity implements View.OnClickListener {


    private final AppCompatActivity activity = StudentRegistration.this;


    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutStudentName;
    private TextInputLayout textInputLayoutStudentUserId;
    private TextInputLayout textInputLayoutStudentPassword;
    private TextInputLayout textInputLayoutStudentBranch;
    private TextInputLayout textInputLayoutStudentConfirmPassword;

    private TextInputEditText textInputEditTextStudentName;
    private TextInputEditText textInputEditTextStudentUserId;
    private TextInputEditText textInputEditTextStudentBranch;
    private TextInputEditText textInputEditTextStudentPassword;
    private TextInputEditText textInputEditTextStudentConfirmPassword;

    private AppCompatButton appCompatButtonStudentRegister;
    private AppCompatTextView appCompatTextViewLoginLink12;


    private InputValidation inputValidation;
    private StudentDB StudentDatabase;
    private Student student;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);
        getSupportActionBar().hide();

        initView();
        initListener();
        initObject();
    }

    /**
     * This method is to initialize views
     */
    private void initView() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewS);

        textInputLayoutStudentName = (TextInputLayout) findViewById(R.id.textInputLayoutStudentName);
        textInputLayoutStudentUserId = (TextInputLayout) findViewById(R.id.textInputLayoutStudentUserId);
        textInputLayoutStudentBranch = (TextInputLayout) findViewById(R.id.textInputLayoutStudentBranch);
        textInputLayoutStudentPassword = (TextInputLayout) findViewById(R.id.textInputLayoutStudentPassword);

        textInputLayoutStudentConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutStudentConfirmPassword);

        textInputEditTextStudentName = (TextInputEditText) findViewById(R.id.textInputEditTextStudentName);
        textInputEditTextStudentUserId = (TextInputEditText) findViewById(R.id.textInputEditTextStudentUserId);
        textInputEditTextStudentBranch = (TextInputEditText) findViewById(R.id.textInputEditTextStudentBranch);
        textInputEditTextStudentPassword = (TextInputEditText) findViewById(R.id.textInputEditTextStudentPassword);
        textInputEditTextStudentConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextStudentConfirmPassword);

        appCompatButtonStudentRegister = (AppCompatButton) findViewById(R.id.appCompatButtonStudentRegister);

        appCompatTextViewLoginLink12 = (AppCompatTextView) findViewById(R.id.appCompatTextViewLogInLink);

    }


    /**
     * This method is to initialize objects to be used
     */
    private void initObject() {
        inputValidation = new InputValidation(activity);
        StudentDatabase = new StudentDB(activity);
        student = new Student();

    }

    private void initListener() {
        appCompatButtonStudentRegister.setOnClickListener(this);
        appCompatTextViewLoginLink12.setOnClickListener(this);

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonStudentRegister:
//                StudentDatabase.deleteAll();
                postDataToSQLiteStudent();
                System.out.print(StudentDatabase.getAllStudent().toString());
                break;

            case R.id.appCompatTextViewLogInLink:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), IndexPage.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void postDataToSQLiteStudent() {


        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentName, textInputLayoutStudentName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentUserId, textInputLayoutStudentUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextUserId(textInputEditTextStudentUserId, textInputLayoutStudentUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentBranch, textInputLayoutStudentBranch, getString(R.string.error_message_branch))) {
                  return;
             }

            if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentPassword, textInputLayoutStudentPassword, getString(R.string.error_message_password))) {
                          return;
                    }
                  if (!inputValidation.isInputEditTextMatches(textInputEditTextStudentPassword, textInputEditTextStudentConfirmPassword,
                        textInputLayoutStudentConfirmPassword, getString(R.string.error_password_match))) {
                  return;
            }

            if (!StudentDatabase.checkStudent(textInputEditTextStudentUserId.getText().toString().trim())) {

                student.setStd_name(textInputEditTextStudentName.getText().toString().trim());
                student.setStd_userID(textInputEditTextStudentUserId.getText().toString().trim());
                student.setStd_branch(textInputEditTextStudentBranch.getText().toString().trim());
                student.setStd_password(textInputEditTextStudentPassword.getText().toString().trim());
                System.out.print(textInputEditTextStudentPassword.getText().toString());
                StudentDatabase.addStudent(student);

                // Snack Bar to show success message that record saved successfully
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                emptyInputEditText();


            } else {
                // Snack Bar to show error message that record already exists
                Snackbar.make(nestedScrollView, getString(R.string.error_user_exists), Snackbar.LENGTH_LONG).show();
            }


        }


        /**
         * This method is to empty all input edit text
         */

    private void emptyInputEditText() {
        textInputEditTextStudentName.setText(null);
        textInputEditTextStudentUserId.setText(null);
        textInputEditTextStudentBranch.setText(null);
        textInputEditTextStudentPassword.setText(null);
        textInputEditTextStudentConfirmPassword.setText(null);
    }

}





