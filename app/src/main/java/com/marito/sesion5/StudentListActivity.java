package com.marito.sesion5;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
  
  public  static FirebaseFirestore fb;
  
  ListView studentList;
  FloatingActionButton addButton;
  
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_student_list);
  
    fb = FirebaseFirestore.getInstance();
    
    studentList = findViewById(R.id.student_list);
    addButton = findViewById(R.id.addStudent);
  
    final ArrayList<String> studentArrayList = new ArrayList<>();
  
    final ArrayAdapter<String> studentListAdapter = new ArrayAdapter<>(this,
            android.R.layout.simple_list_item_1,
            studentArrayList);
  
    studentList.setAdapter(studentListAdapter);
    
    fb.collection("students").get().
            addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
      @Override
      public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
          for (QueryDocumentSnapshot document : task.getResult()) {
            studentArrayList.add((String) document.get("name"));
          }
  
          studentListAdapter.notifyDataSetChanged();
          
          Log.d("firebase", "THIS IS THE LIST: \n" + studentArrayList.toString());
        } else {
          Log.d("firebase", "Error getting documents: ", task.getException());
        }
      }
    });
    
    studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(StudentListActivity.this, "hola", Toast.LENGTH_SHORT).show();
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
