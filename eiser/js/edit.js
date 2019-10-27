'use strict';
// const backendurl = 'http://localhost:8181/';
// const frontendurl = '';

const backendurl = 'http://149.28.154.237:82/';
const frontendurl = 'http://149.28.154.237:80/';


var edit_button = document.querySelector('#edit_product_button');
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

var id = getUrlVars()["id"];

  edit_button.addEventListener('click', function(event){
  document.location.href = frontendurl+ "update.html?id="+ id;
}, true);

var delete_button = document.querySelector('#delete_product_button')
delete_button.addEventListener('click', function(event){
  var $load = $("#loadMe").modal({
    backdrop: "static", //remove ability to close modal with click
    keyboard: false, //remove option to close with keyboard
    show: true //Display loader!
  });
  var request1 = new XMLHttpRequest()
  var url=backendurl+ 'delete/' + id
  request1.open('DELETE',url,true)
  request1.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      $("#loadMe").modal("hide");
      // Here we go on the new page
      alert("Deleted")
      window.location = frontendurl+"shop_category.html";
      
    }
  };
  request1.send();
}, true);


var request = new XMLHttpRequest()
var link = backendurl + 'allproduct/' + id
// Open a new connection, using the GET request on the URL endpoint
request.open('GET', link, true)

request.onload = function () {
  // Begin accessing JSON data here

    var data = JSON.parse(this.response)
   // console.log(data)
    // data.forEach(movie => {
        // const smallimage1 = document.getElementById('smallimage1')
        // smallimage1.src = movie.image1
        // const smallimage2 = document.getElementById('smallimage2')
        // smallimage2.src = movie.image2
        // const smallimage3 = document.getElementById('smallimage3')
        // smallimage3.src = movie.image3

        const image1 = document.getElementById('image1')
        const image2 = document.getElementById('image2')
        const image3 = document.getElementById('image3')
        image1.src = data.image1
        image2.src = data.image2
        image3.src = data.image3

        const smallimage1 = document.getElementById('smallimage1')
        const smallimage2 = document.getElementById('smallimage2')
        const smallimage3 = document.getElementById('smallimage3')
        smallimage1.src = data.smallImage1
        smallimage2.src = data.smallImage2
        smallimage3.src = data.smallImage3
        
        const product_name = document.getElementById('product-name')
        const product_price = document.getElementById('product-price')
        product_name.textContent = data.name
        if(data.discountPrice.length!=0){
        product_price.textContent =  data.discountPrice + ' Taka'
        }
        else{
        product_price.textContent =  data.price + ' Taka'
        }
        const product_description = document.getElementById('description')
        product_description.textContent = data.details
        const details = document.getElementById('details')
        details.textContent = data.details
        const category = document.getElementById('product_category')
        category.textContent = data.category
        const brand = document.getElementById('brand')
        brand.textContent = data.brand
        const model = document.getElementById('model')
        model.textContent = data.model
        const height = document.getElementById('height')
        height.textContent = data.height
        const width = document.getElementById('width')
        width.textContent = data.width
        const weight = document.getElementById('weight')
        weight.textContent = data.weight
        const warrenty = document.getElementById('warrenty')
        warrenty.textContent = data.warrenty

     // })
    }
    request.send();
