/**
 * Router Config
 * This is the router definition that defines all application routes.
 */
define(['angular', 'angular-ui-router'], function(angular) {
    'use strict';
    return angular.module('app.routes', ['ui.router']).config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function($stateProvider, $urlRouterProvider, $locationProvider) {

        //Turn on or off HTML5 mode which uses the # hash
        $locationProvider.html5Mode(true).hashPrefix('!');

        /**
         * Router paths
         * This is where the name of the route is matched to the controller and view template.
         */
        $stateProvider
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'views/dashboards.html',
                controller: 'DashboardsCtrl'
            })
             .state('aircraft', {
                url: '/aircraft',
                templateUrl: 'views/blank-page.html',
                controller: 'SampleCtrl'
            });
        $urlRouterProvider.otherwise('dashboard');
    }]);
});
