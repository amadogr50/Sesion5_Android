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
  
  public String getName () {
    return name;
  }
  
  public void setName (String name) {
    this.name = name;
  }
  
  public String getPhone () {
    return phone;
  }
  
  public void setPhone (String phone) {
    this.phone = phone;
  }
  
  public String getScholarship () {
    return scholarship;
  }
  
  public void setScholarship (String scholarship) {
    this.scholarship = scholarship;
  }
  
  public String getGender () {
    return gender;
  }
  
  public void setGender (String gender) {
    this.gender = gender;
  }
  
  public String getBook () {
    return book;
  }
  
  public void setBook (String book) {
    this.book = book;
  }
  
  public Boolean getDoSports () {
    return doSports;
  }
  
  public void setDoSports (Boolean doSports) {
    this.doSports = doSports;
  }
}
