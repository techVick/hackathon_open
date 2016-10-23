/*global define */
define(['angular', './sample-module'], function (angular, module) {
    'use strict';
    /**
    * PredixViewService is a sample angular service that integrates with Predix View Service API
    */
    module.factory('PredixViewService', ['$http', '$q', function ($http, $q) {
        return {
            baseUrl: '/api/onwing',
            getIssueList: function () {
                var deferred = $q.defer();
                $http.get(this.baseUrl + '/issues')
                    .then(function (res) {
                        deferred.resolve(res.data);
                    },
                    function (error) {
                        deferred.reject(error);
                    });
                return deferred.promise;
            },
            getIssueDetail: function (id, etops) {
                var deferred = $q.defer();
                 $http.get(this.baseUrl + '/issueanalysis/' + id + "?etops=" + etops)
                     .then(function (res) {
                         deferred.resolve(res.data);
                     },
                     function (error) {
                         deferred.reject(error);
                     });
                return deferred.promise;
            },
            getSummary: function (tags) {
                var deferred = $q.defer();
                 $http.get(this.baseUrl + '/issuesummary')
                     .then(function (res) {
                         deferred.resolve(res.data);
                     },
                     function () {
                         deferred.reject('Error fetching decks with tags ' + tags);
                     });
                return deferred.promise;
            }
        };
    }]);
});
