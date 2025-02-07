

// code for changing images in the card of home page

            

$(document).ready(function() {
    const musicConcertImages = ['img_1.jfif', 'img_2.jfif', 'img_3.jfif', 'img_4.jfif'];
    const foodEventImages = ["img-1.jfif", "img-2.jfif", "img-3.webp", "img-4.jfif"];
    const adventureEventImages = ["img-1.jfif", "img-2.jfif", "img-3.jfif", "img-4.jfif"];
    const artandSoulImages = ["img-1.jfif", "img-2.jfif", "img-3.jfif", "img-4.jfif"];
    const techFestImages = ["img-1.jfif", "img-2.jfif", "img-3.jfif", "img-4.jfif"];
    let currentIndex = 1; 
  
    function showImage() {
      $('#music-concert-image').attr('src', "images/musicConcertImages/" + musicConcertImages[currentIndex]);
      $('#food-img').attr('src', "images/food-fiestaImages/" + foodEventImages[currentIndex]);
      $('#adventure-image').attr('src', "images/adventure-quest-images/" + adventureEventImages[currentIndex]);
      $('#art-soul').attr('src',"images/art-and-soulImages/" + artandSoulImages[currentIndex]) ;
      $('#tech-fest-img').attr('src',"images/techfest-images/" + techFestImages[currentIndex]);
  
      currentIndex = (currentIndex + 1) % musicConcertImages.length;
    }
  
    // Initial image display
  
    // Example: Trigger image change every 3 seconds
    setInterval(showImage, 3000); 
  });


  // changing nav-bar after sign-up and login
  /*window.onload = function () {
    const dataInLocalStorage = localStorage.getItem("userData");
    const storeData = JSON.parse(dataInLocalStorage) || [];
    let nameInLocalStorage = "";

    storeData.forEach((item) => {
      if (item.key === "name") {
        nameInLocalStorage = item.value;
      }
    });

  
     
    const navbarHTML = (nameInLocalStorage )
      ? `
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand " href="/" >Event Management</a>
          </div>
          <ul class="nav navbar-nav">
            <li class="active " ><a href="/">Home</a></li>
            <li><a href="/event-folder/event.html">Events</a></li>
            <li><a href="/aboutUs/aboutUs.html">About Us</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="../profileFolder/profile.html" >${nameInLocalStorage}</a></li>
          </ul>
        </div>`
      : `
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="/">Event Management</a>
          </div>
          <ul class="nav navbar-nav">
            <li class="active"><a href="/">Home</a></li>
            <li><a href="../event-folder/event.html">Events</a></li>
            <li><a href="aboutus/aboutUs.html">About Us</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
          </ul>
        </div>`;
  
    document.getElementById("nav-bar-login-signup").innerHTML = navbarHTML;
  };*/