package test;

public abstract class Test {

    // テスト情報
    public final String name;

    // テスト結果
    public static int[] cnt = {0, 0};
    public static final int SUCCESS = 0;
    public static final int FAILED = 1;

    /**
     * Testのコンストラクタ
     * @param name テスト名
     */
    public Test(String name) {
        this.name = name;
    }

    /* テストを記述するメソッド */
    public abstract void test();

    /* エラー発生箇所を出力する */
    private void printFailedOn() {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
        int line = ste.getLineNumber();
        String clsName = ste.getClassName();
        String mtdName = ste.getMethodName();
        System.err.println("Failed at \""+name+"\" ("+clsName+"."+mtdName+":"+line+")");
    }

    /**
     * 2つの値が等しいか検査
     * @param objA, objB 検査対象
     */
    protected <T> void isEqual(T objA, T objB) {
        if(objA.equals(objB)) {
            ++ cnt[SUCCESS];
        } else {
            ++ cnt[FAILED];
            printFailedOn();
            System.err.println("\t"+objA+" is not "+objB);
        }
    }

    /**
     * 値が真かどうか検査
     * @param val 検査対象
     */
    protected void isTrue(boolean val) {
        if(val) {
            ++ cnt[SUCCESS];
        } else {
            ++ cnt[FAILED];
            printFailedOn();
            System.err.println("\t"+val+" is not true");
        }
    }

    /**
     * 値が偽かどうか検査
     * @param val 検査対象
     */
    protected void isFalse(boolean val) {
        if(!val) {
            ++ cnt[SUCCESS];
        } else {
            ++ cnt[FAILED];
            printFailedOn();
            System.err.println("\t"+val+" is not false");
        }
    }

    /**
     * 強制的にテストを失敗させる
     * @param msg メッセージ
     */
    protected void fail(String msg) {
        ++ cnt[FAILED];
        printFailedOn();
        System.err.println("\t"+msg);
    }
}