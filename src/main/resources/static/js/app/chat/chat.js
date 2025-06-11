let stompClient = null;

function connect() {
  const socket = new SockJS('/ws');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);

    stompClient.subscribe('/user/queue/messages', function (message) {
      const chatMessage = JSON.parse(message.body);
      displayMessage(chatMessage);
    });
  });
}

function sendMessage() {
  const messageInput = document.getElementById('messageInput');
  const messageContent = messageInput.value.trim();

  if (messageContent && stompClient) {
    const chatMessage = {
      content: messageContent,
      sender: username,
      recipient: recipient,
    };

    stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(chatMessage));
    messageInput.value = '';

    setTimeout(() => {
      const scrollContainer = document.getElementById('messagesContainer');
      scrollContainer.scrollTop = scrollContainer.scrollHeight;
    }, 50);
  }
}

function displayMessage(message) {
  if (
    (message.sender === currentChatUser && message.recipient === username) ||
    (message.sender === username && message.recipient === currentChatUser)
  ) {
    const messagesContainer = document.getElementById('chatMessages');
    const messageElement = document.createElement('div');
    
    const isCurrentUser = message.sender === username;
    messageElement.className = `flex w-full items-start gap-2 ${isCurrentUser ? 'justify-end' : 'justify-start'}`;

    const avatarDiv = document.createElement('div');
    avatarDiv.className = 'flex h-8 w-8 shrink-0 items-center justify-center overflow-hidden rounded-full bg-gray-200 font-semibold text-gray-500';
    
    const profileImage = isCurrentUser ? 
      (currentUserProfileImage || null) : 
      (friendProfileImage || null);
    const userInitials = isCurrentUser ? 
      (currentUserInitials || message.sender.substring(0, 1).toUpperCase()) : 
      (friendInitials || message.sender.substring(0, 1).toUpperCase());

    if (profileImage) {
      const avatarImg = document.createElement('img');
      avatarImg.src = profileImage;
      avatarImg.alt = 'Profile photo';
      avatarImg.className = 'h-full w-full object-cover';
      avatarDiv.appendChild(avatarImg);
    } else {
      const avatarSpan = document.createElement('span');
      avatarSpan.textContent = userInitials;
      avatarDiv.appendChild(avatarSpan);
    }

    const messageContentDiv = document.createElement('div');
    messageContentDiv.className = 'max-w-[75%]';

    const headerDiv = document.createElement('div');
    headerDiv.className = 'mb-1 flex items-center gap-2';
    
    const usernameSpan = document.createElement('span');
    usernameSpan.className = 'ml-2 font-bold text-gray-900';
    usernameSpan.textContent = message.sender;
    
    const timeSpan = document.createElement('span');
    timeSpan.className = 'text-sm text-gray-500';
    const now = new Date();
    timeSpan.textContent = `${now.getHours()}:${now.getMinutes().toString().padStart(2, '0')}`;
    
    headerDiv.appendChild(usernameSpan);
    headerDiv.appendChild(timeSpan);

    const messageBubble = document.createElement('p');
    messageBubble.className = `rounded-2xl bg-gray-300 p-3 break-words text-gray-900 ${
      isCurrentUser ? 'rounded-br-none bg-primary text-white' : 'rounded-tl-none'
    }`;
    messageBubble.textContent = message.content;

    messageContentDiv.appendChild(headerDiv);
    messageContentDiv.appendChild(messageBubble);
    
    messageElement.appendChild(avatarDiv);
    messageElement.appendChild(messageContentDiv);
    
    messagesContainer.appendChild(messageElement);
    messagesContainer.scrollTop = messagesContainer.scrollHeight;

    const scrollContainer = document.getElementById('messagesContainer');
  scrollContainer.scrollTop = scrollContainer.scrollHeight;
  }
}

window.onload = function () {
  connect();

  document.getElementById('sendButton').addEventListener('click', sendMessage);

  document.getElementById('messageInput').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
      sendMessage();
    }
  });

  // Hacer scroll al fondo al cargar mensajes previos
  const scrollContainer = document.getElementById('messagesContainer');
  scrollContainer.scrollTop = scrollContainer.scrollHeight;
};
