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

    abstract void test();
    /**
     * テストを記述するメソッド
     */

}