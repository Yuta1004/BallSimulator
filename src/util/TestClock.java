package util;

import test.Test;

public class TestClock extends Test{

    public TestClock() {
        super("TestClock");
    }

    public void test() {
        initializeTest();
        setTimeTest();
        tickTest();
    }

    // 初期化テスト
    private void initializeTest() {
        new Clock(0, 0, 0, 0);
        isTrue(true);
    }

    // 時刻設定テスト
    private void setTimeTest() {
        Clock clock = new Clock(0, 0, 0, 0);
        isEqual(clock.toString(), "00:00:00.000");
        clock.set(23, 59, 59, 999);
        isEqual(clock.toString(), "23:59:59.999");
        clock.set(1, 23, 45, 7);
        isEqual(clock.toString(), "01:23:45.007");
    }

    // 時刻増減テスト
    private void tickTest() {
        // 増
        Clock clock = new Clock(23, 59, 59, 0);
        isEqual(clock.toString(), "23:59:59.000");
        clock.tick(999);
        isEqual(clock.toString(), "23:59:59.999");
        clock.tick(1);
        isEqual(clock.toString(), "00:00:00.000");
        clock.tick(124);
        isEqual(clock.toString(), "00:00:00.124");
        clock.tick(1102);
        isEqual(clock.toString(), "00:00:01.226");

        // 減
        clock = new Clock(0, 0, 0, 999);
        isEqual(clock.toString(), "00:00:00.999");
        clock.tick(-999);
        isEqual(clock.toString(), "00:00:00.000");
        clock.tick(-1);
        isEqual(clock.toString(), "23:59:59.999");
        clock.tick(-124);
        isEqual(clock.toString(), "23:59:59.875");
        clock.tick(-1102);
        isEqual(clock.toString(), "23:59:58.773");
    }

}