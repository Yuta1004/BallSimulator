package object;

public abstract class SimulatableObject {

    // 状態変数
    protected Pos pos;
    protected double weight;    // [kg]

    public SimulatableObject(double x0, double y0, double weight) {
        /**
        * オブジェクト情報の初期化
        * @param x0 初期座標(x)
        * @param y0 初期座標(y)
        * @param wiehgt 質量[kg]
        */
        this.pos = new Pos(x0, y0);
        this.weight = weight;
    }

    public Pos getPos() {
        /**
         * 座標情報を返す
         * @return Pos
         */
        return pos;
    }

    /* シミュレートを1ステップ進める */
    public abstract void step();

    /* 状態のリセットを行う */
    public abstract void reset();

}