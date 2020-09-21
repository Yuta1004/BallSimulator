package object;

import test.Test;

public class TestBall extends Test {

    public TestBall()  {
        super("TestBall");
    }

    public void test() {
        initializeTest();
    }

    /* 初期化テスト */
    private void initializeTest() {
        new Ball(0, 0);
        isTrue(true);
    }

}