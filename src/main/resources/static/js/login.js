$("#login").click(function(){
	login($("#username").val(),$("#password").val())
})

function login(username,password){
		$.ajax({
			url:"login" ,
			type: "GET",
			data:{username:username,password:password},
			success:function(data){
				if(data[0]==undefined){
					alert("用户名或密码错误");
				}else{
					Data=data;
					alert("登录成功");
					window.location.href = "http://localhost:8081/main.html?username="+data;
				}
			}
		});
}