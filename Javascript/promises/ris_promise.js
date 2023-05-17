let val = document.getElementById("outputBody");
let row1= val.insertRow(-1);
let cell1 = row1.insertCell(0);
let row2= val.insertRow(-1);
let cell2 = row2.insertCell(0);

let prom1  = new Promise(function(resolve,reject){
    setTimeout(function(){
        console.log(cell1);
        cell1.innerHTML="this the 1st timer";
        resolve("success");
    },4000);
    //implement reject for failure scenarios
})

prom1.then(function(result){
    cell2.innerHTML="this is the 2nd timer";
})
