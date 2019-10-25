// const backendurl = 'http://localhost:8181/';
// const frontendurl = 'http://149.28.154.237:82/';
var link_slider = backendurl +'uploadSlider'
var sliderUploadButton = document.querySelector('#upload_slider_button')
var sliderInput = document.querySelector('#sliderUploadInput')

function uploadSlider(files){
    var formData = new FormData;
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
    var xhr = new XMLHttpRequest();
    xhr.open("POST",link_slider,true);

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          // Here we go on the new page
          window.location = "post_item.html";
          alert("Slide Upload Done")
        }
      };

    xhr.send(formData);
}
sliderUploadButton.addEventListener('click', function(event){
    var files = sliderInput.files;
    console.log(files)
    uploadSlider(files);
    event.preventDefault();
}, true);