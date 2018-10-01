var timeInterval=null; 
var timeInterval_1=null;
var Data=null; 
var Data_1=null;
require([
        "esri/Map",
        "esri/views/MapView",
        "esri/Graphic",
        "esri/geometry/Point",
        "esri/widgets/Sketch/SketchViewModel",
      	"esri/layers/GraphicsLayer",
        "dojo/domReady!"
        ], function(Map,MapView,Graphic,Point,SketchViewModel,GraphicsLayer,PopupTemplate){
        	var tempGraphicsLayer = new GraphicsLayer();
      		var updateGraphic;
         var map = new Map({
            basemap:"osm",
            layers: [tempGraphicsLayer]
          });
         var view=new MapView({
            map:map,
            container:"map",
            zoom:15,
            center:[120.848550, 32.019821],
            ui:{
            	components:["attribution"]
            }
         });
/**=========================================单点显示车辆位置begin=======================================================**/
		function trackDraw(lon,lat){
			view.graphics.removeAll();
			var point;
			var markerSymbol={
			   type:"simple-marker",
			   color:[226,119,40],
			   outline:{
			     color:[225,225,225],
			     width:1
			   }
			 };
			var pointGraphic;
			point={
				type:"point",
				longitude:lon,
				latitude:lat
			};
			pointGraphic=new Graphic({
				geometry:point,
				symbol:markerSymbol
			});
			view.graphics.add(pointGraphic);
		 }
         $(document).on("click", ".edit_btn", function () {

     		var point_lon=$(this).attr("lon");
     		var point_lat=$(this).attr("lat");
     		trackDraw(point_lon,point_lat);
     		view.center=[point_lon,point_lat];
         });
         
/**=========================================单点显示车辆位置end=======================================================**/
         $("#Map_clean").click(function(){
        	 view.graphics.removeAll();
         });
/**=========================================多点显示车辆位置begin=======================================================**/
         function ManyPointDraw(lon_1,lat_1){
             var point;
             var markerSymbol={
               type:"simple-marker",
               color:[226,119,40],
               outline:{
                 color:[225,225,225],
                 width:1
               }
             };
             var pointGraphic;
             for(var i=0;i<lon_1.length;i++){
               point={
                 type:"point",
                 longitude:lon_1[i],
                 latitude:lat_1[i]
               };
               pointGraphic=new Graphic({
                 geometry:point,
                 symbol:markerSymbol
               });
               view.graphics.add(pointGraphic);
             }
           }
         $("#Map_show").click(function(){
        	 ManyPointDraw(lon_1,lat_1);
         });
         
/**=========================================多点显示车辆位置end=======================================================**/
/**=========================================轨迹追踪begin=======================================================**/

$("#track").click(function(){
	$( "#dialog_7" ).dialog("open");
});
$("#track_query").click(function(){
	if($("#track_taxi_id").val()==""){
		alert("请输入车牌号");
	}else{
		$.ajax({
			url:"track_query",
			type:"GET",
			dataType:"json",
			data:{track_taxi:$("#track_taxi_id").val(),date:$("#begin_date").val()},
			success:function(data){
				Track_Draw(data)
				Data=data;
			}
		});
	}
});
$("#clear_all").click(function(){
	view.graphics.removeAll();
});
$("#show_all").click(function(){
	if(timeInterval!=undefined){
		clearInterval(timeInterval);
	}
	view.graphics.removeAll();
	var point;
    var pointGraphic;
    var markerSymbol={
      type:"simple-marker",
      color:[226,119,40],
      outline:{
        color:[225,225,225],
        width:1
      }
    };
    var lineAtt =null;
	for(var i=0;i<Data.length;i++){
		if(Data[i].time.indexOf($("#begin_date").val())==-1){continue;}
		point={
    	        type:"point",
    	        longitude:Data[i].lon,
    	        latitude:Data[i].lat
    	      };
		lineAtt = {
	            Name: $("#track_taxi_id").val(),
	            lon: Data[i].lon,
	            lat: Data[i].lat
	          };
		pointGraphic=new Graphic({
    	        geometry:point,
    	        symbol:markerSymbol,
    	        attributes: lineAtt,
    	        popupTemplate: { 
    	            title: "{Name}",
    	            content: [{
    	              type: "fields",
    	              fieldInfos: [{
    	                fieldName: "Name"
    	              }, {
    	                fieldName: "lon"
    	              }, {
    	                fieldName: "lat"
    	              }]
    	            }]
    	          }
    	      });
    	view.graphics.add(pointGraphic);
	}
});
         
function Track_Draw(data){
	var i=0;
	view.graphics.removeAll();
	view.center=[data[0].lon,data[0].lat];
	var point;
    var pointGraphic;
    var markerSymbol={
      type:"simple-marker",
      color:[226,119,40],
      outline:{
        color:[225,225,225],
        width:1
      }
    };
    //判断起始点从哪里开始
    for(var j=0;j<data.length;j++){
    	if(data[j].time.indexOf($("#begin_date").val())!=-1){
    		i=j;
    		break;
    	}
    }
    timeInterval=setInterval(function(){
    	if(data[i].time.indexOf($("#begin_date").val())!=-1){
    	      point={
    	        type:"point",
    	        longitude:data[i].lon,
    	        latitude:data[i].lat
    	      };
    	      pointGraphic=new Graphic({
    	        geometry:point,
    	        symbol:markerSymbol
    	      });
    	      view.graphics.add(pointGraphic);
    	      i++;
    	      if(i==data.length){
    	    	  clearInterval(timeInterval);
    	      }
    	}else{
    		clearInterval(timeInterval);
    	}
	    
    },50);
};
/**=========================================轨迹追踪end=======================================================**/
/**=========================================上下客点begin=======================================================**/
$("#change_point").click(function(){
	$("#dialog_9").dialog("open");
});
$("#change_pointAnalysis").click(function(){
	$.ajax({
		url:"change_pointAnalysis",
		type:"GET",
		data:{},
		dataType:"json",
		success:function(data){
			Change_Draw(data);
			Data_1=data;
		}
	})
});
$("#init_layer").click(function(){
	map.basemap="osm";
});
$("#clean_layer").click(function(){
	view.graphics.removeAll();
});
$("#show_point").click(function(){
	if(timeInterval_1!=undefined){
		clearInterval(timeInterval_1);
	}
	view.graphics.removeAll();
	var point;
    var pointGraphic;
    var markerSymbol = {
            type: "picture-marker",  // autocasts as new PictureMarkerSymbol()
            url: "img/orange.png",
            width: "10px",
            height: "10px"
    };
	for(var i=0;i<Data_1.length;i++){
		point={
    	        type:"point",
    	        longitude:Data_1[i].lon,
    	        latitude:Data_1[i].lat
    	      };
		pointGraphic=new Graphic({
    	        geometry:point,
    	        symbol:markerSymbol
    	      });
    	view.graphics.add(pointGraphic);
	}
});
function Change_Draw(data){
	map.basemap="dark-gray-vector";
	var i=0;
	view.graphics.removeAll();
	view.center=[data[0].lon,data[0].lat];
	var point;
    var pointGraphic;
    var markerSymbol = {
            type: "picture-marker",  // autocasts as new PictureMarkerSymbol()
            url: "img/orange.png",
            width: "10px",
            height: "10px"
    };
    //判断起始点从哪里开始
    
    timeInterval_1=setInterval(function(){
    	      point={
    	        type:"point",
    	        longitude:data[i].lon,
    	        latitude:data[i].lat
    	      };
    	      pointGraphic=new Graphic({
    	        geometry:point,
    	        symbol:markerSymbol
    	      });
    	      view.graphics.add(pointGraphic);
    	      i++;
    	      if(i==data.length){
    	    	  clearInterval(timeInterval_1);
    	      }
    },50);
};















/**=========================================上下客点end=======================================================**/
});
