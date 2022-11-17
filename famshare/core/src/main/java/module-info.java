module famshare.core {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive json.simple;
    requires transitive unirest.java;
    requires transitive com.google.gson;

    
    exports famshare.core;
    exports famshare.json;
    opens famshare.core to com.google.gson;
}
