const backendurl = 'http://149.28.154.237:82/';
const frontendurl = 'http://149.28.154.237:80/';
var request = new XMLHttpRequest()

// Open a new connection, using the GET request on the URL endpoint
request.open('GET', backendurl +'allfeatured', true)

request.onload = function () {
    // Begin accessing JSON data here
   
      var data = JSON.parse(this.responseXML)

    // data.forEach(featured_product => {
      // Log each movie's title
      // console.log(movie.title)
      data.forEach(movie => {
    const app = document.getElementById('featured-product')


    const col = document.createElement('div')
    col.setAttribute('class', 'col-lg-4 col-md-6')

    app.appendChild(col)

    const single_product = document.createElement('div')
    const product_img = document.createElement('div')
    single_product.setAttribute('class', 'single-product')
    product_img.setAttribute('class', 'product-img')

    col.appendChild(single_product)
    single_product.appendChild(product_img)

    const img = document.createElement('img')
    img.setAttribute('class', 'img-fluid w-100')
    img.src = 'img/product/feature-product/f-p-1.jpg'
    img.alt = ''

    product_img.appendChild(img)

    product_btm = document.createElement('div')
    product_btm.setAttribute('class', 'product-btm')
    single_product.appendChild(product_btm)

    const head4 = document.createElement('h4')
    product_btm.appendChild(head4)
    head4.textContent = data
    const mtt = document.createElement('div')
    mtt.setAttribute('class', 'mt-3')
    product_btm.appendChild(mtt)
    const sp = document.createElement('span')
    sp.setAttribute('class', 'mr-4')
    mtt.appendChild(sp)
    // sp.textContent = this.response
     })
}

// Send request
request.send()


