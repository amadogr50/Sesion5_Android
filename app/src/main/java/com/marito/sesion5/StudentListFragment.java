package com.marito.sesion5;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.marito.sesion5.adapter.StudentAdapter;
import com.marito.sesion5.models.Student;

import java.util.ArrayList;
public class StudentListFragment extends Fragment {
  
  SwipeRefreshLayout swipeRefreshLayout;
  
  RecyclerView recyclerView;
  //Getting students from Firebase
  ArrayList<Student> students;
  //Adapter for representing Student data
  StudentAdapter studentAdapter;
  
  @Override
  public void onActivityCreated (@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  
    students = new ArrayList<>();
    studentAdapter = new StudentAdapter(getActivity(),students);
  
    //Set layout manager
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
  
    //Set adapter
    recyclerView.setAdapter(studentAdapter);
  
    //Set item animator to DefaultAnimator
    recyclerView.setItemAnimator(new DefaultItemAnimator());
  
    getStudentsFromFB();
  }
  
  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  
    View rootView = inflater.inflate(R.layout.fragment_student_list, container, false);
    
    //Get a reference to recyclerView
    recyclerView = rootView.findViewById(R.id.list);
    
    //Get a reference to swipeRefreshLayout
    swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh () {
        getStudentsFromFB();
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), "Students list has been updated", Toast.LENGTH_SHORT).show();
      }
    });
    
    return rootView;
  }
  
  public void getStudentsFromFB () {
    StudentListActivity.fb.collection("students").get().
            addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
              @Override
              public void onComplete (@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  if (task.getResult().size() != students.size()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                      //Student Creation
                      Student student = new Student((String) document.get("name"),
                              (String) document.get("phone"),
                              (String) document.get("scholarship"),
                              (String) document.get("gender"),
                              (String) document.get("book"),
                              (Boolean) document.get("doSports"));
                    
                      //New student addition to students ArrayList
                      students.add(student);
                    }
                  }
                
                  //Notify new data has been added
                  studentAdapter.notifyDataSetChanged();
                
                  Log.d("firebase", "Getting documents successfully completed");
                } else {
                  Log.e("firebase", "Error getting documents: ", task.getException());
                }
              }
            });
  }

}
