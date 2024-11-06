var stompClient = null;
var sock = null;

function clickWS(){
    console.log("Index page is ready");
    connect();
}

function connect(usrId) {
    // let sessionId = Math.floor(Math.random() * 99999999);
    let sessionId = Math.floor(Math.random() * 99999999);
    // sock = new SockJS('/our-websocket', [], {
    sock = new SockJS('/rey-websocket', [], {
        sessionId: () => {
            return sessionId
        //     781311
        }
    });
    stompClient = Stomp.over(sock);
    stompClient.connect({}, function (frame) {
        console.log('Connected sessionID : ------'+sessionId);
        setId(sessionId);
        stompClient.subscribe('/user/topic/private-messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });
    });
}

function dc(){
    sock.close();
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function setId(sessionIdz){
    $("#label-session").append(sessionIdz);
}