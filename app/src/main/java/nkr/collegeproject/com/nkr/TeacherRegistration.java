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
import Modal.Teacher;
import SQL.TeacherDB;

/**
 * Created by Prince on 18-04-2018.
 */
public class TeacherRegistration extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = TeacherRegistration.this;

    private NestedScrollView nestedScrollView1;

    private TextInputLayout textInputLayoutTeacherName;
    private TextInputLayout textInputLayoutTeacherUserId;
    private TextInputLayout textInputLayoutTeacherPassword;
    private TextInputLayout textInputLayoutTeacherConfirmPassword;

    private TextInputEditText textInputEditTextTeacherName;
    private TextInputEditText textInputEditTextTeacherUserId;
    private TextInputEditText textInputEditTextTeacherPassword;
    private TextInputEditText textInputEditTextTeacherConfirmPassword;

    private AppCompatButton appCompatButtonTeacherRegister;
    private AppCompatTextView appCompatTextViewLoginLink;


    private InputValidation inputValidation;
    private TeacherDB TeacherDatabase;
    private Teacher Teacher;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_registration);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView1 = (NestedScrollView) findViewById(R.id.nestedScrollViewT);

        textInputLayoutTeacherName = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherName);
        textInputLayoutTeacherUserId = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherUserId);
        textInputLayoutTeacherPassword = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherPassword);
        textInputLayoutTeacherConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherConfirmPassword);

        textInputEditTextTeacherName = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherName);
        textInputEditTextTeacherUserId = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherUserId);
       textInputEditTextTeacherPassword = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherPassword);
        textInputEditTextTeacherConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherConfirmPassword);

        appCompatButtonTeacherRegister = (AppCompatButton) findViewById(R.id.appCompatButtonTeacherRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonTeacherRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        TeacherDatabase = new TeacherDB(activity);
        Teacher = new Teacher();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonTeacherRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), IndexPage.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void postDataToSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherName, textInputLayoutTeacherName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherUserId, textInputLayoutTeacherUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextUserId(textInputEditTextTeacherUserId, textInputLayoutTeacherUserId, getString(R.string.error_message_userid))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherPassword, textInputLayoutTeacherPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextTeacherPassword, textInputEditTextTeacherConfirmPassword,
                textInputLayoutTeacherConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!TeacherDatabase.checkTeacher(textInputEditTextTeacherUserId.getText().toString())) {

            Teacher.setT_name(textInputEditTextTeacherName.getText().toString().trim());
            Teacher.setT_userID(textInputEditTextTeacherUserId.getText().toString().trim());

            Teacher.setT_password(textInputEditTextTeacherPassword.getText().toString().trim());
            TeacherDatabase.addTeacher(Teacher);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView1, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView1, getString(R.string.error_user_exists), Snackbar.LENGTH_LONG).show();
        }


    }




    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextTeacherName.setText(null);
        textInputEditTextTeacherUserId.setText(null);

        textInputEditTextTeacherPassword.setText(null);
        textInputEditTextTeacherConfirmPassword.setText(null);
    }


}
