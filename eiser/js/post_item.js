'use strict';
var link = 'http://localhost:8181/uploadProduct'
var postItemButton = document.querySelector('#upload_product_button');
var postItemPhotos = document.querySelector('#multipleFileUploadInput');


function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
    // console.log(document.getElementById("price").value)
    // console.log(document.getElementById("discount_price").value)
    // console.log(document.getElementById("procuct_brand").value)
    // console.log(document.getElementById("procuct_model").value)
    var brand=  document.getElementById("procuct_brand").value
    var model = document.getElementById("procuct_model").value
    var height = document.getElementById("procuct_height").value
    var width = document.getElementById("procuct_width").value
    var weight = document.getElementById("procuct_weight").value
    var warrenty = document.getElementById("procuct_warrenty").value

   // console.log(document.getElementById("procuct_model").value)
    var e = document.getElementById("category");
    var category = e.options[e.selectedIndex].text;
    formData.append("product", new Blob([JSON.stringify({
        "name": document.getElementById("product_name").value,
        "category":category,
        "details": document.getElementById("product_details").value,
        "price": document.getElementById("price").value,
        "discountPrice" : document.getElementById("discount_price").value,
        "brand" : brand,
        "model" : model,
        "height" : height,
        "width" : width,
        "weight" : weight,
        "warrenty" : warrenty,
        "type": "Regular",
        "userId" : ""

    })], {
            type: "application/json",
            processData: false, contentType: false, cache: false
        }));
    var xhr = new XMLHttpRequest();
    xhr.open("POST",link,true);
    xhr.send(formData);
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          // Here we go on the new page
          window.location = "post_item.html";
          alert("UPLOAD DONE")
        }
      };
    
  
}

postItemButton.addEventListener('click', function(event){
    var files = postItemPhotos.files;
   /// console.log(files)
    uploadMultipleFiles(files);
    event.preventDefault();
}, true);