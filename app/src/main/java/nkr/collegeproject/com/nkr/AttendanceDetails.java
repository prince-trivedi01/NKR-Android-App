package nkr.collegeproject.com.nkr;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import Adapter.StudentRecyclerAdapter;
import Modal.Student;
import SQL.StudentDB;

/**
 * Created by Prince on 17-04-2018.
 */

public class AttendanceDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_details);

        getSupportActionBar().setTitle("");
        initViews();
        initObjects();
        FinalSubmit();

    }

    private AppCompatButton appCompatButtonFinalSubmit;
    private AppCompatActivity activity = AttendanceDetails.this;
    private AppCompatTextView textViewStudentNameA;
    private RecyclerView recyclerViewStudent;
    private List<Student> listStudent;
    private StudentRecyclerAdapter studentRecyclerAdapter;
    private StudentDB StudentDataBase;



    public void FinalSubmit() {
        final Context context = this;


        appCompatButtonFinalSubmit = (AppCompatButton) findViewById(R.id.appCompatButtonFinalSubmit);
        appCompatButtonFinalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(context, IndexPage.class);
                startActivity(intent);



            }
        });


    }



    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewStudentNameA = (AppCompatTextView) findViewById(R.id.textViewStudentNameAD);
        recyclerViewStudent = (RecyclerView) findViewById(R.id.recyclerViewStudent);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listStudent = new ArrayList<>();
        studentRecyclerAdapter = new StudentRecyclerAdapter(listStudent);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewStudent.setLayoutManager(mLayoutManager);
        recyclerViewStudent.setItemAnimator(new DefaultItemAnimator());
        recyclerViewStudent.setHasFixedSize(true);
        recyclerViewStudent.setAdapter(studentRecyclerAdapter);
        StudentDataBase = new StudentDB(activity);

        String studentFromIntent = getIntent().getStringExtra("UserId");
        textViewStudentNameA.setText(studentFromIntent);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listStudent.clear();
                listStudent.addAll(StudentDataBase.getAllStudent());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                studentRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();



    }


}
