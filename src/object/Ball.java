package object;

public class Ball extends SimulatableObject {

    public Ball(double x0, double y0) {
        /**
         * Ballのコンストラクタ
         * @param x0 初期座標(x)
         * @param y0 初期座標(y)
         */
        super(x0, y0);
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

}