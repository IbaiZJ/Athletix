document.addEventListener('DOMContentLoaded', function () {
    const toggleButton = document.querySelector('[data-collapse-toggle="navbar-cta"]');
    const menu = document.getElementById('navbar-cta');

    toggleButton.addEventListener('click', function () {
        menu.classList.toggle('hidden');
    });
});


