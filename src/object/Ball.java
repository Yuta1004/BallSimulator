package object;

public class Ball extends SimulatableObject {

    // 状態変数
    public final double r, v0x, v0y, f0x, f0y;
    private double dvx = 0.0, dvy = 0.0, dax = 0.0, day = 0.0;  // [m]

    /**
     * Ballのコンストラクタ
     * @param x0,y0 初期座標 [m]
     * @param r 半径 [m]
     * @param weight 質量 [kg]
     * @param v0x,v0y 開始時に与える速度 [m/s]
     * @param f0x,f0y 開始時に与える力 [m/s^2]
     */
    public Ball(double x0, double y0, double r, double weight, double v0x, double v0y, double f0x, double f0y) {
        super(x0, y0, weight);
        this.r = r;
        this.v0x = v0x;
        this.v0y = v0y;
        this.f0x = f0x;
        this.f0y = f0y;
        giveVelocity(v0x, v0y);
        force(f0x, f0y);
    }

    /* 状態をリセットする */
    public void reset() {
        dvx = dvy = dax = day = 0.0;
        pos.reset();
        giveVelocity(v0x, v0y);
        force(f0x, f0y);
    }

    /** シミュレートを進める (※0.01(10^-2)s単位)
     * @param time 進める時間 [10^-2s]
     */
    public void step(int time) {
        pos.updatePos(dvx*time + 0.5*dax*time*time, dvy*time + 0.5*day*time*time, time);
        dvx += dax * time;
        dvy += day * time;
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
        day = fy / weight / 10000.0;
    }

}