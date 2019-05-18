package com.zdq.springmvc.model;

import javax.persistence.*;
@Entity
@Table(name="Usertable")
public class User {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@Column(name="username")
private String username;
@Column(name="password")
private String password;


public void setId(int id){
	this.id=id;
}
public int getId(){
	return this.id;
}

public void setUsername(String username){
	this.username=username;
}
public String getUsername(){
	return this.username;
}
public void setPassword(String password){
	this.password=password;
}
public String getPassword(){
	return this.password;
}
}
