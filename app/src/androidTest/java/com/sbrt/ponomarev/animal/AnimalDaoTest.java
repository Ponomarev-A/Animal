package com.sbrt.ponomarev.animal;

import android.support.test.runner.AndroidJUnit4;
import com.sbrt.ponomarev.animal.bean.Animal;
import com.sbrt.ponomarev.animal.utils.AnimalDaoRule;
import com.sbrt.ponomarev.animal.utils.EntitiesGenerator;
import com.sbrt.ponomarev.animal.utils.TestNameRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Created by user15 on 15.06.2017.
 */
@RunWith(AndroidJUnit4.class)
public class AnimalDaoTest {

    private static final String TAG = AnimalDaoTest.class.getSimpleName();

    private final AnimalDaoRule mDaoRule = new AnimalDaoRule();
    private final ExpectedException mThrown = ExpectedException.none();
    private final TestNameRule mTestNameRule = new TestNameRule();

    @Rule
    public TestRule rule = RuleChain
            .outerRule(mDaoRule)
            .around(mTestNameRule)
            .around(mThrown);

    @Test
    public void testGetAnimalById() throws Exception {
        Animal animal = EntitiesGenerator.createRandomAnimal(true);

        long id = mDaoRule.getDao().insertAnimal(animal);
        animal.setId(id);
        assertThat(animal, is(mDaoRule.getDao().getAnimal(id)));
    }

    @Test
    public void testGetAnimals() throws Exception {
        List<Animal> animals = EntitiesGenerator.createRandomAnimalsList(true);

        for (Animal animal : animals) {
            mDaoRule.getDao().insertAnimal(animal);
        }

        assertThat(mDaoRule.getDao().getAnimals(), containsInAnyOrder(animals.toArray(new Animal[animals.size()])));
    }

    @Test
    public void testInsert() throws Exception {
        Animal animal = EntitiesGenerator.createRandomAnimal(true);

        long id = mDaoRule.getDao().insertAnimal(animal);
        assertThat(true, is(id > 0));
    }

    @Test
    public void testInsertNullAnimal() throws Exception {
        mThrown.expect(NullPointerException.class);

        Animal animal = null;
        mDaoRule.getDao().insertAnimal(animal);
    }

    @Test
    public void testUpdateAnimal() throws Exception {
        Animal animal1 = EntitiesGenerator.createRandomAnimal(true);
        Animal animal2 = EntitiesGenerator.createRandomAnimal(true);

        // Check that animal1 and animal2 are different objects
        assertThat(animal2, not(animal1));

        long id = mDaoRule.getDao().insertAnimal(animal1);

        // Now different animals has same ID
        animal2.setId(id);

        int updatedRows = mDaoRule.getDao().updateAnimal(animal2);
        assertThat(updatedRows, is(1));
    }

    @Test
    public void testUpdateUnExistedAnimal() throws Exception {
        Animal animal1 = EntitiesGenerator.createRandomAnimal(true);
        Animal animal2 = EntitiesGenerator.createRandomAnimal(true);

        // Check that animal1 and animal2 are different objects
        assertThat(animal2, not(animal1));

        mDaoRule.getDao().insertAnimal(animal1);
        int updatedRows = mDaoRule.getDao().updateAnimal(animal2);
        assertThat(updatedRows, is(0));
    }

    @Test
    public void testDeleteAnimal() throws Exception {
        Animal animal = EntitiesGenerator.createRandomAnimal(true);

        long id = mDaoRule.getDao().insertAnimal(animal);

        assertThat(mDaoRule.getDao().deleteAnimal(id), is(1));
        assertThat(mDaoRule.getDao().deleteAnimal(id + 100500), is(0));
    }
}
