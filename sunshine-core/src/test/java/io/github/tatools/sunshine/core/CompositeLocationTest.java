package io.github.tatools.sunshine.core;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmytro Serdiuk (dmytro.serdiuk@gmail.com)
 * @since 16.03.2017
 */
public class CompositeLocationTest {
    @Test
    public void files() throws IOException {
        List<Location> locations = Arrays.asList(
                new Location.Fake(Collections.singletonList(new FsPath.Fake())),
                new Location.Fake(Collections.singletonList(new FsPath.Fake()))
        );
        MatcherAssert.assertThat(
                new CompositeLocation(locations).files(),
                Matchers.hasSize(2)
        );
    }
}