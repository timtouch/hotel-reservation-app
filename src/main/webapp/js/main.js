$(document).ready(function() {

    loadHomeNavBar();
    loadHomePage();


    // Check for click events on the navbar burger icon
    $(".navbar-burger").click(function() {

        // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
        $(".navbar-burger").toggleClass("is-active");
        $(".navbar-menu").toggleClass("is-active");

    });

});

let hotel = {
    hotelId: 1,
    name: "Hotel California"
};

function clearActiveNavBars(){
    $(".navbar-item").removeClass("is-active");
}

/**
 *
 */
function openModal(){
    $(".modal").addClass("is-active");
    $(".modal-close").click(function(){
       $(".modal").removeClass("is-active");
    });
}