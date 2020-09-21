package object;

public class Ball extends SimulatableObject {

    public Ball(double x0, double y0, double weight) {
        /**
         * Ballのコンストラクタ
         * @param x0 初期座標(x)
         * @param y0 初期座標(y)
         * @param weight 質量[kg]
         */
        super(x0, y0, weight);
    }

    public void step() {
        /**
         * シミュレートを1ステップ進める
         */
        ++ pos.x;
        ++ pos.y;
    }

    public void reset() {
        /**
         * 状態をリセットする
         */
        pos.reset();
    }

    /* 未実装メソッド */
    public void giveVelocity(double vx, double vy) {}
    public void force(double fx, double fy) {}

}