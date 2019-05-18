package com.zdq.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zdq.springmvc.model.Good;
import com.zdq.springmvc.model.UserService;

@Controller
public class CartControll {
	private UserService userService;
	@Autowired
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	
	@RequestMapping("/cart")
	public String goCart(){ //��ת�����ﳵ
		return "cart/cart";
	}
	
	@RequestMapping("/shop")
	public String goShop(){	//��ת���̵�
		return "cart/shop";
	}
	
	@RequestMapping("/add_good")
	public String goaddgood(){	//��ת�������Ʒ��ҳ��
		return "cartadmin/addGood";
	}
	@RequestMapping("/admin")
	public String gooprategood(){	//��ת�������Ʒ��ҳ��
		return "cartadmin/admin";
	}
	
	@RequestMapping("/addcart") //�����Ʒ�����ﳵ
	public String addCart(@RequestParam("cid")int cid,@RequestParam("gid")int gid,@RequestParam("num")int num){
		userService.addcart(cid, gid, num);
		return "cart/cart";
	}
	
	@RequestMapping("/deletecart") //�ӹ��ﳵɾ����Ʒ
	public String deleteCart(@RequestParam("id")int id){
		userService.deleteOrder(id);
		return "cart/cart";
	}
	@RequestMapping("/deletecartall") //��չ��ﳵ
	public String deleteCart(@RequestParam("user")String user){
		userService.deletecartall(user);
		return "cart/cart";
	}
	
	@RequestMapping("/addgood") //�����Ʒ
	public String addgood(Good good,@RequestParam(value="uploadphoto",required=false)MultipartFile uploadphoto) throws Exception, IOException{
		if(uploadphoto.isEmpty()){
			userService.addGood(good);
		}else{
			String realpath=System.getProperty("evan.webapp");//��web.xml���õĻ�ȡ��Ŀ�ľ���·��
			String uploadpath=realpath+"/jsp/cartadmin/upload";
			//String fileName=uploadphoto.getOriginalFilename();
			String contentType=uploadphoto.getContentType();
			String[] s=contentType.split("/");
			String filetype=s[1];
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date dt = new Date(System.currentTimeMillis());
			String rndFilename = fmt.format(dt)+"."+filetype;
			File file=new File(uploadpath+"/"+rndFilename);
			uploadphoto.transferTo(file);
			userService.addGood(good, uploadphoto.getInputStream(), rndFilename, contentType);
		}
		return "cartadmin/admin";
	}
	
	@RequestMapping("/gaipicture") //����Ϣ
	public String Gaipicture(@RequestParam("id")int id,@RequestParam(value="storage",required=false)int storage,@RequestParam(value="name",required=false)String name,@RequestParam(value="price",required=false)Double price,@RequestParam(value="info",required=false)String info,@RequestParam(value="uploadphoto",required=false)MultipartFile uploadphoto) throws IOException, Exception{
		Good gd=userService.getGood(id);
		if(storage>0){
			gd.setGd_storage(storage);
		}
		if(name!=null){
			gd.setGd_name(name);
		}
		if(info!=null){
			gd.setGd_info(info);
		}
		if(price>=0){
			gd.setGd_price(price);
		}
		if(!uploadphoto.isEmpty()){
			String realpath=System.getProperty("evan.webapp");//��web.xml���õĻ�ȡ��Ŀ�ľ���·��
			String uploadpath=realpath+"/jsp/cartadmin/upload";
			//String fileName=uploadphoto.getOriginalFilename();
			String contentType=uploadphoto.getContentType();
			String[] s=contentType.split("/");
			String filetype=s[1];
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date dt = new Date(System.currentTimeMillis());
			String rndFilename = fmt.format(dt)+"."+filetype;
			File file=new File(uploadpath+"/"+rndFilename);
			uploadphoto.transferTo(file);
			File oldfile=new File(realpath+"jsp/cartadmin/upload/"+gd.getPhotoFileName());
			oldfile.delete();
			userService.addGood(gd, uploadphoto.getInputStream(), rndFilename, contentType);
		}else{
			userService.addGood(gd);
		}
		
		return "cartadmin/admin";
	}
	
	@RequestMapping("/xiajia") //�¼���Ʒ
	public String deleteGood(@RequestParam("id")int id){
		userService.xiajia(id);
		return "cartadmin/admin";
	}
		

}
