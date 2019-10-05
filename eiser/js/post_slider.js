var link = 'http://localhost:8181/uploadSlider'
var sliderUploadButton = document.querySelector('#upload_slider_button')
var sliderInput = document.querySelector('#sliderUploadInput')

function uploadSlider(files){
    var formData = new FormData;
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
    var xhr = new XMLHttpRequest();
    xhr.open("POST",link,true);

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
    }

    xhr.send(formData);
}
sliderUploadButton.addEventListener('click', function(event){
    var files = sliderInput.files;
    console.log(files)
    uploadSlider(files);
    event.preventDefault();
}, true);