
 const user = JSON.parse(window.localStorage.getItem('user'));
 var nav_item_user_control = document.getElementById("nav_item_user_control");
 var nav_item_edit_slider = document.getElementById("nav_item_edit_slider");
 if(user.type.length!=null){
   if(user.type=="seller"){
     nav_item_user_control.style.display="none";
     nav_item_edit_slider.style.display="none";
   }
 }