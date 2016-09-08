option = null;
function randomData() {
    return Math.round(Math.random()*1000);
}
option = {
    title: {
        text: '在岗总人数',
        subtext: randomData(),
/*        borderColor:'#7ab8f9',
        borderWidth:2,*/
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
		}
    },
    legend: {
    	show:false,
        orient: 'vertical',
        left: 'left',
        data:['人数'],
        textStyle:{
		    color:'#fff'
		}
    },
    grid:{
    	top:'50%',
    	height:'20%',
    	containLabel:true
    },
    series: [
        {
            name: '人数',
            type: 'map',
            mapType: 'area',
            roam: false,
            label: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
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
				textStyle:{
		        	color:'#7ab8f9'
		        },
		        right:0
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
				textStyle:{
		        	color:'#7ab8f9'
		        },
				right:0
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


		var mapchart;
		var barchart;
		var barchart1;
		var barchart2;
		var piechart;
		$(document).ready(function(){
			init ();
		});
		function init (){
		    mapchart = echarts.init(document.getElementById('map'));
			barchart = echarts.init(document.getElementById('effect'));
			barchart1 = echarts.init(document.getElementById('count'));
			barchart2 = echarts.init(document.getElementById('bar'));
			piechart = echarts.init(document.getElementById('pie'));
			$.get('geoJson/area.geo.json', function (chinaJson) {
			    echarts.registerMap('area', chinaJson);
				mapchart.setOption(option, true);  
			});
			barchart.setOption(barOption, true);  
			barchart1.setOption(barOption1, true);  
			barchart2.setOption(barOption2, true);  
			piechart.setOption(pieOption, true);  

		}
/*		window.onresize=function(){resize();}*/
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
/*		alert($(document.body).height());
		alert($(document.body).width());*/
		function searchMap() {
			var url = "chart/getMapData.do";
			var currentCriterias=new Array();
			currentCriterias.push({field:'currentYear',op:'=', value:currentYear});	
			jht.ajax({
				url: url,
				type: "post",
				data: {"criterias":jht.JSON2Str(getExportCriterias(currentCriterias))},
				dataType: "text",
				success: function (data) {
					createTopLeftChart(data);
				}
			});
		};
		function searchMap() {
			var url = "chart/getMapData.do";
			var currentCriterias=new Array();
			currentCriterias.push({field:'currentYear',op:'=', value:currentYear});	
			jht.ajax({
				url: url,
				type: "post",
				data: {"criterias":jht.JSON2Str(getExportCriterias(currentCriterias))},
				dataType: "text",
				success: function (data) {
					createTopLeftChart(data);
				}
			});
		};
