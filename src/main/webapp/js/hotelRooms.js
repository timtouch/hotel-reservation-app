function populateSelectHotelRoom(hotelRoom){
    $('#selectHotelRooms').append(
        `<option id="${hotelRoom.hotelRoomId}">${hotelRoom.roomNumber}</option>`
    );
}

/**
 * Populates a hotel room table row
 * @param hotelRoom
 */
function populateHotelRoomTableRow(hotelRoom){
    $('#hotelRooms').append(
        `<tr>
            <td>${hotelRoom.hotelRoomId}</td>
            <td>${hotelRoom.roomNumber}</td>
            <td>${hotelRoom.numOfBeds}</td>
        </tr>`
    );
}