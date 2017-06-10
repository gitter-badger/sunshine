package io.github.tatools.sunshine.core;


import lombok.EqualsAndHashCode;

/**
 * @author Dmytro Serdiuk (dmytro.serdiuk@gmail.com)
 * @since 21.04.2017
 */
@EqualsAndHashCode
public final class ClasspathConfig implements Config {

    private final Config config;

    public ClasspathConfig(String file) {
        config = new FileConfig(ClassLoader.getSystemResourceAsStream(file));
    }

    @Override
    public String property(String key) {
        return config.property(key);
    }

    @Override
    public boolean has(String key) {
        return config.has(key);
    }
}
