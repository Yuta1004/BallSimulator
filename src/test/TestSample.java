package test;

public class TestSample extends Test {

    public TestSample() {
        super("Sample-v1");
    }

    public void test() {
        isEqual(10, 10);
        isEqual(10, 20);
        isTrue(true);
        isTrue(false);
        isFalse(false);
        isFalse(true);
    }

}