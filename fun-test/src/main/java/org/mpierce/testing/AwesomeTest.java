package org.mpierce.testing;

import org.junit5.sample.SampleTest;

final class AwesomeTest {

    @SampleTest
    void thisIsGreat() {

    }

    @SampleTest
    void notSoGreat() throws Exception {
        throw new Exception("sad face");
    }
}
