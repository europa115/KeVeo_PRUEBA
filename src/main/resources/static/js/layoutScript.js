if (window.matchMedia("(min-width: 400px)").matches) {
  $('.btn-expand-collapse').click(function(e) {
    				$('.navbar-primary').toggleClass('collapsed');
    });
} else {


  $('.navbar-primary').toggleClass('collapsed');
}