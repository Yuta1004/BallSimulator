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
        // 状況1: 初期座標(0, 0)にある静止している質量1.0kgの球に横向き10m/sの初速度を与える
        Ball ball = new Ball(0, 0, 1.0, 1.0);
        ball.giveVelocity(10.0, 0);
        ball.step(100); // 100*10ms = 1s
        isEqual(ball.getPos(), new Pos(10.0, 0.0));   // ※誤差

        // 状況2: 初期座標(0, 0)に静止している質量1.0kgの球に横向き10Nの力を与える
        ball = new Ball(0, 0, 1.0, 1.0);
        ball.force(10.0, 0);
        ball.step(100); // 100*10ms = 1s
        isEqual(ball.getPos(), new Pos(5.0, 0.0));
        ball.step(100); // 100*10ms = 1s
        isEqual(ball.getPos(), new Pos(20.0, 0.0));
        ball.step(100); // 100*10ms = 1s
        isEqual(ball.getPos(), new Pos(45.0, 0.0));

        // 状況3: 横向き10m/sで移動している質量1.0gの球が座標(0,0)に来たときに横向き10Nの力を与える
        ball = new Ball(0, 0, 1.0, 1.0);
        ball.giveVelocity(10.0, 0.0);
        ball.force(10.0, 0);
        ball.step(100); // 100*10ms = 1s
        isEqual(ball.getPos(), new Pos(15.0, 0.0));
        ball.step(100); // 100*10ms = 1s
        isEqual(ball.getPos(), new Pos(40.0, 0.0));
        ball.step(100); // 100*10ms = 1s
        isEqual(ball.getPos(), new Pos(75.0, 0.0));
    }

}