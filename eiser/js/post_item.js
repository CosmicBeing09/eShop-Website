'use strict';
var link = 'http://localhost:8181/uploadMultipleFiles'
var multipleUploadForm = document.querySelector('#upload_product_button');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
// var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
// var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');


function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
    console.log(document.getElementById("price").value)
    console.log(document.getElementById("discount_price").value)
    console.log(document.getElementById("procuct_brand").value)
    console.log(document.getElementById("procuct_model").value)
    var brand=  document.getElementById("procuct_brand").value
    var model = document.getElementById("procuct_model").value
    var height = document.getElementById("procuct_height").value
    var width = document.getElementById("procuct_width").value
    var weight = document.getElementById("procuct_weight").value
    var warrenty = document.getElementById("procuct_warrenty").value

    console.log(document.getElementById("procuct_model").value)
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

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        // if(xhr.status == 200) {
        //     multipleFileUploadError.style.display = "none";
        //     var content = "<p>All Files Uploaded Successfully</p>";
        //     for(var i = 0; i < response.length; i++) {
        //         content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
        //     }
        //     multipleFileUploadSuccess.innerHTML = content;
        //     multipleFileUploadSuccess.style.display = "block";
        // } else {
        //     multipleFileUploadSuccess.style.display = "none";
        //     multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        // }
    }

    xhr.send(formData);
}

multipleUploadForm.addEventListener('click', function(event){
    var files = multipleFileUploadInput.files;
    console.log(files)
    uploadMultipleFiles(files);
    event.preventDefault();
}, true);