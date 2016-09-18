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
            var res ='<div style="margin:0;background:url(images/tooltip.png)no-repeat;background-size: 100% 100% ;text-align:center;padding:0;width:150%;padding-top:20%;box-shadow: 2px 2px 10px #32bbec">'
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
	        	data:['xx分拣中心','xx分拣中心','xx分拣中心','xx分拣中心','xx分拣中心','xx分拣中心','xx分拣中心'],
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
				    getData('',mapName);
				    //loadDault(mapName);
				},statusCode: {404: function() {init ('全国');}}
			});
			mapchart.on('click', function (param){
				var name=param.name;
				//在中国地图上要去掉这几个地方的点击事件 直辖市 台湾 
				if(!name.match(/^北京|^天津|^重庆|^上海|在线|离线|台湾/)||name!=""){
					$("#areaTip").html(name||"全国");
				    var areaId=param.data.areaId;
					$.ajax({
						url: 'geoJson/'+name+'.json',
						type: "get",
						dataType: "json",
						success: function (chinaJson) {
						    echarts.registerMap(name, chinaJson);   
						    getData(areaId,name);
						    //loadDault(name);
						},
					  statusCode: {404: function() {init ('全国');}}
					});
			    }
			}); 
		};

		function loadDault(name){
			
			$("#areaTip").html(name||"全国");
			$("#mapTopLeft").html(name?name+'实时人力构成':'全国实时人力构成');
			option.title.text = name?(name+"在岗总人数"):"在岗总人数";
			option.series[0].mapType= (name||"全国");
			option.series[0].data = [randomData(),randomData(),randomData(),randomData(),randomData(),randomData()];
			pieOption.series[0].data = [
			    			            {
			    			            	name:'正式员工',
			    			            	value:randomData()
			    			            },
			    			            {
			    			            	name:'非正式员工',
			    			            	value:randomData()
			    			            }
			    		            ];
			//pieOption.legend.data = pieLegendData;

			
			$("#effectTopLeft").html(name?name+'一周人效走势图':'全国一周人效走势图');
		    barOption.series[0].data =[randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()];
		    barOption.series[1].data =[randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()];
		    barOption.series[2].data =[randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()];
		   // barOption.xAxis.data = bardata[3];
		    
			$("#countTopLeft").html(name?name+'一周在岗正式员工占比':'全国一周在岗正式员工占比');
			//barOption1.xAxis.data = bardata1[0];
			barOption1.series[0].data = [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()];
			barOption1.series[1].data = [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()];
			barOption1.series[2].data = [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()];
			
			$("#orderCountName").html(name?name+'平均人效':'全国平均人效');
			$("#orderCountNum").html(randomData());
			$("#averageEffectName").html(name?name+'订单量':'全国订单量');
			$("#averageEffectNum").html(randomData());
		    //barOption2.yAxis[0].data = bardata2[0];
		    barOption2.series[0].data = [randomData(),randomData(),randomData(),randomData(),randomData(),randomData(),randomData()];
		    
			mapchart.setOption(option, true);
		    barchart.setOption(barOption, true);
		    barchart1.setOption(barOption1, true);
		    barchart2.setOption(barOption2, true);
		    piechart.setOption(pieOption, true);
		}
		function getData(id,name){
			searchMap(id,name);
			searchBar2(id,name);
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
			if(data!=null){
				$.each(data, function(index, row){
					if(row.name.match(/^黑龙江|^内蒙古/)){
						row.name=data[index].name.substring(0,3);
					}
					mapdata.push({
						//地区名称 取china area表
						name:row.name,
						//numEmp 正式员工数 numTemp 临时工数 numOther 其他员工数
						value:row.EmpNum+row.NotEmpNum,
						//地区的id 取china area表id
				        'areaId':row.id,
				        selected:false
				        //自定义特殊 itemStyle，仅对该数据项有效
					}); 
					numEmp += row.EmpNum;
					notNumEmp += row.NotEmpNum;
				});
			}
			piedata.push(
					{
						name:'正式员工  '+numEmp,
						value:eval(numEmp/(numEmp+notNumEmp))*100+'%'
					},
					{
						name:'非正式员工  '+(numEmp+notNumEmp),
						value:eval(notNumEmp/(numEmp+notNumEmp))*100+'%'
					}

				); 
			pieLegendData.push('正式员工  '+numEmp);
			pieLegendData.push('非正式员工  '+(numEmp+notNumEmp));
			setMapOption(mapdata,piedata,pieLegendData,name);
		}

		function setMapOption(mapdata,piedata,pieLegendData,name){
			$("#areaTip").html(name||"全国");
			$("#mapTopLeft").html(name?name+'实时人力构成':'全国实时人力构成');
			option.title.text = name?(name+"在岗总人数"):"在岗总人数";
			option.series[0].mapType= (name||"全国");
			option.series[0].data = mapdata;
			pieOption.series[0].data = piedata;
			pieOption.legend.data = pieLegendData;
			mapchart.setOption(option, true);
			piechart.setOption(pieOption, true);
		}
			
		function searchBar(id,name) {
			var url = "chart/getBarData.do?id="+id;
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
					bardata[1].push(row.orderNum);
					bardata[2].push(Math.ceil(row.effect));
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
		
		function searchBar1(id,name) {
			var url = "chart/getBar1Data.do?id="+id;
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
		
		function searchBar2(id,name) {
			var url = "chart/getBar2Data.do?id="+id;
			$.ajax({
				url: url,
				type: "post",
				success: function (data) {
					makebar2Data(data);
				}
			});
		};
		//这是用来拼接坐下柱状图数据
		function makebar2Data(data,name){
			bardata2= new Array();
			bardata2[0] = new Array();
			bardata2[1] = new Array();
			var totalEffect = 0.0;
			var totalOrderNum = 0.0;
			var data=eval(data);
			if(data!=null){
				$.each(data, function(index, row){
					//name 地区名 averageEffect 该地区平均人效
					bardata2[0].push(row.name+'分拣中心');
					bardata2[1].push(row.effect);
					totalEffect += row.effect;
					totalOrderNum += row.orderNum;
				});
			}
			setBar2Option(bardata2,(totalEffect/data.length).toFixed(2),totalOrderNum,name);
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
		function logout(){
			window.location.href = "login.html";
		}