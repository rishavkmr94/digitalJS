// create a cart , proceed payment and validate if payment was success using promises

function createCart(list){
    let prom = new Promise(function(resolve,reject){
        if(!list){
            let err = new Error("creating cart failed due to empty list");
            reject(err);
        }
        else{        
            setTimeout(() => {
            console.log("creating cart");
            resolve("cart created successfully");
        }, 4000);}
    })
    return prom;
}

function proceedPayment(result){
    let prom1 = new Promise(function(resolve,reject){
        if(result.toLowerCase() == "cart created successfully"){
            setTimeout(()=> {
                console.log("proceeding to payment");
                resolve("success with order id-32467527");
            },4000)
        }
        else{
            reject("failed in proceedPayment");
        }
    });
    return prom1;
}

function orderConfirmed(result){
    let prom2 = new Promise(function(resolve,reject){
        if(result.toLowerCase().includes("success")){
            setTimeout(()=>{
                console.log("your order is confirmed with us.");
                resolve("success in orderConfirmed");
            },4000);
        }
        else{
            reject("failed in orderConfirmed")
        }
    })
    return prom2;
}

// to execute failed case
// createCart()
createCart("apple","orange")
.then(function(result){
    return proceedPayment(result);
})
.catch(function(result){
    alert("cart is empty");
})
.then(function(result){
    return orderConfirmed(result);
})
.catch(function(result){
    console.log(result);
})