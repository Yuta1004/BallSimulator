package object;

public abstract class SimulatableObject {

    // 状態変数
    protected Pos pos;

    public SimulatableObject(double x0, double y0) {
        /**
        * オブジェクト情報の初期化
        * @param x0 初期座標(x)
        * @param y0 初期座標(y)
        */
        this.pos = new Pos(x0, y0);
    }

    public Pos getPos() {
        /**
         * 座標情報を返す
         * @return Pos
         */
        return pos;
    }

}