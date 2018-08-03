function submitReservation(){
    let reservation = {};


    reservation.userId = userSession.getLoggedInClient().userId;
    console.log("Submitting reservation");
    reservation.hotelRoomId = $(".selected").attr('id');
    reservation.numOfGuests = $('#numOfGuests').val();
    let date = new Date($('#startDate').val());
    console.log(date);
    reservation.startDate = date.toISOString();
    date = new Date($('#endDate').val());
    reservation.endDate = date.toISOString();

    console.log(reservation);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                closeReservationModal();
                loadGuestDashboard();
            } else {
                $("#errorMessage").text("Something went wrong when sending the issue");
            }
            $(".is-success").removeClass('is-loading');
        }
    };
    $(".is-success").addClass('is-loading');

    xhr.open("POST", "api/v1/reservations");

    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(reservation);

    xhr.send(json);


}

function isValidReservation(reservation){
    if (Number.isNaN(reservation.hotelRoomId)){
        console.log("Hotel Room is not selected properly");
    }
    if (Number.isNaN(reservation.numOfGuests)){
        console.log("Number of guests is NaN")
    }
    if (reservation.startDate === null){

    }

}

function openReservationModal(){
    $("#reservationForm.modal").toggle("is-active");
}

function closeReservationModal(){
    $("#reservationForm.modal").toggle("is-active");
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
 * Populates a reservation table row
 * @param reservation
 */
function populateHostReservationTableRow(reservation) {
    let tableBody = document.getElementById('reservations');
    let tableRow = document.createElement('tr');

    let tableData = document.createElement('td');
    let tableText = document.createTextNode(reservation.userId);
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(reservation.hotelRoomId);
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(reservation.numOfGuests);
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(reservation.startDate);
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(reservation.endDate);
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(reservation.currentStatus);
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    if (reservation.currentStatus === "PENDING") {
        tableData = document.createElement('td');
        tableData.className += "level";

        let buttonElement = document.createElement('button');
        buttonElement.className += "level-item button is-success";
        let btnTextNode = document.createTextNode('Approve');

        buttonElement.appendChild(btnTextNode);
        buttonElement.addEventListener('click', function () {
            setReservationStatus(reservation, "APPROVED");
        });

        tableData.appendChild(buttonElement);


        buttonElement = document.createElement('button');
        buttonElement.className += "level-item button is-danger";
        btnTextNode = document.createTextNode('Deny');


        buttonElement.appendChild(btnTextNode);
        buttonElement.addEventListener('click', function () {
            setReservationStatus(reservation, "DENIED");
        });

        tableData.appendChild(buttonElement);
        tableRow.appendChild(tableData);
    }

    tableBody.appendChild(tableRow);

    // $('#reservations').append(
    //     `<tr>
    //         <td>${reservation.userId}</td>
    //         <td>${reservation.hotelRoomId}</td>
    //         <td>${reservation.numOfGuests}</td>
    //         <td>${reservation.startDate}</td>
    //         <td>${reservation.endDate}</td>
    //         <td>${reservation.currentStatus}</td>
    //         <td class="level">
    //             <select class='level-item'>
    //                 <option>Select</option>
    //                 <option>APPROVED</option>
    //                 <option>DENIED</option>
    //             </select>
    //             <button class="button level-item" onclick="setReservationStatus()">Submit</button>
    //         </td>
    //     </tr>`
    // );
}

function setReservationStatus(reservation, status){
    reservation.currentStatus = status;

    updateReservation(reservation);
}