$(function(){
    $('#signup-button').on('click',function(event){
        //flags for checking all input is correct
        let nameFlag = false;
        let emailFlag = false;
        let passwordFlag = false;
        let confirmFlag = false;
        let mobileFlag = false;
        let userOrCreaterFlag = false;

        // getting all the values from the signup page
        let fullName = $('#fullName').val();
        let email = $('#email').val();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        let password = $('#password').val();
        let capPasswordTest = /[A-Z]/
        let numPasswordTest = /[0-9]/
        let confirmPassword = $('#confirm-password').val();
        let mobileNumber = $('#mobileNumber').val();        
        let userOrCreater = $('#user-creater').val();
        console.log(userOrCreater);
        $('#select-user-creater').text="";
        //checking For Name corection
        if(fullName.length<1){
            $('#fullNameStatus').text('Name cannot be empty').css({"color":"red"})
			event.preventDefault();
        }
        /*else {
            nameFlag=true;
        }*/
        //checking For email corection
        if(email.length<1){
            $('#emailCheck').text("Email cannot be empty").css({"color":"red"});
			event.preventDefault();
        }
        else if(!emailRegex.test(email)){
            $('#emailCheck').text("Email is not valid").css({"color":"red"});
			event.preventDefault();
        }
        /*else{
            emailFlag=true;
        }*/

        //checking For Password corection
        if(password.length<1){
        $('#password-check').text("Password cannot be empty").css({"color":"red"});
		event.preventDefault();
        }
		
        else if(password.length<=6){
            $('#password-check').text("Password length must be minimum of 6").css({"color":"red"});
			event.preventDefault();
        }
       else if(!capPasswordTest.test(password)){
        $('#password-check').text("Password must contain a capital letter").css({"color":"red"});
		event.preventDefault();
       }
       else if(!numPasswordTest.test(password)){
        $('#password-check').text("Password must contain number").css({"color":"red"});
		event.preventDefault();
       }
       /*else{
        passwordFlag = true;
       }*/


       //checking For confirmPassword corection
       if(password!==confirmPassword){
        $("#confirm-password-check").text("Passwords do not match").css({"color":"red"})
		event.preventDefault();
       }
       /*else{
        confirmFlag = true;
       }*/

       //checking For mobileNumber corection
       if(mobileNumber.length<1){
        $('#mobile-number-check').text("mobileNumber cannot be empty").css({"color":"red"});
		event.preventDefault();
       }
       else if(mobileNumber.length>10||mobileNumber.length<10){
        $('#mobile-number-check').text("mobileNumber can only be 10 numbers");
		event.preventDefault();
       }
       /*else{
        mobileFlag = true;
       }*/


       //check for selection of creator or user
       if(userOrCreater==='Select' || userOrCreater===''){
        $('#option-check').text("select an option as user or creator");
		event.preventDefault();
       }
       /*else{
         userOrCreaterFlag = true;
       }*/
       



       /*if(nameFlag && emailFlag && passwordFlag && confirmFlag && mobileFlag && userOrCreaterFlag){
        let dataToBeStoredInLocalStorage=[];
        dataToBeStoredInLocalStorage.push({key:"name",value:fullName});
        dataToBeStoredInLocalStorage.push({key:"email",value:email});
        dataToBeStoredInLocalStorage.push({key:"mobileNumber",value:mobileNumber});
        dataToBeStoredInLocalStorage.push({key:'Password',value:password});  
        dataToBeStoredInLocalStorage.push({key:"UserOrCreator",value:userOrCreater});
        dataToBeStoredInLocalStorage.push({key:"login",value:false});
        localStorage.setItem("userData",JSON.stringify(dataToBeStoredInLocalStorage));
        console.log(dataToBeStoredInLocalStorage);
        window.location.href="../Login-Folder/login.html"
       }*/
    })
})