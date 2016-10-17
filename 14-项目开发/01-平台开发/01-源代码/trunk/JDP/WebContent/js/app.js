
angular.module('app', ['ngCookies']).controller('ctrl', ['$scope','$location','$http','$cookieStore', function($scope,$location,$http,$cookieStore){
	console.log("123456:"+$cookieStore.get('showornot'));
	
	if($cookieStore.get('showornot')!='true'){
		window.location.href = "login.html";
	}
/*	$scope.logout = function(){
		$cookieStore.put('showornot', 'false');
		window.href = "login.html";
	}*/
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
		$cookieStore.put('showornot', 'true');
		location.href = "main.html";
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
}]);

angular.module('yyreports', []).controller('yyreportsctrl', ['$scope', function($scope){
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
	$scope.projectList = [
			{id:'1',area:'1',pimname:'运维中',personname:'John',group:'1',shenfen:'1',erpid:'2',offline:'2016-5-12',online:'2016-5-12',identity:'@jh'},
			{id:'2',area:'1',pimname:'运维中',personname:'Bill',group:'1',shenfen:'1',erpid:'2',offline:'2016-5-9',online:'2016-5-12',identity:'@bg'},
			{id:'3',area:'1',pimname:'暂停',personname:'Bobo',group:'1',shenfen:'1',erpid:'2',offline:'2015-5-12',online:'2016-5-12',identity:'@hz'},
			{id:'4',area:'1',pimname:'暂停',personname:'Bobo',group:'1',shenfen:'1',erpid:'2',offline:'2015-5-12',online:'2016-5-12',identity:'@hz'},
			{id:'5',area:'1',pimname:'暂停',personname:'Bobo',group:'1',shenfen:'1',erpid:'2',offline:'2015-5-12',online:'2016-5-12',identity:'@hz'},
			{id:'6',area:'1',pimname:'暂停',personname:'Bobo',group:'1',shenfen:'1',erpid:'2',offline:'2015-5-12',online:'2016-5-12',identity:'@hz'},
			{id:'7',area:'1',pimname:'暂停',personname:'Bobo',group:'1',shenfen:'1',erpid:'2',offline:'2015-5-12',online:'2016-5-12',identity:'@hz'},
			{id:'8',area:'1',pimname:'暂停',personname:'Bobo',group:'1',shenfen:'1',erpid:'2',offline:'2015-5-12',online:'2016-5-12',identity:'@hz'}
		];
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
			{index:2,status:''},
			{index:3,status:''},
			{index:4,status:''}
		];
	$scope.changepage = function(index){
		$scope.pages[$scope.currentpage].status=$scope.status2;
		$scope.pages[index].status=$scope.status3;
		$scope.currentpage=index;
		console.log(index);
		console.log($scope.pages[index]);
		if (index!=0) {$scope.leftpagestatus=!$scope.status1;};
		if ($scope.pages[index].index==0) {$scope.leftpagestatus=$scope.status1;};
		if (index!=$scope.allpages) {$scope.rightpagestatus=!$scope.status1;};
		if ($scope.pages[index].index==$scope.allpages) {$scope.rightpagestatus=$scope.status1;};
	};
	$scope.rightchangepage= function(){
		if ($scope.pages[4].index!=$scope.allpages-1) {
			for (var i = $scope.pages.length - 1; i >= 0; i--) {
				$scope.pages[i].index++;
			};
		};
	};
	$scope.leftchangepage= function(){
		if ($scope.pages[0].index!=0) {
			for (var i = $scope.pages.length - 1; i >= 0; i--) {
				$scope.pages[i].index--;
			};
		};
	};
	$scope.first=function(){
		$scope.currentpage=0;
		$scope.pages=[
			{index:0,status:'font-size:12px;color:white'},
			{index:1,status:''},
			{index:2,status:''},
			{index:3,status:''},
			{index:4,status:''}
		];
	};

	$scope.inputs=[];
	$scope.contents=['区域','开始时间','结束时间','分拣场地'];
	$scope.fuhao=['大于','等于','小于','包含'];
	$scope.times=[];
	$scope.inputshow1=false;
	$scope.input1='';
	$scope.input2='';
	$scope.input3='';
	$scope.inputadd= function (){
		$scope.input1='';
		$scope.input2='';
		$scope.input3='';
		$scope.show=true;
	};
	$scope.inputdelete= function (input){
		$scope.inputs.splice(input,1);
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
			$scope.inputs.push($scope.input1+','+$scope.input2+','+$scope.input3);
			console.log($scope.inputs);
			$scope.show=false;
		}else{
			$scope.inputshow1=true;
		}
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
}]);

