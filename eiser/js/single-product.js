function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

var id = getUrlVars()["id"];
console.log(id)
var request = new XMLHttpRequest()
var link = 'http://localhost:8181/allproduct/' + id
// Open a new connection, using the GET request on the URL endpoint
request.open('GET', link, true)

request.onload = function () {
  // Begin accessing JSON data here

    var data = JSON.parse(this.response)
    console.log(data)
    
        const image1 = document.getElementById('image1')
        const image2 = document.getElementById('image2')
        const image3 = document.getElementById('image3')
        image1.src = data.image1
        image2.src = data.image2
        image3.src = data.image3

        const smallimage1 = document.getElementById('smallimage1')
        const smallimage2 = document.getElementById('smallimage2')
        const smallimage3 = document.getElementById('smallimage3')
        smallimage1.src = data.smallImage1
        smallimage2.src = data.smallImage2
        console.log(data.smallImage2)
        smallimage3.src = data.smallImage3
        
        const product_name = document.getElementById('product-name')
        const product_price = document.getElementById('product-price')
        product_name.textContent = data.name
        product_price.textContent =  data.price + ' Taka'
        const product_description = document.getElementById('description')
        product_description.textContent = data.details
        const details = document.getElementById('details')
        details.textContent = data.details
        const category = document.getElementById('product_category')
        category.textContent = data.category
        const brand = document.getElementById('brand')
        brand.textContent = data.brand
        const model = document.getElementById('model')
        model.textContent = data.model
        const height = document.getElementById('height')
        height.textContent = data.height
        const width = document.getElementById('width')
        width.textContent = data.width
        const weight = document.getElementById('weight')
        weight.textContent = data.weight
        const warrenty = document.getElementById('warrenty')
        warrenty.textContent = data.warrenty

    }
    request.send()
