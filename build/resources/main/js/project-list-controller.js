var ProjectListController=function($scope,Path,$http,$timeout,$location,voteService) {

    var getProjectList=function(){
        var url = Path.getUri("api/project/");
        $http.post(url,sessionStorage.loginUser).success(function (data) {
            $scope.projectList=angular.copy(data);
        }).error(function (data) {
            alert("获取项目列表失败！");
        });
    };
    var initFun = function () {
        $scope.addModal = false;
        $scope.editModal=false;
        $scope.selectedProject = null;
        getProjectList();
    };

    if(sessionStorage.loginUser&&sessionStorage.loginUserState>0){
        $scope.loginUserState=sessionStorage.loginUserState;
        initFun();
    }else{
        $location.path('/admin-login');
    };
    $scope.initNewProject = function () {
        $scope.newProject={
            projectName:"",
            admin:"",
            state:1
        };
    };
    $scope.addNewProject = function () {
        $scope.initNewProject();
        $scope.addModal = true;
        $timeout(function(){
            $("#addProjectName").focus();
        },500);
    };
    $scope.editProjectFun = function () {
        if ($scope.selectedProject!= null ) {
            $scope.editModal=true;
            $timeout(function(){
                $("#addProjectName").focus();
            },500);
        }else{
            alert("请选择项目！");
        }
    };

    var setButton=function(a,b,c,d,e){
        $scope.showConfirm=a;
        $scope.showCancel=b;
        $scope.showEdit=c;
        $scope.showMove=d;
        $scope.flag=e;
    };
    var setInput=function(a,b,c){
        var obj = $("#totalRequirement");
        obj.prop("readOnly", a);
        obj=$("#limitWorker");
        obj.prop("readOnly", b);
        obj=$("#notice");
        obj.prop("readOnly", c);
    };
    $scope.selectedProjectFun=function(project){
        $scope.selectedProject={
            projectId:project.projectId,
            projectName:project.projectName,
            admin:project.admin,
            state:project.state
        };
        var url = Path.getUri("api/background/get");
        $http.post(url, project.projectName).success(function (data) {
            if(data){
                $scope.selectedProjectInfoVo=data;
                $scope.backgroundUrl=Path.getUri("app/background.html?projectName="+data.projectName);
                setButton(false,false,true,true,0);
            }else{
                $scope.selectedProjectInfoVo={
                    projectName:project.projectName,
                    backgroundId:null,
                    backgroundNo:null,
                    totalRequirement:null,
                    finishNum:null,
                    limitWorker:null,
                    participate:null,
                    activeParticipate:null,
                    notice:null
                };
                setButton(false,false,false,true,0);
            }
        }).error(function () {
            alert("查询后台信息失败！");
        });
    };


    $scope.editSelectedProjectInfoVo=function(){
        setInput(false,false,false);
        setButton(true,true,false,false,1);
    };
    $scope.moveBackground=function(){
        setInput(false,false,false);
        $scope.selectedProjectInfoVoBackUp=$scope.selectedProjectInfoVo;
        if($scope.selectedProjectInfoVo.backgroundNo){
            $scope.selectedProjectInfoVo={
                projectName:$scope.selectedProject.projectName,
                backgroundId:$scope.selectedProjectInfoVo.backgroundId,
                backgroundNo:$scope.selectedProjectInfoVo.backgroundNo+1,
                totalRequirement:null,
                finishNum:null,
                limitWorker:null,
                participate:null,
                activeParticipate:null,
                notice:null
            };
        }else{
            $scope.selectedProjectInfoVo={
                projectName:$scope.selectedProject.projectName,
                backgroundId:null,
                backgroundNo:101,
                totalRequirement:null,
                finishNum:null,
                limitWorker:null,
                participate:null,
                activeParticipate:null,
                notice:null
            };
        }
        setButton(true,true,false,false,2);
    };
    $scope.cancel=function(){
        if($scope.flag==2){
            $scope.selectedProjectInfoVo=$scope.selectedProjectInfoVoBackUp;
        }
        setInput(true,true,true);
        if($scope.selectedProjectInfoVo.backgroundNo!=null){
            setButton(false,false,true,true,0);
        }else{
            setButton(false,false,false,true,0);
        }
    };

    $scope.confirmEdit=function(){
        var url;
        if($scope.flag==1){
             url = Path.getUri("api/background/update");
        }else if($scope.flag==2){
             url = Path.getUri("api/background/new");
        }
        $http.post(url, $scope.selectedProjectInfoVo).success(function (data) {
            if(data){
                $scope.selectedProjectInfoVo.backgroundId=data;
                setInput(true,true,true);
                setButton(false,false,true,true,0);
            }else{
                alert("后台异常！");
            }
        }).error(function () {
            alert("后台操作失败！");
        });
    };

    $scope.addProject = function () {
        if ($scope.newProject.projectName.trim() != "" ) {
            var url = Path.getUri("api/project/add");
            $http.post(url, $scope.newProject).success(function () {
                $scope.addModal = false;
                getProjectList();
            }).error(function () {
                alert("添加失败！");
            });

        } else {
            alert("项目名称不能为空！");
        }
    };
    $scope.editProject = function () {
        var url = Path.getUri("api/project/update");
        $http.post(url, $scope.selectedProject).success(function () {
            $scope.editModal = false;
            getProjectList();
        }).error(function () {
            alert("更新失败！");
        });
    };
    $scope.delProject = function () {
        if ($scope.selectedProject!= null ) {
            var result=confirm("要删除["+$scope.selectedProject.projectName+"]吗?");
            if(!result){
                return;
            }
            var url = Path.getUri("api/project/delete");
            $http.post(url, $scope.selectedProject.projectId).success(function () {
                $scope.selectedProject=null;
                getProjectList();
            }).error(function () {
                alert("删除失败！");
            });
        } else {
            alert("请选择项目！");
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

