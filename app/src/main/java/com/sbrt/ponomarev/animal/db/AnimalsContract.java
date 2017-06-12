package com.sbrt.ponomarev.animal.db;

import android.provider.BaseColumns;

/**
 * Created by user15 on 08.06.2017.
 */

public class AnimalsContract {

    public static class Animal implements BaseColumns {

        public static final String SPECIES = "species";
        public static final String NAME = "name";
        public static final String WEIGHT = "weight";
        public static final String HEIGHT = "height";
    }
}
