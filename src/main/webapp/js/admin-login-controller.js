var AdminLoginController=function($scope,Path,$http,$timeout,$location) {
    $scope.checkbox=localStorage.admin?true:false;
    $scope.admin={
        admin:$scope.checkbox?localStorage.admin:"",
        password:$scope.checkbox?localStorage.password:""
    };
    $scope.loginModal=true;
    $scope.adminLogin=function(){
        if($scope.admin.admin.trim()!=""&&$scope.admin.password.trim()!=""){
            var url = Path.getUri("api/admin/login");
            $http.post(url,$scope.admin).success(function (data) {
                if(data){
                    sessionStorage.loginUser=data.admin;
                    sessionStorage.loginUserState=data.state;
                    if($scope.checkbox){
                        localStorage.admin=data.admin;
                        localStorage.password=data.password;
                    }else{
                        localStorage.clear()
                    }
                    $location.path('/project-list');
                }else{
                    alert("用户名或密码不存在！")
                }
            }).error(function (data) {
                alert("登陆服务器异常！");
            });
        }else{
            alert("用户名或密码不能为空！")
        }
    };
    $scope.modalOpt = {
        dialogClass: "modal",
        backdropFade: true,
        dialogFade: true,
        keyboard: false,
        backdrop: true,
        backdropClick: false
    };
};

