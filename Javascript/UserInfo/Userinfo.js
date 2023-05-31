'use strict';


async function createUser() {
    let firstName = document.getElementById("createFirstName").value;
    let lastName = document.getElementById("createLastName").value;
    let email = document.getElementById("createEmail").value;
    let id = document.getElementById("createId").value;

    let requestData = {
        fname: firstName,
        lname: lastName,
        email: email,
        id: id
    };

    try {
        let response = await fetch('http://localhost:8081/api/user/create', {
            method: 'POST',
            headers: {
                'Access-Control-Allow-Origin': '*',
                "content-type": "application/json"
            },
            body: JSON.stringify(requestData)
        });
        let result = await response.text();
        let resultElement = `<br><div id="createResultElement" style="color:#ff7200">${result}</div>`;
        $("#createDivForm").append(resultElement);
        console.log(result);
        setTimeout(function () {
            $('#createResultElement').remove();
        }, 2000);
    }
    catch (err) {
        console.log(err)
    }
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("findall").addEventListener("click", findAll);
})

async function findAll() {
    fetch("http://localhost:8081/api/user/all")
        .then((response) => response.json())
        .then((result) => {
            if (result) {
                let findTableBody = document.getElementById("findAllBody");
                while (findTableBody.hasChildNodes()) {
                    findTableBody.removeChild(findTableBody.firstChild);
                }
                result.forEach(element => {
                    let row1 = findTableBody.insertRow(-1);
                    let cell1 = row1.insertCell(-1);
                    cell1.innerHTML = element.id;
                    let cell2 = row1.insertCell(-1);
                    cell2.innerHTML = element.fname;;
                    let cell3 = row1.insertCell(-1);
                    cell3.innerHTML = element.lname;;
                    let cell4 = row1.insertCell(-1);
                    cell4.innerHTML = element.email;;
                });
            }
        })
        .catch(err => console.log(err))
        ;
}

async function findById() {

}

async function updateUser() {

}