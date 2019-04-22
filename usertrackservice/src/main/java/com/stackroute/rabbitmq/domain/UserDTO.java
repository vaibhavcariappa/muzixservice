package com.stackroute.rabbitmq.domain;

import com.stackroute.usertrackservice.domain.Track;

import java.util.List;

public class UserDTO {

  private String username;
  private String email;
  private String password;
  private List<Track> trackList;

  public UserDTO() {
  }

  public UserDTO(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Track> getTrackList() {
    return trackList;
  }

  public void setTrackList(List<Track> trackList) {
    this.trackList = trackList;
  }

}
