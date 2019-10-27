'use strict';
function getUrlVars() {
  var vars = {};
  var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
      vars[key] = value;
  });
  return vars;
}

const backendurl = 'http://149.28.154.237:82/';
const frontendurl = 'http://149.28.154.237:80/';

var category = getUrlVars()["category"];
var request = new XMLHttpRequest();
//const user =  JSON.parse(window.localStorage.getItem('user'));
// Open a new connection, using the GET request on the URL endpoint
if(user.type=="seller"){
request.open('GET', backendurl +'all_user_product/'+user.id, true);
}
else{
  request.open('GET', backendurl +'allproduct', true);
}

request.onload = function () {
  // Begin accessing JSON data here

  var data = JSON.parse(this.response)

  data.forEach(movie => {
    if(category===undefined){
    const app = document.getElementById('category-product')
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
    
    img.src = movie.image1
    img.alt = ''
    img.style.height="1000"
    img.style.width="1000"
    product_img.appendChild(img)
    const product_btm = document.createElement('div')
    product_btm.setAttribute('class', 'product-btm')
    single_product.appendChild(product_btm)
    const aa = document.createElement('a')
    //aa.href = "#"
    var scrt_var = 10
    var strLink = "edit.html?id="+ movie.id;
    aa.setAttribute("href",strLink)
    //console.log(strLink)
    //document.getElementById("link2").setAttribute("href",strLink);

    const icon = document.createElement('div')
    icon.setAttribute('class','p_icon')

    const a1 = document.createElement('a')
    a1.setAttribute('herf','#')
    const i1 = document.createElement('i')
    i1.setAttribute('class','ti-eye')
    a1.appendChild(i1)

    const a2 = document.createElement('a')
    a2.setAttribute('herf','#')
    const i2 = document.createElement('i')
    i2.setAttribute('class','ti-heart')
    a2.appendChild(i2)

    const a3 = document.createElement('a')
    a3.setAttribute('herf','#')
    const i3 = document.createElement('i')
    i3.setAttribute('class','ti-shopping-cart')
    a3.appendChild(i3)

    icon.appendChild(a1)
    icon.appendChild(a2)
    icon.appendChild(a3)

    product_img.appendChild(icon)



    aa.setAttribute('class', 'd-block')
    const head4 = document.createElement('h4')
    head4.textContent = movie.name;
    aa.appendChild(head4)
    product_btm.appendChild(aa)
    const mtt = document.createElement('div')
    mtt.setAttribute('class', 'mt-3')
    product_btm.appendChild(mtt)
    if (movie.discountPrice.length != 0) {
      const sp = document.createElement('span')
      sp.setAttribute('class', 'mr-4')
      sp.textContent = movie.discountPrice + ' Taka'
      mtt.appendChild(sp)

      const del = document.createElement('del')
      del.textContent = movie.price + ' Taka'
      mtt.appendChild(del)
    }
    else {
      const sp = document.createElement('span')
      sp.setAttribute('class', 'mr-4')
      sp.textContent = movie.price + ' Taka'
      mtt.appendChild(sp)
    }
    }
    else if(category===movie.category){
      const app = document.getElementById('category-product')
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
      
      img.src = movie.image1
      img.alt = ''
      img.style.height="1000"
      img.style.width="1000"
      product_img.appendChild(img)
      product_btm = document.createElement('div')
      product_btm.setAttribute('class', 'product-btm')
      single_product.appendChild(product_btm)
      const aa = document.createElement('a')
      //aa.href = "#"
      var scrt_var = 10
      var strLink = frontendurl+"edit.html?id="+ movie.id;
      aa.setAttribute("href",strLink)
      console.log(strLink)
      //document.getElementById("link2").setAttribute("href",strLink);
  
      const icon = document.createElement('div')
      icon.setAttribute('class','p_icon')
  
      const a1 = document.createElement('a')
      a1.setAttribute('herf','#')
      const i1 = document.createElement('i')
      i1.setAttribute('class','ti-eye')
      a1.appendChild(i1)
  
      const a2 = document.createElement('a')
      a2.setAttribute('herf','#')
      const i2 = document.createElement('i')
      i2.setAttribute('class','ti-heart')
      a2.appendChild(i2)
  
      const a3 = document.createElement('a')
      a3.setAttribute('herf','#')
      const i3 = document.createElement('i')
      i3.setAttribute('class','ti-shopping-cart')
      a3.appendChild(i3)
  
      icon.appendChild(a1)
      icon.appendChild(a2)
      icon.appendChild(a3)
  
      product_img.appendChild(icon)
  
  
  
      aa.setAttribute('class', 'd-block')
      const head4 = document.createElement('h4')
      head4.textContent = movie.name;
      aa.appendChild(head4)
      product_btm.appendChild(aa)
      const mtt = document.createElement('div')
      mtt.setAttribute('class', 'mt-3')
      product_btm.appendChild(mtt)
      if (movie.discountPrice.length != 0) {
        const sp = document.createElement('span')
        sp.setAttribute('class', 'mr-4')
        sp.textContent = movie.discountPrice + ' Taka'
        mtt.appendChild(sp)

        const del = document.createElement('del')
        del.textContent = movie.price + ' Taka'
        mtt.appendChild(del)
      }
      else {
        const sp = document.createElement('span')
        sp.setAttribute('class', 'mr-4')
        sp.textContent = movie.price + ' Taka'
        mtt.appendChild(sp)
      }
    }
  })
}

// Send request
request.send()


