option = null;
function randomData() {
    return Math.round(Math.random()*100)+100;
}
var datalist = null;
var mapName = '全国';
showCenterflag = false;
var currentCenterName=null;
option = {
	backgroundColor:"rgba(0,0,0,0.2)",
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
            var res ='<div style="margin:0;background:url(images/tooltip.png)no-repeat;background-size: 100% 100% ;text-align:center;padding:0;width:150%;padding-top:20%;box-shadow: 2px 2px 10px #32bbec;z-index:4">'
            	+'<p style="background:#32bbec;color:#FFF;padding:0;margin:0;width:70%;margin-left:auto;margin-right:auto;font-size:1px;font-family:黑体, Arial, Helvetica, sans-serif;">'+name+'</p><p style="margin:0;color:#32bced;padding:0;">人数</p><p style="margin:0;padding:0;color:#11d320;font-family:digital-7__mono, Arial, Helvetica, sans-serif; ">'+value[2].toFixed(0)+'</p><input type="button" style="border:none;background:none" value="详情" onclick="showMycenter(\''+name+'\')"/></div>';
                    //设置自定义数据的模板，这里的模板是图片
            console.log(res);
/*            setTimeout(function (){
                // 仅为了模拟异步回调
                callback(ticket, res);//回调函数，这里可以做异步请求加载的一些代码
            }, 1000)
            return 'loading';*/
            return res;
        },alwaysShowContent:false,enterable:true
    },
    grid:{
    	top:0,
    	left:0,
    	bottom:0,
    	right:0,
    	containLabel:true
    },
    geo: {
    	 map: '全国',
         label: {
        	 normal: {
                 position: 'right',
                 show: true,
                 textStyle:{color:'#FFFFFF',fontFamily:'黑体'}
             },
             emphasis: {
            	 show: true,
			     textStyle:{color:'#46D7FF',fontFamily:'黑体',fontSize:20}
			 }
         },
         left:0,
         right:0,
         top:0,
         bottom:0,
         roam: true,
         itemStyle: {
             normal: {
            	 areaColor: '#001F4D',
                 borderColor: '#85c7ff',
                 borderWidth: 2,
                 opacity:0.5,
             },
             emphasis: {
                 areaColor: '#003A92',
                 opacity:0.5,
             }
         }
    },
    series: [
        {
            name: '11',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false,
                    textStyle:{color:'#FFFFFF',fontFamily:'黑体'}
                },
/*                emphasis: {
                    show: true,
                    textStyle:{color:'#46D7FF',fontFamily:'黑体',fontSize:20}
                }*/
            },
            itemStyle: {
                normal: {
                    color: '#f4e925',
                    shadowBlur: 10,
                    shadowColor: '#333'
                },
                emphasis: {
                    color: '#f4e925',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            data:[
                {name: '东北地区',value: [123.38,41.8,randomData()] },
                {name: '华北地区',value: [114.48,38.03,randomData()] },
                {name: '华东地区',value: [122.207216,29.985295,randomData()] },
                {name: '西北地区',value: [87.68,43.77,randomData()] },
                {name: '西南地区',value: [91.11,29.97,randomData(),randomData()] },
                {name: '华中地区',value: [113,28.21,randomData()] },
                {name: '华南地区',value: [113.23,23.16,randomData()] }
            ]
        }
    ]
};

		var barOption = {
				backgroundColor:"rgba(0,0,0,0.2)",
				title: {
		            text: '',
		            left: 'left',
			        top: 5,
		        },
				legend:{
					data:['在岗人数'],
					bottom:5,
					right:30,
					textStyle:{
			        	color:'#7de2ff'
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
			    	data:['1','2','3','4','5','6','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'],
			    	axisLine:{
			    		lineStyle:{
			    			color:'#00acf3'
			    		}
			        },
			        axisLabel:{  
			            interval: 0  
			        } ,
			       	splitLine:{  
	                    show:false  
	                },
	                minInterval: 1
		        },
		        grid:{
		        	left:10,
		        	right:12,
		        	bottom:'10%',
		        	top:'20%',
		        	containLabel:true
		        },
		        yAxis: [
		        {
		        	type:'value',
		        	name: '数量',
		        	axisLine:{
			    		lineStyle:{
			    			color:'#7ab8f9'
			    		}
			        } ,
			        splitLine:{  
	                    show:false  
	                },
	                //position:'right',
		        },
//		        {
//		        	type:'value',
//		        	name: '占比',
//		        	axisLine:{
//			    		lineStyle:{
//			    			color:'#7ab8f9'
//			    		}
//			        } ,
//			        splitLine:{  
//	                    show:false  
//	                },
//	                //position:'right',
//		        }
		        ],
	           // color: ['#32bbec'],
		        series: [
		            {
			            name: '在岗人数',type: 'bar',barMaxWidth:30,
			            label:{
			            	normal:{
			            		show:true,
			            		position:'top'
			            	},
			            },
			            barMinHeight:1,
			            itemStyle:{
			            	normal:{
			            		color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
				            	  offset: 0, color: '#31fbfd' // 0% 处的颜色
			            		}, {
			            		  offset: 1, color: '#0c6add' // 100% 处的颜色
			            		}], false)
			            	}
			            }/*,
			            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()
			                   ,randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()
			                   ,randomData(),randomData(),randomData()]
			            */
		        	},
