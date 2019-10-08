'use strict';
var link = 'http://localhost:8181/order'
var buy = document.querySelector('#buy_button');
// var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
// var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');


function order() {
    var formData = new FormData();
    console.log(document.getElementById('product-name').textContent)
    formData.append("mailbody", new Blob([JSON.stringify({
        "address": document.getElementById('order_address').value,
        "productName":document.getElementById('product-name').textContent,
        "phoneNo": document.getElementById('order_phone_no').value

    })], {
            type: "application/json",
            processData: false, contentType: false, cache: false
        }));
    var xhr = new XMLHttpRequest();
    xhr.open("POST",link,true);

    xhr.onload = function() {
        console.log(xhr.responseText);
            }
    xhr.send(formData);
}
buy.addEventListener('click', function(event){
    
    order();
    event.preventDefault();
}, true);