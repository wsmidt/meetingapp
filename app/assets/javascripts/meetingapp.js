"use strict";

var meetingApp = angular.module('meetingApp', [
    'meetingAppDirectives',
    'ui.router',
//    'ui.bootstrap', 
//    'ui.validate',
//    'ajoslin.promise-tracker',
    'ngRoute', 
    'ngResource', 
    'ngCookies', 
    'ngSanitize', 
    'ngAnimate'
//    'infinite-scroll'
]);


/** 
 * Application State definitions
 * */

var appState = {
  name: 'appState',
  views:{
    "appView" : {
      templateUrl: '/templates/meetingApp'
    }
  }
};

var initiateState = {
  name: 'initiateState',
  parent: appState,
  url: '^/initiate',
  views:{
    "contentView": {
      templateUrl: '/templates/initiate'
    }
  }
}

var responseState = {
  name: 'responseState',
  parent: appState,
  url: '^/response?selectedContactEmail',
  views: {
    "contentView": {
      templateUrl: '/templates/response'
    }
  }
}

var conflictState = { 
  name: 'conflictState',
  parent: appState,
  url: '^/conflict',
  views: {
    "contentView": {
      templateUrl: '/templates/conflict'
    }
  }
}

/*
var discussionDashboardState = {
  name: 'discussionDashboardState',
  parent: appState,
  url: '^/o/:orgName/b/:boardName/',
  views: {
    "contentView":{
      templateUrl: '/templates/discussions/discussion_dashboard'
    }
  }
};
*/

/** Configure Routing for the Application **/
meetingApp.config(function($stateProvider, $locationProvider, $urlRouterProvider, $routeProvider){
  $locationProvider.html5Mode(true);
  $urlRouterProvider.otherwise("/initiate"); 

  $stateProvider
    .state(appState)
    .state(initiateState)
    .state(responseState)
    .state(conflictState);
});


// vim: set ts=2 sw=2 et:"
