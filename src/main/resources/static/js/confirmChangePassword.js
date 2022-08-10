var newPassword = document.getElementById("newPassword")
  , confirm_password = document.getElementById("confirm_password");

function validatePassword(){
  if(newPassword.value != confirm_password.value) {
    confirm_password.setCustomValidity("La contraseña no coincide");
  } else {
    confirm_password.setCustomValidity('');
  }
}

newPassword.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;