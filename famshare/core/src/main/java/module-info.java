module famshare.core {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive json.simple;
    requires transitive unirest.java;

    
    exports famshare.core;
    exports famshare.json;
}
