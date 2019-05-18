package com.zdq.springmvc.model;
import javax.persistence.*;
@Entity
@Table(name="order1")
public class Order {	//订单持久化类
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name="cust_id")
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name="sp_id")
    private Good good;
    @Column(name="num")
    private int num;
    public void setNum(int num){
    	this.num=num;
    }
    public int getNum(){
    	return this.num;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public Good getGood() {
        return this.good;
    }

    public void setUser(User customer) {
        this.user = customer;
    }

    public void setGood(Good good) {
        this.good = good;
    }
}