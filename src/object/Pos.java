package object;

/* データクラス */
public class Pos {

    // 座標用変数
    public double x, y;         // [m]
    public final double x0, y0; // [m]

    // 状態変数(TODO: 速度を取得できるようにする)
    private double recentDx = 0.0, recentDy = 0.0;

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

    /**
     * 座標を更新する
     * @param dx 変更量(x) [m]
     * @param dy 変更量(y) [m]
     */
    public void updatePos(double dx, double dy) {
        x += dx;
        y += dy;
        recentDx = dx;
        recentDy = dy;
    }

    /** 座標を変更する
     * @param x 変更先(x) [m]
     * @param y 変更先(y) [m]
     */
    public void changePos(double x, double y) {
        this.x = x;
        this.y = y;
        recentDx = 0.0;
        recentDy = 0.0;
    }

    /* 比較用 */
    public boolean equals(Object pos) {
        if(!(pos instanceof Pos)) return false;
        Pos posn = (Pos)pos;
        return x == posn.x && y == posn.y;
    }
}