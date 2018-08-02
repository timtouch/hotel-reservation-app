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
                console.log("I screwed up!");
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
                console.log("I screwed up!");
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
                console.log("I screwed up!");
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

                $('#username').addClass('title').text(userSession.getLoggedInClient().username);
                $('#fullName').addClass('subtitle').text(`${userSession.getLoggedInClient().firstName} ${userSession.getLoggedInClient().lastName}`);
                $('#email').addClass('subtitle').text(`${userSession.getLoggedInClient().email}`);

                 clearActiveNavBars();
                $("#profile").addClass("is-active");
            } else {
                console.log("I screwed up!");
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

                getIssues(function(reservations){
                    reservations.forEach(populateIssuesTableRow);
                });

                $('#user').text(userSession.getLoggedInClient().username);

                clearActiveNavBars();
                $("#dashboard").addClass("is-active");
            } else {
                console.log("I screwed up!");
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
                clearActiveNavBars();
            } else {
                console.log("I screwed up!");
            }
        }
    }

    xhr.open("GET", "guest/navbar.html");

    xhr.send();
}

function loadHomeNavBar(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            console.log("Ready!");
            if(xhr.status == 200){
                let htmlSection = xhr.responseText;
                document.getElementById("navbar").innerHTML = htmlSection;
                clearActiveNavBars();
            } else {
                console.log("I screwed up!");
            }
        }
    }

    xhr.open("GET", "navbar.html");

    xhr.send();
}

/**
 * Populates a reservation table row
 * @param reservation
 */
function populateReservationTableRow(reservation){
    $('#reservations').append(
        `<tr>
            <td>${reservation.hotelRoomId}</td>
            <td>${reservation.numOfGuests}</td>
            <td>${reservation.startDate}</td>
            <td>${reservation.endDate}</td>
            <td>${reservation.currentStatus}</td>
        </tr>`
    );
}

/**
 * Populates an issues table row
 * @param issue
 */
function populateIssuesTableRow(issue){
    $('#issues').append(
        `<tr>
            <td>${issue.createdOn}</td>
            <td>${issue.message}</td>
            <td>${issue.resolverId !== 0 ? issue.resolverId : ""}</td>
            <td>${issue.resolvedOn ? issue.resolvedOn : ""}</td>
            <td>${issue.isResolved ? "RESOLVED" : "UNRESOLVED"}</td>
        </tr>`
    );
}