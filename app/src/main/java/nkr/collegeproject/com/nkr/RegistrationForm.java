package nkr.collegeproject.com.nkr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;

/**
 * Created by Prince on 17-04-2018.
 */

public class RegistrationForm extends IndexPage {


   private AppCompatButton appCompatButtonStudentLogin;
    private AppCompatButton appCompatButtonTeacherLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        StudentRegistration();
        TeacherRegistration();
    }



    public void StudentRegistration() {
        final Context context = this;


        appCompatButtonStudentLogin = (AppCompatButton) findViewById(R.id.appCompatButtonStudentRegistration);
        appCompatButtonStudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(context, StudentRegistration.class);
                startActivity(intent);



            }
        });


    }

    public void TeacherRegistration() {

        final Context context = this;


        appCompatButtonTeacherLogin = (AppCompatButton) findViewById(R.id.appCompatButtonTeacherRegistration);
        appCompatButtonTeacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(context, TeacherRegistration.class);
                startActivity(intent);



            }
        });
    }



}
