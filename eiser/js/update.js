'use strict';
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
const backendurl = 'https://proda5-back.herokuapp.com/';
const frontendurl = 'https://proda5.herokuapp.com/';
var link1 = backendurl + 'uploadProduct';
var update_button = document.querySelector('#update_product_button');
var multipleFileUploadInput = document.querySelector('#update_multipleFileUploadInput');
// var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
// var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

var id = getUrlVars()["id"];
var request = new XMLHttpRequest()
//console.log(id)
var link = backendurl + 'allproduct/' + id
var link2 = backendurl +'updateProduct/'
// Open a new connection, using the GET request on the URL endpoint
request.open('GET', link, true)

request.onload = function () {
  // Begin accessing JSON data here

    var data = JSON.parse(this.response)
    // console.log(data)
    // console.log(data.name)
  //  data.forEach(movie => {
        const name = document.getElementById('update_product_name')
        name.value = data.name
        const price = document.getElementById('update_price')
        price.value = data.price
        const discount_price = document.getElementById('update_discount_price')
        discount_price.value = data.discountPrice
        const details = document.getElementById('update_product_details')
        details.value = data.details
        const brand = document.getElementById('update_procuct_brand')
        brand.value = data.brand
      //  console.log(data.brand)
        const model = document.getElementById('update_procuct_model')
        model.value = data.model
        const height = document.getElementById('update_procuct_height')
        height.value = data.height
        const width = document.getElementById('update_procuct_width')
        width.value = data.width
        const weight = document.getElementById('update_procuct_weight')
        weight.value = data.weight
        const warrenty = document.getElementById('update_procuct_warrenty')
        warrenty.value = data.warrenty
        const category = document.getElementById('update_category')
        category.selectedIndex = data.category
     // })
    }
    request.send();




function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
    // console.log(document.getElementById("update_price").value)
    // console.log(document.getElementById("update_discount_price").value)
    // console.log(document.getElementById("update_procuct_brand").value)
    // console.log(document.getElementById("update_procuct_model").value)
    var brand=  document.getElementById("update_procuct_brand").value
    var model = document.getElementById("update_procuct_model").value
    var height = document.getElementById("update_procuct_height").value
    var width = document.getElementById("update_procuct_width").value
    var weight = document.getElementById("update_procuct_weight").value
    var warrenty = document.getElementById("update_procuct_warrenty").value
   

   // console.log(document.getElementById("update_procuct_model").value)
    var e = document.getElementById("update_category");
    var category = e.options[e.selectedIndex].text;
    var temp = document.getElementById('update_type');
    var type = temp.options[temp.selectedIndex].text;
    formData.append("product", new Blob([JSON.stringify({
        "id":id,
        "name": document.getElementById("update_product_name").value,
        "category":category,
        "details": document.getElementById("update_product_details").value,
        "price": document.getElementById("update_price").value,
        "discountPrice" : document.getElementById("update_discount_price").value,
        "brand" : brand,
        "model" : model,
        "height" : height,
        "width" : width,
        "weight" : weight,
        "warrenty" : warrenty,
        "type" : type,

    })], {
            type: "application/json",
            processData: false, contentType: false, cache: false
        }));
    var xhr = new XMLHttpRequest();
    xhr.open("PUT",link2,true);

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          // Here we go on the new page
          alert("Successfully Updated")
          window.location = "shop_category.html";
        }
      };

    xhr.send(formData);
}

update_button.addEventListener('click', function(event){
    var files = multipleFileUploadInput.files;
    //console.log(files)
    uploadMultipleFiles(files);
    event.preventDefault();
}, true);