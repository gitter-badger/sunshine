package io.github.tatools.sunshine.core;

import java.io.IOException;

/**
 * @author Dmytro Serdiuk (dmytro.serdiuk@gmail.com)
 * @since 19.04.2017
 */
public interface File extends FileSystemPath {
    void write(String data) throws IOException;
}
