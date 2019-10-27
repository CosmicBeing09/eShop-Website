const backendurl = 'http://149.28.154.237:82/';
const frontendurl = 'http://149.28.154.237:80/';

var sliderUploadButton = document.querySelector('#upload_slider_button')
var sliderInput = document.querySelector('#sliderUploadInput')

function uploadSlider(files){
    var formData = new FormData;
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
    var xhr = new XMLHttpRequest();
    xhr.open("POST",backendurl+'uploadSlider',true);

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          // Here we go on the new page
          $("#loadMe").modal("hide");
          window.location = "post_item.html";
          alert("Slide Upload Done")
        }
      };
    xhr.send(formData);
}
sliderUploadButton.addEventListener('click', function(event){
    var files = sliderInput.files;
    console.log("clicked");
    $("#loadMe").modal({   
      backdrop: "static", //remove ability to close modal with click
      keyboard: false, //remove option to close with keyboard
      show: true //Display loader!
    });
    uploadSlider(files);
    event.preventDefault();
}, true);