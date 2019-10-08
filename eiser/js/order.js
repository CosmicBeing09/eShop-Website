'use strict';
var link = 'http://localhost:8181/order'
var buy = document.querySelector('#buy_button');

// var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
// var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

 

function order() {
    var formData = new FormData();
    formData.append("mailbody", new Blob([JSON.stringify({
        "address": document.getElementById('order_address').value,
        "productName":document.getElementById('product-name').textContent,
        "phoneNo": document.getElementById('order_phone_no').value,
        "price": document.getElementById('product-price').textContent

    })], {
            type: "application/json",
            processData: false, contentType: false, cache: false
        }));
    var xhr = new XMLHttpRequest();
    xhr.open("POST",link,true);
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          alert("Succesfully ordered your product")
        }
      };
    xhr.send(formData);
}
buy.addEventListener('click', function(event){
    // const root = document.getElementById('popup_holder')
    // const div = document.createElement('div')
    // div.setAttribute('class','spinner-border')
    // div.setAttribute('role','status')
    // const span = document.createElement('span')
    // span.setAttribute('class','sr-only')
    // span.textContent = 'Loading ....'
    // div.appendChild(span)
    // root.appendChild(div)
    order();
    event.preventDefault();
}, true);