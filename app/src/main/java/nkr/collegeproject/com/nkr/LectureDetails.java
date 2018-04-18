package nkr.collegeproject.com.nkr;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Helper.InputValidation;
import Modal.Teacher;
import Modal.Teacher;
import SQL.TeacherDB;

import static android.R.attr.onClick;

/**
 * Created by Prince on 17-04-2018.
 */

public class LectureDetails extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture_details);
        initViews();
        initObjects();
        initListeners();


    }

    private InputValidation inputValidation;
  //  private TeacherDB TeacherDatabase;
    private Teacher Teacher;

    View view;
    private final AppCompatActivity activity = LectureDetails.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutTime;


    private TextInputLayout textInputLayoutTeacherBranch;
    private TextInputLayout textInputLayoutTeacherSemester;
    private TextInputLayout textInputLayoutTeacherSubject;

    private TextInputEditText textInputEditTextTeacherSemester;
    private TextInputEditText textInputEditTextTeacherSubject;
    private TextInputEditText textInputEditTextTeacherBranch;



    private TextInputEditText textInputEditTextTime;

    private AppCompatButton appCompatButtonLectureDetailsSubmit;
    private Teacher teacher;

    Context context;


    private TeacherDB TeacherDatabase;

    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewld);


        textInputLayoutTime = (TextInputLayout) findViewById(R.id.textInputLayoutTime);


        textInputLayoutTeacherBranch = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherBranch);

        textInputLayoutTeacherSemester = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherSemester);
        textInputLayoutTeacherSubject = (TextInputLayout) findViewById(R.id.textInputLayoutTeacherSubject);

        textInputEditTextTeacherBranch = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherBranch);
        textInputEditTextTeacherSemester = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherSemester);
        textInputEditTextTeacherSubject = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherSubject);


        textInputEditTextTime = (TextInputEditText) findViewById(R.id.textInputEditTextTime);

        appCompatButtonLectureDetailsSubmit = (AppCompatButton) findViewById(R.id.appCompatButtonTeacherLectureD);



    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLectureDetailsSubmit.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        TeacherDatabase = new TeacherDB(activity);
        Teacher = new Teacher();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonTeacherLectureD:
                getTeacherDetails();


                break;
        }




    }




    public void  getTeacherDetails(){

        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherBranch, textInputLayoutTeacherBranch, getString(R.string.error_message_branch))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherSemester, textInputLayoutTeacherSemester, getString(R.string.error_message_semester))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherSubject, textInputLayoutTeacherSubject, getString(R.string.error_message_subject))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextTime, textInputLayoutTime, getString(R.string.error_message_time))) {
            return;
        }


        if (TeacherDatabase.addTime(textInputEditTextTime.getText().toString())) {

            Teacher.setT_branch(textInputEditTextTeacherBranch.getText().toString().trim());
            Teacher.setT_semester(textInputEditTextTeacherSemester.getText().toString().trim());
            Teacher.setT_subject(textInputEditTextTeacherSubject.getText().toString().trim());
            Teacher.setT_time(textInputEditTextTime.getText().toString().trim());
            TeacherDatabase.addLectureDetails(Teacher);
            Intent accountsIntent = new Intent(activity, StudentLogin.class);
            startActivity(accountsIntent);
            Toast.makeText(this, "Lecture Details Entered Successfully",
                    Toast.LENGTH_LONG).show();



        } else{
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_wrongdetails), Snackbar.LENGTH_LONG).show();
        }



    }




}
