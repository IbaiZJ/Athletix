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
