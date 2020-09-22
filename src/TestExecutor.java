import test.Test;
// import test.TestSample;
import util.*;
import object.*;
import simulator.*;

class TestExecutor {

    public static void main(String args[]) {
        // テスト実行
        Test allTests[] = {
            // new TestSample()
            new TestPos(),
            new TestBall(),
            new TestWall(),
            new TestSimulator(),
            new TestClock()
        };
        for(Test test: allTests) {
            test.test();
        }

        // テスト結果出力
        System.out.println("---Test Results---");
        System.out.println("> Success: "+Test.cnt[Test.SUCCESS]);
        System.out.println("> Failed : "+Test.cnt[Test.FAILED]);
        if(Test.cnt[Test.FAILED] == 0) {
            System.out.println("Congratulations!\nAll of tests are success!");
        }
    }

}