//		        	{
//			            name: '正式工占比',type: 'line',
//			            label:{
//			            	normal:{
//			            		show:true,
//			            		position:'top'
//			            	},
//			            },
//			            symbol:'circle',
//			            symbolSize:'10',
//			            smooth:true,
//			            barMinHeight:1,
//			            yAxisIndex:1,
//			            itemStyle:{
//			            	normal:{
//			            		color:'#ff9f39'
//				            	}
//			            }/*,
//			            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()
//			                   ,randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()
//			                   ,randomData(),randomData(),randomData()]
//			            */
//		        	}
		        ]
			};

	    var barOption1 = {
	    	backgroundColor:"rgba(0,0,0,0.2)",
			title: {
	            text: '',
	            left: 'center',
		        top: 5,
	        },
			legend:{
				data:['员工数量'],
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
	        	left:10,
	        	right:10,
	        	bottom:'10%',
	        	top:'20%',
	        	containLabel:true
	        },
	        yAxis: [
	        {
	        	type:'value',
	        	name: '数量',
	        	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		        },
		        splitLine:{  
                    show:false  
                },
                splitNumber:6
	        }
	        ],
            color: ['#32bbec','#9035B1',  '#11d320'],
	       	series: [
	            {
		            name: '员工数量',type: 'bar',barMaxWidth:30,
		            label:{
		            	normal:{
		            		show:true,
		            		position:'top'
		            	},
		            },
		            barMinHeight:1,
		            itemStyle:{
		            	normal:{
		            		color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
				            	  offset: 0, color: '#31fbfd' // 0% 处的颜色
			            		}, {
			            		  offset: 1, color: '#0c6add' // 100% 处的颜色
			            		}], false)
			            }
		            }/*,
		            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
		            */
	        	}
	        ]
		};
		
		
		/*
		var barOption3 = {
				title: {
		            text: '',
		            subtext:'人效',
		            left: 'center',
			        top: 5,
			        textStyle:{
			        	color:'#FFF'
			        },
					subtextStyle:{
					    color:'#FFF',
					   // fontFamily:'digital-7__mono',
					    fontSize:'18'
					}
		        },
				legend:{
					data:['实时人效'],
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
			    //color:[ '#7ab8f9','#fedd1b'],
		        grid:{
		        	//width:'85%',
		        	left:0,
		        	bottom:0,
		        	containLabel:true
		        },
		        barWidth:20,
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
		        	data:[''],
			    	axisLine:{
			    		lineStyle:{
			    			color:'#7ab8f9'
			    		}
			       },show:false
		        }
		        ],
	          	series: [
		            {
			            name: '实时人效',
			            type: 'bar',
			            label:{
			            	normal:{
			            		show:true,
			                    textStyle:{
			                    	color:'white',fontWeight:'bold'
			                    },
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
			                    },
			            		color:new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
					            	  offset: 0, color: '#9035B1' // 0% 处的颜色
				            		}, {
				            		  offset: 1, color: '#570E72' // 100% 处的颜色
				            		}], false),
			                   // barBorderRadius:[5, 5, 5, 5]
			                }
		               },
		               data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
	            }
		        ]
			};
		*/
		var pieOption = {
			title: {
	            text: '',
	            left: 'center',
		        top: 5,
	        },
		    tooltip : {
		        trigger: 'item',
		    },
			legend:{
				data:['正式员工','非正式员工'],
				bottom:10,
				right:50,
				textStyle:{
		        	color:'#fff'
		      	},zlevel:1,orient:'horizontal',show:false
			},
		    series : [
		        {
		            name:'',
		            type:'pie',
		            selectedMode: 'single',
		            radius: ['50', '70%'],
		            center: ['50%', '50%'],

		            //因为有visualMap这个是无效的
		            color: ['#FFFF00',  '#00FF00'],
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            selectedOffset:0,
		            label: {
		                normal: {
		                    position: 'inside',
		                    show:false
		                },
		                
		            },
		            //roseType: 'radius',
		            data:[
			            {
			            	name:'正式员工',
			            	value:randomData(),
				            label: {
				                normal: {
				                    position: 'center',
				                    show:false
				                },               
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '13',
				                        fontWeight: '600'
				                    }
				                }			                
				            },
			            },
			            {
			            	name:'非正式员工',
			            	value:randomData(),
				            label: {
				                normal: {
				                    position: 'center',
				                    show:false
				                },				                
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '13',
				                        fontWeight: '600'
				                    }
				                }

				                
				            },
			            }
		            ]
		        }
		    ]
		};
		$(document).ready(function () {
			$('#myCenterFrame').hide();
			var newDate = new Date();
			/*newDate.setDate(newDate.getDate());*/

			setInterval( function() {
				var year = newDate.getFullYear();
				var month = newDate.getMonth()+1;
				var day = newDate.getDate();
				var seconds = new Date().getSeconds();
				var minutes = new Date().getMinutes();
				var hours = new Date().getHours();
				if(seconds=='0'&&(minutes%5==0)){
					//location.reload(true);
					// init (mapName||'全国');
					if(!showCenterflag){
						getData2(mapName);
					}else{
						searchMyCenterData(currentCenterName);
					} 
				}
				$("#year").html(year);
				$("#month").html(( month < 10 ? "0" : "" ) + month);
				$("#day").html(( day < 10 ? "0" : "" ) + day);
				$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
				$("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
				$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
				},1000);
		    var stars = 800;
		    var $stars = $('.stars');
		    var r = 800;
		    for (var i = 0; i < stars; i++) {
		        if (window.CP.shouldStopExecution(1)) {
		            break;
		        }
		        var $star = $('<div/>').addClass('star');
		        $stars.append($star);
		    }
		    window.CP.exitedLoop(1);
		    $('.star').each(function () {
		        var cur = $(this);
		        var s = 0.2 + Math.random() * 1;
		        var curR = r + Math.random() * 300;
		        cur.css({
		            transformOrigin: '0 0 ' + curR + 'px',
		            transform: ' translate3d(0,0,-' + curR + 'px) rotateY(' + Math.random() * 360+ 'deg) rotateX(' + Math.random() * -50 + 'deg) scale(' + s + ',' + s + ')'
		        });
		    });
		    init ('全国'); 
			getUserInfo();
		});

		var enlarged = false;
		function init (nameMap){
			$("#areaTip").html(nameMap||"全国");
		    mapchart = echarts.init(document.getElementById('map'));
			barchart = echarts.init(document.getElementById('effect'));
			barchart1 = echarts.init(document.getElementById('count'));
			barchart2 = echarts.init(document.getElementById('bar'));
/*			barchart3 = echarts.init(document.getElementById('bar1'));*/
			piechart = echarts.init(document.getElementById('pie'));
			$.ajax({
				url: 'geoJson/'+nameMap+'.json',
				type: "get",
				dataType: "json",
				success: function (chinaJson) {
				    echarts.registerMap(nameMap, chinaJson);   
				    getData(nameMap);
				    //loadDault(mapName);
				},statusCode: {404: function() {init ('全国');}}
			});
			mapchart.on('click', function (param){
				mapName=param.name;
				componentType=param.componentType;
				if(param.componentType=='geo'){
					//在中国地图上要去掉这几个地方的点击事件 直辖市 台湾 
					if(!mapName.match(/^北京|^天津|^重庆|^上海|^在线|^离线|^台湾/)&&mapName!=""){
						$("#areaTip").html(mapName||"全国");
						$.ajax({
							url: 'geoJson/'+mapName+'.json',
							type: "get",
							dataType: "json",
							success: function (chinaJson) {
							    echarts.registerMap(mapName, chinaJson);   
							    getData(mapName);
							    //loadDault(mapName);
							},
						  statusCode: {404: function() {init ('全国');}}
						});
				    }
				}else{
					getData1(mapName);
				}
			}); 
		};

		function loadDault(name){
			var data = [
			            {name: '天津京滨分拨中心', value: randomData()},
			            {name: '呼和浩特分拨中心', value: randomData()},
			            {name: '天津分拨中心', value: randomData()},
			            {name: '济南分拨中心', value: randomData()},
			            {name: '青岛分拨中心', value: randomData()},
			            {name: '浦东分拨中心', value: randomData()},
			            {name: '台州分拨中心', value: randomData()},
			            {name: '南京分拨中心', value: randomData()},
			            {name: '宿迁分拨中心', value: randomData()},
			            {name: '金华分拨中心', value: randomData()},
			            {name: '广州太和分拨中心 ', value: randomData()},
			            {name: '深圳清湖分拨中心', value: randomData()},
			            {name: '深圳松岗分拨中心', value: randomData()},
			            {name: '厦门分拨中心', value: randomData()},
			            {name: '泉州分拨中心', value: randomData()},
			            {name: '南宁分拨中心', value: randomData()},
			            {name: '海口分拨中心', value: randomData()},
			            {name: '茂名分拨中心', value: randomData()},
			            {name: '佛山分拨中心', value: randomData()},
			            {name: '中山分拨中心', value: randomData()},
			            {name: '贵阳亚一分拨中心', value: randomData()},
			            {name: '昆明分拨中心', value: randomData()},
			            {name: '南昌分拨中心', value: randomData()},
			            {name: '郑州分拨中心', value: randomData()},
			            {name: '长沙分拨中心', value: randomData()},
			            {name: '哈尔滨分拨中心', value: randomData()},
			            {name: '大连分拨中心', value: randomData()},
			            {name: '沈阳沈北分拣中心', value: randomData()},
			            {name: '长春分拨中心', value: randomData()},
			            {name: '兰州分拨中心', value: randomData()},
			            {name: '银川分拨中心', value: randomData()}
			            
			       ];
			       var geoCoordMap = {
			           '天津京滨分拨中心':[117.2,39.13],
			           '呼和浩特分拨中心':[111.65,40.82],
			           '天津分拨中心':[117.4,39.16],
			           '济南分拨中心':[117,36.65],
			           '青岛分拨中心':[120.33,36.07],
			           '浦东分拨中心':[121.48,31.22],
			           '台州分拨中心':[121.421,28.6564],
			           '南京分拨中心':[118.78,32.04],
			           '宿迁分拨中心':[118.3,33.96],
			           '金华分拨中心':[119.64,29.12],
			           '广州太和分拨中心':[113.23,23.16],
			           '深圳清湖分拨中心':[114.07,22.62],
			           '深圳松岗分拨中心':[114.04,22.6],
			           '厦门分拨中心':[118.1,24.46],
			           '泉州分拨中心':[118.58,24.93],
			           '南宁分拨中心':[108.33,22.84],
			           '海口分拨中心':[110.35,20.02],
			           '茂名分拨中心':[110.88,21.68],
			           '佛山分拨中心':[113.11,23.05],
			           '中山分拨中心':[113.38,22.52],
			           '贵阳亚一分拨中心':[106.71,26.57],
			           '昆明分拨中心':[102.73,25.04],
			           '南昌分拨中心':[115.89,28.68],
			           '郑州分拨中心':[113.65,34.76],
			           '长沙分拨中心':[113,28.21],
			           '哈尔滨分拨中心':[126.63,45.75],
			           '大连分拨中心':[121.62,38.92],
			           '沈阳沈北分拣中心':[123.38,41.8],
			           '长春分拨中心':[125.35,43.88],
			           '兰州分拨中心':[103.73,36.03],
			           '银川分拨中心':[106.27,38.47]
			       };

			       var convertData = function (data) {
			           var res = [];
			           for (var i = 0; i < data.length; i++) {
			               var geoCoord = geoCoordMap[data[i].name];
			               if (geoCoord) {
			                   res.push({
			                       name: data[i].name,
			                       value: geoCoord.concat(data[i].value)
			                   });
			               }
			           }
			           return res;
			       };

			$("#areaTip").html(name||"全国");
			$("#mapTopLeft").html(name?name+'实时人力构成':'全国实时人力构成');
			option.title.text = name?(name+"在岗总人数"):"在岗总人数";
			option.title.subtext =10821;
			option.geo.map= (name||"全国");
			option.series[0].data =convertData(data);
			pieOption.series[0].data = [
			    			            {
			    			            	name:'正式员工'+eval(7035/10821).toFixed(2)*100+'%',
			    			            	value:7035,
			    				            label: {
			    				                normal: {
			    				                    position: 'center',
			    				                    textStyle:{
			    				                    	fontWeight:'normal',fontSize:'1'
			    				                    },
			    				                    show:true
			    				                },               
			    				                emphasis: {
			    				                    show: true,
			    				                    textStyle: {
			    				                        fontSize: '1',
			    				                        fontWeight: 'bold'
			    				                    }
			    				                }	
			    				                
			    				            },
			    			            },
			    			            {
			    			            	name:'非正式员工'+eval(3786/10821).toFixed(2)*100+'%',
			    			            	value:3786,
			    				            label: {
			    				                normal: {
			    				                    position: 'center',			    				                    
			    				                    textStyle:{
			    				                    	fontWeight:'normal',fontSize:'1'
			    				                    },
			    				                    show:true
			    				                },               
			    				                emphasis: {
			    				                    show: true,
			    				                    textStyle: {
			    				                        fontSize: '1',
			    				                        fontWeight: 'bold'
			    				                    }
			    				                }	
			    				            },
			    			            }
			    		            ];
			//pieOption.legend.data = pieLegendData;
			pieOption.legend.data = ['正式员工'+eval(7035/10821).toFixed(2)*100+'%','非正式员工'+eval(3786/10821).toFixed(2)*100+'%'];
			$("#effectTopLeft").html(name?name+'实时在岗人数走势图':'全国实时在岗人数走势图');
		    barOption.series[0].data =[10823,11375,10315,10008,11027,9875,9763];
		    barOption.series[1].data =[8019843,8679125,7756880,7315848,8248196,7119875,7126990];
		    barOption.series[2].data =[741,763,752,731,748,721,730];
		   // barOption.xAxis.data = bardata[3];
		    
			$("#countTopLeft").html(name?name+'一周在岗员工数':'全国一周在岗员工数');
			//barOption1.xAxis.data = bardata1[0];
			barOption1.series[0].data = [7035,7394,6704,6505,7167,6418,6346];
			//barOption1.series[1].data = [3788,3981,3611,3503,3859,3457,3417];
			//barOption1.series[2].data = [0.61,0.62,0.65,0.63,0.64,0.62,0.65];
			
			$("#orderCountName").html(name?name+'平均人效':'全国平均人效');
			$("#orderCountNum").html(763);
			$("#averageEffectName").html('小时处理量|当日总单量');
			$("#averageEffectNum").html(7488742+'|'+77488742);
		    barOption2.yAxis[0].data = ['东北地区','华北地区','华东地区','西北地区','西南地区','华中地区','华南地区'];
		    barOption2.series[0].data = [812,788,747,727,702,698,710];
		    barOption2.series[1].data = [812,788,747,727,702,698,710];
			mapchart.setOption(option, true);
		    barchart.setOption(barOption, true);
		    barchart1.setOption(barOption1, true);
		    var barOption2 = {
		    		backgroundColor:"rgba(0,0,0,0.2)",
					title: {
			            text: '前一小时人数/人效监控',
			            subtext:'人数',
			            left: 'left',
				        top: 5,
				        textStyle:{
				        	color:'#33bced'
				        },
						subtextStyle:{
						    color:'#FFF',
						    //fontFamily:'digital-7__mono',
						    fontSize:'18'
						}
			        },
					legend:{
						data:['正式工','非正式工'],
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
				    color:[ '#7ab8f9','#fedd1b'],
			        grid:{
			        	//width:'85%',
			        	left:'4%',
			        	right:'10%',
			        	bottom:0,
			        	containLabel:true
			        },
			        barWidth:20,
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
			        	data:[''],
				    	axisLine:{
				    		lineStyle:{
				    			color:'#7ab8f9'
				    		}
				       },
			        }
			        ],
		          	series: [
			            {
				            name: '正式工',
				            type: 'bar',
				            stack: 1,
				            label:{
				            	normal:{
				            		show:true,
				            		position:'top'
				            	},
				            },
				            itemStyle: {
				                normal: {
		/*		                    color: function(params) {
				                        // build a color map as your need.
				                        var colorList = [
				                          '#7ab8f9','#fedd1b'
				                        ];
				                        return colorList[params.dataIndex%2]
				                    },*/
				            		color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
					            	  offset: 0, color: '#31fbfd' // 0% 处的颜色
				            		}, {
				            		  offset: 1, color: '#0c6add' // 100% 处的颜色
				            		}], false)
				            //barBorderRadius:[5, 5, 5, 5]
				                }
			               }/*,
			               data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
			               */
		            },	           
		            {
			            name: '非正式工',
			            type: 'bar',
			            stack: 1,
			            label:{
			            	normal:{
			            		show:true,
			            		position:'right',
			            		formatter : function(a,b,c){
			        		    	alert(a,b,c);
			        		    	return datalist[a];
			        		    },		                    
			            		textStyle: {
			            			color:'#FFF',
			                        fontSize: '12',
			                        fontWeight: 'bold'
			                    }
			            	},
			            },
			            itemStyle: {
			                normal: {
		/*	                    color: function(params) {
			                        // build a color map as your need.
			                        var colorList = [
			                          '#7ab8f9','#fedd1b'
			                        ];
			                        return colorList[params.dataIndex%2]
			                    },*/
			                	color:new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
					            	  offset: 0, color: '#fedd1b' // 0% 处的颜色
				            		}, {
				            		  offset: 1, color: '#DAC12F' // 100% 处的颜色
				            		}], false),
			                   // barBorderRadius:[5, 5, 5, 5]
			                }
		               }/*,
		               data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()]
		               */
		        }
			        ]
				};
		    barchart2.setOption(barOption2, true);
