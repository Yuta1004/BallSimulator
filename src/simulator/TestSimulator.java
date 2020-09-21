package simulator;

import test.Test;

public class TestSimulator extends Test {

    /* TestSimulatorのコンストラクタ */
    public TestSimulator() {
        super("TestSimulator");
    }

    public void test() {
        initialzeTest();
    }

    /* 初期化テスト */
    public void initialzeTest() {
        new Simulator();
        isTrue(true);
    }

}