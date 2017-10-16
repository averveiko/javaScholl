### Разработать клиент-серверное приложение SchoolChat

* Вход в систему по логину – дополнительных проверок не надо делать;
* Историю чатов хранить не нужно;
* Сервер понимает 2 команды:
  * Отправить сообщение пользователю;
  * Получить все сообщения адресованные текущему пользователю;
* Клиент при получении сообщения просто выводит информацию: 
«user >> message_text»;
* При входе нового пользователя все участники чата получают нотификацию (сообщение от пользователя system).

**Работа чата:**

_Server_
<pre>
Starting chat server at port 1234
User Alice connected
User Bob connected
receive msg test message one to Alice
receive msg test message two to Alice
receive msg Hello Bob! to Bob
user Bob disconnected
user Alice disconnected
</pre>
_Clinet one_
<pre>
Trying connection to localhost:1234 connected
Login: <b>Alice</b>
Type 'q' for quit
'send &lt;receiver> &lt;msg>' to send message
'receive' to receive messages
<b>receive</b>
Messages for you: 
Server: User Bob connected to chat
Bob: test message one
Bob: test message two
<b>receive</b>
Messages for you: 
Server: No message for you
send Bob Hello Bob!
<b>q</b>
Disconnected
</pre>
_Client two_
<pre>
Trying connection to localhost:1234 connected
Login: <b>Bob</b>
Type 'q' for quit
'send &lt;receiver> &lt;msg>' to send message
'receive' to receive messages
<b>send Alice test message one</b>
<b>send Alice test message two</b>
<b>receive</b>
Messages for you: 
Alice: Hello Bob!
<b>q</b>
Disconnected
</pre>