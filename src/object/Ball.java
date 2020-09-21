package object;

public class Ball extends SimulatableObject {

    // 状態変数
    public final double r;
    private double dx = 0.0, dy = 0.0;  // [m]

    /**
     * Ballのコンストラクタ
     * @param x0 初期座標(x) [m]
     * @param y0 初期座標(y) [m]
     * @param r 半径 [m]
     * @param weight 質量 [kg]
     */
    public Ball(double x0, double y0, double r, double weight) {
        super(x0, y0, weight);
        this.r = r;
    }

    /* シミュレートを1ステップ進める */
    public void step() {
        pos.updatePos(dx, dy);
    }

    /* 状態をリセットする */
    public void reset() {
        pos.reset();
    }

    /**
     * 速度を与える
     * @param vx x方向の速度 [m/s]
     * @param vy y方向の速度 [m/s]
     */
    public void giveVelocity(double vx, double vy) {
        dx = vx / 100.0;
        dy = vy / 100.0;
    }

    /* !!未実装メソッド!! */
    public void force(double fx, double fy) {}

}