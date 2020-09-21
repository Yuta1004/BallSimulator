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
        new Ball(0, 0, 1.0, 1.0);
        isTrue(true);
    }

    /* stepのテスト */
    private void stepTest() {
        // 初期座標(0, 0)にある質量1.0kg、半径1.0mの球に横向き10m/sの速度を与える
        Ball ball = new Ball(0, 0, 1.0, 1.0);
        ball.giveVelocity(10.0, 0);
        for(int cnt = 0; cnt < 1*100; ++ cnt) {
            ball.step();
        }
        isEqual(ball.getPos(), new Pos(9.99999999999998, 0));   // ※誤差
    }

}