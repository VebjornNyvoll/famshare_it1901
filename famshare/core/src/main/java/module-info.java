module famshare.core {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires famshare.json.simple;

    exports famshare.core;
    exports famshare.json;
}
