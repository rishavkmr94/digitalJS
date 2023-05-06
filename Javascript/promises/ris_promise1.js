
const tableBody = document.getElementById("outputBody");
const row = tableBody.insertRow(-1);

// timeout at global level but executes after promise
setTimeout(()=>{
    let cell1 = row.insertCell(-1);
    cell1.innerHTML = " this the 3rd timer";
},4001);

//timeout in promise
const myPromise = new Promise((resolve,reject)=>{
    setTimeout(()=>{
        let num = Math.random() * 10;
        if(num < 5) {
            let cell2 = row.insertCell(-1);
            cell2.innerHTML = " this the 1st timer with success in number";
            resolve(num);          
        }
        else {
            let cell2 = row.insertCell(-1);
            cell2.innerHTML = " this the 1st timer with failure in number";
            reject(`   the number is high ${num}`);
        }      
    },4000);
});

myPromise.then((result)=>{
    let cell3 = row.insertCell(-1);
    cell3.innerHTML = ` this is the output ${result}`;
})
.catch((err)=>{
    let cell3 = row.insertCell(-1);
    cell3.innerHTML = ` this is the output ${err}`;
})