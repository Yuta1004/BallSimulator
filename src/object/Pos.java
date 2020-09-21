package object;

/* データクラス */
public class Pos {

    // 座標用変数
    public double x, y;         // [m]
    public final double x0, y0; // [m]

    /* Posのコンストラクタ */
    public Pos(double x, double y) {
        this.x = x;
        this.y = y;
        this.x0 = x;
        this.y0 = y;
    }

    /* 座標をリセットする */
    public void reset() {
        x = x0;
        y = y0;
    }

    /* 比較用 */
    public boolean equals(Object pos) {
        if(!(pos instanceof Pos)) return false;
        Pos posn = (Pos)pos;
        return x == posn.x && y == posn.y;
    }
}