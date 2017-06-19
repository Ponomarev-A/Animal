package com.sbrt.ponomarev.animal.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;
import com.sbrt.ponomarev.animal.db.AnimalsDAO;
import com.sbrt.ponomarev.animal.db.SQLiteAnimalDao;
import org.junit.rules.ExternalResource;

public class AnimalDaoRule extends ExternalResource {

    private static final String DATABASE_NAME = "test.db";
    private static final String TAG = AnimalDaoRule.class.getSimpleName();

    private SQLiteAnimalDao dao;
    private Context context = InstrumentationRegistry.getTargetContext();

    public AnimalsDAO getDao() {
        return dao;
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        dao = new SQLiteAnimalDao(context, DATABASE_NAME, SQLiteAnimalDao.CURRENT_VERSION);
        Log.e(TAG, "before");
    }

    @Override
    protected void after() {
        super.after();
        dao.close();
        context.deleteDatabase(DATABASE_NAME);
        Log.e(TAG, "after");
    }
}