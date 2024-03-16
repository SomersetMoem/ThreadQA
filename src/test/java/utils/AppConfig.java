package utils;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:project.properties")
public interface AppConfig extends Config {
    @Key("url")
    String url();
    @Key("isProd")
    Boolean isProd();
    @Key("threads")
    Integer threads();
}
