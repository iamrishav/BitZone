package com.example.bitzone;

public class Notice {
  private String UserName , Heading , Message , Image , Time , Email;

  public Notice() {
  }

  public Notice(String userName, String heading, String message, String image, String time, String email) {
    UserName = userName;
    Heading = heading;
    Message = message;
    Image = image;
    Time = time;
    Email = email;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }

  public String getHeading() {
    return Heading;
  }

  public void setHeading(String heading) {
    Heading = heading;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }

  public String getImage() {
    return Image;
  }

  public void setImage(String image) {
    Image = image;
  }

  public String getTime() {
    return Time;
  }

  public void setTime(String time) {
    Time = time;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }
}
