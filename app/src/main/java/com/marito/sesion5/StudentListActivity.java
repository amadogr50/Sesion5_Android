package com.marito.sesion5;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

public class StudentListActivity extends AppCompatActivity {
  
  public  static FirebaseFirestore fb;
  FloatingActionButton addButton;
  
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_student_list);
  
    fb = FirebaseFirestore.getInstance();
    
    addButton = findViewById(R.id.addStudent);
    
    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        Intent addStudentIntent = new Intent(StudentListActivity.this,
                AddStudentActivity.class);
        
        startActivity(addStudentIntent);
    
      }
    });
  }
}
