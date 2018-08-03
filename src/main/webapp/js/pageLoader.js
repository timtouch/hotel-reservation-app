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

