var voteApp=angular.module('voteApp',['ui.bootstrap','wiki.common','ngRoute']);
voteApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/admin-login', {
                templateUrl: 'admin-login.html',
                controller: 'AdminLoginController'
            }).
            when('/admin-manage', {
                templateUrl: 'admin-manage.html',
                controller: 'AdminManageController'
            }).
            when('/project-list', {
                templateUrl: 'project-list.html',
                controller: 'ProjectListController'
            }).otherwise({
                redirectTo: '/admin-login'
            });
    }]);

voteApp
    .controller('AdminLoginController', AdminLoginController)
    .controller('AdminManageController', AdminManageController)
    .controller('ProjectListController', ProjectListController);

voteApp.service( 'voteService', function() {
    var service = {
    };
    return service;
});