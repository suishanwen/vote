var backgroundApp=angular.module('backgroundHistoryApp',['ui.bootstrap','wiki.common']);
backgroundApp.controller("BackgroundHistoryController",["$scope","Path","$http","$timeout","$q",function($scope,Path,$http,$timeout,$q,$location,voteService) {

    $scope.projectName=getQueryString(window.location.search,"projectName");
    $scope.backgroundNo=getQueryString(window.location.search,"backgroundNo");
    $scope.backgroundOld={
        projectName:$scope.projectName,
        backgroundNo:$scope.backgroundNo
    };
    $scope.historyBackground="";
    function getQueryString(queryString, name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = queryString.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2])
        }
        return null;
    }
    var url = Path.getUri("api/background/history");
    $http.post(url,$scope.backgroundOld).success(function (data) {
        if(data){
            $scope.BackgroundVo=data;
            if ($scope.BackgroundVo.backgroundNo > 101) {
                for (var i = 101; i < $scope.BackgroundVo.backgroundNo; i++) {
                    $scope.historyBackground = $scope.historyBackground + " <a href=\'background-history.html?projectName=" + $scope.projectName + "&backgroundNo=" + i + "\'>" + i + "</a>"
                }
            }else{
                $scope.countLoad=20;
            }
            $timeout(function(){
                $scope.search={workerId:""}
            },100)
        }else{
            alert("无此历史后台记录，将返回最新后台！");
            window.location.href="background.html?projectName="+$scope.projectName;
        }
    }).error(function () {
        alert("查询后台信息失败！");
    });

    $scope.$watch("search.workerId", function (newvalue, oldvalue) {
        if (newvalue||newvalue=="") {
            calBackground();
        }
    });

    var calBackground=function(){
        var now = new Date();
        var date = new Date(now.getTime() - 900 * 1000);
        var participate=0;
        var activeParticipate=0;
        var finishNum=0;
        angular.forEach($scope.BackgroundVo.recordVoList, function (record) {
            if(record.workerId.toUpperCase().indexOf($scope.search.workerId.toUpperCase()) > -1 ){
                participate++;
                if (record.uploadTime > date) {
                    record.onLine=true;
                    activeParticipate++;
                }else{
                    record.online=false;
                }
                finishNum += record.succNum;
            }
        });
        $scope.BackgroundVo.participate = participate;
        $scope.BackgroundVo.activeParticipate = activeParticipate;
        $scope.BackgroundVo.finishNum = finishNum;
    };
    $scope.countLoad=0;
    $scope.checkLoadOver=function(element){
        if($scope.historyBackground||$scope.countLoad==20){
            element.append($scope.historyBackground)
        }else{
            $timeout(function(){
                $scope.countLoad++;
                $scope.checkLoadOver(element)
            },1000);
        }
    }
}]);
backgroundApp.directive('historyBackground', function ($q) {
    return {
        restrict: 'E',
        template: '<span class=\"app-content\">历史后台:</span>',
        link: function (scope, element, attrs) {
            scope.checkLoadOver(element);
        }
    }
});


