'use strict';

const backendurl = 'http://149.28.154.237:82/';
const frontendurl = 'http://149.28.154.237:80/';


var link = backendurl+'order'
var buy = document.querySelector('#buy_button');

function order() {
    var formData = new FormData();
    formData.append("mailbody", new Blob([JSON.stringify({
        "address": document.getElementById('order_address').value,
        "productName":document.getElementById('product-name').textContent,
        "phoneNo": document.getElementById('order_phone_no').value,
        "price": document.getElementById('product-price').textContent,
        "shopName": document.getElementById('shop-name').textContent

    })], {
            type: "application/json",
            processData: false, contentType: false, cache: false
        }));
    var xhr = new XMLHttpRequest();
    xhr.open("POST",link,true);
    xhr.onreadystatechange = function() {
      console.log('order 2');
        if (this.readyState == 4 && this.status == 200) {
          $("#loadMe").modal("hide");
          alert("Succesfully ordered your product")
        }
      };
    xhr.send(formData);
}
buy.addEventListener('click', function(event){
    
  order();
  var $load = $("#loadMe").modal({
    backdrop: "static", //remove ability to close modal with click
    keyboard: false, //remove option to close with keyboard
    show: true //Display loader!
  });
   event.preventDefault();
}, true);