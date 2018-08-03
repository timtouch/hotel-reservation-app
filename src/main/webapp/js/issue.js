function submitIssue(){
    let issue = {};

    issue.createdById = userSession.getLoggedInClient().userId;
    issue.message = $('#message').val();

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                closeIssueModal();
                loadGuestDashboard();
            } else {
                $("#errorMessage").text("Something went wrong when sending the issue");
            }
        }
    };

    xhr.open("POST", "api/v1/issues");

    xhr.setRequestHeader("Content-type", "application/json");

    let json = JSON.stringify(issue);

    xhr.send(json);

}

function updateIssue(){

}

function openIssueModal(){
    $("#issueForm.modal").toggle("is-active");
}

function closeIssueModal(){
    $("#issueForm.modal").toggle("is-active");
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
            <td>${issue.resolved ? "RESOLVED" : "UNRESOLVED"}</td>
        </tr>`
    );
}