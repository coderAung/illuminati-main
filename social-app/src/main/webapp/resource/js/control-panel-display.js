document.addEventListener("DOMContentLoaded", () => {
    const controlBtn = document.getElementById("control-btn");
    const controlPanel = document.querySelector(".control-panel");

    var openedCommentControlPanel = null;
    var clickedCommentControlBtn = null;

    // Function to open the control panel
    controlBtn.addEventListener("click", (event) => {
        event.stopPropagation(); // Prevent event from bubbling up to the document
        controlPanel.classList.toggle("open"); // Toggle class to apply the transition effect

        if (openedCommentControlPanel !== null && clickedCommentControlBtn !== null) {
            closeCommentControlPanel();
        }
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

        if (openedCommentControlPanel !== null && clickedCommentControlBtn !== null) {
            if (!openedCommentControlPanel.contains(event.target) &&
                !clickedCommentControlBtn.contains(event.target)) {
                closeCommentControlPanel();
            }
        }
    });

    // Close the panel on scroll
    document.addEventListener("scroll", () => {
        closePanel();
        closeCommentControlPanel();
    });

    // Comment control panel manager with event delegation
    document.addEventListener("click", (e) => {
        const commentControlBtn = e.target.closest(".comment-control-btn");

        if (commentControlBtn) {
            const commentControlPanel = commentControlBtn.nextElementSibling;

            if (commentControlPanel && commentControlPanel.classList.contains("comment-control-panel")) {
                if (commentControlPanel === openedCommentControlPanel) {
                    // Toggle the current comment control panel if it's the same button
                    commentControlPanel.classList.toggle("comment-control-open");

                    if (!commentControlPanel.classList.contains("comment-control-open")) {
                        openedCommentControlPanel = null;
                        clickedCommentControlBtn = null;
                    } else {
                        openedCommentControlPanel = commentControlPanel;
                        clickedCommentControlBtn = commentControlBtn;
                    }
                } else {
                    // Close previously opened panel and open a new one
                    closeCommentControlPanel();
                    commentControlPanel.classList.add("comment-control-open");
                    openedCommentControlPanel = commentControlPanel;
                    clickedCommentControlBtn = commentControlBtn;
                }
            }
        }
    });

    function closeCommentControlPanel() {
        if (openedCommentControlPanel !== null) {
            if (openedCommentControlPanel.classList.contains("comment-control-open")) {
                openedCommentControlPanel.classList.remove("comment-control-open"); // Remove class to reverse the transition effect
                openedCommentControlPanel = null;
                clickedCommentControlBtn = null;
            }
        }
    }
});
