let apiEndpoint = "api/v1/";

function sendRequest(){
    let xhr = new XMLHttpRequest();
    let guest = {

    };

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            if(xhr.status == 200){
                console.log(xhr.response);
            }
        }
    }
    xhr.open("POST", "login");
    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(guest);

    xhr.send(json);
}

function getGuests(){
    let xhr = new XMLHttpRequest();
    let guests;

    console.log("Pressed the button!");
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            if(xhr.status == 200){
                guests = JSON.parse(xhr.responseText);
                console.log(guests);
            } else {
                console.log("I screwed up!");
            }
        }
    }

    xhr.open("GET", apiEndpoint + "guests");

    xhr.send();
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
                    loadGuestNavBar();
                    loadGuestProfile();
                } catch (e) {
                    console.log(e);
                    document.getElementById("errorMessage").innerText = xhr.responseText;
                }
            } else {
                console.log("I screwed up!");
            }
        }
    };

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
                console.log("I screwed up!");
            }
        }
    };

    xhr.open("POST", apiEndpoint + "guests");
    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(user);

    xhr.send(json);
}

// /* attach a submit handler to the form */
// $("#loginForm").submit(function (event){
//
//     /* stop form from submitting normally */
//    event.preventDefault();
//
//     /* get the action attribute from the <form action=""> element */
//    var $form = $(this),
//        url = $form.attr('action');
//
//     /* Send the data using post with element id name and name2*/
//     var posting = $.post(url, { username: $('#username').val(), password: $('#password').val() } );
//
//     /* Alerts the results */
//     posting.done(function (data){
//         alert('success');
//     });
// });