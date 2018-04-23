package Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Modal.Student;
import nkr.collegeproject.com.nkr.R;

/**
 * Created by Prince on 18-04-2018.
 */

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder> {

    private List<Student> listStudent;

    public StudentRecyclerAdapter(List<Student>listStudent)
    {
        this.listStudent=listStudent;
        return;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // inflating recycler item view
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student_recycler,parent,false);

        return new StudentViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(StudentViewHolder holder,int position){
        holder.textViewStudentName.setText(listStudent.get(position).getStd_name());
        holder.textViewStudentUserId.setText(listStudent.get(position).getStd_userID());
        holder.textViewStudentBranch.setText(listStudent.get(position).getStd_branch());
    }

    @Override
    public int getItemCount(){
        Log.v(StudentRecyclerAdapter.class.getSimpleName(),""+listStudent.size());
        return listStudent.size();
    }


    /**
     * ViewHolder class
     */
    public class StudentViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewStudentName;
        public AppCompatTextView textViewStudentUserId;
        public AppCompatTextView textViewStudentBranch;

        public StudentViewHolder(View view) {
            super(view);
            textViewStudentName = (AppCompatTextView) view.findViewById(R.id.textViewStudentName);
            textViewStudentUserId = (AppCompatTextView) view.findViewById(R.id.textViewSudentBranch);
            textViewStudentBranch = (AppCompatTextView) view.findViewById(R.id.textViewStudentUserId);

        }
    }


}
