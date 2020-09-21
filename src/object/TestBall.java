package object;

import test.Test;

public class TestBall extends Test {

    public TestBall()  {
        super("TestBall");
    }

    public void test() {
        initializeTest();
        stepTest();
    }

    /* 初期化テスト */
    private void initializeTest() {
        new Ball(0, 0, 1.0);
        isTrue(true);
    }

    /* stepのテスト */
    private void stepTest() {
        Ball ball = new Ball(0, 0, 1.0);
        for(int cnt = 0; cnt < 10; ++ cnt) {
            ball.step();
            isEqual(ball.getPos(), new Pos(cnt+1, cnt+1));
        }
    }

}