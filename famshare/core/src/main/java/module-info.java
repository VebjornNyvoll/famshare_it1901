module famshare.core {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires java.net.http;
    requires famshare.json.simple;
    

    exports famshare.core;
    exports famshare.jsoncore;
}
