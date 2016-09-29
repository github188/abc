option = null;
function randomData() {
    return Math.round(Math.random()*100)+100;
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
            var res ='<div style="margin:0;background:url(images/tooltip.png)no-repeat;background-size: 100% 100% ;text-align:center;padding:0;width:150%;padding-top:20%;box-shadow: 2px 2px 10px #32bbec">'
            	+'<p style="background:#32bbec;color:black;padding:0;margin:0;width:70%;margin-left:auto;margin-right:auto;font-size:1px;font-family:"造字工房悦圆常规体", Arial, Helvetica, sans-serif;">'+name+'</p><p style="margin:0;color:#32bced;padding:0;">人数</p><p style="margin:0;padding:0;color:#11d320;font-family:digital-7__mono, Arial, Helvetica, sans-serif; ">'+value[2].toFixed(0)+'</p></div>';
                    //设置自定义数据的模板，这里的模板是图片
            console.log(res);
/*            setTimeout(function (){
                // 仅为了模拟异步回调
                callback(ticket, res);//回调函数，这里可以做异步请求加载的一些代码
            }, 1000)
            return 'loading';*/
            return res;
        },alwaysShowContent:false
    },
    grid:{
    	top:0,
    	height:'100%',
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
                 textStyle:{color:'#FFFFFF',fontFamily:'造字工房悦圆常规体'}
             },
             emphasis: {
            	 show: true,
			     textStyle:{color:'#46D7FF',fontFamily:'造字工房悦圆常规体',fontSize:20}
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
                    textStyle:{color:'#FFFFFF',fontFamily:'造字工房悦圆常规体'}
                },
