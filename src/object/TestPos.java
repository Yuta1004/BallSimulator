package object;

import test.Test;

public class TestPos extends Test {

    public TestPos()  {
        super("TestPos");
    }

    public void test() {
        initializeTest();
        updatePosTest();
        changePosTest();
        resetTest();
    }

    /* 初期化テスト */
    private void initializeTest() {
        new Pos(0, 0);
        isTrue(true);
    }

    /* 値増減テスト(※誤差によるテスト失敗を防ぐために整数値での増減) */
    private void updatePosTest() {
        double changesX[] = {-78, 8, 2, -94, -38};
        double changesY[] = {77, -63, -13, 54, -52};
        double correctX[] = {22, 30, 32, -62, -100};
        double correctY[] = {177, 114, 101, 155, 103};
        Pos pos = new Pos(100, 100);
        for(int cnt = 0; cnt < 5; ++ cnt) {
            pos.updatePos(changesX[cnt], changesY[cnt]);
            isEqual(pos.x, correctX[cnt]);
            isEqual(pos.y, correctY[cnt]);
        }
    }

    /* 座標変更テスト */
    private void changePosTest() {
        Pos pos = new Pos(0, 0);
        isEqual(pos, new Pos(0, 0));
        pos.changePos(100, 100);
        isEqual(pos, new Pos(100, 100));
    }

    /* リセットテスト */
    private void resetTest() {
        Pos pos = new Pos(100, 100);
        pos.x = 200;
        pos.y = 300;
        pos.reset();
        isEqual(pos.x, 100.0);
        isEqual(pos.y, 100.0);
    }

}