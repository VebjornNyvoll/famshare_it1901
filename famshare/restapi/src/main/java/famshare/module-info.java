module famshare.apiservice {
    requires famshare.core;
    requires famshare.json.simple;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires spring.context;
    requires spring.webmvc;

    opens famshare.apiservice to spring.core, spring.beans, spring.context;


}
