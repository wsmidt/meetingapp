angular.module("meetingApp").controller("AppController", function AppController($scope, $state, $stateParams, $log){
  $scope.$on('$stateChangeStart', function(event, toState, fromState, fromParams){
    $log.debug("State change start: " + fromState.name + " to " + toState.name);
  });

  $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
    $log.debug("State change success: " + fromState.name + " to " + toState.name);
  });

  $scope.$on('$stateChangeError', function(event, toState, toParams, fromState, fromParams){
    $log.debug('State change ERROR: ' + fromState.name + "to" + toState.name);
  });

  $log.debug("AppController initialized");

});
