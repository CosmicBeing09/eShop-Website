var request = new XMLHttpRequest()
const backendurl = 'https://proda5-back.herokuapp.com/';
  const frontendurl = 'https://proda5.herokuapp.com/';

// Open a new connection, using the GET request on the URL endpoint
request.open('GET', backendurl+ 'allSlider', true)

var data;

request.onload = function () {

    data = JSON.parse(this.response)

    data.forEach(temp => {
        const carousel_inner = document.getElementById('carousel_inner')
        const div_carousel_active = document.createElement('div')
        div_carousel_active.setAttribute('class','carousel-item')
        const img = document.createElement('img')
        img.setAttribute('class','d-block w-100')
        img.src = temp.image
        
        div_carousel_active.appendChild(img)
        carousel_inner.appendChild(div_carousel_active)
    })
}
request.send();