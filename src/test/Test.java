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

    private void printFailedOn() {
        /**
         * エラー発生箇所を出力する
         */
        StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
        int line = ste.getLineNumber();
        String clsName = ste.getClassName();
        String mtdName = ste.getMethodName();
        System.err.println("Failed at \""+name+"\" ("+clsName+"."+mtdName+":"+line+")");
    }

    protected <T> void isEqual(T objA, T objB) {
        /**
         * 2つの値等しいか検査
         * @param objA, objB 検査対象
         */
        if(objA.equals(objB)) {
            ++ cnt[SUCCESS];
        } else {
            ++ cnt[FAILED];
            printFailedOn();
            System.err.println("\t"+objA+" is not "+objB);
        }
    }

    protected void isTrue(boolean val) {
        /**
         * 値が真かどうか検査
         * @param val 検査対象
         */
        if(val) {
            ++ cnt[SUCCESS];
        } else {
            ++ cnt[FAILED];
            printFailedOn();
            System.err.println("\t"+val+" is not true");
        }
    }

    protected void isFalse(boolean val) {
        /**
         * 値が偽かどうか検査
         * @param val 検査対象
         */
        if(!val) {
            ++ cnt[SUCCESS];
        } else {
            ++ cnt[FAILED];
            printFailedOn();
            System.err.println("\t"+val+" is not false");
        }
    }

    protected void fail(String msg) {
        /**
         * 強制的にテストを失敗させる
         * @param msg メッセージ
         */
        ++ cnt[FAILED];
        printFailedOn();
        System.err.println("\t"+msg);
    }
}