
angular.module('app', ['ngCookies']).controller('ctrl', ['$scope','$location','$http','$cookieStore', function($scope,$location,$http,$cookieStore){
	console.log("123456:"+$cookieStore.get('showornot'));
	
	if($cookieStore.get('showornot')!='true'){
		window.location.href = "login.html";
	}
	$scope.logout = function(){
		$cookieStore.put('showornot', 'false');
		window.location.href = "login.html";
	}
}]);
var app = angular.module('jsywp', ['ui.bootstrap','ui.router','chart.js','ngFileUpload','ngCookies']);

app.controller('indexCtrl', ['$scope','$rootScope', '$http','$cookieStore',function($scope,$rootScope,$http,$cookieStore){
    $rootScope.systems=["一系统","二系统","三系统"];
    $rootScope.subsystems = [
        {
          label: '一系统',
          subcompanys: [
            '1','1','1','1'
          ]
        },
        {
          label: '二系统',
          subcompanys: [
            '2','2','2','2'
          ]
        },
        {
          label: '三系统',
          subcompanys: [
            '3','3','3','3'
          ]
        }
    ];
	$scope.status = {
		closeothers : false,
		isoneopen : true ,
		isonedisable : false
	}
	$scope.showornot='true';
	console.log($cookieStore.get('showornot'));
	  // Setting a cookie
	if($cookieStore.get('showornot')=='true'){
//		$scope.showornot='false';
	}else{
//		$scope.showornot='true';
	}
	$scope.usr='';
	$scope.pwd='';
	$scope.cc = function(){
//		$cookieStore.put('showornot', 'true');
		console.log($scope.usr+':'+$scope.pwd);
		var url = "user/login.do?";
//		$cookieStore.put('showornot', 'true');
//		location.href = "main.html";
		$http.post(url+"account="+$scope.usr+"&password="+$scope.pwd+"").success(function(response) {
			console.log(response);
			console.log(angular.fromJson(angular.fromJson(response)).returnCode);
			console.log('123:'+$cookieStore.get('showornot'));
			if(angular.fromJson(angular.fromJson(response)).returnCode=="0"){
//				$scope.showornot='false';
				$cookieStore.put('showornot', 'true');
				location.href = "main.html";
			}else{
				$("#alert-user").attr("class","alert alert-danger");
			}
		});
	}
	$scope.logout = function(){
		$cookieStore.put('showornot', 'false');
		window.location.href = "login.html";
	}
}]);

