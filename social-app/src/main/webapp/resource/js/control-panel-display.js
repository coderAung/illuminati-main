document.addEventListener("DOMContentLoaded", () => {
    const controlBtn = document.getElementById("control-btn");
    const controlPanel = document.querySelector(".control-panel");

    // Function to open the control panel
    controlBtn.addEventListener("click", (event) => {
        event.stopPropagation(); // Prevent event from bubbling up to the document
        controlPanel.classList.toggle("open"); // Toggle class to apply the transition effect
    });

    // Function to close the control panel
    const closePanel = () => {
        if (controlPanel.classList.contains("open")) {
            controlPanel.classList.remove("open"); // Remove class to reverse the transition effect
        }
    };

    // Close the panel when clicking anywhere on the document
    document.addEventListener("click", (event) => {
        if (!controlPanel.contains(event.target) && event.target !== controlBtn) {
            closePanel();
        }
    });

    // Close the panel on scroll
    document.addEventListener("scroll", () => {
        closePanel();
    });
});
