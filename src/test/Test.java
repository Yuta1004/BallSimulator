package test;

public abstract class Test {

    public final String name;

    public Test(String name) {
        /**
         * Testのコンストラクタ
         * @param name テスト名
         */
        this.name = name;
    }

    /* テストを記述するメソッド */
    public abstract void test();

}