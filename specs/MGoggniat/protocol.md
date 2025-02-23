# Protocol objectives: what does the protocol do?

    Communication serveur client : le serveur renvoit le résultat 
    du calcul envoyé par le client

## Overall behavior:
### What transport protocol do we use?

    TCP

### How does the client find the server (addresses and ports)?

    Connu au moment du lancement du programme

### Who speaks first?

    le client

### Who closes the connection and when?

    le client, lorsqu'il ferme l'application (et eventuellement le 
    serveur après un timeout ?)

## Messages:
### What is the syntax of the messages?

    "nombre op nombre op ...."
    sans paranthèse
    nombre entier uniquement

### What is the sequence of messages exchanged by the client and the server? (flow)

    client :
    "open"
    "*calcul*"
    "close"

    serveur :
    "connection ok"
    "*résultat*"
    "format incorrect"

### What happens when a message is received from the other party? (semantics)

    client :
    "open" -> indique au serveur que la connexion est correcte
    "*calcul*" -> retourne résulats ou erreur
    "close" -> ferme connexion

    serveur :
    "connection ok" permet les calculs
    "*résultat*" rien
    "format incorrect" rien

### Specific elements (if useful)
### Supported operations
    '+ - * /
### Error handling
    "error in pattern"

### Extensibility
    ajout d'opérations
    priorité des opérations
    paranthèses

## Examples: examples of some typical dialogs.

    C -> open
    S <- connection ok
    C -> calcule
    S <- résulat ou erreur
    ...
    C -> close