document.addEventListener('DOMContentLoaded', function () {
  const toggleButton = document.querySelector('[data-collapse-toggle="navbar-cta"]');
  const menu = document.getElementById('navbar-cta');
  const header = document.getElementById('page-header');

  toggleButton.addEventListener('click', function () {
    menu.classList.toggle('hidden');
  });

  window.addEventListener('scroll', () => {
    if (window.scrollY > 0) {
      header.classList.add('shadow-md');
    } else {
      header.classList.remove('shadow-md');
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
    const toggleButton = document.querySelector('[data-dropdown-toggle="language-dropdown-menu"]');
    const dropdownMenu = document.getElementById("language-dropdown-menu");

    toggleButton.addEventListener("click", function () {
      dropdownMenu.classList.toggle("hidden");
    });

    // Cierra el menú si haces clic fuera
    document.addEventListener("click", function (event) {
      if (!toggleButton.contains(event.target) && !dropdownMenu.contains(event.target)) {
        dropdownMenu.classList.add("hidden");
      }
    });
  });
