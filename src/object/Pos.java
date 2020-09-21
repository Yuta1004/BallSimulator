package object;

/* データクラス */
public class Pos {

    // 座標用変数
    public double x, y;
    public final double x0, y0;

    public Pos(double x, double y) {
        /**
         * Posのコンストラクタ
         */
        this.x = x;
        this.y = y;
        this.x0 = x;
        this.y0 = y;
    }

    public void reset() {
        /**
         * 座標をリセットする
         */
        x = x0;
        y = y0;
    }
}