package object;

/* データクラス */
public class Pos {

    // 座標用変数
    private double x, y;         // [m]
    private final double x0, y0; // [m]

    // 状態変数
    private double recentVx = 0.0, recentVy = 0.0;

    /* Posのコンストラクタ */
    public Pos(double x, double y) {
        this.x = x;
        this.y = y;
        this.x0 = x;
        this.y0 = y;
    }

    /* ゲッター */
    public double getX() { return x; };
    public double getY() { return y; };
    public double getVx() { return recentVx; }
    public double getVy() { return recentVy; }

    /* 座標をリセットする */
    public void reset() {
        x = x0;
        y = y0;
    }

    /**
     * 座標を更新する
     * @param dx 変更量(x) [m]
     * @param dy 変更量(y) [m]
     * @param time 変更するのにかけた時間 (10^-2s)
     */
    public void updatePos(double dx, double dy, int time) {
        x += dx;
        y += dy;
        recentVx = dx / (time / 100.0);
        recentVy = dy / (time / 100.0);
    }

    /* 比較用 */
    public boolean equals(Object pos) {
        if(!(pos instanceof Pos)) return false;
        Pos posn = (Pos)pos;
        return x == posn.x && y == posn.y;
    }
}