package com.zdq.springmvc.model;
import java.sql.Blob;

import javax.persistence.*;
@Entity
@Table(name="shop2")
public class Good { //商品持久化类
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@Column(name="SP_NAME")
private String gd_name;
	@Column(name="SP_PIC")
private String gd_picture;
	@Column(name="SP_PRICE")
private Double gd_price;
	@Column(name="SP_INFO")
private String gd_info;
	@Column(name="SP_STORAGE")
private int gd_storage;
private Blob photo;
private String photofileName;
private String contentType;
	public void setContentType(String contentType){
		this.contentType=contentType;
	}
	public String getContentType(){
		return this.contentType;
	}

	public void setPhoto(Blob photo){
		this.photo=photo;	
	}
	public Blob getPhoto(){
		return this.photo;
	}
	public void setPhotoFileName(String photoFileName){
		this.photofileName=photoFileName;
	}
	public String getPhotoFileName(){
		return this.photofileName;
	}
	public void setGd_storage(int num){
		this.gd_storage=num;
	}
	public int getGd_storage(){
		return this.gd_storage;
	}
	public void setGd_number(int num){
		this.id=num;
	}
	public int getGd_number(){
		return this.id;
	}
	public void setGd_name(String name){
		this.gd_name=name;
	}
	public String getGd_name(){
		return this.gd_name;
	}
	public void setGd_picture(String pic){
		this.gd_picture=pic;
	}
	public String getGd_picture(){
		return this.gd_picture;
	}
	public void setGd_price(Double pri){
		this.gd_price=pri;
	}
	public Double getGd_price(){
		return this.gd_price;
	}
	public void setGd_info(String info){
		this.gd_info=info;
	}
	public String getGd_info(){
		return this.gd_info;
	}
}
