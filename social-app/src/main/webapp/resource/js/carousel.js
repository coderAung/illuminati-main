$(document).ready(function () {
    $('.carousel').carousel({
        interval: 3000, // Set the time interval between slides (3000ms = 3 seconds)
        pause: 'hover', // Pause the carousel when hovering over it
        wrap: true // Loop back to the first item after the last
    });
});
