package com.github.queueflow.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:environment.properties"
})
public interface Environment extends Config {
    @Key("base.uri")
    String baseUri();
}
