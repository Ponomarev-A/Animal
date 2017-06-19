package com.sbrt.ponomarev.animal;

import com.sbrt.ponomarev.animal.bean.Animal;
import com.sbrt.ponomarev.animal.bean.AnimalsStorage;
import com.sbrt.ponomarev.animal.bean.OnContentChangeListener;
import com.sbrt.ponomarev.animal.db.AnimalsDAO;
import com.sbrt.ponomarev.animal.utils.EntitiesGenerator;
import com.sbrt.ponomarev.animal.utils.TestNameRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Ponomarev on 19.06.2017.
 */

public class AnimalStorageTest {

    private static List<Animal> sAnimals = EntitiesGenerator.createRandomAnimalsList(true);
    private static Animal sAnimal = EntitiesGenerator.createRandomAnimal(true);

    @Mock
    public AnimalsDAO mAnimalsDao;
    @Mock
    public OnContentChangeListener mChangeListener;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @Rule
    public TestNameRule testNameRule = new TestNameRule();

    private AnimalsStorage mAnimalsStorage;


    @Before
    public void setUp() {
        mAnimalsDao = mock(AnimalsDAO.class);
        mChangeListener = mock(OnContentChangeListener.class);
        mAnimalsStorage = new AnimalsStorage(mAnimalsDao);
    }

    @Test
    public void testAddAnimal() throws Exception {
        mAnimalsStorage.addOnContentChangeListener(mChangeListener);
        mAnimalsStorage.addAnimal(sAnimal);

        verify(mAnimalsDao).insertAnimal(sAnimal);
        for (OnContentChangeListener listener : mAnimalsStorage.getContentChangeListeners()) {
            verify(listener).onAnimalChanged();
        }
    }

    @Test
    public void testGetAnimals() {
        doReturn(sAnimals).when(mAnimalsDao).getAnimals();

        List<Animal> actual = mAnimalsStorage.getAnimals();

        assertThat(actual, is(sAnimals));
        verify(mAnimalsDao).getAnimals();
    }

    @Test
    public void testUpdateAnimal() throws Exception {
        mAnimalsStorage.addOnContentChangeListener(mChangeListener);
        mAnimalsStorage.updateAnimal(sAnimal);

        verify(mAnimalsDao).updateAnimal(sAnimal);
        for (OnContentChangeListener listener : mAnimalsStorage.getContentChangeListeners()) {
            verify(listener).onAnimalChanged();
        }
    }

    @Test
    public void testDeleteAnimal() throws Exception {
        mAnimalsStorage.addOnContentChangeListener(mChangeListener);
        mAnimalsStorage.deleteAnimal(sAnimal.getId());

        verify(mAnimalsDao).deleteAnimal(sAnimal.getId());
        for (OnContentChangeListener listener : mAnimalsStorage.getContentChangeListeners()) {
            verify(listener).onAnimalChanged();
        }
    }

    @Test
    public void testGetAnimal() throws Exception {
        doReturn(sAnimal).when(mAnimalsDao).getAnimal(sAnimal.getId());

        Animal actual = mAnimalsStorage.getAnimal(sAnimal.getId());

        assertThat(actual, is(sAnimal));
        verify(mAnimalsDao).getAnimal(sAnimal.getId());
    }

    @Test
    public void testAddAndRemoveOnContentChangeListener() throws Exception {
        mAnimalsStorage.addOnContentChangeListener(mChangeListener);
        assertThat(mAnimalsStorage.getContentChangeListeners().size(), is(1));

        mAnimalsStorage.removeOnContentChangeListener(mChangeListener);
        assertThat(mAnimalsStorage.getContentChangeListeners().size(), is(0));
    }
}
