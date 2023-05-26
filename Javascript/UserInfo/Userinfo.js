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
        console.log(result);
    }
    catch (err) {
        console.log(err)
    }
}

async function findAll() {

}

async function findById() {

}

async function updateUser() {

}