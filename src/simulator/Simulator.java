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
     * シミュレートを進める
     * @param time 進める時間 [10^-2s]
     */
    public void step(int time) {
        for(SimulatableObject obj: objectList.values()) {
            obj.step(time);
        }
    }

    /* リセット */
    public void reset() {
        for(SimulatableObject obj: objectList.values()) {
            obj.reset();
        }
    }

    /* オブジェクトリストを返す */
    public HashMap<String, SimulatableObject> getObjectList() {
        return objectList;
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

    /**
     * オブジェクト削除
     * @param id ID
     */
    public void removeObject(String id) {
        if(objectList.get(id) == null) { return; }
        objectList.remove(id);
    }

}