# Classic4J

Java implementation of the ClassiCube and BetaCraft protocol

## Use in Gradle

If you want to depend on Classic4J in your own project, use the Maven repository here:

https://mvnrepository.com/artifact/de.florianreuth/classic4j

or

https://maven.florianreuth.de/#/snapshots/de/florianreuth/classic4j (for snapshots)

The repository page includes the latest coordinates and setup instructions.

Jar builds can be downloaded from my build server: https://build.florianreuth.de/job/Classic4J/

## Requirements

- [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1)
- Java **17**.

## Terminology

The two main classes of the library are **BetaCraftHandler** and **ClassiCubeHandler**, where you can get API requests
for the respective platforms.

All API Requests are asynchronous and require a callback, the callback is called on the main thread, so you can safely
modify the UI in the callback. They are located in the **de.florianreuth.classic4j.request** package.

All Models are located in the **de.florianreuth.classic4j.model** and **de.florianreuth.classic4j.api** package.

The internal API is located in the **de.florianreuth.classic4j.util** package.

**You can either use the high-level frontend for API requests using the Handler classes or use the low-level backend
using the Request classes.**

## API Usage

### BetaCraft

Classic4J allows you to dump the server list from https://betacraft.uk/ and generate an MP Pass from the BetaCraft
launcher, keep in mind that for the MP Pass generator you need to implement the ExternalInterface from above

```java
BetaCraftHandler.requestServerList(serverList -> {
    System.out.println(serverList.servers().size());
    System.out.println(serverList.serversOfVersion(BCVersion.ALPHA).size());
    System.out.println(serverList.serversWithOnlineMode(false)); // offline mode
});

// You can authenticate to a BetaCraft server by doing:
BetaCrafthandler.authenticate(serverId -> {
    // You have to call the joinServer Statement in here     
});
```

### ClassiCube

Classic4J allows you to authenticate with ClassiCube and retrieve the server list

```java
final CCAccount account = new CCAccount("<username>", "<password>");
ClassiCubeHandler.requestAuthentication(account, null, new LoginProcessHandler() {
    @Override
    public void handleMfa(CCAccount account) {
        // Called when the account requires to be verified via MFA
        // If this is the case, you can call the authenticate method again and specify the MFA code instead of null
    }

    @Override
    public void handleSuccessfulLogin(CCAccount account) {
        // Called when the login was successfully
    }

    @Override
    public void handleException(Throwable throwable) {
        // Called when something went wrong
    }
});
```

Once you are authenticated, you can then dump the server list like BetaCraft, other API requests like searching are also
implemented

```java
ClassiCubeHandler.requestServerList(account, serverList -> {
    System.out.println(serverList.servers().size());
});
```

## Contact

- Issues: https://github.com/florianreuth/Classic4J/issues
- Discord: https://florianreuth.de/discord
