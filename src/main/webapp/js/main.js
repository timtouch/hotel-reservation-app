$(document).ready(function() {

    loadHomeNavBar();
    loadHomePage();




});

function addNavBarBurgerToggle(){
    // Check for click events on the navbar burger icon
    $(".navbar-burger").click(function() {

        // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
        $(".navbar-burger").toggleClass("is-active");
        $(".navbar-menu").toggleClass("is-active");

    });
}

let hotel = {
    hotelId: 1,
    name: "Hotel California"
};

function clearActiveNavBars(){
    $(".navbar-item").removeClass("is-active");
}


/**
 * Gives each <option> an onclick event that adds a class to it
 */
function addOptionEventHandler(){
    $('option').click(function(){
        $('option').removeClass("selected");
        $(this).addClass("selected");
    });
}

