document.addEventListener('DOMContentLoaded', () => {
  const sidebar = document.getElementById('sidebar');
  const toggleButton = document.getElementById('toggleSidebar');
  const mainSection = document.getElementById('main');
  const notificationsButton = document.getElementById('notificationsButton');
  const notificationsDropDown = document.getElementById('notificationsDropDown');
  const userHeaderButton = document.getElementById('userHeaderButton');
  const userDropDown = document.getElementById('userDropDown');
  const backgroundFilter = 'blur-xs';

  const isSidebarOpen = () => !sidebar.classList.contains('-translate-x-full');

  function toggleSidebar() {
    if (isSidebarOpen()) {
      toggleButton.innerHTML =
        '<img src="/svg/app/header/sidebarX.svg" alt="Notifications" class="h-8 w-8 bg-gray-200 rounded-md" />';
      mainSection.classList.add(backgroundFilter);
    } else {
      toggleButton.innerHTML = '<img src="/svg/app/header/sidebarButton.svg" alt="Notifications" class="h-8 w-8" />';
      mainSection.classList.remove(backgroundFilter);
    }
  }

  toggleButton.addEventListener('click', () => {
    sidebar.classList.toggle('-translate-x-full');
    toggleSidebar();
  });

  mainSection.addEventListener('click', () => {
    if (isSidebarOpen()) {
      sidebar.classList.add('-translate-x-full');
    }
    toggleSidebar();
  });

  window.addEventListener('resize', () => {
    if (window.innerWidth < 768 && isSidebarOpen()) {
      sidebar.classList.add('-translate-x-full');
    }
    toggleSidebar();
  });

  if (window.innerWidth < 768) {
    sidebar.classList.add('-translate-x-full');
    toggleSidebar();
  }

  notificationsButton.addEventListener('click', (e) => {
    e.preventDefault();
    const isVisible = !notificationsDropDown.classList.contains('hidden');

    if (isVisible) {
      notificationsDropDown.classList.add('hidden');
      notificationsButton.blur();
    } else {
      notificationsDropDown.classList.remove('hidden');
      notificationsButton.focus();
    }
  });

  notificationsButton.addEventListener('blur', () => {
    notificationsDropDown.classList.add('hidden');
  });

  userHeaderButton.addEventListener('click', (e) => {
    e.preventDefault();
    const isVisible = !userDropDown.classList.contains('hidden');

    if (isVisible) {
      userDropDown.classList.add('hidden');
      userHeaderButton.blur();
    } else {
      userDropDown.classList.remove('hidden');
      userHeaderButton.focus();
    }
  });

  userHeaderButton.addEventListener('blur', () => {
    userDropDown.classList.add('hidden');
  });
});
