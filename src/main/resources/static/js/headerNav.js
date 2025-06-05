document.addEventListener('DOMContentLoaded', function () {
  // Código para el menú de navegación
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

  // Código para el selector de idioma
  const languageButtons = document.querySelectorAll('[data-dropdown-toggle="language-dropdown-menu"]');
  const dropdownMenu = document.getElementById("language-dropdown-menu");
  
  // Función para mostrar/ocultar el menú desplegable
  function toggleDropdown() {
    dropdownMenu.classList.toggle("hidden");
  }

  // Agregar evento de clic a todos los botones de idioma
  languageButtons.forEach(button => {
    button.addEventListener("click", function(event) {
      event.stopPropagation();
      toggleDropdown();
    });
  });

  // Cerrar el menú si se hace clic fuera
  document.addEventListener("click", function (event) {
    const isClickInsideDropdown = Array.from(languageButtons).some(button => button.contains(event.target));
    if (!isClickInsideDropdown && !dropdownMenu.contains(event.target)) {
      dropdownMenu.classList.add("hidden");
    }
  });
  
  // Ejecutar al cargar la página
  setActiveLanguageButton();
});