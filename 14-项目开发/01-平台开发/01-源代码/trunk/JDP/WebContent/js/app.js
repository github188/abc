
angular.module('app', ['ngCookies']).controller('ctrl', ['$scope','$location','$http','$cookieStore', function($scope,$location,$http,$cookieStore){
	console.log("123456:"+$cookieStore.get('showornot'));
	
	if($cookieStore.get('showornot')!='true'){
		window.location.href = "login.html";
	}
	
/*	$scope.logout = function(){
		$cookieStore.put('showornot', 'false');
		window.href = "login.html";
	}*/
	
	$scope.c=['col-md-7 col-sm-7','col-md-5 col-sm-5','col-md-5 col-sm-5'];
	$scope.h=['false','false','false'];
	$scope.s=['height: 98%;','height: 49%;','height: 49%;'];
	$scope.islarge=false;
	$scope.large = function(index){
		console.log(index);
		if($scope.islarge==false){
			if(index==0){
				$scope.c[0]='col-md-12 col-sm-12';
				$scope.h[1]='true';
				$scope.h[2]='true';
				$scope.islarge=true;
			}else if(index==1){
				$scope.h[0]='true';
				$scope.c[1]='col-md-12 col-sm-12';
				$scope.s[1]='height: 98%;';
				$scope.h[2]='true';
				$scope.islarge=true;
			}else if(index==2){
				$scope.h[0]='true';
				$scope.h[1]='true';
				$scope.c[2]='col-md-12 col-sm-12';
				$scope.s[2]='height: 98%;';
				$scope.islarge=true;
				
			}
			return $(window).resize();
		}else{
			$scope.c=['col-md-7 col-sm-7','col-md-5 col-sm-5','col-md-5 col-sm-5'];
			$scope.h=['false','false','false'];
			$scope.s=['height: 98%;','height: 49%;','height: 49%;'];
			$scope.islarge=false;
		}
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
		$cookieStore.put('showornot', 'true');
//		location.href = "main.html";
		$http.post(url+"account="+$scope.usr+"&password="+$scope.pwd+"").success(function(response) {
			console.log(response);
			console.log(angular.fromJson(angular.fromJson(response)).returnCode);
			console.log('123:'+$cookieStore.get('showornot'));
			if(angular.fromJson(angular.fromJson(response)).returnCode=="0"){
//				$scope.showornot='false';
				$cookieStore.put('showornot', 'true');
				location.href = "main.html";
			}
		});
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

