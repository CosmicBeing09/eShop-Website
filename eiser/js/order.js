'use strict';
const backendurl = 'https://proda5-back.herokuapp.com/';
  const frontendurl = 'https://proda5.herokuapp.com/';
var link = backendurl+'order'
var buy = document.querySelector('#buy_button');
console.log('order 1');
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
      console.log('order 2');
        if (this.readyState == 4 && this.status == 200) {
          alert("Succesfully ordered your product")
        }
      };
    xhr.send(formData);
}
buy.addEventListener('click', function(event){
    
  order();
    event.preventDefault();
}, true);