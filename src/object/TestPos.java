package object;

import test.Test;

public class TestPos extends Test {

    public TestPos()  {
        super("TestPos");
    }

    public void test() {
        initializeTest();
        changeValTest();
    }

    /* 初期化テスト */
    private void initializeTest() {
        new Pos(0, 0);
        isTrue(true);
    }

    /* 値増減テスト(※誤差によるテスト失敗を防ぐために整数値での増減) */
    private void changeValTest() {
        double changesX[] = {-78, 8, 2, -94, -38};
        double changesY[] = {77, -63, -13, 54, -52};
        double correctX[] = {22, 30, 32, -62, -100};
        double correctY[] = {177, 114, 101, 155, 103};
        Pos pos = new Pos(100, 100);
        for(int cnt = 0; cnt < 5; ++ cnt) {
            pos.x += changesX[cnt];
            pos.y += changesY[cnt];
            isEqual(pos.x, correctX[cnt]);
            isEqual(pos.y, correctY[cnt]);
        }
    }

}