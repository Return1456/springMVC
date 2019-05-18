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

public void addUser(User user){	//����û�
	Session session=sessionFactory.getCurrentSession();
	session.save(user);
}

public void addGood(Good good){	//�������Ƭ��Ʒ
	Session session=sessionFactory.getCurrentSession();
	session.saveOrUpdate(good);
}

public void addGood(Good good,InputStream photo,String fileName,String contentType) throws Exception{ //�������Ƭ����Ʒ
	Session session=sessionFactory.getCurrentSession();
	Blob photoBlob=session.getLobHelper().createBlob(photo,photo.available());
	good.setPhoto(photoBlob);
	good.setPhotoFileName(fileName);
	good.setContentType(contentType);
	good.setGd_picture("123");
	session.saveOrUpdate(good);
}

public boolean checkUser(User user){    //����û����������Ƿ�����ݿ��еĶ�Ӧ�����ض�Ӧ�Ĳ���ֵ
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

public boolean registercheck(String username){   //����û����Ƿ���ڣ����ض�Ӧ�Ĳ���ֵ
	String hql="from User user where user.username='"+username+"'";
	Session session=sessionFactory.getCurrentSession();
	User get_user =(User) session.createQuery(hql).uniqueResult();
	if(get_user!=null){
		return false;
	}else{
		return true;
	}
}

public void addcart(int cid,int gid,int num){ //�����Ʒ�����ﳵ
	Session session=sessionFactory.getCurrentSession();
	User stu=(User) session.get(User.class, cid);
	Good gd=(Good) session.get(Good.class, gid);
	Order ord=new Order();
	ord.setGood(gd);
	ord.setUser(stu);
	ord.setNum(num);
	session.save(ord);
}

public void deleteOrder(int id){   //��ȡ���id����ɾ��order
	Session session=sessionFactory.getCurrentSession();
	Order ord=(Order) session.get(Order.class, id);
	session.delete(ord);
}

public void deletecartall(String user){ //��չ��ﳵ
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


public void readphoto(int id) throws IOException, SQLException{   //ͨ����Ʒid��ȡ��Ʒ���󣬶�ȡ��Ʒ��Ƭ
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class, id);
	String realpath=System.getProperty("evan.webapp");//��web.xml���õĻ�ȡ��Ŀ�ľ���·��
			File file1=new File(realpath+"/jsp/cartadmin/upload/"+gd.getPhotoFileName());
			if(!file1.exists()){
				if(gd.getPhotoFileName()!=null){
					   byte[] in = gd.getPhoto().getBytes(1, (int)gd.getPhoto().length());
				       //1:ʹ��File�ഴ��һ��Ҫ�������ļ�·��
				       File file = new File(realpath+"/jsp/cartadmin/upload/"+gd.getPhotoFileName()); 
				       if(!file.getParentFile().exists()){ //����ļ���Ŀ¼������
				           file.getParentFile().mkdirs(); //����Ŀ¼
				       }
				       OutputStream output = new FileOutputStream(file);
				       output.write(in);
				       output.close();
				}
			}
}

public void xiajia(int id){ //������Ʒidɾ����Ʒ
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class, id);
	String realpath=System.getProperty("evan.webapp");//��web.xml���õĻ�ȡ��Ŀ�ľ���·��
	File file=new File(realpath+"/jsp/cartadmin/upload/"+gd.getPhotoFileName());
	file.delete();
	session.delete(gd);
     }

public void gaiInfo(int id,String info){ //������Ʒid,����info������info�ֶ�
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class,id);
	gd.setGd_info(info);
	session.update(gd);
     }

public void gaiMing(int id,String name){ //������Ʒid,����name������name�ֶ�
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class,id);
	gd.setGd_name(name);
	session.update(gd);
     }

public void gaiJia(int id,Double price){ //������Ʒid,����price������price�ֶ�
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class,id);
	gd.setGd_price(price);
	session.update(gd);
     }

public void gaiStorage(int id,int storage){ //������Ʒid,����name������name�ֶ�
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good) session.get(Good.class,id);
	gd.setGd_storage(storage);
	session.update(gd);
     }

public Good getGood(int id){ //��id��ȡgood����
	Session session=sessionFactory.getCurrentSession();
	Good gd=(Good)session.get(Good.class, id);
	return  gd;
}

public List<Good> getAllgood(){	//���ذ���������Ʒ��list
	Session session=sessionFactory.getCurrentSession();
	String hql="from Good";		//hibernate ��ѯshop��
	@SuppressWarnings("unchecked")
	List<Good> list=session.createQuery(hql).list();
	return list;
}

public int getCid(String user){		//�û�����ȡ�û�id
	Session session=sessionFactory.getCurrentSession();
	String hql="from User stu where stu.username='"+user+"'";
	User stu=(User)session.createQuery(hql).uniqueResult();
	int cid=stu.getId();
	return cid;
}
}

