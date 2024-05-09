function toggleStyleSheet() {
    //Assign Variables//
    theme = document.getElementById('colorScheme');
    glassDoor = document.getElementById('glassDoorLogo');
    handShake = document.getElementById('handshakeLogo');
    indeed = document.getElementById('indeedLogo');
    linkedIn = document.getElementById('linkedInLogo');
    zipRecuiter = document.getElementById('zipRecuiterLogo');

    //Switch to Dark Mode//
    if (theme.getAttribute("href") == "style_light.css") { 
        theme.setAttribute("href", "style_dark.css");
        glassDoor.setAttribute("src", "../images/glassDoor_darkMode.png");
        handShake.setAttribute("src", "../images/handShake_darkMode.png");
        indeed.setAttribute("src", "../images/indeed_darkMode.png");
        linkedIn.setAttribute("src", "../images/linkedIn_darkMode.png");
        zipRecuiter.setAttribute("src", "../images/zipRecruiter_darkMode.png");
    //Switch to Light Mode//
    } else { 
        theme.setAttribute("href", "style_light.css"); 
        glassDoor.setAttribute("src", "../images/glassDoor_lightMode.png");
        handShake.setAttribute("src", "../images/handShake_lightMode.png");
        indeed.setAttribute("src", "../images/indeed_lightMode.png");
        linkedIn.setAttribute("src", "../images/linkedIn_lightMode.png");
        zipRecuiter.setAttribute("src", "../images/zipRecruiter_lightMode.png");
    } 
}//ends function - toggleStyleSheet()
