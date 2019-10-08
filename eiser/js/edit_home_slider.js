function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
  }
  var id = getUrlVars()["id"];
  if(id!=undefined){
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE",'http://localhost:8181/deleteSlide/'+id, true);

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
    }
    xhr.send();
  }

var request = new XMLHttpRequest()

// Open a new connection, using the GET request on the URL endpoint
request.open('GET', 'http://localhost:8181/allSlider', true)

var data;

request.onload = function () {

    data = JSON.parse(this.response)

    data.forEach(temp => {
    const root = document.createElement('div')
    const app = document.getElementById('edit_slider')
    root.setAttribute('class','product')
    const col = document.createElement('div')
    col.setAttribute('class', 'col-lg-4 col-md-6')
    app.appendChild(col)
    const single_product = document.createElement('div')
    const product_img = document.createElement('div')
    single_product.setAttribute('class', 'single-product')
    product_img.setAttribute('class', 'product-img')
    root.appendChild(single_product)
    col.appendChild(root)
    single_product.appendChild(product_img)
    const img = document.createElement('img')
    img.setAttribute('class', 'img-fluid w-100')
    img.src = temp.image
    product_img.appendChild(img)
    product_btm = document.createElement('div')
    single_product.appendChild(product_btm)

    const aa = document.createElement('a')
    var strLink = "edit_slider.html?id="+ temp.id;
    aa.setAttribute("href",strLink)

  
    aa.setAttribute('class', 'd-block')
    const head4 = document.createElement('h4')
    head4.textContent = "Delete";
    aa.appendChild(head4)
    product_btm.appendChild(aa)
   
    })
}
request.send();