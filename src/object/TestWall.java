package object;

import test.Test;

public class TestWall extends Test {

    public TestWall()  {
        super("TestWall");
    }

    public void test() {
        initializeTest();
    }

    /* 初期化テスト */
    private void initializeTest() {
        new Wall(0, 0, 1.0, 1.0);
        isTrue(true);
    }

}