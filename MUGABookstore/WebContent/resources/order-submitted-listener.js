function servletContext() {
    const sc = window.location.pathname.split( '/' );
    return "/"+sc[1];
}
const socket = new SockJS(`${servletContext()}/order-notifications`)
const stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {
    stompClient.subscribe('/order-submitted', function(messageOutput) {
        const form = document.createElement('form');
        form.method = 'post'
        document.body.appendChild(form);
        form.submit();
    });
});