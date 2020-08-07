
let stompClient = null;

function connect() {
  let socket = new SockJS('/web-socket', null);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
    stompClient.debug = () => {};
    stompClient.subscribe('/user/queue/messages', function(messageOutput) {
      processMessage(messageOutput.body);
    });
  });
}

function processMessage(message) {
  if(message === "GAME START") {
    window.location.href = '/play';
    return;
  }
  if(message === "GAME END") {
    //TODO: Display end message, and block input
    return;
  }
  console.log("PROCESSING");
  let screen = $('#screen');
  screen.text(screen.text() + '> ' + message + '\n\n');
}

function disconnect() {
  if(stompClient != null) {
    stompClient.disconnect();
  }
  console.log("Disconnected");
}

function sendMessage(msg) {
  stompClient.send("/web-socket", {}, msg);
}

connect();