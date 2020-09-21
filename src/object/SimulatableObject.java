package object;

public abstract class SimulatableObject {

    // 状態変数
    protected Pos pos;
    protected double weight;    // [kg]

    /**
    * オブジェクト情報の初期化
    * @param x0 初期座標(x) [m]
    * @param y0 初期座標(y) [m]
    * @param wiehgt 質量 [kg]
    */
    public SimulatableObject(double x0, double y0, double weight) {
        this.pos = new Pos(x0, y0);
        this.weight = weight;
    }

    /**
     * 座標情報を返す
     * @return Pos
     */
    public Pos getPos() {
        return pos;
    }

    /* シミュレートを1ステップ進める */
    public abstract void step();

    /* 状態のリセットを行う */
    public abstract void reset();

    /**
     * 速度を与える
     * @param vx x方向の速度 [m/s]
     * @param vy y方向の速度 [m/s]
     */
    public abstract void giveVelocity(double vx, double vy);

    /**
     * 力を加える
     * @param fx x方向の力 [N]
     * @param fy y方向の力 [N]
     */
    public abstract void force(double fx, double fy);

}