package io.github.tatools.sunshine.testng;

import io.github.tatools.sunshine.core.Status;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

/**
 * The class provides an implementation of the {@link Status} of TestNG execution.
 *
 * @author Dmytro Serdiuk (dmytro.serdiuk@gmail.com)
 * @version $Id$
 * @since 0.2
 */
public final class TestNGStatus implements Status {

    private final short exit_code;
    private final List<ISuite> reports;
    private final List<Integer> counts;

    public TestNGStatus(int status, List<ISuite> suites) {
        this.exit_code = (short) status;
        this.reports = suites;
        this.counts = new ArrayList<>(3);
    }


    @Override
    public short code() {
        return this.exit_code;
    }

    @Override
    public int runCount() {
        this.calculate();
        return this.counts.get(0).intValue();
    }

    @Override
    public int failureCount() {
        this.calculate();
        return this.counts.get(1).intValue();
    }

    @Override
    public int ignoreCount() {
        this.calculate();
        return this.counts.get(2).intValue();
    }

    private void calculate() {
        if (!this.counts.isEmpty()) return;
        int passed = 0;
        int failed = 0;
        int skipped = 0;

        for (ISuite suite : this.reports) {
            for (ISuiteResult result : suite.getResults().values()) {
                final ITestContext testContext = result.getTestContext();
                passed += testContext.getPassedTests().size();
                failed += testContext.getFailedTests().size();
                skipped += testContext.getSkippedTests().size();
            }
        }
        this.counts.add(0, passed + failed + skipped);
        this.counts.add(1, failed);
        this.counts.add(2, skipped);
    }
}
