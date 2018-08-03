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

/**
 * Populates an issues row for host view.
 * Has a button that when pressed, sets the issue's resolve status to <code>true</code>
 * @param issue
 */
function populateHostIssuesTableRow(issue){

    let tableBody = document.getElementById('issues');
    let tableRow = document.createElement('tr');

    let tableData = document.createElement('td');
    let tableText = document.createTextNode(issue.createdOn);
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(issue.message);
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(issue.resolverId !== 0 ? issue.resolverId : "");
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(issue.resolverId !== 0 ? issue.resolverId : "");
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    tableData = document.createElement('td');
    tableText = document.createTextNode(issue.resolved ? "RESOLVED" : "UNRESOLVED");
    tableData.appendChild(tableText);
    tableRow.appendChild(tableData);

    if (!issue.resolved){
        let buttonElement = document.createElement('button');
        let btnTextNode = document.createTextNode('Resolve');

        buttonElement.appendChild(btnTextNode);
        buttonElement.addEventListener('click', function(){
            resolveIssue(issue);
        });

        tableData = document.createElement('td');
        tableData.appendChild(buttonElement);
        tableRow.appendChild(tableData);
    }


    tableBody.appendChild(tableRow);


}

/**
 * Takes an issue and resolves it
 * @param issue
 */
function resolveIssue(issue){
    let resolveDate = new Date(Date.now());

    issue.resolverId = userSession.getLoggedInClient().userId;
    issue.resolvedOn = resolveDate.toISOString();
    issue.resolved = true;

    updateIssue(issue);
}


