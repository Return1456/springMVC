  function xiajia(id,name){
 	 if(confirm("确认下架  "+name))
  	{
  	window.location.href="xiajia?id="+id;
  	}
  }
  function refresh(){
window.location.href="admin";
  }
  var a1,a2,a3,a4;
function sub(){	
a1=document.getElementById("q1").value;
a2=document.getElementById("q2").value;
a3=document.getElementById("q3").value;
a4=document.getElementById("q4").value;
if(a2==""){
alert("价格不能为空！");
}else if(a4==""){
alert("库存不能为空！");
}else{
document.getElementById("name").setAttribute("value", a1);
document.getElementById("info").setAttribute("value", a3);
document.getElementById("price").setAttribute("value", a2);
document.getElementById("storage").setAttribute("value", a4);
document.getElementById("f6").submit();  
}

}

function addgood(){	
a1=document.getElementById("addprice").value;
a2=document.getElementById("addstorage").value;
a3=document.getElementById("addname").value;
if(a1==""){
alert("价格不能为空！");
}else if(a2==""){
alert("库存不能为空！");
}else if(a3==""){
alert("商品名字不能为空！");
}else{
document.getElementById("addgood1").submit();  
}

}
    function updatePrice(price)
  {      if(isNaN(price)){
        alert("请输入数字!");
        document.getElementById("q2").value="";
        }else{
		document.getElementById("price").setAttribute("value", price);
        }
  }
      function updateStorage(storage)
  {	   
  if(storage%1===0){
    document.getElementById("storage").setAttribute("value", storage);
  } else{
  alert("请输入整数！");
  document.getElementById("q4").value="";
  }      

  }
  
  
  
      function updatePrice1(price)
  {      if(isNaN(price)){
        alert("请输入数字!");
        document.getElementById("addprice").value="";
        }
  }
  
        function updateStorage1(price)
  {      if(isNaN(price)){
        alert("请输入整数!");
        document.getElementById("addstorage").value="";
        }
  }
  
  