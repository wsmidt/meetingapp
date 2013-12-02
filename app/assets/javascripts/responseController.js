angular.module("meetingApp").controller("ResponseController", function ResponseController($scope, $state, $stateParams, $timeout, $log){

  var NUM_DAYS_FOR_DEMO = 100;
  var dayArray = [ "Sunday", "Monday", "Tuesday", "Wendsday", "Thursday", "Friday", "Saturday" ]
  $scope.contactArray = [{name: "Warner Smidt", email: "scarab9151@gmail.com"}, 
                         {name: "Peter Gavares", email: "ggavares@gmail.com"}]

  if($stateParams.selectedContactEmail != undefined){
    for(var i = 0; i < $scope.contactArray.length; i++){
      if($scope.contactArray[i].email == $stateParams.selectedContactEmail){
        $scope.selectedContact = $scope.contactArray[i];
        break;
      }
    }
  } else {
    $scope.selectedContact = $scope.contactArray[0];
  }

  var init = function() {
    $scope.days = [];
    var day = new Date();

    for(var i = NUM_DAYS_FOR_DEMO - 1; i >= 0; i--){
      $scope.days[i] = {};
      $scope.days[i].date = day.getDate();
      $scope.days[i].day = getDayString(day.getDay());
      day.setTime(day.getTime() + (24 * 60 * 60 * 10000));
    }

    $scope.isThinking = false;

    $log.debug("InitiateController initialized");
  }


  var getDayString = function(dayNum){
    if(dayNum < 0 || dayNum > 6)
      return "Unknown";
    return dayArray[dayNum];
  }

  //functions...

  $scope.selectNext = function(){
    $scope.isThinking = true;
    $timeout(function(){
      $scope.isThinking = false;
      $state.transitionTo('responseState');
    }, 2000);
  }



  init();
});
