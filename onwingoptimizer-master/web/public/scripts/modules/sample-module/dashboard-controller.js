
define(['angular', './sample-module'], function (angular, controllers) {
        'use strict';
        // Controller definition
        controllers.controller('DashboardsCtrl', ['$scope', '$http', '$window', 'PredixViewService', function ($scope, $http, $window, PredixViewService) {

                $scope.sbTypes = { hardDate: 'SB-ONETIME-HARDDATE', cycle: 'SB-ONETIME-CYCLE' };
                $scope.summaryData = [];

                $scope.issueList = [];
                $scope.selectedIssue = null;

                $scope.issueDefaultConfig = {};
                $scope.issueData = [];
                $scope.issueEventData = [];

                $scope.hardDateData = [];
                $scope.alertMessage = ""
                $scope.overDueAlerts = [];
                $scope.potcompAlerts = [];
                $scope.islogedIn = false;
                $scope.isETOPS = false;

                $http({
                        method: 'GET',
                        url: '/userinfo'
                }).then(function successCallback(response) {
                        // this callback will be called asynchronously
                        // when the response is available
                        $scope.islogedIn = true;
                        initChart();
                }, function errorCallback(response) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.

                        $window.location = '/login';
                });

               // initChart();

                var loadSummaryChart = function (issueList, seriesData) {
                        var summaryChart = new Highcharts.Chart({
                                chart: {
                                        renderTo: 'div-chart-summary',
                                        type: 'column'
                                },
                                title: {
                                        text: ''
                                },
                                xAxis: {
                                        categories: issueList
                                },
                                yAxis: {
                                        min: 0,
                                        title: {
                                                text: 'Engines'
                                        },
                                        stackLabels: {
                                                enabled: true,
                                                style: {
                                                        fontWeight: 'bold',
                                                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                                                }
                                        }
                                },
                                legend: {
                                        align: 'right',
                                        x: -30,
                                        verticalAlign: 'top',
                                        y: 25,
                                        floating: true,
                                        backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                                        borderColor: '#CCC',
                                        borderWidth: 1,
                                        shadow: false
                                },
                                tooltip: {
                                        headerFormat: '<b>{point.x}</b><br/>',
                                        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
                                },
                                plotOptions: {
                                        column: {
                                                stacking: 'normal',
                                                dataLabels: {
                                                        enabled: true,
                                                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                                                        style: {
                                                                textShadow: '0 0 3px black'
                                                        }
                                                }
                                        }
                                },
                                series: seriesData
                        });
                };

                var loadIssueDetailChart = function (title, events, seriesItems, minDate, maxDate) {
                        var issueDetail = new Highcharts.Chart({
                                chart: {
                                        renderTo: 'div-chart-issue-detail',
                                        type: 'spline'
                                },
                                title: {
                                        text: ''
                                },
                                subtitle: {
                                        text: ''
                                },
                                xAxis: {
                                        type: 'datetime',
                                        dateTimeLabelFormats: {
                                                year: '%Y'
                                        },
                                        title: {
                                                text: 'Date'
                                        },
                                        plotLines: events,
                                        min: Date.UTC(minDate.getFullYear(), minDate.getMonth(), minDate.getDay()),
                                        max: Date.UTC(maxDate.getFullYear(), maxDate.getMonth(), maxDate.getDay()),

                                },
                                yAxis: {
                                        title: {
                                                text: 'Engines'
                                        },
                                        min: 0
                                },
                                tooltip: {
                                        headerFormat: '<b>{series.name}</b><br>',
                                        pointFormat: 'ESN: {point.name} - {point.x:%e %b %Y}'
                                },
                                plotOptions: {
                                        spline: {
                                                marker: {
                                                        enabled: true
                                                }
                                        }
                                },
                                series: seriesItems
                        });
                }

                var loadPendingDetailChart = function (events, seriesItems, minDate, maxDate) {
                        var issueDetail = new Highcharts.Chart({
                                chart: {
                                        renderTo: 'div-chart-issue-pending',
                                        type: 'spline'
                                },
                                title: {
                                        text: ''
                                },
                                subtitle: {
                                        text: ''
                                },
                                xAxis: {
                                        type: 'datetime',
                                        dateTimeLabelFormats: {
                                                month: '%e %b %Y',
                                                year: '%y'
                                        },
                                        title: {
                                                text: 'Date'
                                        },
                                        plotLines: events,
                                        min: Date.UTC(minDate.getFullYear(), minDate.getMonth(), minDate.getDay()),
                                        max: Date.UTC(maxDate.getFullYear(), maxDate.getMonth(), maxDate.getDay()),
                                },
                                yAxis: {
                                        title: {
                                                text: 'Engines'
                                        },
                                        min: 0
                                },
                                tooltip: {
                                        pointFormat: 'ESN: {point.serial} - {point.x:%e %b %Y}'
                                },
                                plotOptions: {
                                        spline: {
                                                marker: {
                                                        enabled: true
                                                }
                                        }
                                },
                                series: seriesItems
                        });
                }

                function initChart() {
                        PredixViewService.getIssueList().then(function (data) {
                                $scope.issueList = data;
                                $scope.selectedIssue = $scope.issueList[0];
                                loadIssues($scope.selectedIssue.id, $scope.isETOPS);
                                 $scope.islogedIn = true;
                        }, function (error) {
                                alert(error);
                        });

                        PredixViewService.getSummary().then(function (summary) {
                                var xAxisIssueNames = [];
                                var completed = { name: 'Complied', data: [], 'color': 'green' };
                                var pending = { name: 'Pending', data: [], 'color': 'red' };

                                for (var i = 0; i < summary.length; i++) {
                                        xAxisIssueNames.push(summary[i].name);
                                        completed.data.push(summary[i].completed);
                                        pending.data.push(summary[i].scheduled);
                                }
                                loadSummaryChart(xAxisIssueNames, [completed, pending]);

                        }, function (error) {
                                alert(error);
                        });
                }

                var loadIssues = function (id, etops) {
                        PredixViewService.getIssueDetail(id,etops).then(function (problem) {
                                setIssueDetailData(problem);
                        }, function (error) {
                                alert(error);
                        });
                }

                function setIssueDetailData(problem) {

                        var completedSeries = { name: 'Complied', data: [], color: "green" };
                        var pendingSeries = { name: 'Pending', data: [], color: "red" };

                        var onWingScheduled = { name: 'On wing scheduled', data: [], color: "red", type: 'scatter' };
                        var onWingScheduledSeries = [];

                        $scope.overDueAlerts = [];
                        $scope.potcompAlerts = [];
                        if (problem.defaulters && problem.defaulters.length) {
                                for (var i = 0; i < problem.defaulters.length; i++) {
                                        $scope.overDueAlerts.push("ESN: " + problem.defaulters[i] + " is overdue")
                                }
                        }
                        if (problem.potentialDefaulters && problem.potentialDefaulters.length) {
                                for (var i = 0; i < problem.potentialDefaulters.length; i++) {
                                        $scope.potcompAlerts.push("ESN: " + problem.potentialDefaulters[i] + " scheduled beyond due date");
                                }
                        }

                        if (problem.benefitDuration) {
                                $scope.alertMessage = "Burn down opportunities window - " + problem.benefitDuration + " days";
                        }

                        for (var i = 0; i < problem.completed.length; i++) {
                                var item = problem.completed[i];
                                completedSeries.data.push({ x: new Date(item.compliedOn), y: item.countNo, name: item.serial, marker: { fillColor: 'green', color: "red", lineColor: "green" } });
                        }

                        for (var i = 0; i < problem.pending.length; i++) {
                                var item = problem.pending[i];
                                if (!item.onWing) {
                                        pendingSeries.data.push({ x: new Date(item.dueOn), y: item.countNo, name: item.serial, marker: { fillColor: 'blue', radius: 6 } });
                                } else {
                                        pendingSeries.data.push({ x: new Date(item.dueOn), y: item.countNo, name: item.serial, marker: { fillColor: 'red', lineColor: "#fff" } });
                                        if ($scope.selectedIssue.sbType == $scope.sbTypes.hardDate) {
                                                var obj = { serial: item.serial, name: 'Proposed Slots', data: [], color: "blue" };

                                                for (var j = 0; j < item.opportunities.length; j++) {
                                                        obj.data.push({ x: new Date(item.opportunities[j].plannedStartDate), y: item.countNo, serial: item.serial, marker: { fillColor: 'blue', radius: 6, symbol: 'triangle-down' } });
                                                }
                                                if (obj.data.length) {
                                                        if (onWingScheduledSeries.length) {
                                                                obj.linkedTo = ':previous';
                                                        }
                                                        onWingScheduledSeries.push(obj);
                                                }
                                                onWingScheduled.data.push({ x: new Date(item.dueOn), y: item.countNo, serial: item.serial, marker: { fillColor: 'red', radius: 6, symbol: 'circle', lineColor: "#fff" } });
                                        }
                                }

                        }

                        var detailChartEvents = [{
                                color: 'blue',
                                dashStyle: 'line',
                                value: new Date(problem.hardDate),
                                width: 2,
                                label: { text: 'SB Issue date' }
                        }, {
                                        color: 'yellow',
                                        dashStyle: 'line',
                                        value: new Date(problem.adDate),
                                        width: 2,
                                        label: { text: 'Airline Document Date' }
                                }, {
                                        color: 'green',
                                        dashStyle: 'line',
                                        value: new Date(problem.currentDate),
                                        width: 2,
                                        label: { text: 'Today' }
                                }, {
                                        color: 'red',
                                        dashStyle: 'line',
                                        value: new Date(problem.deadLineDate),
                                        width: 2,
                                        label: { text: 'OEM Dead Line' }
                                },]

                        var pendingDetailChartEvents = [{
                                color: 'red',
                                dashStyle: 'line',
                                value: new Date(problem.deadLineDate),
                                width: 2,
                                label: { text: 'OEM Dead Line' }
                        }, {
                                        color: 'green',
                                        dashStyle: 'line',
                                        value: new Date(problem.currentDate),
                                        width: 2,

                                        label: { text: 'Today' }
                                }]

                        loadIssueDetailChart(problem.name, detailChartEvents, [completedSeries, pendingSeries], new Date(problem.issueStartDate), new Date(problem.issueEndDate));
                        onWingScheduledSeries.push(onWingScheduled);
                        loadPendingDetailChart(pendingDetailChartEvents, onWingScheduledSeries, new Date(problem.issueStartDate), new Date(problem.issueEndDate));
                }

                $scope.onIssueSelected = function () {
                        $scope.isETOPS = false;
                        loadIssues($scope.selectedIssue.id, $scope.isETOPS);
                }

                $scope.onEtopChange = function(){
                         loadIssues($scope.selectedIssue.id, $scope.isETOPS);
                }

        }]);
});
