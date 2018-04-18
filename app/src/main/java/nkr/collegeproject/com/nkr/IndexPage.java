package nkr.collegeproject.com.nkr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IndexPage extends AppCompatActivity {

    private AppCompatButton appCompatButtonTLogin;
    private TextView textViewRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);
        TeacherLogin();
        RegistrationPage();
    }



    private void TeacherLogin() {

        final Context context = this;


        appCompatButtonTLogin = (AppCompatButton) findViewById(R.id.appCompatButtonTeacherLogin);


        appCompatButtonTLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, TeacherLogin.class);
                startActivity(intent);

            }

        });

    }

    public void RegistrationPage(){

        final Context context = this;

       textViewRegister = (TextView) findViewById(R.id.textViewLinkRegister);
        textViewRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,  RegistrationForm.class);
                startActivity(intent);

            }


        } );

    }

}
