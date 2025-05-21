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
  
  // Manejar la selección de idioma (para actualizar el botón visible)
  const languageLinks = dropdownMenu.querySelectorAll('a');
  languageLinks.forEach(link => {
    link.addEventListener('click', function(event) {
      // Obtener el código de idioma del href
      const href = this.getAttribute('href');
      const lang = href.split('=')[1]; // Extraer el código de idioma
      
      // Guardar la preferencia de idioma seleccionada (opcional)
      localStorage.setItem('selectedLanguage', lang);
      
      // No cancelamos el evento para permitir que la navegación ocurra
    });
  });
  
  // Mostrar el botón del idioma actual basado en el parámetro URL o localStorage
  function setActiveLanguageButton() {
    // Intentar obtener el idioma de la URL
    const urlParams = new URLSearchParams(window.location.search);
    let currentLang = urlParams.get('lang');
    
    // Si no hay idioma en la URL, intentar obtenerlo del localStorage
    if (!currentLang) {
      currentLang = localStorage.getItem('selectedLanguage');
    }
    
    // Si aún no hay idioma, usar el predeterminado (inglés)
    if (!currentLang) {
      currentLang = 'en';
    }
    
    // Ocultar todos los botones de idioma primero
    languageButtons.forEach(button => {
      button.classList.add('hidden');
    });
    
    // Mostrar el botón del idioma actual
    const activeButton = document.querySelector(`[data-lang="${currentLang}"]`);
    if (activeButton) {
      activeButton.classList.remove('hidden');
      activeButton.classList.add('inline-flex');
    } else {
      // Si no se encuentra un botón para el idioma actual, mostrar el inglés por defecto
      const defaultButton = document.querySelector('[data-lang="en"]');
      defaultButton.classList.remove('hidden');
      defaultButton.classList.add('inline-flex');
    }
  }
  
  // Ejecutar al cargar la página
  setActiveLanguageButton();
});