/*		    barchart3.setOption(barOption3, true);*/
		    piechart.setOption(pieOption, true);
		}
		function getData(name){
			setTimeout(searchMap(name),1);
			setTimeout(searchBar(name),1);
			setTimeout(searchBar1(name),1);
			setTimeout(searchBar2(name),1);
		/*	if(!enlarged){*/

/*			}*/
		};
		
		function getData1(name){
			setTimeout(searchMap1(name),1);
			setTimeout(searchBar2(name),1);
		/*	if(!enlarged){*/
			setTimeout(searchBar(name),1);
			setTimeout(searchBar1(name),1);
/*			}*/
		};
		
		function getData2(name){
			setTimeout(searchMap2(name),1);
			setTimeout(searchBar2(name),1);
		/*	if(!enlarged){*/
			setTimeout(searchBar(name),1);
			setTimeout(searchBar1(name),1);
/*			}*/
		};
		
		function searchMap(name) {
			var url = "chart/getMapData.do?name="+name;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					makeMapData(data,name);
				}
			});
		};
		
		function searchMap1(name) {
			var url = "chart/getMapData.do?name="+name;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					makeMapData1(data,name);
				}
			});
		};
		
		function searchMap2(name) {
			var url = "chart/getMapData.do?name="+name;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					makeMapData2(data,name);
				}
			});
		};
		
		//获取拼接地图数据
		function makeMapData(data,name){
			mapdata= new Array();
			piedata= new Array();
			pieLegendData= new Array();
			var data=eval(data);
			var numEmp = 0.0;
			var notNumEmp = 0.0;
			var otherNumEmp = 0.0;
			var colorList={1:"#f4e925",2:"#F327F5"}
			if(data!=null){
				$.each(data, function(index, row){
/*					if(row.name.match(/^黑龙江|^内蒙古/)){
						row.name=data[index].name.substring(0,3);
					}*/
				//	var c = eval(row.EmpNum+row.NotEmpNum+row.otherNumEmp);
					var c = eval(row.EmpNum+row.NotEmpNum);
					if(c<1){
						mapdata.push({
							//地区名称 取china area表
							name:row.name,
							//numEmp 正式员工数 numTemp 临时工数 numOther 其他员工数
							value:[row.x,row.y,c],
							//地区的id 取china area表id
					        selected:false,
					        //自定义特殊 itemStyle，仅对该数据项有效
					        symbol:'diamond',
					        symbolSize:5,
					        zlevel:2,
				            itemStyle: {
				                normal: {
				                    color: '#ED1C24',
				                    shadowBlur: 20,
				                    shadowColor: '#333'
				                },
				                emphasis: {
				                    color: '#ED1C24',
				                    shadowBlur: 20,
				                    shadowColor: '#333'
				                },
				            },
				            
						}); 
					}else if(c>=1&&c<100){
						var size1 = Math.round(c / 20);
						mapdata.push({
							//地区名称 取china area表
							name:row.name,
							//numEmp 正式员工数 numTemp 临时工数 numOther 其他员工数
							value:[row.x,row.y,c],
							//地区的id 取china area表id
					        selected:false,
					        //自定义特殊 itemStyle，仅对该数据项有效
					        symbolSize:10,
				            itemStyle: {
				                normal: {
				                    color: colorList[row.level],
				                    shadowBlur: size1,
				                    shadowColor: colorList[row.level]
				                },
				                emphasis: {
				                    color: colorList[row.level],
				                    shadowBlur: size1,
				                    shadowColor: colorList[row.level]
				                }
				            },
						}); 
					}else if(c>=100){
						var size = Math.round(c / 20);
						//var size = {1:"20",2:"10"};
						mapdata.push({
							//地区名称 取china area表
							name:row.name,
							//numEmp 正式员工数 numTemp 临时工数 numOther 其他员工数
							value:[row.x,row.y,c],
							//地区的id 取china area表id
					        selected:false,
					        //自定义特殊 itemStyle，仅对该数据项有效
				            symbolSize: 10,
				            itemStyle: {
				                normal: {
				                    color: colorList[row.level],
				                    shadowBlur: size,
				                    shadowColor:colorList[row.level]
				                },
				                emphasis: {
				                    color: colorList[row.level],
				                    shadowBlur: size,
				                    shadowColor:colorList[row.level]
				                }
				            },
						}); 
					}
					otherNumEmp += row.otherNumEmp;
					numEmp += row.EmpNum;
					notNumEmp += row.NotEmpNum;
				});
			}
			var numEmpPer = 0.00;
			var notNumEmpPer = 0.00;
			numEmpPer=(numEmp+notNumEmp==0)?0:((numEmp/(numEmp+notNumEmp)).toFixed(2));
			notNumEmpPer=(numEmp+notNumEmp==0)?0:((notNumEmp/(numEmp+notNumEmp)).toFixed(2));
			piedata.push(
					{
						name:'正式员工  '+(numEmpPer*100)+'%',
						value: Math.ceil(numEmp),
			            label: {
			                normal: {
			                    position: 'center',
			                    textStyle:{
			                    	fontWeight:'normal',fontSize:'1'
			                    },
			                    show:true
			                },               
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '1',
			                        fontWeight: 'bold'
			                    }
			                }	
			                
			            },
					},
					{
						name:'非正式员工  '+(notNumEmpPer*100)+'%',
						value:Math.ceil(notNumEmp),
			            label: {
			                normal: {
			                    position: 'center',
			                    textStyle:{
			                    	fontWeight:'normal',fontSize:'1'
			                    },
			                    show:true
			                },               
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '1',
			                        fontWeight: 'bold'
			                    }
			                }	
			                
			            },
					}

				); 
			pieLegendData.push('正式员工  '+(numEmpPer*100)+'%');
			pieLegendData.push('非正式员工  '+(notNumEmpPer*100)+'%');
			//setMapOption(mapdata,piedata,pieLegendData,numEmp+notNumEmp+otherNumEmp,name);
			setMapOption(mapdata,piedata,pieLegendData,numEmp+notNumEmp,name);
		}

		//获取拼接地图数据
		function makeMapData1(data,name){
			mapdata= new Array();
			piedata= new Array();
			pieLegendData= new Array();
			var data=eval(data);
			var numEmp = 0.0;
			var notNumEmp = 0.0;
			var otherNumEmp = 0.0;
			var a = 0.00;
			var b = 0.00;
			if(data!=null){
				$.each(data, function(index, row){
/*					if(row.name.match(/^黑龙江|^内蒙古/)){
						row.name=data[index].name.substring(0,3);
					}*/
//					var c = eval(row.EmpNum+row.NotEmpNum+row.otherNumEmp);
					var c = eval(row.EmpNum+row.NotEmpNum);
					otherNumEmp += row.otherNumEmp;
					numEmp += row.EmpNum;
					notNumEmp += row.NotEmpNum;
				});
			}
			var numEmpPer = 0.00;
			var notNumEmpPer = 0.00;
			numEmpPer=(numEmp+notNumEmp==0)?0:((numEmp/(numEmp+notNumEmp)).toFixed(2));
			notNumEmpPer=(numEmp+notNumEmp==0)?0:((notNumEmp/(numEmp+notNumEmp)).toFixed(2));
			piedata.push(
					{
						name:'正式员工  '+(numEmpPer*100)+'%',
						value: Math.ceil(numEmp),
			            label: {
			                normal: {
			                    position: 'center',
			                    textStyle:{
			                    	fontWeight:'normal',fontSize:'1'
			                    },
			                    show:true
			                },               
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '1',
			                        fontWeight: 'bold'
			                    }
			                }	
			                
			            },
					},
					{
						name:'非正式员工  '+(notNumEmpPer*100)+'%',
						value:Math.ceil(notNumEmp),
			            label: {
			                normal: {
			                    position: 'center',
			                    textStyle:{
			                    	fontWeight:'normal',fontSize:'1'
			                    },
			                    show:true
			                },               
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '1',
			                        fontWeight: 'bold'
			                    }
			                }	
			                
			            },
					}

				); 
			pieLegendData.push('正式员工  '+(numEmpPer*100)+'%');
			pieLegendData.push('非正式员工  '+(notNumEmpPer*100)+'%');
			//setMapOption(null,piedata,pieLegendData,numEmp+notNumEmp+otherNumEmp,name);
			setMapOption1(null,piedata,pieLegendData,numEmp+notNumEmp,name);
		}
		
		//获取拼接地图数据
		function makeMapData2(data,name){
			mapdata= new Array();
			piedata= new Array();
			pieLegendData= new Array();
			var data=eval(data);
			var numEmp = 0.0;
			var notNumEmp = 0.0;
			var otherNumEmp = 0.0;
			var a = 0.00;
			var b = 0.00;
			if(data!=null){
				$.each(data, function(index, row){
/*					if(row.name.match(/^黑龙江|^内蒙古/)){
						row.name=data[index].name.substring(0,3);
					}*/
//					var c = eval(row.EmpNum+row.NotEmpNum+row.otherNumEmp);
					var c = eval(row.EmpNum+row.NotEmpNum);
					otherNumEmp += row.otherNumEmp;
					numEmp += row.EmpNum;
					notNumEmp += row.NotEmpNum;
				});
			}
			var numEmpPer = 0.00;
			var notNumEmpPer = 0.00;
			numEmpPer=(numEmp+notNumEmp==0)?0:((numEmp/(numEmp+notNumEmp)).toFixed(2));
			notNumEmpPer=(numEmp+notNumEmp==0)?0:((notNumEmp/(numEmp+notNumEmp)).toFixed(2));
			piedata.push(
					{
						name:'正式员工  '+(numEmpPer*100)+'%',
						value: Math.ceil(numEmp),
			            label: {
			                normal: {
			                    position: 'center',
			                    textStyle:{
			                    	fontWeight:'normal',fontSize:'1'
			                    },
			                    show:true
			                },               
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '1',
			                        fontWeight: 'bold'
			                    }
			                }	
			                
			            },
					},
					{
						name:'非正式员工  '+(notNumEmpPer*100)+'%',
						value:Math.ceil(notNumEmp),
			            label: {
			                normal: {
			                    position: 'center',
			                    textStyle:{
			                    	fontWeight:'normal',fontSize:'1'
			                    },
			                    show:true
			                },               
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '1',
			                        fontWeight: 'bold'
			                    }
			                }	
			                
			            },
					}

				); 
			pieLegendData.push('正式员工  '+(numEmpPer*100)+'%');
			pieLegendData.push('非正式员工  '+(notNumEmpPer*100)+'%');
