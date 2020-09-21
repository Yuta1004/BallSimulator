package test;

public abstract class Test {

    // テスト情報
    public final String name;

    // テスト結果
    public static int[] cnt = {0, 0};
    public static final int SUCCESS = 0;
    public static final int FAILED = 1;


    public Test(String name) {
        /**
         * Testのコンストラクタ
         * @param name テスト名
         */
        this.name = name;
    }

    /* テストを記述するメソッド */
    public abstract void test();

    protected <T> void isEqual(T objA, T objB) {
        /**
         * 2つの値等しいか検査
         * @param objA, objB 検査対象
         */
        if(objA.equals(objB)) ++ cnt[SUCCESS];
        else                  ++ cnt[FAILED];
    }

    protected void isTrue(boolean val) {
        /**
         * 値が真かどうか検査
         * @param val 検査対象
         */
        if(val) ++ cnt[SUCCESS];
        else    ++ cnt[FAILED];
    }

    protected void isFalse(boolean val) {
        /**
         * 値が偽かどうか検査
         * @param val 検査対象
         */
        isTrue(!val);
    }

}