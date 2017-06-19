package com.sbrt.ponomarev.animal;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.sbrt.ponomarev.animal.bean.Animal;
import com.sbrt.ponomarev.animal.utils.AnimalDaoRule;
import com.sbrt.ponomarev.animal.utils.EntitiesGenerator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by user15 on 15.06.2017.
 */
@RunWith(AndroidJUnit4.class)
public class AnimalDaoTest {

    private static final String TAG = AnimalDaoTest.class.getSimpleName();

    private final AnimalDaoRule daoRule = new AnimalDaoRule();
    private final TestName testName = new TestName();
    private final ExpectedException thrown = ExpectedException.none();

    @Rule
    public TestRule rule = RuleChain
            .outerRule(daoRule)
            .around(testName)
            .around(thrown);

    @Test
    public void testInsert() throws Exception {
        Log.e(TAG, "test name: " + testName.getMethodName());

        Animal animal = EntitiesGenerator.createRandomAnimal(true);

        long id = daoRule.getDao().insertAnimal(animal);
        assertThat(true, is(id > 0));
    }

    @Test
    public void testInsertNullAnimal() throws Exception {
        Log.e(TAG, "test name: " + testName.getMethodName());

        thrown.expect(NullPointerException.class);

        Animal animal = null;
        daoRule.getDao().insertAnimal(animal);
    }

    @Test
    public void testGetAnimalById() throws Exception {
        Log.e(TAG, "test name: " + testName.getMethodName());

        Animal animal = EntitiesGenerator.createRandomAnimal(true);

        long id = daoRule.getDao().insertAnimal(animal);
        animal.setId(id);
        assertThat(animal, is(daoRule.getDao().getAnimal(id)));
    }
}
