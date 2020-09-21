package object;

public class Ball extends SimulatableObject {

    // 状態変数
    public final double r;
    private double dvx = 0.0, dvy = 0.0, dax = 0.0, day = 0.0;  // [m]

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

    /** シミュレートを進める (※0.01(10^-2)s単位)
     * @param time 進める時間 [10^-2s]
     */
    public void step(int time) {
        pos.updatePos(dvx*time + 0.5*dax*time*time, dvy*time + 0.5*day*time*time, time);
        dvx += dax * time;
        dvy += day * time;
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
        dvx += vx / 100.0;
        dvy += vy / 100.0;
    }

    /**
     * 力を加える
     * @param fx x方向の力 [N]
     * @param fy y方向の力 [N]
     */
    public void force(double fx, double fy) {
        // ma = f
        dax = fx / weight / 10000.0;
        day = fy / weight / 100.0;
    }

}