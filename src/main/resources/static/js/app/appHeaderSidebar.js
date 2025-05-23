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
        '<img src="/svg/app/header/sidebarX.svg" alt="Notifications" class="h-8 w-8 bg-primary-hover rounded-md" />';
      mainSection.classList.add(backgroundFilter);
    } else {
      toggleButton.innerHTML =
        '<img src="/svg/app/header/sidebarButton.svg" alt="Notifications" class="h-8 w-8" />';
      mainSection.classList.remove(backgroundFilter);
    }
  }

  toggleButton.addEventListener('click', () => {
    sidebar.classList.toggle('-translate-x-full');
    toggleSidebar();
  });

  mainSection.addEventListener('click', () => {
    if (isSidebarOpen() && window.innerWidth < 768) {
      sidebar.classList.add('-translate-x-full');
      toggleSidebar();
    }
  });

  function updateSidebarPosition() {
    if (window.innerWidth < 768) {
      sidebar.classList.remove('sticky');
      sidebar.classList.add('absolute');
    } else {
      sidebar.classList.remove('absolute');
      sidebar.classList.add('sticky');
    }
  }

  window.addEventListener('resize', () => {
    if (window.innerWidth < 768 && isSidebarOpen()) {
      sidebar.classList.add('-translate-x-full');
      toggleSidebar();
    } else if (window.innerWidth >= 768) {
      sidebar.classList.remove('-translate-x-full');
      mainSection.classList.remove(backgroundFilter);
    }
    updateSidebarPosition();
  });

  if (window.innerWidth < 768) {
    sidebar.classList.add('-translate-x-full');
    toggleSidebar();
  }

  notificationsButton.addEventListener('click', (e) => {
    e.preventDefault();
    e.stopPropagation();
    const isOpen = !notificationsDropDown.classList.contains('hidden');

    notificationsDropDown.classList.add('hidden');
    userDropDown.classList.add('hidden');

    if (!isOpen) {
      notificationsDropDown.classList.remove('hidden');
    }
  });

  userHeaderButton.addEventListener('click', (e) => {
    e.preventDefault();
    e.stopPropagation();
    const isOpen = !userDropDown.classList.contains('hidden');

    userDropDown.classList.add('hidden');
    notificationsDropDown.classList.add('hidden');

    if (!isOpen) {
      userDropDown.classList.remove('hidden');
    }
  });

  document.addEventListener('click', (e) => {
    if (
      !notificationsDropDown.contains(e.target) &&
      !notificationsButton.contains(e.target)
    ) {
      notificationsDropDown.classList.add('hidden');
    }

    if (
      !userDropDown.contains(e.target) &&
      !userHeaderButton.contains(e.target)
    ) {
      userDropDown.classList.add('hidden');
    }
  });

  // Initial position setup
  updateSidebarPosition();
});