//			setMapOption1(null,piedata,pieLegendData,numEmp+notNumEmp+otherNumEmp,name);
			setMapOption1(null,piedata,pieLegendData,numEmp+notNumEmp,name);
		}
		
		function setMapOption(mapdata,piedata,pieLegendData,total,name){
			$("#areaTip").html(name||"全国");
			$("#mapTopLeft").html(name?name+'实时人力构成':'全国实时人力构成');
			option.title.text = name?(name+"在岗总人数"):"在岗总人数";
			option.title.subtext =(total?""+Math.ceil(total)+"":'0');
			if(mapdata){
				option.geo.map= (name||"全国");
				option.series[0].data = mapdata;
				if(name=='海南'){
					option.geo.center = [109.8,19];
					option.geo.zoom = 4;
				}else{
					option.geo.center = [];
					option.geo.zoom = 1;
				};
/*				if(name=='全国'){
					option.geo.label.normal.show = true;
				}else{
					option.geo.label.normal.show = false;
				}*/
			};
			pieOption.series[0].data = piedata;
			pieOption.legend.data = pieLegendData;
			mapchart.setOption(option, true);
			piechart.setOption(pieOption, true);
		}
		
		function setMapOption1(mapdata,piedata,pieLegendData,total,name){
			$("#areaTip").html(name||"全国");
			$("#mapTopLeft").html(name?name+'实时人力构成':'全国实时人力构成');
			option.title.text = name?(name+"在岗总人数"):"在岗总人数";
			option.title.subtext =(total?""+Math.ceil(total)+"":'0');
			if(mapdata){
				option.series[0].data = mapdata;
				if(name=='海南'){
					option.geo.center = [109.8,19];
					option.geo.zoom = 4;
				}else{
					option.geo.center = [];
					option.geo.zoom = 1;
				};
/*				if(name=='全国'){
					option.geo.label.normal.show = true;
				}else{
					option.geo.label.normal.show = false;
				}*/
			};
			pieOption.series[0].data = piedata;
			pieOption.legend.data = pieLegendData;
			mapchart.setOption(option, true);
			piechart.setOption(pieOption, true);
		}
			
		function searchBar(cuName) {
			currentCenterName=(cuName==null?window.uerInfo.cuName:cuName);
			var url = 'chart/getMyCenterData.do?cuName='+(currentCenterName==='全国'?'京东集团':currentCenterName);
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					makebarData(data,cuName);
				}
			});
		
		};
		
		//获取拼接右上数据
		function makebarData(data,name){	
			$("#effectTopLeft").html(name?name+'今天在岗人数走势图':'全国今天在岗人数走势图');
			myCenterdata= new Array();
			myCenterdata[0] = new Array();
			myCenterdata[1] = new Array();
			myCenterdata[2] = new Array();
			var data=eval(data);
			var eachHourTotalNum = 0;
			var nowClerkNum = 0;
			var nowNotClerkNum = 0;
			var nowOtherClerkNum = 0;
			if(data!=null){
				//clerkNum 员工数 orderNum 订单数  date 日期
				var dataL=data.length;
				for(var i=0;i<24;i++){
					if(i<dataL){
						eachHourTotalNum=(data[i].clerkNum?data[i].clerkNum:0)+(data[i].notClerkNum?data[i].notClerkNum:0)+(data[i].otherClerkNum?data[i].otherClerkNum:0);
						if(new Date().getHours()==data[i].time){
							nowClerkNum =(data[i].clerkNum?data[i].clerkNum:0);
							nowNotClerkNum =(data[i].notClerkNum?data[i].notClerkNum:0);
							nowOtherClerkNum =(data[i].otherClerkNum?data[i].otherClerkNum:0);
						}
						myCenterdata[0].push(Math.ceil(eachHourTotalNum));
						myCenterdata[1].push(eachHourTotalNum==0?0:Math.ceil(((data[i].clerkNum?data[i].clerkNum:0)/eachHourTotalNum)*100));

					}else{
						myCenterdata[0].push(Math.ceil(0));
						//myCenterdata[1].push(eachHourTotalNum==0?0:Math.ceil(((row.clerkNum?row.clerkNum:0)/eachHourTotalNum)*100));
					}
					myCenterdata[2].push((i+1)+"点");
				}
				barOption.series[0].data = myCenterdata[0];
				//barOption.series[1].data = myCenterdata[1];
				barOption.xAxis.data = myCenterdata[2];
				barchart.setOption(barOption, true);
			}
		}
