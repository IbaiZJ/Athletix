// chat.js
const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

function connect(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value.trim();
    
    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        
        // Suscribirse a la cola personal de mensajes
        stompClient.subscribe(`/user/queue/messages`, (message) => {
            showMessage(JSON.parse(message.body));
        });
        
        // Notificar que el usuario se ha unido
        const chatMessage = {
            sender: username,
            type: 'JOIN'
        };
        stompClient.send("/app/chat.addUser", {}, JSON.stringify(chatMessage));
    });
}

function sendMessage(event) {
    event.preventDefault();
    
    const messageContent = document.getElementById('message').value.trim();
    const recipient = document.getElementById('recipient').value.trim();
    
    if(messageContent && stompClient) {
        const chatMessage = {
            sender: document.getElementById('username').value.trim(),
            recipient: recipient,
            content: messageContent,
            type: 'CHAT'
        };
        
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        document.getElementById('message').value = '';
    }
}

function showMessage(message) {
    const messageElement = document.createElement('div');
    messageElement.innerHTML = `<strong>${message.sender}:</strong> ${message.content}`;
    document.getElementById('messageArea').appendChild(messageElement);
}

document.getElementById('connectForm').addEventListener('submit', connect, true);
document.getElementById('sendForm').addEventListener('submit', sendMessage, true);