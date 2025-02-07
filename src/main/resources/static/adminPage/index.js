

// code for changing images in the card of home page

            


    // Initial image display
  
    // Example: Trigger image change every 3 seconds
    


  // changing nav-bar after sign-up and login
  window.onload = function () {
    const dataInLocalStorage = localStorage.getItem("userData");
    const storeData = JSON.parse(dataInLocalStorage) || [];
    let nameInLocalStorage = "";

    storeData.forEach((item) => {
      if (item.key === "name") {
        nameInLocalStorage = item.value;
      }
    });

  
     
    
  };