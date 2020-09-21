package object;

public abstract class SimulatableObject {

    // 状態変数
    protected final double x0, y0;
    protected double x, y;

    public SimulatableObject(double x0, double y0) {
        /**
        * オブジェクト情報の初期化
        * @param x0 初期座標(x)
        * @param y0 初期座標(y)
        */
        this.x0 = x0;
        this.y0 = y0;
        this.x = x0;
        this.y = y0;
    }

}