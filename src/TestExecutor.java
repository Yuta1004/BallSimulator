package src;

import java.util.ArrayList;

import test.Test;
import test.TestSample;

class TestExecutor {

    public static void main(String args[]) {
        Test allTests[] = {
            new TestSample()
        };
        for(Test test: allTests) {
            test.test();
        }
    }

}