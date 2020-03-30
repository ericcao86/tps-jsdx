package com.iflytek.tps.common;

import java.util.concurrent.ConcurrentHashMap;

public class CommUtils {

    private static ConcurrentHashMap mons = new ConcurrentHashMap();

    static {

        mons.put("01","01Jan");
        mons.put("02","02Feb");
        mons.put("03","03Mar");
        mons.put("04","04Apr");
        mons.put("05","05May");
        mons.put("06","06Jun");
        mons.put("07","07Jul");
        mons.put("08","08Aug");
        mons.put("09","09Sept");
        mons.put("10","10Oct");
        mons.put("11","11Nov");
        mons.put("12","12Dec");
    }

    public static ConcurrentHashMap getMons() {
        return mons;
    }
}
