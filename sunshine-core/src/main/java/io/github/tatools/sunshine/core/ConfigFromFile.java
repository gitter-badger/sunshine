package io.github.tatools.sunshine.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Dmytro Serdiuk (dmytro.serdiuk@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ConfigFromFile implements Config {

    private final Wrapper wrapper;

    public ConfigFromFile(InputStream file) {
        this.wrapper = new Wrapper(file);
    }

    @Override
    public String attribute(String key) {
        return wrapper.get().getProperty(key);
    }

    @Override
    public boolean has(String key) {
        return wrapper.get().getProperty(key) != null;
    }

    private class Wrapper {
        final List<Properties> props = new ArrayList<>();
        final InputStream from;

        private Wrapper(InputStream from) {
            this.from = from;
        }

        Properties get() {
            if (props.isEmpty()) {
                try {
                    Properties property = new Properties();
                    property.load(from);
                    props.add(property);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return props.get(0);
        }
    }
}
