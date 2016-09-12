option = null;
function randomData() {
    return Math.round(Math.random()*1000);
}
option = {
    title: {
        text: '在岗总人数',
        subtext: randomData(),
        left: 'left',
        textStyle:{
		    color:'#7ab8f9'
		},
		subtextStyle:{
		    color:'#1bff2f',
		    fontFamily:'digital-7__mono',
		    fontSize:'50'
		}
    },
    tooltip: {
        trigger: 'item',
        textStyle:{
		    color:'#33bced'
		},
		padding:0,
		margin:0,
		backgroundColor:'none',
		border:'none',
        formatter: function (params,ticket,callback){
            var name = params.name;
            var value = params.value;
            var res ='<div style="margin:0;background:url(images/地图浮框.9.png)no-repeat;background-size: 100% 100% ;text-align:center;padding:0;width:150%;padding-top:20%;box-shadow: 2px 2px 10px #32bbec">'
            	+'<p style="background:#32bbec;color:black;padding:0;margin:0;width:70%;margin-left:auto;margin-right:auto;font-size:1px;font-family:"造字工房悦圆常规体", Arial, Helvetica, sans-serif;">'+name+'</p><p style="margin:0;color:#32bced;padding:0;">人数</p><p style="margin:0;padding:0;color:#11d320;font-family:digital-7__mono, Arial, Helvetica, sans-serif; ">'+value+'</p></div>';
                    //设置自定义数据的模板，这里的模板是图片
            console.log(res);
/*            setTimeout(function (){
                // 仅为了模拟异步回调
                callback(ticket, res);//回调函数，这里可以做异步请求加载的一些代码
            }, 1000)
            return 'loading';*/
            return res;
        },alwaysShowContent:true
    },
    grid:{
    	top:0,
    	height:'90%',
    	containLabel:true
    },
    series: [
        {
            name: '11',
            type: 'map',
            mapType: '全国',
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    areaColor: '#001F4D',
                    borderColor: '#85c7ff',
                    borderWidth: 2,
                    opacity:0.5
                },
                emphasis: {
                    areaColor: '#003A92',
                    opacity:0.5
                }
            },
            left:'10%',
            right:'10%',
            top:0,
            bottom:0,
            data:[
                {name: '东北地区',value: randomData() },
                {name: '华北地区',value: randomData() },
                {name: '华东地区',value: randomData() },
                {name: '西北地区',value: randomData() },
                {name: '西南地区',value: randomData() },
                {name: '华中地区',value: randomData() },
                {name: '华南地区',value: randomData() }
            ]
        }
    ]
};

		var barOption = {
			title: {
	            text: '',
	            left: 'left',
		        top: 5,
	        },
			legend:{
				data:['员工数量','总订单数量','人效'],
				bottom:5,
				right:30,
				textStyle:{
		        	color:'#7ab8f9'
		        }
			},
		    tooltip : {
		        trigger: 'item',
		        textStyle:{
		        	color:'#7ab8f9'
		        }
		    },
		    xAxis: {
		    	type:'category',
		    	data:['周一','周二','周三','周四','周五','周六','周日'],
		    	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		        },
		       	splitLine:{  
                    show:false  
                }
	        },
	        grid:{
	        	width:'85%',
	        	left:'10%',
	        	top:'30%',
	        	bottom:'15%',
	        	containLabel:true
	        },
	        yAxis: [
	        {
	        	type:'value',
	        	name: '数量（个）',
	        	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		        } ,
		        splitLine:{  
                    show:false  
                }
	        },{
	        	type:'value',
	        	name: '百分比（%）',
	        	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		        } ,
		        splitLine:{  
                    show:false  
                }
	        	 
	        }
	        ],
            color: ['#32bbec','#9035B1',  '#11d320'],
	        series: [
	            {
		            name: '员工数量',type: 'bar',
		            label:{
		            	normal:{
		            		show:true,
		            		position:'top'
		            	},
		            },
		            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
	        	},
	        	{
		            name: '总订单数量',type: 'bar',
		            label:{
		            	normal:{
		            		show:true,
		            		position:'top'
		            	},
		            },
		            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
	        	},
	        	{
		            name: '人效',type: 'line',
		            label:{
		            	normal:{
		            		show:true,
		            		position:'top'
		            	},
		            },
		            symbol:'circle',
		            yAxisIndex:1,
		            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
	        	},
	        ]
		};

	    var barOption1 = {
			title: {
	            text: '',
	            left: 'center',
		        top: 5,
	        },
			legend:{
				data:['正式员工','非正式员工','占比'],
				bottom:5,
				right:30,
				textStyle:{
		        	color:'#7ab8f9'
		        }
			},
		    tooltip : {
		        trigger: 'item',
		        textStyle:{
		        	color:'#7ab8f9'
		        }
		    },
		    xAxis: {
		    	type:'category',
		    	data:['周一','周二','周三','周四','周五','周六','周日'],
		    	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		       },
		       	splitLine:{  
                    show:false  
                }
	        },
	        grid:{
	        	width:'85%',
	        	left:'10%',
	        	top:'30%',
	        	bottom:'15%',
	        	containLabel:true
	        },
	        yAxis: [
	        {
	        	type:'value',
	        	name: '数量（个）',
	        	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		        },
		        splitLine:{  
                    show:false  
                }
	        },{
	        	type:'value',
	        	name: '百分比（%）',
	        	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		        },
		        splitLine:{  
                    show:false  
                }
	        }
	        ],
            color: ['#32bbec','#9035B1',  '#11d320'],
	       	series: [
	            {
		            name: '正式员工',type: 'bar',
		            label:{
		            	normal:{
		            		show:true,
		            		position:'top'
		            	},
		            },
		            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
	        	},
	        	{
		            name: '非正式员工',type: 'bar',
		            label:{
		            	normal:{
		            		show:true,
		            		position:'top'
		            	},
		            },
		            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
	        	},
	        	{
		            name: '占比',type: 'line',
		            label:{
		            	normal:{
		            		show:true,
		            		position:'top'
		            	},
		            },
		            symbol:'circle',
		            yAxisIndex:1,
		            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
	        	},
	        ]
		};
		
		var barOption2 = {
			title: {
	            text: '实时人效监控',
	            left: 'left',
		        top: 5,
		        textStyle:{
		        	color:'#33bced'
		        }
	        },
			legend:{
				data:['平均人效'],
				bottom:5,
				textStyle:{
		        	color:'#fff'
		      	},
		     	show:false
			},
		    tooltip : {
				trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
	        grid:{
	        	width:'85%',
	        	left:'10%',
	        	bottom:0,
	        	containLabel:true
	        },
		    xAxis: {
		    	type:'value',
		    	left:'10%',
		    	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		     	},
		    	show:false
	       },
	        yAxis: [
	        {
	        	type:'category',
	        	data:['xx分拣中心','xx分拣中心','xx分拣中心','xx分拣中心','xx分拣中心'],
		    	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		       },
	        }
	        ],
          	series: [
	            {
		            name: '平均人效',
		            type: 'bar',
		            label:{
		            	normal:{
		            		show:true,
		            		position:'right'
		            	},
		            },
		            itemStyle: {
		                normal: {
		                    color: function(params) {
		                        // build a color map as your need.
		                        var colorList = [
		                          '#7ab8f9','#fedd1b'
		                        ];
		                        return colorList[params.dataIndex%2]
		                    }
		                }
	               },
	               data: [randomData(),randomData(),randomData(),randomData(),randomData()]
            }
	        ]
		};
		
		var pieOption = {
			title: {
	            text: '',
	            left: 'center',
		        top: 5,
	        },
		    tooltip : {
		        trigger: 'item'
		    },
			legend:{
				data:['正式员工','非正式员工'],
				bottom:0,
				right:0,
				textStyle:{
		        	color:'#fff'
		      	}
			},
		    series : [
		        {
		            name:'',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '45%'],
		            center: ['65%', '50%'],
		            label: {
		                normal: {
		                    position: 'outside',
		                    show:false
		                },
		                
		            },
		            //因为有visualMap这个是无效的
		            color: ['#FE8C02',  '#9035B1'],
		            labelLine: {
		                normal: {
		                    show: true
		                }
		            },
//		            roseType: 'area',
		            data:[
			            {
			            	name:'正式员工',
			            	value:randomData()
			            },
			            {
			            	name:'非正式员工',
			            	value:randomData()
			            }
		            ]
		        }
		    ]
		};

		var enlarged = false;
		$(document).ready(function(){
			init ('全国'); 
		});
		
		function init (mapName){
			$("#areaTip").html(mapName||"全国");
		    mapchart = echarts.init(document.getElementById('map'));
			barchart = echarts.init(document.getElementById('effect'));
			barchart1 = echarts.init(document.getElementById('count'));
			barchart2 = echarts.init(document.getElementById('bar'));
			piechart = echarts.init(document.getElementById('pie'));
			$.ajax({
				url: 'geoJson/'+mapName+'.json',
				type: "get",
				dataType: "json",
				success: function (chinaJson) {
				    echarts.registerMap(mapName, chinaJson);   
				    getData(null,'全国');
				},statusCode: {404: function() {init ('全国');}}
			});
			mapchart.on('click', function (param){
				var name=param.name;
				//在中国地图上要去掉这几个地方的点击事件 直辖市 台湾 
				if(!name.match(/^北京|^天津|^重庆|^上海|在线|离线|台湾/)||name!=""){
					$("#areaTip").html(name||"全国");
				    var id=param.data.id;
					$.ajax({
						url: 'geoJson/'+name+'.json',
						type: "get",
						dataType: "json",
						success: function (chinaJson) {
						    echarts.registerMap(name, chinaJson);   
						    getData(id,'全国');
						},
					  statusCode: {404: function() {init ('全国');}}
					});
			    }
			}); 
		};

		function getData(id,name){
			searchMap(id,name);
			searchBar2(id,name);
			searchOrderNumber(id,name);
			if(!enlarged){
				searchBar(id,name);
				searchBar1(id,name);
			}
		};
		
		function searchMap(id,name) {
			var url = "chart/getMapData.do?id="+id;
			$.ajax({
				url: url,
				type: "post",
				dataType: "text",
				success: function (data) {
					makeMapData(data,name);
				}
			});
		};
		
		function makeMapData(data,name){
			mapdata= new Array();
			piedata= new Array();
			var data=eval(data);
			var numEmp = 0.0;
			var notNumEmp = 0.0;
			if(data!=null){
				$.each(data, function(index, row){
					if(row.name.match(/^黑龙江|^内蒙古/)){
						row.name=data[i].name.substring(0,3);
					}else{
						row.name=data[i].name.substring(0,2);
					}
					mapdata.push({
						name:row.name,
						value:row.numEmp+row.numTemp+row.numOther,
				        id:row.id,
				        selected:false
				        //自定义特殊 itemStyle，仅对该数据项有效
					}); 
					numEmp += row.numEmp;
					notNumEmp += row.numTemp+row.numOther;
				});
			}
			piedata.push(
					{
						name:'正式工  '+numEmp,
						value:eval(numEmp/(numEmp+notNumEmp))*100+'%'
					},
					{
						name:'非正式工  '+(numEmp+notNumEmp),
						value:eval(notNumEmp/(numEmp+notNumEmp))*100+'%'
					}

				); 
			setMapOption(mapdata,piedata,name);
		}

		function setMapOption(mapdata){
			$("#areaTip").html(name||"全国");
			$("#mapTopLeft").html(name?name+'实时人力构成':'全国实时人力构成');
			mapOption.title.text = name?(name+"在岗总人数"):"在岗总人数";
			mapOption.series[0].mapType= (name||"全国");
			mapOption.series[0].data = mapdata;
			pieOption.series[0].data = piedata;
		    mapChart.setOption(mapOption, true);
		    pieChart.setOption(pieOption, true);
		}
			
		function searchBar(id,name) {
			var url = "chart/getBarData.do?id="+id;
			$.ajax({
				url: url,
				type: "post",
				dataType: "text",
				success: function (data) {
					makebarData(data,name);
				}
			});
		};
		
		function makebarData(data,name){
			bardata= new Array();
			bardata[0] = new Array();
			bardata[1] = new Array();
			bardata[2] = new Array();
			if(data!=null){
				$.each(data, function(index, row){
					bardata[0].push(row.);
					bardata[1].push(row.);
					bardata[2].push(row.);
				});
			}
			setBarOption(bardata,name);
		}
		
		function setBarOption(bardata,name){
			$("#effectTopLeft").html(name?name+'一周人效走势图':'全国一周人效走势图');
		    barOption.series[0].data = bardata[0];
		    barOption.series[1].data = bardata[1];
		    barOption.series[2].data = bardata[2];
			barchart.setOption(barOption, true);  
		}
		
		function searchBar1(id,name) {
			var url = "chart/getBar1Data.do?id="+id;
			$.ajax({
				url: url,
				type: "post",
				dataType: "text",
				success: function (data) {
					makebar1Data(data);
				}
			});
		};
		
		function makebar1Data(data,name){
			bardata1= new Array();
			bardata1[0] = new Array();
			bardata1[1] = new Array();
			bardata1[2] = new Array();
			if(data!=null){
				$.each(data, function(index, row){
					bardata1[0].push(row.name);
					bardata1[1].push(row.num);
					bardata1[2].push(row.order);
				});
			}
			setBar1Option(bardata1,name);
		}
		
		function setBar1Option(bardata1,name){
			$("#countTopLeft").html(name?name+'一周在岗正式员工占比':'全国一周在岗正式员工占比');
		    bar1Option.series[0].data = bardata1[0];
		    bar1Option.series[1].data = bardata1[1];
		    bar1Option.series[2].data = bardata1[2];
			bar1chart.setOption(bar1Option, true);  
		}
		
		function searchBar2(id,name) {
			var url = "chart/getBar2Data.do?id="+id;
			$.ajax({
				url: url,
				type: "post",
				dataType: "text",
				success: function (data) {
					makebar2Data(data);
				}
			});
		};
		
		function makebar2Data(data,name){
			bardata2= new Array();
			bardata2[0] = new Array();
			bardata2[1] = new Array();
			var total = 0.0;
			if(data!=null){
				$.each(data, function(index, row){
					bardata2[0].push(row.name+'分拣中心');
					bardata2[1].push(row.averageEffect);
					total += row.averageEffect;
				});
			}
			setBar2Option(bardata2,total/data.length,name);
		}

		function setBar2Option(bardata2,num,name){
			$("#orderCountName").html(name?name+'平均人效':'全国平均人效');
			$("#orderCountNum").html(num);
		    bar2Option.yAxis[0].data = bardata2[0];
		    bar2Option.series[1].data = bardata2[1];
			bar2chart.setOption(bar2Option, true);  
		}
		
		function searchOrderNumber(id,name) {
			var url = "chart/getOrderNumberData.do?id="+id;
			$.ajax({
				url: url,
				type: "post",
				dataType: "text",
				success: function (data) {
					$("#averageEffectName").html(name?name+'订单量':'全国订单量');
					$("#averageEffectNum").html(num);
				}
			});
		};
		
		function resize(index){
			var a = $('#a');
			var b = $('#b');
			var c = $('#c');
			switch(index){
				case 0 :
					b.hide();
					c.hide();
					a.attr("class", "col-md-12");
					var mapTopRight = $('#mapTopRight');
					mapTopRight.removeAttr("onclick");
					var mapTopRight = $('#mapTopRight');
					mapTopRight.bind("click", function() { back(0);});
					mapchart.resize();
					barchart2.resize();
					piechart.resize();
					enlarged = true;
					break;
				case 1 :
					a.hide();
					c.hide();
					b.attr("class", "col-md-12");
					b.css({
						"height":'98%'
					});
					var effectTopRight = $('#effectTopRight');
					effectTopRight.removeAttr("onclick");
					var effectTopRight = $('#effectTopRight');
					effectTopRight.bind("click", function() { back(1);});
					barchart.resize();
					break;
				case 2 :
					a.hide();
					b.hide();
					c.css({
						"height":'98%'
					});
					c.attr("class", "col-md-12");
					var countTopRight = $('#countTopRight');
					countTopRight.removeAttr("onclick");
					var countTopRight = $('#countTopRight');
					countTopRight.bind("click", function() { back(2);});
					barchart1.resize();
					break;	
			}	
		}
		function back(index){
			var a = $('#a');
			var b = $('#b');
			var c = $('#c');
			switch(index){
				case 0 :
					b.show();
					c.show();
					a.attr("class", "col-md-7");
					var mapTopRight = $('#mapTopRight');
					mapTopRight.removeAttr("onclick");
					var mapTopRight = $('#mapTopRight');
					mapTopRight.bind("click", function() { resize(0);});
					mapchart.resize();
					barchart2.resize();
					piechart.resize();
					enlarged = false;
					break;
				case 1 :
					a.show();
					c.show();
					b.css({
						"height":'49%'
					});
					b.attr("class", "col-md-5");
					var effectTopRight = $('#effectTopRight');
					effectTopRight.removeAttr("onclick");
					var effectTopRight = $('#effectTopRight');
					effectTopRight.bind("click", function() { resize(1);});
					barchart.resize();
					break;
				case 2 :
					a.show();
					b.show();
					c.css({
						"height":'49%'
					});
					c.attr("class", "col-md-5");
					var countTopRight = $('#countTopRight');
					countTopRight.removeAttr("onclick");
					var countTopRight = $('#countTopRight');
					countTopRight.bind("click", function() { resize(2);});
					barchart1.resize();
					break;
					
			}	
		}
		window.onresize=function(){
			mapchart.resize();
			barchart.resize();
			barchart1.resize();
			barchart2.resize();
			piechart.resize();
		};