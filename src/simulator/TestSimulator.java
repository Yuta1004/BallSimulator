package simulator;

import test.Test;
import object.Ball;
import object.SimulatableObject;

public class TestSimulator extends Test {

    /* TestSimulatorのコンストラクタ */
    public TestSimulator() {
        super("TestSimulator");
    }

    public void test() {
        initialzeTest();
        addObjectTest();
        removeObjectTest();
    }

    /* 初期化テスト */
    public void initialzeTest() {
        new Simulator();
        isTrue(true);
    }

    /* オブジェクト追加テスト */
    public void addObjectTest() {
        Simulator simulator = new Simulator();
        SimulatableObject ball = new Ball(2.0, 2.0, 1.0, 1.0);
        simulator.addObject("test", ball);
        isTrue(true);
    }

    /* オブジェクト削除テスト */
    public void removeObjectTest() {
        Simulator simulator = new Simulator();
        SimulatableObject ball = new Ball(0.0, 0.0, 1.0, 1.0);
        simulator.addObject("id", ball);
        simulator.removeObject("aaa");
        simulator.removeObject("0");
        isTrue(true);
    }
}