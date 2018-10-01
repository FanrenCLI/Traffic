/**=========================================添加车辆信息=======================================================**/
$("#add_taxi").click(function(){
	$( "#dialog_2" ).dialog("open");
});
$("#add_taxi_1").click(function(){
	if($("#taxi_id").val()=="" || $("#taxi_tel").val()==""){
		alert("请输入信息");
	}else{
		$.ajax({
			url:"add_taxi",
			type:"GET",
			data:{taxi_id:$("#taxi_id").val(),tel:$("#taxi_tel").val()},
			success:function(data){
				alert(data);
			}
		});
	}
});
/**=========================================删除车辆信息=======================================================**/
$("#del_taxi").click(function(){
	$( "#dialog_3" ).dialog("open");
});
$("#del_taxi_1").click(function(){
	if($("#taxi_id_1").val()=="" || $("#taxi_tel_1").val()==""){
		alert("请输入信息");
	}else{
		$.ajax({
			url:"del_taxi",
			type:"GET",
			data:{taxi_id:$("#taxi_id_1").val(),tel:$("#taxi_tel_1").val()},
			success:function(data){
				alert(data);
			}
		});
	}
});
/**=========================================修改车辆信息=======================================================**/
$("#updata_taxi").click(function(){
	$( "#dialog_4" ).dialog("open");
});
$("#updata_taxi_1").click(function(){
	if($("#old_taxi_id").val()=="" || $("#new_taxi_id").val()=="" || $("#old_taxi_tel").val()==""||$("#new_taxi_tel").val()==""){
		alert("请输入信息");
	}else{
		$.ajax({
			url:"updata_taxi",
			type:"GET",
			data:{old_taxi_id:$("#old_taxi_id").val(),new_taxi_id:$("#new_taxi_id").val(),old_taxi_tel:$("#old_taxi_tel").val(),new_taxi_tel:$("#new_taxi_tel").val()},
			success:function(data){
				alert(data);
			}
		});
	}
});
/**=========================================查询车辆信息=======================================================**/
var lon_1=[];
var lat_1=[];
var object={};
$("#query_data").click(function(){
	$( "#dialog_5" ).dialog("open");
});
$("#query_data_1").click(function(){
	$.ajax({
		url:"query_data",
		type:"GET",
		data:{query_taxi_id:$("#query_taxi_id").val()},
		dataType:"json",
		success:function(data){
			//alert(JSON.stringify(data));
			if(data[0]==undefined){
				alert("请输入正确的车牌号");
			}else{
				showTable(data);
			}
		}
	});
});
function showTable(taxiInfo){
	$("#taxi_table tbody").empty();
    var data=taxiInfo;
    $.each(data, function (index, item) {
        var Id_taxiTd = $("<td></td>").append(item.idCar);
        var telTd = $("<td></td>").append(item.tel);
        var lonTd =$("<td></td>").append(item.lon);
        var latTd = $("<td></td>").append(item.lat);
        var statusTd = $("<td></td>").append(item.status);
        var speedTd = $("<td></td>").append(item.speed + " Km/h");
        var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
            .append(" 缩放至");
        // 为编辑按钮添加自定义的属性，来表示当前员工的id
        editBtn.attr("lon", item.lon);
        editBtn.attr("lat",item.lat);
        //下面是为全局变量Data存值，为了地图显示js文件可以获取数据
        lat_1[index]=item.lat;
        lon_1[index]=item.lon;
        var btnTd = $("<td> </td>").append(editBtn);
        $("<tr></tr>").append(Id_taxiTd).append(telTd).append(lonTd).append(latTd)
            .append(statusTd).append(speedTd).append(btnTd).appendTo("#taxi_table tbody");
    });
}
/**=========================================发送信息=======================================================**/
$("#send_data").click(function(){
	$( "#dialog_10" ).dialog("open");
});
$("#show_success").click(function(){
	$("#my_success").toggle();
	setTimeout(function(){
		$("#my_success").toggle();
	},5000);
});















