'use strict';

const backendurl = 'http://149.28.154.237:82/';
const frontendurl = 'http://149.28.154.237:80/';

if (user.type.length != null) {
  if (user.type != "admin") {
    var upload_home_slider_title = document.getElementById("upload_home_slider_title");
    var upload_home_slider_input = document.getElementById("upload_home_slider_input");
    var upload_home_slider_button = document.getElementById("upload_home_slider_button");

    upload_home_slider_title.style.display = "none";
    upload_home_slider_input.style.display = "none";
    upload_home_slider_button.style.display="none";
  }
}



var postItemButton = document.querySelector('#upload_product_button');
var postItemPhotos = document.querySelector('#multipleFileUploadInput');

//var user =  JSON.parse(window.localStorage.getItem('user'))
//console.log(user.id)
function uploadMultipleFiles(files) {
  var formData = new FormData();
  for (var index = 0; index < files.length; index++) {
    formData.append("files", files[index]);
  }

  var brand = document.getElementById("procuct_brand").value
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
    "category": category,
    "details": document.getElementById("product_details").value,
    "price": document.getElementById("price").value,
    "discountPrice": document.getElementById("discount_price").value,
    "brand": brand,
    "model": model,
    "height": height,
    "width": width,
    "weight": weight,
    "warrenty": warrenty,
    "type": "Regular",
    "userId": user.id,
    "shopName": user.shopname

  })], {
    type: "application/json",
    processData: false, contentType: false, cache: false
  }));
  var xhr = new XMLHttpRequest();
  xhr.open("POST", backendurl + 'uploadProduct', true);
  xhr.send(formData);
  xhr.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      // Here we go on the new page
      //   $("#loadMe").modal("hide");
      $("#loadMe").modal("hide");
      window.location = "post_item.html";
      alert("UPLOAD DONE")
    }
  };
}

postItemButton.addEventListener('click', function (event) {
  var files = postItemPhotos.files;
  /// console.log(files)
  uploadMultipleFiles(files);
  $("#loadMe").modal({
    backdrop: "static", //remove ability to close modal with click
    keyboard: false, //remove option to close with keyboard
    show: true //Display loader!
  });
  event.preventDefault();
}, true);