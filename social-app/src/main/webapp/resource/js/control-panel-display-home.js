document.addEventListener("DOMContentLoaded", () => {
    var openedControlPanel = null;
    var clickedControlBtn = null;

    document.querySelectorAll(".control-btn")
        .forEach(btn => {
            btn.addEventListener("click", e => {
                const controlBtn = e.currentTarget;
                if (controlBtn instanceof HTMLElement) {

                    const controlPanel = controlBtn.nextElementSibling;

                    if (controlPanel.classList.contains("control-panel")) {

                        // If the clicked button is the same as the currently opened panel's button, toggle the panel
                        if (controlPanel === openedControlPanel) {
                            controlPanel.classList.toggle("open");

                            if (!controlPanel.classList.contains("open")) {
                                openedControlPanel = null;
                                clickedControlBtn = null;
                            } else {
                                openedControlPanel = controlPanel;
                                clickedControlBtn = controlBtn;
                            }
                        } else {
                            // If a different button is clicked, close the previously opened panel
                            closeControlPanel();

                            // Open the new control panel
                            controlPanel.classList.add("open");
                            openedControlPanel = controlPanel;
                            clickedControlBtn = controlBtn;
                        }
                    }
                }
            });
        });

    document.addEventListener("click", e => {
        const clickedTarget = e.target;

        if (openedControlPanel !== null && clickedControlBtn !== null) {
            if (!openedControlPanel.contains(clickedTarget) && !clickedControlBtn.contains(clickedTarget)) {
                closeControlPanel();
            }
        }
    });

    // Close the panel on scroll
    document.addEventListener("scroll", () => {
        closeControlPanel();
    });

    const closeControlPanel = () => {
        if (openedControlPanel) {
            openedControlPanel.classList.remove("open"); // Remove class to reverse the transition effect
            openedControlPanel = null;
            clickedControlBtn = null;
        }
    };
});