var reports = angular.module('reports', ['ngCookies','ui.bootstrap','ngFileUpload']);
reports.controller('yyreportsctrl', ['$scope','$http','$cookieStore','Upload', function($scope,$http,$cookieStore,Upload){
	end = new Date();
	$scope.ee="2016-01-11T05:25:07Z";
	$scope.dt=new Date(end.valueOf() - 7*24*60*60*1000).getFullYear()+"-"+(new Date(end.valueOf() - 7*24*60*60*1000).getMonth()+1)+"-"+new Date(end.valueOf() - 7*24*60*60*1000).getDate()+" "+new Date(end.valueOf() - 7*24*60*60*1000).getHours()+" 时";
    $scope.openCalendar = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };
    $scope.dt1=new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()+" "+new Date().getHours()+" 时";
    $scope.openCalendar1 = function ($event) {
    	$event.preventDefault();
    	$event.stopPropagation();
    	$scope.opened1 = true;
    };
	if($cookieStore.get('showornot')!='true'){
		window.location.href = "login.html";
	}
	$scope.activebase='active';
	$scope.active1=$scope.activebase;
	$scope.active2=!$scope.activebase;
	$scope.active3=!$scope.activebase;
	$scope.loglog = function(item){
		console.log(item);
	}
	$scope.items=[
			{name:'总接入项目数',count:'100',style:'active'},
			{name:'新接入项目数',count:'20',style:'disable'},
			{name:'离线项目数',count:'10',style:'disable'}
		];
	$scope.lastactive=0;//保存上一个被选中的按钮
	$scope.active = function(index){
		$scope.items[$scope.lastactive].style='disable';
		$scope.items[index].style='active';
		$scope.lastactive=index;
	};
	$scope.projectList = [];
	$scope.menus=['项目名称','接入日期','所属系统'];
	$scope.sortBy = function(menu){
		console.log(menu);
	};
	$scope.currentpage=0;
	$scope.allpages=10;
	$scope.status1='disabled';
	$scope.status2='enable';
	$scope.status3='font-size:12px;color:white';
	$scope.leftpagestatus=$scope.status1;
	$scope.rightpagestatus=!$scope.status1;
	$scope.pages=[
			{index:0,status:'font-size:12px;color:white'},
			{index:1,status:''},
			{index:2,status:''}
		];
	$scope.changepage = function(index){
		$scope.inputsss=[];
		$scope.inputsss.push("区域,"+$scope.input11);
		$scope.inputsss.push("开始时间,"+$scope.dt);
		$scope.inputsss.push("结束时间,"+$scope.dt1);
		$scope.inputsss.push("分拣场地,"+$scope.input22);
//		$scope.pages[$scope.currentpage].status=$scope.status2;
		$scope.pages[index].status=$scope.status3;
		$scope.currentpage=$scope.pages[index].index;
		console.log(index);
		console.log($scope.pages[index]);
		if (index!=0) {$scope.leftpagestatus=!$scope.status1;};
		if ($scope.pages[index].index==0) {$scope.leftpagestatus=$scope.status1;};
		if (index!=$scope.allpages) {$scope.rightpagestatus=!$scope.status1;};
		if ($scope.pages[index].index==$scope.allpages) {$scope.rightpagestatus=$scope.status1;};
		console.log($scope.currentpage);
		var url = "export/queryyydata.do?";
		$http.post(url+"inputs="+$scope.inputsss+"&pages="+$scope.currentpage).success(function(response) {
			console.log(response);
			$scope.projectList=angular.fromJson(angular.fromJson(response));
		});

	};
	$scope.rightchangepage= function(){
		if ($scope.pages[2].index!=$scope.allpages-1) {
			for (var i = $scope.pages.length - 1; i >= 0; i--) {
				$scope.pages[i].index++;
			};
		};
		console.log($scope.currentpage);
		
	};
	$scope.leftchangepage= function(){
		if ($scope.pages[0].index!=0) {
			for (var i = $scope.pages.length - 1; i >= 0; i--) {
				$scope.pages[i].index--;
			};
		};
	};
	$scope.first=function(){
		$scope.inputsss=[];
		$scope.inputsss.push("区域,"+$scope.input11);
		$scope.inputsss.push("开始时间,"+$scope.dt);
		$scope.inputsss.push("结束时间,"+$scope.dt1);
		$scope.inputsss.push("分拣场地,"+$scope.input22);
		$scope.currentpage=0;
		$scope.pages=[
			{index:0,status:'font-size:12px;color:white'},
			{index:1,status:''},
			{index:2,status:''}
		];
		console.log($scope.currentpage);
		var url = "export/queryyydata.do?";
		$http.post(url+"inputs="+$scope.inputsss+"&pages="+$scope.currentpage).success(function(response) {
			console.log(response);
			$scope.projectList=angular.fromJson(angular.fromJson(response));
		});
	};

	$scope.inputs=[];
	$scope.contents=['区域','开始时间','结束时间','分拣场地'];
	$scope.fuhao=['>','=','<'];
	$scope.times=[];
	$scope.inputshow1=false;
	$scope.input1='';
	$scope.input2='=';
	$scope.input3='';
	console.log($scope.currentpage);
	$scope.inputssss=[];
	$scope.inputssss.push("区域, ");
	$scope.inputssss.push("开始时间,"+$scope.dt);
	$scope.inputssss.push("结束时间,"+$scope.dt1);
	$scope.inputssss.push("分拣场地, ");
	var url = "export/queryyydata.do?";
	$scope.flag=true;
	if($scope.flag){
		$http.post(url+"inputs="+$scope.inputssss+"&pages="+$scope.currentpage).success(function(response) {
			console.log(angular.fromJson(angular.fromJson(response)));
			$scope.projectList=angular.fromJson(angular.fromJson(response));
			if(angular.fromJson(angular.fromJson(response)).length==0){
				$scope.allpages=3;
				$scope.counts=0;
			}else{
				$scope.counts=angular.fromJson(angular.fromJson(response))[0].counts;
				if(angular.fromJson(angular.fromJson(response))[0].allpages<3){
					$scope.allpages=3;
				}else{
					$scope.allpages=angular.fromJson(angular.fromJson(response))[0].allpages;
				}
			}
			$scope.flag=false;
		});
	}
	$scope.inputadd= function (){
		if($scope.contents.length!=0){
			$scope.input1='';
			$scope.input2='=';
			$scope.input3='';
			$scope.show=true;
		}
	};
	$scope.inputdelete= function (input){
		console.log($scope.inputs[input].split(',')[0]);
		$scope.contents.push($scope.inputs[input].split(',')[0]);
		$scope.inputs.splice(input,1);
		console.log($scope.contents);
		console.log($scope.inputs);
	};
	$scope.inputblur= function (){
		if($scope.inputshow1.length==0){
			$scope.inputshow1=true;
		}else{
			$scope.inputshow1=false;
		}
	};
	$scope.save1= function (){
		if($scope.input1.length!=0&&$scope.input2.length!=0&&$scope.input3.length!=0){
			$scope.inputs.push($scope.input1+','+$scope.input3);
			console.log($scope.inputs);
			$scope.show=false;
			for (var i = $scope.contents.length - 1; i >= 0; i--) {
				if($scope.contents[i]==$scope.input1){
					$scope.contents.splice(i,1);
					break;
				}
			};
		}else{
			$scope.inputshow1=true;
		}
	};
	$scope.save11= function (){
		$scope.inputsss=[];
		console.log($scope.input11);
		console.log($scope.dt);
		console.log($scope.dt1);
		console.log($scope.input22);
		$scope.inputsss.push("区域,"+$scope.input11);
		$scope.inputsss.push("开始时间,"+$scope.dt);
		$scope.inputsss.push("结束时间,"+$scope.dt1);
		$scope.inputsss.push("分拣场地,"+$scope.input22);
		$scope.currentpage=0;
		$scope.pages=[
			{index:0,status:'font-size:12px;color:white'},
			{index:1,status:''},
			{index:2,status:''}
		];
		console.log($scope.currentpage);
		var url = "export/queryyydata.do?";
		$http.post(url+"inputs="+$scope.inputsss+"&pages="+$scope.currentpage).success(function(response) {
			console.log(response);
			$scope.projectList=angular.fromJson(angular.fromJson(response));
			if(angular.fromJson(angular.fromJson(response)).length==0){
				$scope.allpages=3;
				$scope.counts=0;
			}else{
				$scope.counts=angular.fromJson(angular.fromJson(response))[0].counts;
				if(angular.fromJson(angular.fromJson(response))[0].allpages<3){
					$scope.allpages=3;
				}else{
					$scope.allpages=angular.fromJson(angular.fromJson(response))[0].allpages;
				}
			}
		});
		$scope.inputsss=[];
	};

	$scope.outputshow1=false;
	$scope.output1='';
	$scope.outputadd= function (){
		$scope.output1='';
		$scope.show1=true;
	};
	$scope.outputdelete= function (output){
		console.log(output);
		$scope.apis.output.splice(output,1);
		console.log($scope.apis.output);
	};
	$scope.outputblur= function (){
		if($scope.outputshow1.length==0){
			$scope.outputshow1=true;
		}else{
			$scope.outputshow1=false;
		}
	};
	$scope.save2= function (){
		if($scope.output1.length!=0){
			$scope.apis.output.push($scope.output1);
			console.log($scope.apis.output);
			$scope.show1=false;
		}else{
			$scope.outputshow1=true;
		}
	};
	$scope.checkornot= function(index,value){
		console.log(index + value);
	}
	$scope.bijiaofu='等于';
	$scope.blur1=function(){
		if($scope.input1=="开始时间"||$scope.input1=="结束时间"){
			$scope.input3type="date";
			$scope.bijiaofu='等于';
		}else{
			$scope.input3type="text";
			$scope.bijiaofu='等于';
		}
	}
	$scope.daochu=function(){
		$scope.inputsss=[];
		console.log($scope.input11);
		console.log($scope.dt);
		console.log($scope.dt1);
		console.log($scope.input22);
		$scope.inputsss.push("区域,"+$scope.input11);
		$scope.inputsss.push("开始时间,"+$scope.dt);
		$scope.inputsss.push("结束时间,"+$scope.dt1);
		$scope.inputsss.push("分拣场地,"+$scope.input22);
		var url = "export/yydata.do?";
		$http.post(url+"inputs="+$scope.inputsss).success(function(response) {
			console.log(response);
			if(response!=""){
				window.location.href = ""+angular.fromJson(response);
			}
//			window.open("111.xls"); 
		});
	}
	$scope.save=function(){
		$scope.inputsss=[];
		console.log($scope.input11);
		console.log($scope.dt);
		console.log($scope.dt1);
		console.log($scope.input22);
		$scope.inputsss.push("区域,"+$scope.input11);
		$scope.inputsss.push("开始时间,"+$scope.dt);
		$scope.inputsss.push("结束时间,"+$scope.dt1);
		$scope.inputsss.push("分拣场地,"+$scope.input22);
		$scope.currentpage=0;
		$scope.pages=[
			{index:0,status:'font-size:12px;color:white'},
			{index:1,status:''},
			{index:2,status:''}
		];
		console.log($scope.currentpage);
		var url = "export/queryyydata.do?";
		$http.post(url+"inputs="+$scope.inputsss+"&pages="+$scope.currentpage).success(function(response) {
			console.log(response);
			$scope.projectList=angular.fromJson(angular.fromJson(response));
			if(angular.fromJson(angular.fromJson(response)).length==0){
				$scope.allpages=3;
				$scope.counts=0;
			}else{
				$scope.counts=angular.fromJson(angular.fromJson(response))[0].counts;
				if(angular.fromJson(angular.fromJson(response))[0].allpages<3){
					$scope.allpages=3;
				}else{
					$scope.allpages=angular.fromJson(angular.fromJson(response))[0].allpages;
				}
			}
//			window.open("images/111.xls"); 
		});
	}
	$scope.logout = function(){
		$cookieStore.put('showornot', 'false');
		window.location.href = "login.html";
	}
	$scope.uploadresult="";
	$scope.uploadExcel=function(file){
		console.log(file);
		Upload.upload({
            //服务端接收
            url: 'user/fileUpload.do?',
            //上传的同时带的参数
            data: {},
            //上传的文件
            file: file
        }).progress(function (evt) {
            //进度条
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progess:' + progressPercentage + '%' + evt.config.file.name);
            $scope.uploadresult="上传 "+progressPercentage + '%';
        }).success(function (data, status, headers, config) {
            //上传成功
            console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
           // $scope.uploadImg = data;
        }).error(function (data, status, headers, config) {
            //上传失败
            console.log('error status: ' + status);
        });
	};
}]);

reports.controller('ycreportsctrl', ['$scope','$http', function($scope,$http){
	
}]);