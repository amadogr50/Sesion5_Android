package com.marito.sesion5;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.marito.sesion5.models.Student;

public class AddStudentActivity extends AppCompatActivity {
  
  Button button_clear;
  Spinner scholarship;
  TextView name;
  TextView phone;
  RadioGroup gender;
  RadioButton genderSelected;
  AutoCompleteTextView favBook;
  CheckBox doSports;
  AlertDialog.Builder clearDialog;
  
  
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    name = findViewById(R.id.name);
    phone = findViewById(R.id.phone);
  
    button_clear = findViewById(R.id.button_clear);
    
    gender = findViewById(R.id.gender);
    
    favBook = findViewById(R.id.fav_book);
    
    ArrayAdapter<String> favBooksAdapter = new ArrayAdapter<>(this,
            android.R.layout.simple_list_item_1,
            getResources().getStringArray(R.array.fav_books));
    
    favBook.setAdapter(favBooksAdapter);
  
    scholarship = findViewById(R.id.scholarship);
  
    ArrayAdapter<String> scholarshipAdapter = new ArrayAdapter<>(this,
            android.R.layout.simple_spinner_item,
            getResources().getStringArray(R.array.scholarship));
    
    scholarship.setAdapter(scholarshipAdapter);
    
    doSports = findViewById(R.id.do_sports);

    clearDialog = new AlertDialog.Builder(this);

    clearDialog.setPositiveButton(R.string.clear, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        clear();
      }
    });

    clearDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        // User cancelled the dialog
      }
    });

    clearDialog.setMessage(R.string.dialog_clear)
            .setTitle(R.string.clear);

    final AlertDialog dialog = clearDialog.create();
  
    button_clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        dialog.show();
      }
    });
    
  }
  
  @Override
  public boolean onCreateOptionsMenu (Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_main, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    save();
    return true;
  }
  
  public void save(){
    
    boolean nameExists = false,
            phoneExists = false,
            genderIsSelected = false;
    
    if (name.getText().toString().isEmpty()) {
      name.setError(getString(R.string.error_no_name));
    } else {
      nameExists = true;
    }
    
    if (phone.getText().toString().isEmpty()) {
      phone.setError(getString(R.string.error_no_phone));
    } else {
      phoneExists = true;
    }
    
    if (genderSelected == null) {
      Toast.makeText(AddStudentActivity.this,
              getString(R.string.select_gender),
              Toast.LENGTH_SHORT).show();
    } else {
      genderIsSelected = true;
    }
    
    if (nameExists && phoneExists && genderIsSelected) {
      Student student = new Student(
              name.getText().toString(),
              phone.getText().toString(),
              scholarship.getSelectedItem().toString(),
              genderSelected.getText().toString(),
              favBook.getText().toString(),
              doSports.isChecked()
      );
      
      DocumentReference docRef = StudentListActivity.fb.collection("students").document();
      
      docRef.set(student).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure (@NonNull Exception e) {
          Log.w("firebase", "Error uploading data: " + e);
        }
      });
  
      Toast.makeText(AddStudentActivity.this, student.toString(), Toast.LENGTH_LONG).show();
    }
  }

  public void clear() {
    name.setText("");
    phone.setText("");
    favBook.setText("");
    gender.clearCheck();
  }

  public void onGenderButtonCliked (View v) {
    genderSelected = findViewById(gender.getCheckedRadioButtonId());
  }

}
