$(document).ready(function(){
  var contRecipes = 0;
  var contRestaurants = 0;
  var contEvents = 0;
  
  $("#moreRecipes").on("click", function() {
    contRecipes++;
    $.get("/moreRecipes", {
        page: contRecipes
      })
      .done(function(data) {
        if (!$.trim(data)) {
          $("#moreRecipes").attr("disabled", "disabled");
        } else {
          $("#recipes .listItems").append(data);
        }
      });
  });
  
  $("#moreRestaurants").on("click", function() {
    contRestaurants++;
    $.get("/moreRestaurants", {
        page: contRestaurants
      })
      .done(function(data) {
        if (!$.trim(data)) {
          $("#moreRestaurants").attr("disabled", "disabled");
        } else {
          $("#restaurants .listItems").append(data);
        }
      });
  });
  
  /*$("#moreEvents").on("click", function() {
    contEvents++;
    $.get("/moreEvents", {
        page: contEvents
      })
      .done(function(data) {
        if (!$.trim(data)) {
          $("#moreEvents").attr("disabled", "disabled");
        } else {
          $("#events .listItems").append(data);
        }
      });
  }); */
});
