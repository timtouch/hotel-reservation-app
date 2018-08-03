let apiEndpoint = "api/v1/";

function getGuests(){
    let xhr = new XMLHttpRequest();
    let guests;

    console.log("Pressed the button!");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                guests = JSON.parse(xhr.responseText);
                console.log(guests);
            } else {
                console.log("Something went wrong!");
            }
        }
    }

    xhr.open("GET", apiEndpoint + "guests");

    xhr.send();
}


/**
 * Get the logged in user's reservation
 */
function getUsersReservations(callback){
    let xhr = new XMLHttpRequest();
    let parameters = `?guestId=${userSession.getLoggedInClient().userId}&hotelId=${hotel.hotelId}`;
    let reservations;


    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                try{
                    reservations = JSON.parse(xhr.responseText);
                    console.log(reservations);
                    if(typeof callback === 'function'){
                        callback(reservations);
                    }
                } catch (e) {
                    console.log(e);
                }
            } else {
                console.log("Something went wrong!");
            }
        }
    };

    xhr.open("GET", apiEndpoint + "reservations" + parameters);
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send();
}

function getAllReservations(callback){
    let xhr = new XMLHttpRequest();
    let parameters = `?hotelId=${hotel.hotelId}`;
    let reservations;


    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                try{
                    reservations = JSON.parse(xhr.responseText);
                    console.log(reservations);
                    if(typeof callback === 'function'){
                        callback(reservations);
                    }
                } catch (e) {
                    console.log(e);
                }
            } else {
                console.log("Something went wrong!");
            }
        }
    };

    xhr.open("GET", apiEndpoint + "reservations" + parameters);
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send();
}

function updateReservation(reservation){
    let xhr = new XMLHttpRequest();

    console.log("Update reservation: " + reservation);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                loadHostReservations();
            } else {
                console.log("Something went wrong!");
            }

        }
    };

    xhr.open("PUT", apiEndpoint + "reservations");
    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(reservation);

    xhr.send(json);
}

function getAllGuests(callback){
    let xhr = new XMLHttpRequest();
    let guests;


    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                try{
                    guests = JSON.parse(xhr.responseText);
                    console.log(guests);
                    if(typeof callback === 'function'){
                        callback(guests);
                    }
                } catch (e) {
                    console.log(e);
                }
            } else {
                console.log("Something went wrong!");
            }
        }
    };

    xhr.open("GET", apiEndpoint + "guests");
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send();
}
/**
 * Gets all issues
 * @param callback (issues)
 */
function getIssues(callback){
    let xhr = new XMLHttpRequest();
    let parameters = "";
    let issues;


    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                try{
                    issues = JSON.parse(xhr.responseText);
                    if (typeof callback === 'function'){
                        callback(issues);
                    }
                } catch (e) {
                    console.log(e);
                }
            } else {
                console.log("Something went wrong!");
            }
        }
    };

    xhr.open("GET", apiEndpoint + "issues" + parameters);
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send();
}

/**
 * Updates the issue to the database
 * @param iss Issue to update
 */
function updateIssue(issue){
    let xhr = new XMLHttpRequest();

    console.log(issue);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                loadHostIssues();
            } else {
                console.log("Something went wrong!");
            }

        }
    };
    xhr.open("PUT", apiEndpoint + "issues");
    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(issue);

    xhr.send(json);
}


function getLoginUser(){
    let xhr = new XMLHttpRequest();
    let user = {};

    user.role = "guest";
    user.username = $("#username").val();
    user.password = $("#password").val();

    console.log(user);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                try{
                    user = JSON.parse(xhr.responseText);
                    userSession.setLoggedInClient(user);
                    console.log(userSession.getLoggedInClient());
                    if (user.role === "guest"){
                        loadGuestNavBar();
                        loadGuestDashboard();
                    } else if (user.role === "host"){
                        loadHostNavBar();
                        loadHostDashboard();
                    }
                } catch (e) {
                    console.log(e);
                    document.getElementById("errorMessage").innerText = xhr.responseText;
                }
            } else {
                console.log("Something went wrong!");
            }
            $(".is-info").removeClass('is-loading');
        }
    };

    $("button.is-info").addClass('is-loading');
    xhr.open("POST", apiEndpoint + "login");
    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(user);

    xhr.send(json);
}

function registerNewUser(){
    let xhr = new XMLHttpRequest();
    let user = {};

    user.role = "guest";
    user.username = $("#username").val();
    user.password = $("#password").val();
    user.firstName = $("#firstName").val();
    user.lastName = $('#lastName').val();
    user.email = $("#email").val();

    console.log(user);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
               let message = xhr.responseText;
               if(message === ""){
                   loadHomePage(function(){
                       document.getElementById("successMessage").innerText = "Successfully registered";
                   });
               } else {
                   document.getElementById("errorMessage").innerText = message;
               }
            } else {
                console.log("Something went wrong!");
            }
            $(".is-info").removeClass('is-loading');
        }
    };
    $("button.is-info").addClass('is-loading');
    xhr.open("POST", apiEndpoint + "guests");
    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(user);

    xhr.send(json);
}

function getAllHotelRooms(callback){
    let xhr = new XMLHttpRequest();
    let parameters = `?hotelId=${hotel.hotelId}`;
    let hotelRooms;


    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                try{
                    hotelRooms = JSON.parse(xhr.responseText);
                    if (typeof callback === 'function'){
                        callback(hotelRooms);
                    }

                } catch (e) {
                    console.log(e);
                }
            } else {
                console.log("Something went wrong!");
            }
        }
    };

    xhr.open("GET", apiEndpoint + "hotelRooms" + parameters);

    xhr.send();
}
