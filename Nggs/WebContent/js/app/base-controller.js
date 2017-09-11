(function(){
    'use strict';
    
    angular.module('emiApp')
        .controller('baseController', baseController)
        .factory('baseService', baseService);

    baseController.$inject = ['baseService'];
    function baseController(baseService) {
        var basevm = this;

        basevm.context = baseService;
        basevm.context.loadUserView();
    }

    baseService.$inject = ['$http', '$location', '$rootScope'];
    function baseService($http, $location, $rootScope) {
        var api = {
            pageClass: null,
            search: search,
            home: home,
            userPreferences: userPreferences,
            loadUserView: loadUserView
        }

        var userPreference = null;

        return api;

        function search() {
            $location.path('/search/home');
        }

        function home() {
            $location.path('/');
        }

        function userPreferences() {
            $location.path('/user');
        }

        function loadUserView() {
            return $http({
                method: 'GET',
                url: 'data/user-preference/core-user-role-preference.json'
            }).then(
                function(response) {
                    userPreference = response.data;
                    $rootScope.userPreference = userPreference;
                }
            );
        }
    }
})();