angular.module('meetingAppDirectives', [])
  .directive('ngcRandBorderColor', function(){
    return{
      link: function(scope, element, attrs){
        element.css('border-style','solid');
        element.css('border-width','2px');
        var randNum = Math.floor(Math.random() * 100) + 1;
        if(randNum <= 25)  //25% are red (you are already buisy durring this time)
          element.css('border-color','red');
        else if (randNum <= 25 + 15)  //15% are green (have already been selected)
          element.css('border-color','green');
        else //60% do nothing. (these are available times)
          element.css('border-color','black');
      }
    }
  });

