package object;

public class Ball extends SimulatableObject {

    // 状態変数
    private double dx = 0.0, dy = 0.0;  // [m]

    /**
     * Ballのコンストラクタ
     * @param x0 初期座標(x)
     * @param y0 初期座標(y)
     * @param weight 質量[kg]
     */
    public Ball(double x0, double y0, double weight) {
        super(x0, y0, weight);
    }

    /* シミュレートを1ステップ進める */
    public void step() {
        pos.x += dx;
        pos.y += dy;
    }

    /* 状態をリセットする */
    public void reset() {
        pos.reset();
    }

    /* !!未実装メソッド!! */
    public void giveVelocity(double vx, double vy) {}
    public void force(double fx, double fy) {}

}