/*                emphasis: {
                    show: true,
                    textStyle:{color:'#46D7FF',fontFamily:'造字工房悦圆常规体',fontSize:20}
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
	        	left:10,
	        	right:12,
	        	bottom:'10%',
	        	top:'20%',
	        	containLabel:true
	        },
	        yAxis: [
	        {
	        	type:'value',
	        	name: '数量(个)',
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
	        	name: '人效(单/每人)',
	        	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		        } ,
		        splitLine:{  
                    show:false  
                },//position:'right',
	        }
	        ],
            color: ['#32bbec','#11d320','#9035B1'],
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
	        	left:10,
	        	right:10,
	        	bottom:'10%',
	        	top:'20%',
	        	containLabel:true
	        },
	        yAxis: [
	        {
	        	type:'value',
	        	name: '数量(个)',
	        	axisLine:{
		    		lineStyle:{
		    			color:'#7ab8f9'
		    		}
		        },
		        splitLine:{  
                    show:false  
                },
                splitNumber:6
	        },{
	        	type:'value',
	        	name: '百分比(%)',
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
	        	left:'4%',
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
	        	data:['哈尔滨分拨中心'],
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
		                    },
		                    barBorderRadius:[5, 5, 5, 5]
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
		        trigger: 'item',
		    },
			legend:{
				data:['正式员工','非正式员工'],
				bottom:10,
				right:0,
				textStyle:{
		        	color:'#fff'
		      	},zlevel:1,orient:'horizontal'
			},
		    series : [
		        {
		            name:'',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '45%'],
		            center: ['50%', '50%'],

		            //因为有visualMap这个是无效的
		            color: ['#FE8C02',  '#9035B1'],
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
				                    position: 'inside',
				                    show:false
				                },
				                
				            },
			            },
			            {
			            	name:'非正式员工',
			            	value:randomData(),
				            label: {
				                normal: {
				                    position: 'inside',
				                    show:false
				                },
				                
				            },
			            }
		            ]
		        }
		    ]
		};
		$(document).ready(function () {
			var newDate = new Date();
			/*newDate.setDate(newDate.getDate());*/

			setInterval( function() {
				var year = newDate.getFullYear();
				var month = newDate.getMonth()+1;
				var day = newDate.getDate();
				var seconds = new Date().getSeconds();
				var minutes = new Date().getMinutes();
				var hours = new Date().getHours();
				if(seconds=='0'&&minutes=='30'){
					location.reload(true);
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
		});
		var enlarged = false;
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
				    getData(mapName);
				    //loadDault(mapName);
				},statusCode: {404: function() {init ('全国');}}
			});
			mapchart.on('click', function (param){
				var name=param.name;
				//在中国地图上要去掉这几个地方的点击事件 直辖市 台湾 
				if(!name.match(/^北京|^天津|^重庆|^上海|在线|离线|台湾/)||name!=""){
					$("#areaTip").html(name||"全国");
					$.ajax({
						url: 'geoJson/'+name+'.json',
						type: "get",
						dataType: "json",
						success: function (chinaJson) {
						    echarts.registerMap(name, chinaJson);   
						    getData(name);
						    //loadDault(name);
						},
					  statusCode: {404: function() {init ('全国');}}
					});
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
			    				                    position: 'inside',
			    				                    show:true
			    				                },
			    				                
			    				            },
			    			            },
			    			            {
			    			            	name:'非正式员工'+eval(3786/10821).toFixed(2)*100+'%',
			    			            	value:3786,
			    				            label: {
			    				                normal: {
			    				                    position: 'inside',
			    				                    show:true
			    				                },
			    				                
			    				            },
			    			            }
			    		            ];
			//pieOption.legend.data = pieLegendData;
			pieOption.legend.data = ['正式员工'+eval(7035/10821).toFixed(2)*100+'%','非正式员工'+eval(3786/10821).toFixed(2)*100+'%'];
			$("#effectTopLeft").html(name?name+'一周人效走势图':'全国一周人效走势图');
		    barOption.series[0].data =[10823,11375,10315,10008,11027,9875,9763];
		    barOption.series[1].data =[8019843,8679125,7756880,7315848,8248196,7119875,7126990];
		    barOption.series[2].data =[741,763,752,731,748,721,730];
		   // barOption.xAxis.data = bardata[3];
		    
			$("#countTopLeft").html(name?name+'一周在岗正式员工占比':'全国一周在岗正式员工占比');
			//barOption1.xAxis.data = bardata1[0];
			barOption1.series[0].data = [7035,7394,6704,6505,7167,6418,6346];
			barOption1.series[1].data = [3788,3981,3611,3503,3859,3457,3417];
			barOption1.series[2].data = [0.61,0.62,0.65,0.63,0.64,0.62,0.65];
			
			$("#orderCountName").html(name?name+'平均人效':'全国平均人效');
			$("#orderCountNum").html(763);
			$("#averageEffectName").html(name?name+'订单量':'全国订单量');
			$("#averageEffectNum").html(7488742);
		    barOption2.yAxis[0].data = ['东北地区','华北地区','华东地区','西北地区','西南地区','华中地区','华南地区'];
		    barOption2.series[0].data = [812,788,747,727,702,698,710];
			mapchart.setOption(option, true);
		    barchart.setOption(barOption, true);
		    barchart1.setOption(barOption1, true);
		    barchart2.setOption(barOption2, true);
		    piechart.setOption(pieOption, true);
		}
		function getData(name){
			searchMap(name);
			searchBar2(name);
		/*	if(!enlarged){*/
				searchBar(name);
				searchBar1(name);
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
		
		//获取拼接地图数据
		function makeMapData(data,name){
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
					var c = eval(row.EmpNum+row.NotEmpNum+row.otherNumEmp);
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
						mapdata.push({
							//地区名称 取china area表
							name:row.name,
							//numEmp 正式员工数 numTemp 临时工数 numOther 其他员工数
							value:[row.x,row.y,c],
							//地区的id 取china area表id
					        selected:false,
					        //自定义特殊 itemStyle，仅对该数据项有效
					        symbolSize:5,
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
						}); 
					}else if(c>=100){
						var size = Math.round(c / 80);
						mapdata.push({
							//地区名称 取china area表
							name:row.name,
							//numEmp 正式员工数 numTemp 临时工数 numOther 其他员工数
							value:[row.x,row.y,c],
							//地区的id 取china area表id
					        selected:false,
					        //自定义特殊 itemStyle，仅对该数据项有效
				            symbolSize: size,
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
						}); 
					}
					otherNumEmp += row.otherNumEmp;
					numEmp += row.EmpNum;
					notNumEmp += row.NotEmpNum;
				});
			}
			a=numEmp+notNumEmp==0?0:((numEmp/(numEmp+notNumEmp)).toFixed(2));
			b=numEmp+notNumEmp==0?0:((notNumEmp/(numEmp+notNumEmp)).toFixed(2));
			piedata.push(
					{
						name:'正式员工  '+Math.ceil(a*100)+'%',
						value: Math.ceil(numEmp),
			            label: {
			                normal: {
			                    position: 'inside',
			                    show:false
			                },
			                
			            },
					},
					{
						name:'非正式员工  '+Math.ceil(b*100)+'%',
						value:Math.ceil(notNumEmp),
			            label: {
			                normal: {
			                    position: 'inside',
			                    show:false
			                },
			                
			            },
					}

				); 
			pieLegendData.push('正式员工  '+Math.ceil(a*100)+'%');
			pieLegendData.push('非正式员工  '+Math.ceil(b*100)+'%');
			setMapOption(mapdata,piedata,pieLegendData,numEmp+notNumEmp+otherNumEmp,name);
		}

		function setMapOption(mapdata,piedata,pieLegendData,total,name){
			$("#areaTip").html(name||"全国");
			$("#mapTopLeft").html(name?name+'实时人力构成':'全国实时人力构成');
			option.title.text = name?(name+"在岗总人数"):"在岗总人数";
			option.title.subtext =(total?""+Math.ceil(total)+"":'0');
			option.geo.map= (name||"全国");
			if(name=='海南'){
				option.geo.center = [109.8,19];
				option.geo.zoom = 4;
			}else{
				option.geo.center = [];
				option.geo.zoom = 1;
			}
			option.series[0].data = mapdata;
			pieOption.series[0].data = piedata;
			pieOption.legend.data = pieLegendData;
			mapchart.setOption(option, true);
			piechart.setOption(pieOption, true);
		}
			
		function searchBar(name) {
			var url = "chart/getBarData.do?name="+name;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					makebarData(data,name);
				}
			});
		};
		
		//获取拼接右上数据
		function makebarData(data,name){
			bardata= new Array();
			bardata[0] = new Array();
			bardata[1] = new Array();
			bardata[2] = new Array();
			bardata[3] = new Array();
			var data=eval(data);
			if(data!=null){
				$.each(data, function(index, row){
					//clerkNum 员工数 orderNum 订单数  date 日期
					bardata[0].push(row.clerkNum);
					bardata[2].push(eval(row.orderNum/4));
					bardata[1].push(Math.ceil(row.effect/4));
					bardata[3].push(row.name);
				});
			}
			setBarOption(bardata,name);
		}
		
		function setBarOption(bardata,name){
			$("#effectTopLeft").html(name?name+'一周人效走势图':'全国一周人效走势图');
		    barOption.series[0].data = bardata[0];
		    barOption.series[1].data = bardata[1];
		    barOption.series[2].data = bardata[2];
		    barOption.xAxis.data = bardata[3];
			barchart.setOption(barOption, true);  
		}
		
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
			bardata1[2] = new Array();
			bardata1[3] = new Array();
			var data=eval(data);
			if(data!=null){
				$.each(data, function(index, row){
					//date 时间  empNum 员工人数 otherClerkNum 其他员工数
					bardata1[0].push(row.name);
					bardata1[1].push(row.EmpNum);
					bardata1[2].push(row.NotEmpNum);
					bardata1[3].push(row.NotEmpNum=='0'?'0':(row.EmpNum/(row.EmpNum+row.NotEmpNum)).toFixed(2));
				});
			}
			setBar1Option(bardata1,name);
		}
		
		function setBar1Option(bardata1,name){
			$("#countTopLeft").html(name?name+'一周在岗正式员工占比':'全国一周在岗正式员工占比');
			barOption1.xAxis.data = bardata1[0];
			barOption1.series[0].data = bardata1[1];
			barOption1.series[1].data = bardata1[2];
			barOption1.series[2].data = bardata1[3];
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
		//这是用来拼接坐下柱状图数据
		function makebar2Data(data,name){
			bardata2= new Array();
			bardata2[0] = new Array();
			bardata2[1] = new Array();
			var totalClerkNum = 0.0;
			var totalOrderNum = 0.0;
			var data=eval(data);
			if(data!=null){
				$.each(data, function(index, row){
					//name 地区名 averageEffect 该地区平均人效
					bardata2[0].push(row.name);
					bardata2[1].push(row.clerkNum>0?eval(row.orderNum/row.clerkNum).toFixed(2):0);
					totalClerkNum += row.clerkNum;
					totalOrderNum += row.orderNum;
				});
			}
			setBar2Option(bardata2,((data&&data.length>0&&totalClerkNum>0)?(totalOrderNum/totalClerkNum).toFixed(2):'0'),totalOrderNum,name);
		}

		function setBar2Option(bardata2,avgEffect,totalOrderNum,name){
			$("#orderCountName").html(name?name+'平均人效':'全国平均人效');
			$("#orderCountNum").html(avgEffect);
			$("#averageEffectName").html(name?name+'订单量':'全国订单量');
			$("#averageEffectNum").html(totalOrderNum);
		    barOption2.yAxis[0].data = bardata2[0];
		    barOption2.series[0].data = bardata2[1];
			barchart2.setOption(barOption2, true);  
		}
		
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
					var map = $('#map');
					map.css({"height":"93%","width":"60%"});
					var bar = $('#bar');
					bar.css({"height":"50%","width":"40%","bottom":"4%","top":"auto","left":"auto","right":"0"});
					var pie = $('#pie');
					pie.css({"height":"40%","width":"30%","bottom":"auto","top":"4%","right":"18%"});
					pieOption.legend.right='20%';
					pieOption.legend.bottom=20;
					pieOption.legend.orient='horizontal';
					pieOption.grid={
						width:'100%',
						hight:'100%',
			        	containLabel:true
			        };
					pieOption.series[0].radius=[0, '60%'];
					piechart.setOption(pieOption, true);
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.resize();
					piechart.resize();
					$('.text1 ').css({"bottom":"44rem","right":"10%"});
					$('.text2 ').css({"bottom":"36rem","right":"10%"});
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
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.resize();
					piechart.resize();
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
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.resize();
					piechart.resize();
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
					var map = $('#map');
					map.css({"height":"65%","width":"90%"});
					var bar = $('#bar');
					bar.css({"height":"38%","width":"70%","top":"61%","left":"2%"});
					var pie = $('#pie');
					pie.css({"height":"30%","width":"30%","top":"auto","bottom":"15rem","right":"1%"});
					pieOption.legend.right=0;pieOption.legend.bottom=10;
					pieOption.series[0].radius=[0, '45%'];
					piechart.setOption(pieOption, true);
					$('.text1 ').css({"bottom":"8rem","right":"2%"});
					$('.text2 ').css({"bottom":"2rem","right":"2%"});
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
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
					mapchart.resize();
					barchart.resize();
					barchart1.resize();
					barchart2.resize();
					piechart.resize();
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
		};
		function logout(){
			window.location.href = "login.html";
		}