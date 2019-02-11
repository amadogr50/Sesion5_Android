package com.marito.sesion5;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
  
  ListView studentList;
  FloatingActionButton addButton;
  
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_student_list);
  
    
    studentList = findViewById(R.id.student_list);
    addButton = findViewById(R.id.addStudent);
    
    String[] studentsNames = new String[] {
      "Juan", "Pepe", "Miguel", "Pepencio"
    };
    
    ArrayList<String> students = new ArrayList<>();
    
    for (int i = 0; i < studentsNames.length; i++) {
      students.add(studentsNames[i]);
    }
    
    ArrayAdapter<String> studentListAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            students);
    
    studentList.setAdapter(studentListAdapter);
    
    
    studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(StudentListActivity.this, "Hola", Toast.LENGTH_SHORT).show();
      }
    });
    
   
    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        Intent addStudentIntent = new Intent(StudentListActivity.this,
                MainActivity.class);
        
        startActivity(addStudentIntent);
    
      }
    });
  }
}
