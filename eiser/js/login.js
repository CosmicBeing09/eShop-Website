'use strict';


const backendurl = 'http://149.28.154.237:82/';
const frontendurl = 'http://149.28.154.237:80/';


var link = backendurl+ 'reg'
var multipleUploadForm = document.querySelector('#button_login');


function loginfunction() {
    var formData = new FormData();
    
    var username = document.getElementById("login_phone").value
    var password = document.getElementById("login_password").value

    formData.append("useraccount", new Blob([JSON.stringify({
        "username": document.getElementById("login_phone").value,
        "password": document.getElementById("login_password").value
        
    })], {
            type: "application/json",
            processData: false, contentType: false, cache: false
        }));
    var xhr = new XMLHttpRequest();
    

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          // Here we go on the new page
        //   window.location = "http://localhost/eiser/index.html";
          var jsonResponse = JSON.parse(xhr.responseText);
          alert("UPLOAD DONE"+jsonResponse)
        }
      };
    xhr.open("POST",link,true);
    xhr.send(formData);
}
