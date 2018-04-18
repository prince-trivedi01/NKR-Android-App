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
import SQL.TeacherDB;

/**
 * Created by Prince on 18-04-2018.
 */

public class TeacherLogin extends AppCompatActivity implements View.OnClickListener{




    private final AppCompatActivity activity = TeacherLogin.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutTUserId;
    private TextInputLayout textInputLayoutTPassword;

    private TextInputEditText textInputEditTextTeacherUserId;
    private TextInputEditText textInputEditTextTeacherPassword;

    private AppCompatButton appCompatTeacherLogin;



    private InputValidation inputValidation;
    private TeacherDB TeacherDatabase;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewtl);

        textInputLayoutTUserId = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherAUserId);
        textInputLayoutTPassword = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherAPassword);

        textInputEditTextTeacherUserId = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherAUserId);
        textInputEditTextTeacherPassword = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherAPassword);

        appCompatTeacherLogin = (AppCompatButton) findViewById(R.id.appCompatButtonTeacherALogin);



    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatTeacherLogin.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        TeacherDatabase = new TeacherDB(activity);
        inputValidation = new Helper.InputValidation(activity);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.appCompatButtonTeacherALogin:
                verifyFromSQLiteTeacher();


                break;
        }
    }


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
public boolean TeacherLD(){
    return true;
}

    public void verifyFromSQLiteTeacher() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherUserId, textInputLayoutTUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextUserId(textInputEditTextTeacherUserId, textInputLayoutTUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherPassword, textInputLayoutTPassword, getString(R.string.error_message_userid))) {
            return;
        }


        if (TeacherDatabase.checkTeacher(textInputEditTextTeacherUserId.getText().toString().trim(),textInputEditTextTeacherPassword
                .getText().toString().trim())) {

            TeacherLD();
            Intent accountsIntent = new Intent(activity, LectureDetails.class);
            accountsIntent.putExtra("UserId", textInputEditTextTeacherUserId.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
            Toast.makeText(this, "Login Successfully",
                    Toast.LENGTH_LONG).show();

        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_user_password), Snackbar.LENGTH_LONG).show();
        }

    }


    private void emptyInputEditText() {
        textInputEditTextTeacherUserId.setText(null);
        textInputEditTextTeacherPassword.setText(null);
    }



}
