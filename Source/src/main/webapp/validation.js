function isValid() {
	var x = document.forms["sign-form"]["password"].value;
	var y = document.forms["sign-form"]["user"].value;
	if (!(/^\w+$/.test(y))) {
		alert("Имя может содержать лишь латинские буквы и цифры");	       
        if ((x.indexOf(' ') >= 0)) {
    		alert("Пароль не может содержать пробелы");
        }		       
        return false;
    } 
	else
	if ((x.indexOf(' ') >= 0)) {
		alert("Пароль не может содержать пробелы");	
		return false;
	}
}