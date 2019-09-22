
var request = new XMLHttpRequest()

// Open a new connection, using the GET request on the URL endpoint
request.open('GET', 'http://localhost:8181/allfeatured', true)

request.onload = function () {
  // Begin accessing JSON data here

  var data = JSON.parse(this.response)
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
    img.src = movie.coverimage
    img.alt = ''
    img.setAttribute('height','900px')
    img.width='200px'
    product_img.appendChild(img)
    product_btm = document.createElement('div')
    product_btm.setAttribute('class', 'product-btm')
    single_product.appendChild(product_btm)
    const aa = document.createElement('a')
    //aa.href = "#"
    var scrt_var = 10
    var strLink = "single-product.html?id="+ movie.product_id;
    aa.setAttribute("href",strLink)
    //document.getElementById("link2").setAttribute("href",strLink);


    aa.setAttribute('class', 'd-block')
    const head4 = document.createElement('h4')
    head4.textContent = movie.product_name;
    aa.appendChild(head4)
    product_btm.appendChild(aa)
    const mtt = document.createElement('div')
    mtt.setAttribute('class', 'mt-3')
    product_btm.appendChild(mtt)
    const sp = document.createElement('span')
    sp.setAttribute('class', 'mr-4')
    sp.textContent = '$ '+ movie.price
    mtt.appendChild(sp)
  })
}

// Send request
request.send()


