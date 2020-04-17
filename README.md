# AMS

## Packages

Client
```
<user.home>
\- /AMS
   \- /client
      \- /clients
      \- /log
   \- .user.config
```

Server
```
<user.home>
\- /AMS
   \- /server
      \- /clients
      \- /log
   \- .server.config
```

### /clients

Server mailbox
```
<TOKEN>
\- <FROM>.chat
```

Client chat
```
<TOKEN>
\- /chat
   \- block:<ZAHL>
\- .info
```

### LOG

Naming
```
dd-MM-uuuu HH-mm-ss.log

// Example
03-04-2020 11-31-22.log
```

## Protocol

### First Login Server

```
Client - Server [KEY - SERVER]

Client - send([TOKEN])
```

### Client First \[MESSAGE SEND]

```
Client1 - Client2 [KEY - CLIENT]
```

### Message Send

```
Client1 - encrypt([TOKEN]\n[INITIALS]\n[MESSAGE], [KEY - CLIENT]) -> [MESSAGE]
Client1 - encrypt([MESSAGE], [KEY - SERVER[1]]) -> [MESSAGE]
Client1  send([TOKEN]\n[MESSAGE])

Server - getToken([MESSAGE]) -> [TOKEN]
Server - getMessage([MESSAGE]) -> [TOKEN]
Server - decrypt([MESSAGE], [KEY - SERVER[1]]) -> [MESSAGE]
```

### Message Fetch

```
[Client Verifying]
Client1 - Client2 [KEY - CLIENT]

Server - encrypt([MESSAGE], [KEY - SERVER[2]]) -> [MESSAGE]
Server - send([MESSAGE])

Client2 - decrypt([MESSAGE], [KEY - SERVER[2]]) -> [MESSAGE]
Client2 - decrypt([MESSAGE], [KEY - CLIENT]) -> [MESSAGE]
```

### Client Verifying

```
Client - Server [KEY - Server]

Server - <64 Zeichen Random Text> -> [CHALLANGE]
Server - encrypt([CHALLANGE], [KEY - SERVER]) -> [CHALLANGE]
Server - send([CHALLANGE])
Client - decrypt([CHALLANGE], [KEY - SERVER]) -> [CHALLANGE]

Client - Server [KEY - TEMP]

Client - encrypt([CHALLANGE], [KEY - TEMP]) -> [CHALLANGE]
Client - send([CHALLANGE])
Server - decrypt([CHALLANGE], [KEY - TEMP]) -> [CHALLANGE]
Server - checkValid([CHALLANGE])
```

## Mailbox sync system
2 mal am tag
Netzwerk clienten ausfindig machen