//		function setBarOption(bardata,name){
//			$("#effectTopLeft").html(name?name+'一周人效走势图':'全国一周人效走势图');
//		    barOption.series[0].data = bardata[0];
//		    barOption.series[1].data = bardata[1];
//		    barOption.series[2].data = bardata[2];
//		    barOption.xAxis.data = bardata[3];
//			barchart.setOption(barOption, true);  
//		}
		
		function searchBar1(name) {
			var url = "chart/getBar1Data.do?name="+name;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					makebar1Data(data,name);
				}
			});
		};
		
		//右下数据获取拼接
		function makebar1Data(data,name){
			bardata1= new Array();
			bardata1[0] = new Array();
			bardata1[1] = new Array();
			//bardata1[2] = new Array();
			//bardata1[3] = new Array();
			var data=eval(data);
			if(data!=null){
				$.each(data, function(index, row){
					//date 时间  empNum 员工人数 otherClerkNum 其他员工数
					bardata1[0].push(row.name);
					bardata1[1].push(row.EmpNum+row.NotEmpNum);
					//bardata1[2].push();
					//bardata1[3].push(row.NotEmpNum=='0'?'0':(row.EmpNum/(row.EmpNum+row.NotEmpNum)).toFixed(2));
				});
			}
			setBar1Option(bardata1,name);
		}
		
		function setBar1Option(bardata1,name){
			$("#countTopLeft").html(name?name+'一周总人数展示图':'全国一周总人数展示图');
			barOption1.xAxis.data = bardata1[0];
			barOption1.series[0].data = bardata1[1];
			//barOption1.series[1].data = bardata1[2];
			//barOption1.series[2].data = bardata1[3];
			barchart1.setOption(barOption1, true);  
		}
		
		function searchBar2(name) {
			var url = "chart/getBar2Data.do?name="+name;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					makebar2Data(data,name);
				}
			});
		};
		//这是用来拼接左下柱状图数据
		function makebar2Data(data,name){
			bardata2= new Array();
			bardata2[0] = new Array();
			bardata2[1] = new Array();
			bardata2[2] = new Array();
			datalist = new Object();
			datalist[0] = new Object();
			datalist[1] = new Object();
			var totalClerkNum = 0.0;
			var totalOrderNum = 0.0;
			var totalOrder = 0.0;
			var data=eval(data);
			if(data!=null){
				$.each(data, function(index, row){
					//name 地区名 averageEffect 该地区平均人效
					bardata2[0].push(row.name);
					bardata2[1].push(Math.ceil(row.EmpNum));
					bardata2[2].push(Math.ceil(row.NotEmpNum));
					var zhanbi = (row.NotEmpNum+row.EmpNum)>0?(Math.ceil(row.EmpNum/(row.NotEmpNum+row.EmpNum)))*100:0;
					var renxiao = (row.NotEmpNum+row.EmpNum)>0?Math.ceil(eval(row.orderNum/(row.NotEmpNum+row.EmpNum))):0
					datalist[0][row.name]='';
					/*datalist[1][row.name]='总人数：'+Math.ceil(row.NotEmpNum+row.EmpNum)+' | 正式员工占比：'+zhanbi+'% | 人效:'+renxiao;*/
					datalist[1][row.name]='总人数：'+Math.ceil(row.NotEmpNum+row.EmpNum)+' | 人效:'+renxiao;
					totalClerkNum += (row.NotEmpNum+row.EmpNum);
					totalOrderNum += row.orderNum;
					totalOrder += row.totalOrder;
				});
			}
			setBar2Option(bardata2,((data&&data.length>0&&totalClerkNum>0)?Math.ceil(totalOrderNum/totalClerkNum):'0'),totalOrderNum,name,totalOrder);
		}

		function setBar2Option(bardata2,avgEffect,totalOrderNum,name,totalOrder){
			$("#orderCountName").html(name?name+'平均人效':'全国平均人效');
			$("#orderCountNum").html(avgEffect);
/*			$("#averageEffectName").html('小时处理量<span style="font-size:3rem;font-family:黑体;color:#FFF"> | </span>当日总单量');
			$("#averageEffectNum").html(totalOrderNum+'<span style="font-size:3rem;font-family:黑体;color:#FFF"> | </span>'+totalOrder);*/
			$("#averageEffectName").html('<p style="color:#FFF;font-size: 2.5rem;font-family:黑体,微软雅黑, Arial, Helvetica, sans-serif;">小时处理量</p><span style="color:#fedd1b;font-size: 4rem;font-family:digital-7__mono, Arial, Helvetica, sans-serif;">'+totalOrderNum+'</span>');
			$("#averageEffectNum").html('<p style="color:#FFF;font-size: 2.5rem;font-family:黑体,微软雅黑, Arial, Helvetica, sans-serif;">当日总单量</p><span style="color:#fedd1b;font-size: 4rem;font-family:digital-7__mono, Arial, Helvetica, sans-serif;">'+totalOrder+'</span>');
			barOption2 = {
					background:"rga(0,0,0,0.9)",
					title: {
			            text: '前一小时人数/人效监控',
			            subtext:'',
			            left: 'left',
				        top: 5,
				        textStyle:{
				        	color:'#33bced'
				        },
						subtextStyle:{
						    color:'#FFF',
						    //fontFamily:'digital-7__mono',
						    fontSize:'18'
						}
			        },
					legend:{
						data:['正式工','非正式工'],
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
				    color:[ '#7ab8f9','#fedd1b'],
			        grid:{
			        	width:'50%',
			        	left:'20%',
			        	//right:'4%',
/*			        	x:'20%',
			        	y:'20%',
			        	x2:'20%',
			        	y2:'20%',*/
			        	bottom:'5%',
			        	//containLabel:true
			        },
			        barWidth:20,
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
			        	data: bardata2[0],
				    	axisLine:{
				    		lineStyle:{
				    			color:'#7ab8f9'
				    		}
				       },
			        }
			        ],
		          	series: [
			            {
				            name: '正式工',
				            type: 'bar',
				            stack: 1,
				            barMinHeight:1,
				            zlevel:10086,
				            label:{
				            	normal:{
				            		show:true,
				            		position:['190%', '10%'],			            		
				        		    textStyle: {
				            			color:'#FFF',
				                        fontSize: '13',
				                        fontWeight: '400'
				                    },
				            		formatter : function(params){
				        		    	//alert(a,b,c);
				        		    	//return datalist[b];
				            			return datalist[0][params.name];
				        		    }
				            	},
				            },
				            itemStyle: {
				                normal: {
		/*		                    color: function(params) {
				                        // build a color map as your need.
				                        var colorList = [
				                          '#7ab8f9','#fedd1b'
				                        ];
				                        return colorList[params.dataIndex%2]
				                    },*/
				            		color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
					            	  offset: 0, color: '#31fbfd' // 0% 处的颜色
				            		}, {
				            		  offset: 1, color: '#0c6add' // 100% 处的颜色
				            		}], false)
				                    //barBorderRadius:[5, 5, 5, 5]
				                }
			               },
			               data: bardata2[1]
		            },	           
		            {
			            name: '非正式工',
			            type: 'bar',
			            stack: 1,
			            barMinHeight:1,
			            zlevel:10086,
			            label:{
			            	normal:{
			            		show:true,
			            		position:['100%', '10%'],		                    
			            		textStyle: {
			            			color:'#FFF',
			                        fontSize: '13',
			                        fontWeight: '400'
			                    },
			            		formatter : function(params){
			        		    	//alert(a,b,c);
			        		    	//return datalist[b];
			            			return datalist[1][params.name];
			        		    }
			            	},
			            },
			            itemStyle: {
			                normal: {
		/*	                    color: function(params) {
			                        // build a color map as your need.
			                        var colorList = [
			                          '#7ab8f9','#fedd1b'
			                        ];
			                        return colorList[params.dataIndex%2]
			                    },*/
			                	color:new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
					            	  offset: 0, color: '#fedd1b' // 0% 处的颜色
				            		}, {
				            		  offset: 1, color: '#DAC12F' // 100% 处的颜色
				            		}], false),
			                   // barBorderRadius:[5, 5, 5, 5]
			                }
		               },
		               data: bardata2[2]
		        }
			        ]
			};
			if('全国'==name){barOption2.grid.left='10%'}else{barOption2.grid.left='20%'}
