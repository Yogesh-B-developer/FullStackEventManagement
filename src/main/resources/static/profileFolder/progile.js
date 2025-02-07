import {eventData} from '../event-folder/eventData.js'
window.onload = function () {
    const dataInLocalStorage = localStorage.getItem("userData");
    const storeData = JSON.parse(dataInLocalStorage) || [];
    let nameInLocalStorage = "";
    let emailInLocalStorage = "";
    let mobileNumberInLocalStorage = "";
    let UserOrCreatorInLocalStorage = "";
    storeData.forEach((item) => {
      if (item.key === "name") {
        nameInLocalStorage = item.value;
      }
      if(item.key === "email"){
        emailInLocalStorage = item.value;
      }
      if(item.key === "mobileNumber"){
        mobileNumberInLocalStorage = item.value;
      }
      if(item.key === "UserOrCreator"){
        UserOrCreatorInLocalStorage = item.value;
      }
    });
  
    const navbarHTML = nameInLocalStorage
      ? `
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="../index.html">Event Management</a>
          </div>
          <ul class="nav navbar-nav">
            <li ><a href="../index.html">Home</a></li>
            <li><a href="/event-folder/event.html">Events</a></li>
            <li><a href="../aboutus/aboutUs.html">About Us</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="../profileFolder/profile.html">${nameInLocalStorage}</a></li>
          </ul>
        </div>`  

        
  : `

        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="index.html">Event Management</a>
          </div>
          <ul class="nav navbar-nav">
            <li ><a href="../index.html">Home</a></li>
            <li><a href="event.html">Events</a></li>
            <li><a href="../aboutus/aboutUs.html">About Us</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="Login-Folder/login.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
          </ul>
        </div>`;
  
  
  
    
        document.getElementById("nav-bar-login-signup").innerHTML = navbarHTML;

        

// appending profile details from JS

    $('.profile-section').append(

        `<div class="container py-5">         
          <div class="row">
            <div class="col-lg-4">
              <div class="card mb-4">
                <div class="card-body text-center">
                  <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                    class="rounded-circle img-fluid" style="width: 150px;">
                  <h5 class="my-3">${nameInLocalStorage}</h5>
                  <p class="text-muted mb-1">${UserOrCreatorInLocalStorage}</p>
                  <div class="d-flex justify-content-center mb-2">
                    
                  </div>
                </div>
              </div>
            
            </div>
            <div class="col-lg-8">
              <div class="card mb-4">
                <div class="card-body">
                  <div class="row">
                    <div class="col-sm-3">
                      <p class="mb-0">Full Name</p>
                    </div>
                    <div class="col-sm-9">
                      <p class="text-muted mb-0">${nameInLocalStorage}</p>
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <p class="mb-0">Email</p>
                    </div>
                    <div class="col-sm-9">
                      <p class="text-muted mb-0">${emailInLocalStorage}</p>
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <p class="mb-0">Phone</p>
                    </div>
                    <div class="col-sm-9">
                      <p class="text-muted mb-0">${mobileNumberInLocalStorage}</p>
                    </div>
                  </div>
                  <hr>
                  <hr>
                </div>
              </div>              
          </div>
        </div>
        `
    )
    //for logout button

// User of creator page
    if(UserOrCreatorInLocalStorage === "User"){



      
      let eventRegistered = localStorage.getItem("userRegisteredEventId");
      let data = JSON.parse(eventRegistered) ||[];
      console.log(data)
      let x=eventData.find((item)=>{
        if(item.id === data[0].key){
          return item;
        }
      })
      console.log('data'+data[0].idOfEvent);
      if(data.length > 0){
      let eventRegisteredByUser = eventData.map(x=>{
        if(data[0].key === 'idOfEvent'){
          return [x.eventTitle,x.eventDate]
        }
      })
      
        $('.profile-section').append(
            `<div class="userCreator">
            <h1>Events Registered by you</h1>
            <div>
            <h4>${eventRegisteredByUser[0]}</h4>
            </div>
            </div>`
        )
    }
  }
  };

