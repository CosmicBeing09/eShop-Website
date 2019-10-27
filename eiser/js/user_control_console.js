'use strict'
// const backendurl = 'http://localhost:8181/';
// const frontendurl = '';

const backendurl = 'http://149.28.154.237:81/';
const frontendurl = 'http://149.28.154.237:82/';


//const user = JSON.parse(window.localStorage.getItem('user'));
var request = new XMLHttpRequest();
var formData = new FormData();
request.open('GET', backendurl + 'alluser', false);
request.onload = function () {
  var data = JSON.parse(this.response)
  data.forEach(temp => {
    const root = document.getElementById('root');
    const button_group_area = document.createElement('div');
    button_group_area.setAttribute('class', 'button-group-area');
    const container = document.createElement('div');
    container.setAttribute('class', 'container');
    const h4 = document.createElement('h4');
    h4.textContent = temp.shopname;
    const h5 = document.createElement('h5');
    h5.textContent = temp.name;
    const span = document.createElement('span');
    span.textContent = temp.phone;

    container.appendChild(h4);
    container.appendChild(h5);
    container.appendChild(span);

    button_group_area.appendChild(container);

    const button_status = document.createElement('button');
    if (temp.status.length != 0) {
      if (temp.status == 'inactive') {

        button_status.setAttribute('type', 'submit');
        button_status.setAttribute('class', 'genric-btn warning circle arrow');
        button_status.textContent = 'Inactive';
      }
      else if (temp.status == 'active') {
        button_status.setAttribute('type', 'submit');
        button_status.setAttribute('class', 'genric-btn info circle arrow');
        button_status.textContent = 'Activated';
      }

      button_status.addEventListener('click', function (event) {
        formData.append("user", new Blob([JSON.stringify({
          "id": temp.id,
          "status": temp.status,
        })], {
          type: "application/json",
          processData: false, contentType: false, cache: false
        }));
        var xhr = new XMLHttpRequest();
        xhr.open("PUT", backendurl + 'updateAccountStatus/', false);

        $("#loadMe").modal({
          backdrop: "static", //remove ability to close modal with click
          keyboard: false, //remove option to close with keyboard
          show: true //Display loader!
        });
        xhr.onreadystatechange = function () {
          if (this.readyState == 4 && this.status == 200) {
            $("#loadMe").modal("hide");
            window.location = "user_control_console.html";
            alert("User Status Changed")
          }
        };
        xhr.send(formData);
      }, true);
      button_group_area.appendChild(button_status);
    }

    const button_delete = document.createElement('button');
    button_delete.setAttribute('type', 'submit');
    button_delete.setAttribute('class', 'genric-btn danger circle arrow');
    button_delete.textContent = 'Delete Account';

    button_delete.addEventListener('click', function (event) {

      var xhr = new XMLHttpRequest();
      xhr.open("DELETE", backendurl + 'deleteUser/' + temp.phone, false);
      $("#loadMe").modal({
        backdrop: "static", //remove ability to close modal with click
        keyboard: false, //remove option to close with keyboard
        show: true //Display loader!
      });
      xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
          $("#loadMe").modal("hide");
          // Here we go on the new page
          window.location = "user_control_console.html";
          alert("User Account Deleted")
        }
      };
      xhr.send();
    }, true);

    button_group_area.appendChild(button_delete);

    root.appendChild(button_group_area);


  })
}
request.send();
