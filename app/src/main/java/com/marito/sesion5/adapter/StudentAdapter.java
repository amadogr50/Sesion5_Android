package com.marito.sesion5.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marito.sesion5.R;
import com.marito.sesion5.models.Student;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
  private ArrayList<Student> students;
  private Context context;
  
  public StudentAdapter(Context context, ArrayList<Student> students) {
    this.students = students;
    this.context = context;
  }
  
  @Override
  public  StudentAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
    return new StudentViewHolder(view);
  }
  
  @Override
  public void onBindViewHolder (@NonNull StudentViewHolder holder, int position) {
    holder.bindStudent(students.get(position));
  }
  
  @Override
  public int getItemCount () {
    return students.size();
  }
  
  public class StudentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.student_name)
    TextView studentName;
    @BindView(R.id.student_scholarship)
    TextView studentScholarship;
    
    private Context context;
    
    private StudentViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      this.context = view.getContext();
    }
    
    private void bindStudent(Student student) {
      studentName.setText(student.getName());
      studentScholarship.setText(student.getScholarship());
    }
  }
  
  public interface OnStudentListener {
    void onStudentClick(int position);
  }
}
