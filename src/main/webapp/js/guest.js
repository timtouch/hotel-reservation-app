/**
 * Populates a reservation table row
 * @param reservation
 */
function populateGuestsTableRow(guest){
    $('#guests').append(
        `<tr>
            <td>${guest.userId}</td>
            <td>${guest.firstName} ${guest.lastName}</td>
            <td>${guest.email}</td>
        </tr>`
    );
}