/*		    barOption3.series[0].data = bardata2[3];*/
			barchart2.setOption(barOption2, true);  
/*			barchart3.setOption(barOption3, true);*/
		}
		
		function resize(index){
			var areaA = $('#areaA');
			var areaB = $('#areaB');
			var areaC = $('#areaC');
			switch(index){
				case 0 :
					areaB.hide();
					areaC.hide();
					areaA.attr("class", "col-md-12");
					var mapTopRight = $('#mapTopRight');
					mapTopRight.removeAttr("onclick");
					var mapTopRight = $('#mapTopRight');
					mapTopRight.bind("click", function() { back(0);});
					var map = $('#map');
					map.css({"height":"93%","width":"60%"});
					var bar = $('#bar');
					bar.css({"height":"55%","width":"42%","bottom":"0%","top":"auto","left":"auto","right":"1%"});
/*					var bar1 = $('#bar1');
					bar1.css({"height":"50%","width":"25%","bottom":"4%","top":"auto","left":"auto","right":"0"});*/
					var pie = $('#pie');
					pie.css({"height":"40%","width":"30%","bottom":"auto","top":"9%","right":"-2%"});
					pieOption.legend.right='25%';
					pieOption.legend.bottom='5%';
					pieOption.legend.orient='horizontal';
					pieOption.grid={
						width:'100%',
						hight:'100%',
			        	containLabel:true
			        };
					pieOption.series[0].radius=['55%', '70%'];
					piechart.setOption(pieOption, true);
					//barOption2.series[0].label.normal.position=['10%', '10%'];
					//barOption2.series[1].label.normal.position=['230%', '10%'];
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.setOption(barOption2, true);
					barchart2.resize();
					/*barchart3.resize();*/
					piechart.resize();
					$('.text1 ').css({"bottom":"69%","right":"25%"});
					$('.text2 ').css({"bottom":"55%","right":"25%","width":"16%"});
					enlarged = true;
					break;
				case 1 :
					areaA.hide();
					areaC.hide();
					areaB.attr("class", "col-md-12");
					areaB.css({
						"height":'98%'
					});
					var effectTopRight = $('#effectTopRight');
					effectTopRight.removeAttr("onclick");
					var effectTopRight = $('#effectTopRight');
					effectTopRight.bind("click", function() { back(1);});
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.resize();
					piechart.resize();
					break;
				case 2 :
					areaA.hide();
					areaB.hide();
					areaC.css({
						"height":'98%'
					});
					areaC.attr("class", "col-md-12");
					var countTopRight = $('#countTopRight');
					countTopRight.removeAttr("onclick");
					var countTopRight = $('#countTopRight');
					countTopRight.bind("click", function() { back(2);});
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.resize();
					piechart.resize();
					break;	
			}	
		}
		function back(index){
			var areaA = $('#areaA');
			var areaB = $('#areaB');
			var areaC = $('#areaC');
			switch(index){
				case 0 :
					areaB.show();
					areaC.show();
					areaA.attr("class", "col-md-7");
					var mapTopRight = $('#mapTopRight');
					mapTopRight.removeAttr("onclick");
					var mapTopRight = $('#mapTopRight');
					mapTopRight.bind("click", function() { resize(0);});
					var map = $('#map');
					map.css({"height":"65%","width":"90%"});
					var bar = $('#bar');
					bar.css({"height":"38%","width":"70%","top":"61%","left":"2%"});
/*					var bar1 = $('#bar1');
					bar1.css({"height":"38%","width":"20%","top":"61%","left":"49%"});*/
					var pie = $('#pie');
					pie.css({"height":"28%","width":"28%","top":"auto","bottom":"24%","right":"0"});
					pieOption.legend.right='15%';
					pieOption.legend.bottom=0;
					pieOption.series[0].radius=['50%', '70%'];
					piechart.setOption(pieOption, true);
					$('.text1 ').css({"bottom":"13%","right":"2%"});
					$('.text2 ').css({"bottom":"2%","right":"2%","width":"27%"});
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					//barOption2.series[0].label.normal.position=['10%', '10%'];
					//barOption2.series[1].label.normal.position=['260%', '10%'];
					//barchart2.setOption(barOption2, true);
					barchart2.resize();
/*					barchart3.resize();*/
					piechart.resize();
					enlarged = false;
					break;
				case 1 :
					areaA.show();
					areaC.show();
					areaB.css({
						"height":'49%'
					});
					areaB.attr("class", "col-md-5");
					var effectTopRight = $('#effectTopRight');
					effectTopRight.removeAttr("onclick");
					var effectTopRight = $('#effectTopRight');
					effectTopRight.bind("click", function() { resize(1);});
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.resize();
					piechart.resize();
					break;
				case 2 :
					areaA.show();
					areaB.show();
					areaC.css({
						"height":'49%'
					});
					areaC.attr("class", "col-md-5");
					var countTopRight = $('#countTopRight');
					countTopRight.removeAttr("onclick");
					var countTopRight = $('#countTopRight');
					countTopRight.bind("click", function() { resize(2);});
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.resize();
					piechart.resize();
					break;
					
			}	
		}
		window.onresize=function(){
			mapchart.resize();
			barchart.resize();
			barchart1.resize();
			barchart2.resize();
			piechart.resize();
			if(thisHourDatachart){
				thisHourDatachart.resize();				
			}
			if(myCenterchart){
				myCenterchart.resize();				
			}
		};
		function logout(){
			window.location.href = "login.html";
		}
		function reports(){
			window.location.href = "reports.html";
		}
		function yyreports(){
			window.location.href = "yyreports.html";
		}
		function ycreports(){
			window.location.href = "ycreports.html";
		}
		function getUserInfo(){
			var thisURL = document.URL;    
			var  getval =thisURL.split('?')[1];  
			var empName= getval.split("=")[1];  
			var url = "user/getUserInfo.do?empName="+empName;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					window.uerInfo = eval("("+data+")");
					if("MANAGE"!=window.uerInfo.cuType){
						$('#myCenterButton').hide();
					}
				}
			});
		}
		
		function showMycenter(cuName){
			if(window.uerInfo){
				$('#mainFrame').fadeOut();
				if(null==cuName){
					cuName=window.uerInfo.cuName;
				}
				$('#myCenterFrame').fadeIn(); 
				$('#myCenterTile').html(cuName);
				initMyCenter();
				assembThisHourBar(145582,25633,7525);
				$('#myCenterButton').css({"background":"url(images/2on.png)no-repeat","color":"#9eddff","background-size":"cover"});
				searchMyCenterData(cuName);
				showCenterflag =true;
			}else{
				getUserInfo();
			}
			$("#myCenterButton").blur();
			$('#showMainButton').show();
		}
		
		function showMain(){
			if(!window.uerInfo){
				getUserInfo();
			}
			$('#myCenterFrame').fadeOut();
			$('#mainFrame').fadeIn(); 
			$('#myCenterButton').css({"background":"url(images/2off.png)no-repeat","color":"#008bd6","background-size":"cover"});
			showCenterflag = false;
			$('#showMainButton').hide();
		}
		
		function initMyCenter(){
			MyCenterOption = {
					backgroundColor:"rgba(0,0,0,0.2)",
					title: {
			            text: '',
			            left: 'left',
				        top: 5,
			        },
					legend:{
						data:['在岗人数','正式工占比'],
						bottom:5,
						right:30,
						textStyle:{
				        	color:'#7de2ff'
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
				    	data:['1','2','3','4','5','6','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'],
				    	axisLine:{
				    		lineStyle:{
				    			color:'#00acf3'
				    		}
				        },
				        axisLabel:{  
				            interval: 0  
				        } ,
				       	splitLine:{  
		                    show:false  
		                },
		                minInterval: 1
			        },
			        grid:{
			        	left:10,
			        	right:12,
			        	bottom:'10%',
			        	top:'20%',
			        	containLabel:true
			        },
			        yAxis: [
			        {
			        	type:'value',
			        	name: '数量',
			        	axisLine:{
				    		lineStyle:{
				    			color:'#7ab8f9'
				    		}
				        } ,
				        splitLine:{  
		                    show:false  
		                },
		                //position:'right',
			        },
			        {
			        	type:'value',
			        	name: '占比',
			        	axisLine:{
				    		lineStyle:{
				    			color:'#7ab8f9'
				    		}
				        } ,
				        splitLine:{  
		                    show:false  
		                },
		                //position:'right',
			        }
			        ],
		           // color: ['#32bbec'],
			        series: [
			            {
				            name: '在岗人数',type: 'bar',barMaxWidth:30,
				            label:{
				            	normal:{
				            		show:true,
				            		position:'top'
				            	},
				            },
				            barMinHeight:1,
				            itemStyle:{
				            	normal:{
				            		color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
					            	  offset: 0, color: '#31fbfd' // 0% 处的颜色
				            		}, {
				            		  offset: 1, color: '#0c6add' // 100% 处的颜色
				            		}], false)
				            	}
				            }/*,
				            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()
				                   ,randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()
				                   ,randomData(),randomData(),randomData()]
				            */
			        	},
			        	{
				            name: '正式工占比',type: 'line',
				            label:{
				            	normal:{
				            		show:true,
				            		position:'top'
				            	},
				            },
				            symbol:'circle',
				            symbolSize:'10',
				            smooth:true,
				            barMinHeight:1,
				            yAxisIndex:1,
				            itemStyle:{
				            	normal:{
				            		color:'#ff9f39'
					            	}
				            }/*,
				            data: [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()
				                   ,randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()
				                   ,randomData(),randomData(),randomData()]
				            */
			        	}
			        ]
				};
			myCenterchart = echarts.init(document.getElementById('myCenterAreaA'));
			myCenterchart.setOption(MyCenterOption, true);
		};
		function searchMyCenterData(cuName){
			currentCenterName=cuName==null?window.uerInfo.cuName:cuName;
			var url = "chart/getMyCenterData.do?cuName="+currentCenterName;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					myCenterdata= new Array();
					myCenterdata[0] = new Array();
					myCenterdata[1] = new Array();
					myCenterdata[2] = new Array();
					var data=eval(data);
					var eachHourTotalNum = 0;
					var nowClerkNum = 0;
					var nowNotClerkNum = 0;
					var nowOtherClerkNum = 0;
					if(data!=null){
						//clerkNum 员工数 orderNum 订单数  date 日期
						var dataL=data.length;
						for(var i=1;i<24;i++){
							if(i<dataL){
								eachHourTotalNum=(data[i].clerkNum?data[i].clerkNum:0)+(data[i].notClerkNum?data[i].notClerkNum:0)+(data[i].otherClerkNum?data[i].otherClerkNum:0);
								if(new Date().getHours()==data[i].time){
									nowClerkNum =(data[i].clerkNum?data[i].clerkNum:0);
									nowNotClerkNum =(data[i].notClerkNum?data[i].notClerkNum:0);
									nowOtherClerkNum =(data[i].otherClerkNum?data[i].otherClerkNum:0);
								}
								myCenterdata[0].push(Math.ceil(eachHourTotalNum));
								myCenterdata[1].push(eachHourTotalNum==0?0:Math.ceil(((data[i].clerkNum?data[i].clerkNum:0)/eachHourTotalNum)*100));

							}else{
								myCenterdata[0].push(Math.ceil(0));
								//myCenterdata[1].push(eachHourTotalNum==0?0:Math.ceil(((row.clerkNum?row.clerkNum:0)/eachHourTotalNum)*100));
							}
							myCenterdata[2].push((i+1)+"点");
						}
						MyCenterOption.series[0].data = myCenterdata[0];
						MyCenterOption.series[1].data = myCenterdata[1];
						MyCenterOption.xAxis.data = myCenterdata[2];
						myCenterchart.setOption(MyCenterOption, true);
						$("#nowClerkNum").html(nowClerkNum);
						$("#nowNotClerkNum").html(nowNotClerkNum);
						$("#nowOtherkNum").html(nowOtherClerkNum);
						assembThisHourBar(nowClerkNum,nowNotClerkNum,nowOtherClerkNum);
					}

			   }
			});
		};
