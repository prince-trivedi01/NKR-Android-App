package nkr.collegeproject.com.nkr;

import android.content.Intent;
import android.os.Bundle;
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
import SQL.StudentDB;
import SQL.TeacherDB;

/**
 * Created by Prince on 23-04-2018.
 */

public class DeleteStudent extends AppCompatActivity  implements  View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_student);
        initViews();
        initListeners();
        initObjects();

    }


    private final AppCompatActivity activity = DeleteStudent.this;

    private NestedScrollView nestedScrollViewds;


    private TextInputLayout textInputLayoutDeleteStudent;


    private TextInputEditText textInputEditTextStudentDUSerId;

    private AppCompatButton appCompatButtonStudentDelete;


    private InputValidation inputValidation;
   private StudentDB StudentDataBase;







    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollViewds = (NestedScrollView) findViewById(R.id.nestedScrollViewds);


        textInputLayoutDeleteStudent = (TextInputLayout) findViewById(R.id.textInputLayoutStudentDUserId);


        textInputEditTextStudentDUSerId = (TextInputEditText) findViewById(R.id.textInputEditTextReStudentDUserId);


        appCompatButtonStudentDelete = (AppCompatButton) findViewById(R.id.appCompatButtonDeleteSubmit);


    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {

        appCompatButtonStudentDelete.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        StudentDataBase = new SQL.StudentDB(activity);
        inputValidation = new Helper.InputValidation(activity);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.appCompatButtonTeacherSignIn:
                DeleteStudents();


                break;

        }
    }

    Student student;


    public void DeleteStudents(){

        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentDUSerId, textInputLayoutDeleteStudent, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextUserId(textInputEditTextStudentDUSerId, textInputLayoutDeleteStudent, getString(R.string.error_message_userid))) {
            return;
        }

        if (StudentDataBase.deleteStudent(student)){

            Toast.makeText(this, "Delete Student Successfully",
                    Toast.LENGTH_LONG).show();

        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollViewds, getString(R.string.error_message_userid), Snackbar.LENGTH_LONG).show();
        }




    }

}
