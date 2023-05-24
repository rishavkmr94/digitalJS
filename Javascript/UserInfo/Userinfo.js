'use strict';


async function createUser() {
    let firstName = document.getElementById("createFirstName").value;
    let lastName = document.getElementById("createLastName").value;
    let email = document.getElementById("createEmail").value;
    let id = document.getElementById("createId").value;

    let requestData = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        id: id
    };

    let response = await fetch('http://localhost:8080/api/user/create', {
        method: 'POST',
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(requestData)
    });
    let responseData = await response.json();
    console.log(responseData);
}

async function findAll() {

}

async function findById() {

}

async function updateUser() {

}