package simulator;

import java.util.ArrayList;

import object.SimulatableObject;

public abstract class Simulator {

    // オブジェクト管理用
    private ArrayList<SimulatableObject> objectList;

    /* コンストラクタ */
    public Simulator() {
        objectList = new ArrayList<SimulatableObject>();
    }

}