package com.marito.sesion5;

import android.content.Context;

public class Student {
  private Context context;
  
  private String name;
  private String phone;
  private String scholarship;
  private String gender;
  private String book;
  private Boolean doSports;
  
  public Student (String name, String phone, String scholarship, String gender, String book, Boolean doSports, Context context) {
    this.name = name;
    this.phone = phone;
    this.scholarship = scholarship;
    this.gender = gender;
    this.book = book;
    this.doSports = doSports;
    this.context = context;
  }
  
  @Override
  public String toString () {
    return (this.book.isEmpty()) ?
            (findString(R.string.filed_name) + ": " + this.name + "\n" +
            findString(R.string.field_phone) + ": " + this.phone + "\n" +
            findString(R.string.field_scholarship) + ": " + this.scholarship + "\n" +
            findString(R.string.gender) + ": " + this.gender + "\n" +
            findString(R.string.do_sports) + ": " + this.doSports + "\n"
      ) : (findString(R.string.filed_name) + ": " + this.name + "\n" +
            findString(R.string.field_phone) + ": " + this.phone + "\n" +
            findString(R.string.field_scholarship) + ": " + this.scholarship + "\n" +
            findString(R.string.gender) + ": " + this.gender + "\n" +
            findString(R.string.fav_book) + ": " + this.book + "\n" +
            findString(R.string.do_sports) + ": " + this.doSports + "\n"
      );
  }
  
  private String findString(int resource){
    return context.getResources().getString(resource);
  }
}
