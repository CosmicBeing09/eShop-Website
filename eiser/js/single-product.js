

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

var number = getUrlVars()["id"];
var request = new XMLHttpRequest()
var link = 'http://localhost:8181/allfeatured/' + number
// Open a new connection, using the GET request on the URL endpoint
request.open('GET', link, true)

request.onload = function () {
  // Begin accessing JSON data here

    var data = JSON.parse(this.response)
    data.forEach(movie => {
        const smallimage1 = document.getElementById('smallimage1')
        smallimage1.src = movie.smallimage1
        const smallimage2 = document.getElementById('smallimage2')
        smallimage2.src = movie.smallimage2
        const smallimage3 = document.getElementById('smallimage3')
        smallimage3.src = movie.smallimage3

        const image1 = document.getElementById('image1')
        const image2 = document.getElementById('image2')
        const image3 = document.getElementById('image3')
        image1.src = movie.image1
        image2.src = movie.image2
        image3.src = movie.image3
        
        const product_name = document.getElementById('product-name')
        const product_price = document.getElementById('product-price')
        product_name.textContent = movie.product_name
        product_price.textContent = '$' + movie.price
        const product_description = document.getElementById('description')
        product_description.textContent = movie.description

      })
    }
    request.send()
