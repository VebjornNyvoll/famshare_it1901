module famshare.core {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires famshare.json.simple;
    requires transitive unirest.java;

    
    exports famshare.core;
    exports famshare.json;
    opens famshare.core to com.google.gson;
}
