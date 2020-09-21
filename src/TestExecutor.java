package src;

import java.util.ArrayList;

import test.Test;
import test.TestSample;

class TestExecutor {

    public static void main(String args[]) {
        // テスト実行
        Test allTests[] = {
            new TestSample()
        };
        for(Test test: allTests) {
            test.test();
        }

        // テスト結果出力
        System.out.println("---Test Results---");
        System.out.println("> Success: "+Test.cnt[Test.SUCCESS]);
        System.out.println("> Failed : "+Test.cnt[Test.FAILED]);
    }

}