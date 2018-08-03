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
};
