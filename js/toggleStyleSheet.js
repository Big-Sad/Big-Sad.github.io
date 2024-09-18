function toggleStyleSheet() {
    //Assign Variables//
    theme = document.getElementById('colorScheme');
    handShake = document.getElementById('handshakeLogo');
    linkedIn = document.getElementById('linkedInLogo');
    const element = document.querySelector('style');
    const stylesheet = element.sheet;

    //Switch to Dark Mode//
    if (theme.getAttribute("href") == "style_light.css") { 
        theme.setAttribute("href", "../style_dark.css");
        handShake.setAttribute("src", "../images/handShake_darkMode.png");
        linkedIn.setAttribute("src", "../images/linkedIn_darkMode.png");
        stylesheet.replaceSync('.body { background-color: #4d4d4d; }');
        //document.body.style.backgroundColor = "#4d4d4d";
        //document.body.setAttribute("background-color", "#4d4d4d");
        //document.body.style.color = "#f7f7f7";
    //Switch to Light Mode//
    } else { 
        theme.setAttribute("href", "style_light.css"); 
        handShake.setAttribute("src", "../images/handShake_lightMode.png");
        linkedIn.setAttribute("src", "../images/linkedIn_lightMode.png");
        
    } 
}//ends function - toggleStyleSheet()
