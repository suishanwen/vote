var AdminManageController=function($scope,Path,$http,$timeout,$location,voteService) {
    if(!sessionStorage.loginUser||sessionStorage.loginUserState=="1"){
        $location.path('/admin-login');
    };
    var getAdminList=function(){
        var url = Path.getUri("api/admin");
        $http.get(url).success(function (data) {
            $scope.adminList=angular.copy(data);
        }).error(function (data) {
            alert("获取管理员列表失败！");
        });
    };
    var initFun = function () {
        $scope.addModal = false;
        $scope.editModal=false;
        $scope.selectedAdmin = null;
        getAdminList();
    };
    initFun();

    $scope.initNewAdmin = function () {
        $scope.newAdmin={
            admin:"",
            password:""
        };
    };
    $scope.addNewAdmin = function () {
        $scope.initNewAdmin();
        $scope.addModal = true;
        $timeout(function(){
            $("#addAdmin").focus();
        },500);
    };
    $scope.editAdminFun = function () {
        if ($scope.selectedAdmin!= null ) {
            $scope.editModal=true;
            $timeout(function(){
                $("#editAdmin").focus();
            },500);
        }else{
            alert("请选择管理员！");
        }
    };
    $scope.selectedAdminFun=function(admin){
        $scope.selectedAdmin={
            adminId:admin.adminId,
            admin:admin.admin,
            password:admin.password
        };
    };

    $scope.addAdmin = function () {
        if ($scope.newAdmin.admin.trim() != ""&& $scope.newAdmin.password.trim() != "") {
            var url = Path.getUri("api/admin/add");
            $http.post(url, $scope.newAdmin).success(function () {
                $scope.addModal = false;
                getAdminList();
            }).error(function () {
                alert("添加失败！");
            });
        } else {
            alert("用户名及密码不能为空！");
        }
    };
    $scope.editAdmin = function () {
        var url = Path.getUri("api/admin/update");
        $http.post(url, $scope.selectedAdmin).success(function () {
            $scope.editModal = false;
            getAdminList();
        }).error(function () {
            alert("更新失败！");
        });
    };
    $scope.delAdmin = function () {
        if ($scope.selectedAdmin!= null ) {
            var result=confirm("要删除["+$scope.selectedAdmin.admin+"]吗?");
            if(!result){
                return;
            }
            var url = Path.getUri("api/admin/delete");
            $http.post(url, $scope.selectedAdmin.adminId).success(function () {
                $scope.selectedAdmin=null;
                getAdminList();
            }).error(function () {
                alert("删除失败！");
            });
        } else {
            alert("请选择管理员！");
        }
    };

    $scope.modalOpt = {
        dialogClass: "modal",
        backdropFade: true,
        dialogFade: true,
        keyboard: true,
        backdrop: true,
        backdropClick: false
    };

};

