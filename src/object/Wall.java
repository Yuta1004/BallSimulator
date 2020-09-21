package object;

public class Wall extends SimulatableObject {

    // 状態変数
    public final double width, height;  // [m]

    /**
     * Ballのコンストラクタ
     * @param x0 初期座標(x) [m]
     * @param y0 初期座標(y) [m]
     * @param width 横幅 [m]
     * @param height 縦幅 [m]
     * @param weight 質量 [kg]
     */
    public Wall(double x0, double y0, double width, double height, double weight) {
        super(x0, y0, weight);
        this.width = width;
        this.height = height;
    }

    /* 操作必要なし */
    public void step() {}
    public void reset() {}
    public void giveVelocity(double vx, double vy) {}
    public void force(double fx, double fy) {}

}