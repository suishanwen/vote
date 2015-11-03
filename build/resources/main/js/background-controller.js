var backgroundApp = angular.module('backgroundApp', ['ui.bootstrap', 'wiki.common']);
backgroundApp.controller("BackgroundController", ["$scope", "Path", "$http", "$timeout", function ($scope, Path, $http, $timeout, $location, voteService) {
    $scope.projectName = getQueryString(window.location.search, "projectName");
    $scope.historyBackground = "";
    function getQueryString(queryString, name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = queryString.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2])
        }
        return null;
    }

    var url = Path.getUri("api/background/");
    $http.post(url, $scope.projectName).success(function (data) {
        if (data) {
            $scope.BackgroundVo = data;
            if ($scope.BackgroundVo.backgroundNo > 101) {
                for (var i = 101; i < $scope.BackgroundVo.backgroundNo; i++) {
                    $scope.historyBackground = $scope.historyBackground + " <a href=\'background-history.html?projectName=" + $scope.projectName + "&backgroundNo=" + i + "\'>" + i + "</a>"
                }
            }
            $timeout(function(){
                $scope.search={workerId:""}
            },100)
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
                    record.onLine=false;
                }
                finishNum += record.succNum;
            }
        });
        $scope.BackgroundVo.participate = participate;
        $scope.BackgroundVo.activeParticipate = activeParticipate;
        $scope.BackgroundVo.finishNum = finishNum;
    }
}]);
backgroundApp.directive('historyBackground', function ($timeout) {
    return {
        restrict: 'E',
        template: '<span class=\"app-content\">历史后台:</span>',
        link: function (scope, element, attrs) {
            $timeout(function () {
                element.append(scope.historyBackground);
            }, 100);
        }
    }
});

