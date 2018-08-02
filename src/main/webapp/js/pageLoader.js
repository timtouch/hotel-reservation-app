function loadHomePage(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            console.log("Ready!");
            if(xhr.status == 200){
                let htmlSection = xhr.responseText;
                document.getElementById("main").innerHTML = htmlSection;
                clearActiveNavBars();
                $("#home").addClass("is-active");
            } else {
                console.log("I screwed up!");
            }
        }
    }

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
    }

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
    }

    xhr.open("GET", "authentication/registration.html");

    xhr.send();

}



