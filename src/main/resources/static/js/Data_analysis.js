/**=========================================空驶率查询功能begin=======================================================**/

$("#KSL_analysis").click(function(){
	$( "#dialog_6" ).dialog("open");
});
$("#KSL_query").click(function(){
	if($("#KSL_taxi_id").val()==""){
		$.ajax({
			url:"KSL_query_NOId",
			type:"GET",
			dataType: "json",
			data:{start:$("#start_data").val(),end:$("#end_data").val()},
			success:function(data){
				//alert(JSON.stringify(data));
				DrawTable(data);
			}
		});
	}else{
		$.ajax({
			url:"KSL_query",
			type:"GET",
			dataType: "json",
			data:{start:$("#start_data").val(),end:$("#end_data").val(),taxi_id:$("#KSL_taxi_id").val()},
			success:function(data){
				DrawTable(data);
				//alert(JSON.stringify(data));
			}
		});
	}
});
function DrawTable(data){
	
	var dom = document.getElementById("container");
	var myChart = echarts.init(dom);
	var app = {};
	var data_time=[];
	var data_KSL=[];
	option = null;
	for(var i=0;i<data.length;i++){
		data_time[i] = data[i].time;
		data_KSL[i] = (data[i].ksl*100).toFixed(1);
	}

	var yMax = 100;
	var data_KSLShadow = [];

	for (var i = 0; i < data_KSL.length; i++) {
		data_KSLShadow.push(yMax);
	}

	option = {
	    title: {
	        text: '车辆空驶率',
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow',
	            label: {
	                show: true
	            }
	        }
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    xAxis: {
	        type :'category',
	        data: data_time,
	        axisLabel: {
	            textStyle: {
	                color: '#255'
	            }
	        },
	        axisTick: {
	            show: false
	        },
	        axisLine: {
	            show: false
	        },
	        z: 10
	    },
	    yAxis: {
	        type : 'value',
	        axisLabel: {
	            textStyle: {
	                color: '#999'
	            },
	            formatter: '{value} %'
	        }
	    },
	    dataZoom: [
	        {
	           type: 'inside',
	           xAxisIndex:[0],
	           start: 0,
	           end: 100
	        },
	        {
	            type: 'slider',
	            show: true,
	            xAxisIndex: [0],
	            start: 0,
	            end: 10
	        },
	    ],
	    
	    series: [
	        {
	            name:'空驶率',
	            type: 'bar',
	            animationDelay: function (idx) {
	                return idx * 10 + 100;
	            },
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#83bff6'},
	                            {offset: 0.5, color: '#188df0'},
	                            {offset:	 1, color: '#188df0'}
	                        ]
	                    )
	                },
	                emphasis: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#2378f7'},
	                            {offset: 0.7, color: '#2378f7'},
	                            {offset: 1, color: '#83bff6'}
	                        ]
	                    )
	                }
	            },
	            data: data_KSL

	        }
	    ]
	};

	// Enable data zoom when user click bar.
	var zoomSize = 6;
	myChart.on('click', function (params) {
	    console.log(data_time[Math.max(params.dataIndex - zoomSize / 2, 0)]);
	    myChart.dispatchAction({
	        type: 'dataZoom',
	        startValue: data_time[Math.max(params.dataIndex - zoomSize / 2, 0)],
	        endValue: data_time[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
	    });
	});;
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}
/**=========================================空驶率查询功能end=======================================================**/
/**=========================================轨迹追踪功能begin=======================================================**/
//见loadmap.js中
/**=========================================轨迹追踪功能end=======================================================**/
/**=========================================平均速度功能begin=======================================================**/
$("#speed").click(function(){
	$( "#dialog_8" ).dialog("open");
});
$("#speed_query").click(function(){
	if($("#speed_taxi_id").val()==""){
		alert("请输入车牌号")
	}else{
		$.ajax({
			url:"speed_query",
			tpye:"GET",
			data:{speed_taxi_id:$("#speed_taxi_id").val(),speed_date:$("#speed_start_data").val()},
			success:function(data){
				DrawSpeedTable(data);
			},
			error:function(){
				alert("车牌号输入错误！");
			}
		});
	}
});
function DrawSpeedTable(data){
	var taxi_status=[];
	var taxi_time=[];
	var taxi_speed=[];
	var taxi_averageSpeed=0;
	var num=0;
	var j=0;
	for(var i=0;i<data.length;i++){
		if(data[i].time.indexOf($("#speed_start_data").val())!=-1){
			taxi_status[j]=data[i].status;
			taxi_time[j]=data[i].time;
			taxi_speed[j]=data[i].speed;
			j++;
			if(data[i].status=="重车"){
				taxi_averageSpeed=taxi_averageSpeed+parseFloat(data[i].speed);
				num++;
			}
		}
		
	}
	taxi_averageSpeed=taxi_averageSpeed/num;
	/**图表绘制**/
	var dom = document.getElementById("speed_table");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	option = {
	    title : {
	        text: '车辆日行驶速度图',
	        x: 'center',
	        align: 'right'
	    },
	    grid: {
	        bottom: 80
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross'
	        },
	        formatter:"{b}<br/>{a0}: {c0} km/h<br/>{a1}: {c1}"
	    },
	    legend: {
	        data:['速度','载客状态'],
	        x: 'left'
	    },
	    dataZoom: [
	        {
	            show: true,
	            realtime: true,
	            start: 65,
	            end: 85
	        },
	        {
	            type: 'inside',
	            realtime: true,
	            start: 65,
	            end: 85
	        }
	    ],
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            axisLine: {onZero: false},
	            data : taxi_time.map(function (str) {
	                return str.replace(' ', '\n')
	            })
	        }
	    ],
	    yAxis: [
	        {
	            name: '速度(Km/h)',
	            type: 'value',
	            max: 100
	        },
	        {
	        	name: '载客状态',
	            type : 'category',
	            nameLocation:"start",
	            boundaryGap : false,
	            axisLine: {onZero: false},
	            data:["空车","重车","","",""],
	            inverse: true
	        }
	    ],
	    series: [
	        {
	            name:'速度',
	            type:'line',
	            smooth:true,
	            symbol: 'none',
	            areaStyle: {
	                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                    offset: 0,
	                    color: 'rgb(255, 158, 68)'
	                }, {
	                    offset: 1,
	                    color: 'rgb(255, 70, 131)'
	                }])
	            },
	            lineStyle: {
	                width: 1
	            },
	            markLine:{
	            	data:[
	            		{
	            	        name: '载客均速',
	            	        yAxis: taxi_averageSpeed
	            	    }
	            	]
	            },
	            data:taxi_speed
	        },
	        {
	            name:'载客状态',
	            type:'line',
	            yAxisIndex:1,
	            lineStyle: {
	                width: 1
	            },
	            symbol: 'none',
	            areaStyle: {
	                        normal: {
	                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                                offset: 0,
	                                color: 'rgb(0, 191, 255)'
	                            }, {
	                                offset: 1,
	                                color: 'rgb(65, 105, 255)'
	                            }])
	                        }
	                    },
	            data: taxi_status
	        }
	    ]
	};
	;
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}

/**=========================================平均速度功能end=======================================================**/
/**=========================================上下客点功能begin=======================================================**/
//见LoadMap.js
/**=========================================上下客点功能end=======================================================**/


