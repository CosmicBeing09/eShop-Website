// const backendurl = 'http://localhost:8181/';
// const frontendurl = 'http://149.28.154.237:82/';


const backendurl = 'http://149.28.154.237:82/';
const frontendurl = 'http://149.28.154.237:80/';


var request = new XMLHttpRequest()

// Open a new connection, using the GET request on the URL endpoint
request.open('GET', backendurl+ 'allSlider', true)

var data;

request.onload = function () {

  data = JSON.parse(this.response)

  data.forEach(temp => {
    const root = document.createElement('div')
    const app = document.getElementById('edit_slider')
    root.setAttribute('class', 'product')
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
    const id = temp.id
    

    const h4 = document.createElement('h4')
    h4.textContent + ' '
    const div = document.createElement('div')
    div.setAttribute('class', 'container')
    const btn = document.createElement('button')
    btn.setAttribute('id', 'delete_slider')
    btn.setAttribute('type', 'submit')
    btn.setAttribute('class', 'genric-btn danger circle arrow')
    btn.textContent = 'Delete'
    btn.addEventListener('click', function (event) {
      $("#loadMe").modal({
        backdrop: "static", //remove ability to close modal with click
        keyboard: false, //remove option to close with keyboard
        show: true //Display loader!
      });
      var xhr = new XMLHttpRequest();
      xhr.open("DELETE", backendurl+ 'deleteSlide/' + id, true);
      xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
          $("#loadMe").modal("hide");
          // Here we go on the new page
          window.location = "edit_slider.html";
          alert("Item Deleted")
        }
      };
      xhr.send();
    }, true);
    div.appendChild(btn)
    product_btm.appendChild(h4)
    //root.appendChild(div)               
    product_btm.appendChild(btn)
  })
}
request.send();
