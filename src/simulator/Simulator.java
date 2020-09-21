package simulator;

import java.util.HashMap;

import object.SimulatableObject;

public abstract class Simulator {

    // オブジェクト管理用
    private HashMap<String, SimulatableObject> objectList;

    /* コンストラクタ */
    public Simulator() {
        objectList = new HashMap<String, SimulatableObject>();
    }

}