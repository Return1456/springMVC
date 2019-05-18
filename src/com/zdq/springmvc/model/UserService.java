package com.zdq.springmvc.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("userService")
@Transactional
public class UserService {
private SessionFactory sessionFactory;
@Autowired
public void  setSessionFactory(SessionFactory sessionFactory){
	this.sessionFactory=sessionFactory;
}
public SessionFactory  getSessionFactory(){
	return this.sessionFactory;
}

public void addUser(User user){	//添加用户
	Session session=sessionFactory.getCurrentSession();
	session.save(user);
}

public void addGood(Good good){	//添加无照片商品
	Session session=sessionFactory.getCurrentSession();
	session.saveOrUpdate(good);
}

public void addGood(Good good,InputStream photo,String fileName,String contentType) throws Exception{ //添加有照片的商品
	Session session=sessionFactory.getCurrentSession();
	Blob photoBlob=session.getLobHelper().createBlob(photo,photo.available());
	good.setPhoto(photoBlob);
	good.setPhotoFileName(fileName);
	good.setContentType(contentType);
	good.setGd_picture("123");
	session.saveOrUpdate(good);
}

public boolean checkUser(User user){    //检查用户名和密码是否和数据库中的对应，返回对应的布尔值
	String username=user.getUsername();
	String hql="from User user where user.username='"+username+"'";
	Session session=sessionFactory.getCurrentSession();
	User get_user =(User) session.createQuery(hql).uniqueResult();
	if(get_user!=null){
		if(get_user.getPassword().equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
	}else{
		return false;
	}
}

public boolean registercheck(String username){   //检查用户名是否存在，返回对应的布尔值
	String hql="from User user where user.username='"+username+"'";
	Session session=sessionFactory.getCurrentSession();
	User get_user =(User) session.createQuery(hql).uniqueResult();
	if(get_user!=null){
		return false;
	}else{
		return true;
	}
}

public void addcart(int cid,int gid,int num){ //添加商品到购物车
	Session session=sessionFactory.getCurrentSession();
	User stu=(User) session.get(User.class, cid);
	Good gd=(Good) session.get(Good.class, gid);
	Order ord=new Order();
	ord.setGood(gd);
	ord.setUser(stu);
	ord.setNum(num);
	session.save(ord);
}

public void deleteOrder(int id){   //获取这个id用来删除order
	Session session=sessionFactory.getCurrentSession();
	Order ord=(Order) session.get(Order.class, id);
	session.delete(ord);
}

public void deletecartall(String user){ //清空购物车
	Session session=sessionFactory.getCurrentSession();
	String hql="from Order ord where ord.user.username='"+user+"'";
 	@SuppressWarnings("unchecked")
	List<Order> list=session.createQuery(hql).list();
     Iterator<Order> it=list.iterator();
     while(it.hasNext()){
     Order ord=it.next();
     session.delete(ord);
     }
}


public void readphoto(int id) throws IOException, SQLException{   //通过商品id获取商品对象，读取商品照片
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class, id);
	String realpath=System.getProperty("evan.webapp");//从web.xml配置的获取项目的绝对路径
			File file1=new File(realpath+"/jsp/cartadmin/upload/"+gd.getPhotoFileName());
			if(!file1.exists()){
				if(gd.getPhotoFileName()!=null){
					   byte[] in = gd.getPhoto().getBytes(1, (int)gd.getPhoto().length());
				       //1:使用File类创建一个要操作的文件路径
				       File file = new File(realpath+"/jsp/cartadmin/upload/"+gd.getPhotoFileName()); 
				       if(!file.getParentFile().exists()){ //如果文件的目录不存在
				           file.getParentFile().mkdirs(); //创建目录
				       }
				       OutputStream output = new FileOutputStream(file);
				       output.write(in);
				       output.close();
				}
			}
}

public void xiajia(int id){ //传入商品id删除商品
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class, id);
	String realpath=System.getProperty("evan.webapp");//从web.xml配置的获取项目的绝对路径
	File file=new File(realpath+"/jsp/cartadmin/upload/"+gd.getPhotoFileName());
	file.delete();
	session.delete(gd);
     }

public void gaiInfo(int id,String info){ //传入商品id,和新info，更新info字段
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class,id);
	gd.setGd_info(info);
	session.update(gd);
     }

public void gaiMing(int id,String name){ //传入商品id,和新name，更新name字段
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class,id);
	gd.setGd_name(name);
	session.update(gd);
     }

public void gaiJia(int id,Double price){ //传入商品id,和新price，更新price字段
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class,id);
	gd.setGd_price(price);
	session.update(gd);
     }

public void gaiStorage(int id,int storage){ //传入商品id,和新name，更新name字段
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class,id);
	gd.setGd_storage(storage);
	session.update(gd);
     }

public Good getGood(int id){ //用id获取good对象
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good)session.get(Good.class, id);
	return  gd;
}

public List<Good> getAllgood(){	//返回包含所有商品的list
	Session session=sessionFactory.getCurrentSession();
	String hql="from Good";		//hibernate 查询shop表
	@SuppressWarnings("unchecked")
	List<Good> list=session.createQuery(hql).list();
	return list;
}

public int getCid(String user){		//用户名获取用户id
	Session session=sessionFactory.getCurrentSession();
	String hql="from User stu where stu.username='"+user+"'";
	User stu=(User)session.createQuery(hql).uniqueResult();
	int cid=stu.getId();
	return cid;
}
}

