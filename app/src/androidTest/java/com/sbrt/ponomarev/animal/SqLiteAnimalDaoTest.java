package com.sbrt.ponomarev.animal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import com.sbrt.ponomarev.animal.db.AnimalsContract;
import com.sbrt.ponomarev.animal.db.SQLiteAnimalDao;
import com.sbrt.ponomarev.animal.utils.TestNameRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

/**
 * Created by Ponomarev on 19.06.2017.
 */

public class SqLiteAnimalDaoTest {

    private static final String DATABASE_NAME = "test.db";
    private static final int VERSION = 1;

    private final ExpectedException mThrown = ExpectedException.none();
    private final TestNameRule mTestNameRule = new TestNameRule();

    private final Context mContext = InstrumentationRegistry.getTargetContext();

    @Rule
    public TestRule mRule = RuleChain
            .outerRule(mTestNameRule)
            .around(mThrown);

    @Test
    public void testCreateTable() throws Exception {
        SQLiteAnimalDao dao = new SQLiteAnimalDao(mContext, DATABASE_NAME, VERSION);
        SQLiteDatabase db = dao.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + SQLiteAnimalDao.TABLE_NAME, null);

        List<String> expected = getFieldNames(new AnimalsContract.Animal());
        List<String> actual = Arrays.asList(cursor.getColumnNames());

        assertThat(actual, containsInAnyOrder(expected.toArray(new String[expected.size()])));
    }

    @NonNull
    private List<String> getFieldNames(AnimalsContract.Animal animal) throws IllegalAccessException {
        List<String> list = new ArrayList<>();

        list.add(BaseColumns._ID);
        for (Field field : animal.getClass().getDeclaredFields()) {
            list.add((String) field.get(animal));
        }
        return list;
    }
}