app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/index');
    $stateProvider
        .state('index', {
            url: '/index',
            templateUrl: 'templates/project/ProjectOnlineState.html',
            controller: 'ProjectOnlineStateCtrl'
        })
    .state('ProjectList', {
            url: '/ProjectList',
            templateUrl: 'templates/project/ProjectList.html',
            controller : 'ProjectListCtrl'
        })
    .state('ProjectInfo',{
        url:'/ProjectInfo/id=:id',
        templateUrl:'templates/project/ProjectInfo.html',
        controller :'ProjectInfoCtrl'
    })
    .state('ProjectOnlineList', {
            url: '/ProjectOnlineList',
            templateUrl: 'templates/project/ProjectOnlineList.html',
            controller :'ProjectOnlineListCtrl'
        })
    .state('ProjectOnlineEdit',{
        url:'/ProjectOnlineEdit/id=:id',
        templateUrl:'templates/project/ProjectOnlineEdit.html',
        controller :'ProjectOnlineEditCtrl'
    })
    .state('ProjectOnlineAdd',{
        url:'/ProjectOnlineAdd',
        templateUrl:'templates/project/ProjectOnlineAdd.html',
        controller :'ProjectOnlineAddCtrl'
    })
    .state('ProjectOnlineState', {
            url: '/ProjectOnlineState',
            templateUrl: 'templates/project/ProjectOnlineState.html',
            controller: 'ProjectOnlineStateCtrl'
        })
    .state('APICustomerList', {
            url: '/APICustomerList',
            templateUrl: 'templates/apilink/APICustomerList.html',
            controller : 'APICustomerListCtrl'
        })
    .state('APICustomerEdit',{
        url:'/APICustomerEdit/id=:id',
        templateUrl:'templates/apilink/APICustomerEdit.html',
        controller : 'APICustomerEditCtrl'
    })
    .state('APICustomerAdd',{
        url:'/APICustomerAdd',
        templateUrl:'templates/apilink/APICustomerAdd.html',
        controller : 'APICustomerAddCtrl'
    })
    .state('APIProjectList', {
            url: '/APIProjectList',
            templateUrl: 'templates/apilink/APIProjectList.html',
            controller : 'APIProjectListCtrl'
        })
    .state('APIProjectInfo',{
        url:'/APIProjectInfo/id=:id',
        templateUrl:'templates/apilink/APIProjectInfo.html',
        controller : 'APIProjectInfoCtrl'
    })
    .state('APIProjectAdd',{
        url:'/APIProjectAdd',
        templateUrl:'templates/apilink/APIProjectAdd.html',
        controller : 'APIProjectAddCtrl'
    })
    .state('APIProjectState', {
            url: '/APIProjectState',
            templateUrl: 'templates/apilink/APIProjectState.html',
            controller:'APIProjectStateCtrl'
        })
    .state('APIList',{
        url: '/APIList',
        templateUrl: 'templates/apilink/APIList.html',
        controller : 'APIListCtrl'
    })
    .state('APIEdit',{
        url: '/APIEdit/id=:id',
        templateUrl: 'templates/apilink/APIEdit.html',
        controller : 'APIEditCtrl'
    })
    .state('APIAdd',{
        url: '/APIAdd',
        templateUrl: 'templates/apilink/APIAdd.html',
        controller : 'APIAddCtrl'
    })
    .state('Hosts',{
        url: '/Hosts',
        templateUrl: 'templates/automation/Hosts.html',
        controller : 'HostsCtrl'
    })
    .state('HostEdit',{
        url: '/HostEdit/id=:id',
        templateUrl: 'templates/automation/HostEdit.html',
        controller : 'HostEditCtrl'
    })
    .state('HostAdd',{
        url: '/HostAdd',
        templateUrl: 'templates/automation/HostAdd.html',
        controller : 'HostAddCtrl'
    })
    .state('Projects',{
        url: '/Projects',
        templateUrl: 'templates/automation/Projects.html',
        controller : 'ProjectsCtrl'
    })
    .state('ProjectsEdit',{
        url: '/ProjectsEdit/id=:id',
        templateUrl: 'templates/automation/ProjectsEdit.html',
        controller : 'ProjectsEditCtrl'
    })
    .state('ProjectsAdd',{
        url: '/ProjectsAdd',
        templateUrl: 'templates/automation/ProjectsAdd.html',
        controller : 'ProjectsAddCtrl'
    })
    .state('Services',{
        url: '/Services',
        templateUrl: 'templates/automation/Services.html',
        controller : 'ServicesCtrl'
    })
    .state('ServicesEdit',{
        url: '/ServicesEdit/id=:id',
        templateUrl: 'templates/automation/ServicesEdit.html',
        controller : 'ServicesEditCtrl'
    })
    .state('ServicesAdd',{
        url: '/ServicesAdd',
        templateUrl: 'templates/automation/ServicesAdd.html',
        controller : 'ServicesAddCtrl'
    })
    .state('Automation',{
        url: '/Automation',
        templateUrl: 'templates/automation/Automation.html',
        controller : 'AutomationCtrl'
    });
});
