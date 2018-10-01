var url = location.search; //获取url中"?"符后的字串 ('?modFlag=business&role=1')
var str = url.substr( 1 ); //substr()方法返回从参数值开始到结束的字符串；
var username=str.split("=")[1];
$("#userInfo").html(username);
/**=========================================查询用户信息=======================================================**/
$("#user_data").click(function(){
	$( "#dialog" ).dialog("open");
	$.ajax({
		url:"user_data" ,
		type: "GET",
		data:{username:$("#userInfo").html()},
		success:function(data){
			if(data.id==undefined){
				alert("尚未登录");
			}else{
				
				$("#user_id").val(data.id);
				$("#user_tel").val(data.tel);
				$("#user_name").val(data.username);
				$("#user_card").val(data.idCard);
			}
		}
	});
});
/**=========================================密码修改=======================================================**/
$("#pwd_change").click(function(){
	$( "#dialog_1" ).dialog("open");
});
$("#pwd_change_2").click(function(){
	$.ajax({
		url:"pwd_change" ,
		type: "GET",
		data:{username:username,old_pwd:$("#old_pwd").val(),new_pwd:$("#new_pwd").val()},
		success:function(data){
			alert(data);
		}
	});
});
/**=========================================密码修改=======================================================**/
$("#exit").click(function(){
	window.location.href = "http://localhost:8081/login.html";
});














