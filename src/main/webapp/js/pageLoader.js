function loadHomeNavBar(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            console.log("Ready!");
            if(xhr.status == 200){
                let htmlSection = xhr.responseText;
                document.getElementById("navbar").innerHTML = htmlSection;
                addNavBarBurgerToggle();
                clearActiveNavBars();
            } else {
                console.log("Something went wrong");
            }
        }
    }

    xhr.open("GET", "navbar.html");

    xhr.send();
}

function loadHomePage(callback){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            console.log("Ready!");
            if(xhr.status == 200){
                let htmlSection = xhr.responseText;
                document.getElementById("main").innerHTML = htmlSection;
                clearActiveNavBars();
                $("#home").addClass("is-active");
                if( typeof callback === 'function'){
                    callback();
                }
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "home.html");

    xhr.send();
}

function loadLoginPage(){

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            console.log("Ready!");
            if(xhr.status == 200){
                let htmlSection = xhr.responseText;
                document.getElementById("main").innerHTML = htmlSection;
                clearActiveNavBars();
                $("#login").addClass("is-active");

            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "authentication/login.html");

    xhr.send();
}

function loadRegisterPage(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            console.log("Ready!");
            if(xhr.status == 200){
                let htmlSection = xhr.responseText;
                document.getElementById("main").innerHTML = htmlSection;
                clearActiveNavBars();
                $("#register").addClass("is-active");
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "authentication/registration.html");

    xhr.send();

}

function loadGuestProfile(){

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            console.log("Ready!");
            if(xhr.status === 200){
                let htmlSection = xhr.responseText;
                document.getElementById("main").innerHTML = htmlSection;

                $('#username').text(userSession.getLoggedInClient().username);
                $('#fullName').text(`${userSession.getLoggedInClient().firstName} ${userSession.getLoggedInClient().lastName}`);
                $('#email').text(`${userSession.getLoggedInClient().email}`);
                $('#role').text(`${userSession.getLoggedInClient().role}`);
                 clearActiveNavBars();
                $("#profile").addClass("is-active");
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "guest/profile.html");

    xhr.send();
}

function loadGuestDashboard(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            console.log("Ready!");
            if(xhr.status === 200){
                document.getElementById("main").innerHTML = xhr.responseText;

                getUsersReservations(function(reservations){
                    reservations.forEach(populateReservationTableRow);
                });

                getIssues(function(issues){
                    issues.filter(issue => issue.createdById === userSession.getLoggedInClient().userId)
                        .forEach(populateIssuesTableRow);
                });

                getAllHotelRooms(function(hotelRooms){
                    hotelRooms.forEach(populateSelectHotelRoom);
                    addOptionEventHandler();
                });


                $('#user').text(userSession.getLoggedInClient().username);
                clearActiveNavBars();
                $("#dashboard").addClass("is-active");
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "guest/dashboard.html");

    xhr.send();
}

function loadGuestNavBar(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            console.log("Ready!");
            if(xhr.status == 200){
                let htmlSection = xhr.responseText;
                document.getElementById("navbar").innerHTML = htmlSection;
                addNavBarBurgerToggle();
                clearActiveNavBars();
            } else {
                console.log("Something went wrong");
            }
        }
    }

    xhr.open("GET", "guest/navbar.html");

    xhr.send();
}


function loadHostNavBar(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            console.log("Ready!");
            if(xhr.status == 200){
                let htmlSection = xhr.responseText;
                document.getElementById("navbar").innerHTML = htmlSection;
                addNavBarBurgerToggle();
                clearActiveNavBars();
            } else {
                console.log("Something went wrong");
            }
        }
    }

    xhr.open("GET", "host/navbar.html");

    xhr.send();
}

function loadHostDashboard(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            console.log("Ready!");
            if(xhr.status === 200){
                document.getElementById("main").innerHTML = xhr.responseText;

                getAllReservations(function(reservations){
                    reservations.filter(reservation => reservation.currentStatus === "PENDING")
                        .forEach(populateReservationTableRow);
                });

                getIssues(function(issues){
                    issues.forEach(populateIssuesTableRow);
                });


                $('#user').text(userSession.getLoggedInClient().username);
                clearActiveNavBars();
                $("#dashboardLink").addClass("is-active");
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "host/dashboard.html");

    xhr.send();
}

function loadHostReservations(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            console.log("Ready!");
            if(xhr.status === 200){
                document.getElementById("main").innerHTML = xhr.responseText;

                getAllReservations(function(reservations){
                    reservations.forEach(populateHostReservationTableRow);
                });

                clearActiveNavBars();
                $("#reservationsLink").addClass("is-active");
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "host/reservations.html");

    xhr.send();
}

function loadHostIssues(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            console.log("Ready!");
            if(xhr.status === 200){
                document.getElementById("main").innerHTML = xhr.responseText;

                getIssues(function(issues){
                    issues.forEach(populateHostIssuesTableRow);
                });

                clearActiveNavBars();
                $("#issuesLink").addClass("is-active");
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "host/issues.html");

    xhr.send();
}

function loadHostHotelRooms(){
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            console.log("Ready!");
            if(xhr.status === 200){
                document.getElementById("main").innerHTML = xhr.responseText;

                getAllHotelRooms(function(hotelRooms){
                    hotelRooms.forEach(populateHotelRoomTableRow);
                });

                clearActiveNavBars();
                $("#hotelRoomsLink").addClass("is-active");
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "host/hotelRooms.html");

    xhr.send();

}

function loadGuestList(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4) {
            console.log("Ready!");
            if(xhr.status === 200){
                document.getElementById("main").innerHTML = xhr.responseText;

                getAllGuests(function(guests){
                    guests.forEach(populateGuestsTableRow);
                });

                clearActiveNavBars();
                $("#guestListLink").addClass("is-active");
            } else {
                console.log("Something went wrong");
            }
        }
    };

    xhr.open("GET", "host/guests.html");

    xhr.send();
}