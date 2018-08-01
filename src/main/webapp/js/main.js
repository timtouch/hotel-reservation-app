function sendRequest(){
    let xhr = new XMLHttpRequest();
    let guest = {

    };

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            if(xhr.status = 200){
                console.log(xhr.response);
            }
        }
    }
    xhr.open("POST", "api/login");
    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(guest);

    xhr.send(json);
}