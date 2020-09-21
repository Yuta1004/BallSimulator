package simulator;

import java.util.HashMap;

import object.SimulatableObject;

public class Simulator {

    // オブジェクト管理用
    private int autoIncID = 0;
    private HashMap<String, SimulatableObject> objectList;

    /* コンストラクタ */
    public Simulator() {
        objectList = new HashMap<String, SimulatableObject>();
    }

    /**
     * オブジェクト追加(ID自動指定)
     * @param obj 追加オブジェクト
     */
    public void addObject(SimulatableObject obj) {
        addObject(""+autoIncID, obj);
    }

    /**
     * オブジェクト追加(ID任意指定)
     * @param id ID
     * @param obj 追加オブジェクト
     */
    public void addObject(String id, SimulatableObject obj) {
        ++ autoIncID;
        objectList.put(id, obj);
    }

}