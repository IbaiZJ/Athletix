document.addEventListener('DOMContentLoaded', function () {
  flatpickr('#datepicker', {
    dateFormat: 'Y-m-d',
    defaultDate: 'today',
  });

  const form = document.querySelector('form');
  const passwordInput = document.querySelector('input[name="password"]');
  const repeatPasswordInput = document.querySelector('input[type="password"]:not([name="password"])');

  const matchMsg = document.getElementById("error-message");
  matchMsg.className = 'text-sm mt-1 h-5 transition-all';
  repeatPasswordInput.insertAdjacentElement('afterend', matchMsg);

  function validatePasswords() {
    const pass = passwordInput.value;
    const repeat = repeatPasswordInput.value;

    if (!repeat) {
      matchMsg.textContent = '';
      matchMsg.className = 'text-sm mt-1 h-5';
      return;
    }

    if (pass === repeat) {
      matchMsg.textContent = '';
      matchMsg.className = '';
    } else {
      matchMsg.textContent = '❌ Passwords dont match';
      matchMsg.className = 'text-sm mt-1 h-5 text-red-600';
    }
  }

  passwordInput.addEventListener('input', validatePasswords);
  repeatPasswordInput.addEventListener('input', validatePasswords);

  form.addEventListener('submit', function (e) {
    if (passwordInput.value !== repeatPasswordInput.value) {
      e.preventDefault();
      validatePasswords();
    }
  });
});