//		function DrawCanvas(nowClerkNum,nowNotClerkNum,nowOtherClerkNum){
//            $("#myCanvas").attr("width",$("#myCenterAreaB").width());
//            $("#myCanvas").attr("height",$("#myCenterAreaB").height());
//            var painting = document.getElementById("myCanvas");
//            var cxt = painting.getContext("2d");
//            //实践表明在不设施fillStyle下的默认fillStyle=black
//            var paintingWidth = Math.ceil(painting.clientWidth);
//            var paintingHeight = Math.ceil(painting.clientHeight);
//            cxt.beginPath();
//            cxt.fillStyle = "#31fbfd";
//            cxt.moveTo(paintingWidth*0.1,paintingHeight*0.1);
//            cxt.lineTo(paintingWidth*0.85,paintingHeight*0.1);
//            cxt.lineTo(paintingWidth*0.9,paintingHeight*0.12);
//            cxt.lineTo(paintingWidth*0.9,paintingHeight*0.5);
//            cxt.lineTo(paintingWidth*0.1,paintingHeight*0.5);
//            cxt.fill();
//            cxt.closePath();
//            cxt.beginPath();
//            cxt.fillStyle = "#3088ff";
//            cxt.moveTo(paintingWidth*0.1,paintingHeight*0.52);
//            cxt.lineTo(paintingWidth*0.85,paintingHeight*0.52);
//            cxt.lineTo(paintingWidth*0.9,paintingHeight*0.54);
//            cxt.lineTo(paintingWidth*0.9,paintingHeight*0.76);
//            cxt.lineTo(paintingWidth*0.1,paintingHeight*0.76);
//            cxt.fill();
//            cxt.closePath();
//            cxt.beginPath();
//            cxt.fillStyle = "#dc61ff";
//            cxt.moveTo(paintingWidth*0.1,paintingHeight*0.78);
//            cxt.lineTo(paintingWidth*0.85,paintingHeight*0.78);
//            cxt.lineTo(paintingWidth*0.9,paintingHeight*0.8);
//            cxt.lineTo(paintingWidth*0.9,paintingHeight*0.9);
//            cxt.lineTo(paintingWidth*0.1,paintingHeight*0.9);
//            cxt.fill();
//            cxt.closePath();
//
//		}
		
		function assembThisHourBar(nowClerkNum,nowNotClerkNum,nowOtherClerkNum){
			var thisHourOption = {
					title: {
			            text: '员工数量',
			            left: '20%',
			            top:'4%',
				        textStyle:{
				        	color:'#84E9FF',
				        	fontFamily:'黑体',
				        	fontSize:'20'
				        }
			        },
				    xAxis: {
				    	type:'category',
				    	data:[''],
				    	axisLine:{
				    		lineStyle:{
				    			color:'#7ab8f9'
				    		}
				        },
				       	splitLine:{  
		                    show:false  
		                },
		                show:false
			        },
			        yAxis: [
			    	        {
			    	        	type:'value',
			    	        	name: '数量',
			    	        	axisLine:{
			    		    		lineStyle:{
			    		    			color:'#7ab8f9'
			    		    		}
			    		        },
			    		        splitLine:{  
			                        show:false  
			                    },
			                    splitNumber:6,
			                    show:false  
			    	        }
			    	     ],
			        grid:{
			        	left:0,
			        	right:'20%',
			        	bottom:0,
			        	top:'20%',
			        	containLabel:true
			        },

		            //color: ['#32bbec','#11d320','#9035B1'],
			        series: [

			        	{
				            name: '非正式工',
				            type: 'bar',
				            barWidth:300,
				            stack:1,
				            label:{
				            	normal:{
				            		show:true,
				            		position:'inside',
				            		formatter : function(params){
				            			return "非正式工 : "+params.value;
				        		    },			            		
				        		    textStyle: {
				            			color:'#00132a',
				                        fontSize: '20',
				                        fontWeight: 'bold',
				                        fontFamily:'digital-7__mono'
				                    }	
				            	}
			            	},
				            itemStyle:{
				            	normal:{
				            		color:"#3088ff",
				            		barBorderRadius: [0, 20, 0, 0]
				            	}
				            },
				            data: [nowNotClerkNum]
			        	},		          
			        	{
				            name: '正式工',
				            type: 'bar',
				            barWidth:300,
				            stack:1,
				            label:{
				            	normal:{
				            		show:true,
				            		position:'inside',
				            		formatter : function(params){
				            			return "正式工 : "+params.value;
				        		    },			            		
				        		    textStyle: {
				            			color:'#00132a',
				                        fontSize: '20',
				                        fontWeight: 'bold',
				                        fontFamily:'digital-7__mono'
				                    }	
				            	}
			            	},
				            itemStyle:{
				            	normal:{
				            		color:"#31fbfd",
				            		barBorderRadius: [0, 20, 0, 0]
				            	}
				            },
				            data: [nowClerkNum]
			        	},
			        	{
				            name: '其他',
				            type: 'bar',
				            barWidth:300,
				            stack:1,
				            label:{
				            	normal:{
				            		show:true,
				            		position:'inside',
				            		formatter : function(params){
				            			return "其他 : "+params.value;
				        		    },			            		
				        		    textStyle: {
				            			color:'#00132a',
				                        fontSize: '20',
				                        fontWeight: 'bold',
				                        fontFamily:'digital-7__mono'
				                    }	
				            	},
				            },
				            itemStyle:{
				            	normal:{
				            		color:"#dc61ff",
				            		barBorderRadius: [0, 20, 0, 0]
				            	}
				            },
				            data: [nowOtherClerkNum]
			        	},	
			        ]
				};
			thisHourDatachart = echarts.init(document.getElementById('thisHourData'));
			thisHourDatachart.setOption(thisHourOption, true);
			
		}
		function jumpToReport(){
			var thisURL = document.URL;    
			var  getval =thisURL.split('?')[1];  
			var empName= getval.split("=")[1];  
			window.location.href='reports.html?empName='+empName;
		} 
