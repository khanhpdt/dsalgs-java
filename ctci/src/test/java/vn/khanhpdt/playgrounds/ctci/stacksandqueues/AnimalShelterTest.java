package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.stacksandqueues.AnimalShelter;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static vn.khanhpdt.playgrounds.ctci.stacksandqueues.AnimalShelter.AnimalType.CAT;
import static vn.khanhpdt.playgrounds.ctci.stacksandqueues.AnimalShelter.AnimalType.DOG;

/**
 * @author khanhpdt
 */
public class AnimalShelterTest {

	@Test
	public void testDequeueAny() {
		AnimalShelter animalShelter = new AnimalShelter();
		UUID oldestAnimalUuid = animalShelter.enqueue(DOG);
		animalShelter.enqueue(CAT);
		animalShelter.enqueue(DOG);
		animalShelter.enqueue(CAT);
		animalShelter.enqueue(DOG);

		assertThat(animalShelter.dequeueAny().getKey(), is(oldestAnimalUuid));
	}

	@Test
	public void testDequeueDog() {
		AnimalShelter animalShelter = new AnimalShelter();
		animalShelter.enqueue(CAT);
		animalShelter.enqueue(CAT);
		UUID oldestDogUuid = animalShelter.enqueue(DOG);
		animalShelter.enqueue(DOG);
		animalShelter.enqueue(CAT);
		animalShelter.enqueue(DOG);

		assertThat(animalShelter.dequeueDog().getKey(), is(oldestDogUuid));
	}

	@Test
	public void testDequeueDogNotFound() {
		AnimalShelter animalShelter = new AnimalShelter();
		animalShelter.enqueue(CAT);
		animalShelter.enqueue(CAT);
		animalShelter.enqueue(CAT);

		assertThat(animalShelter.dequeueDog(), is(nullValue()));
	}

	@Test
	public void testDequeueCat() {
		AnimalShelter animalShelter = new AnimalShelter();
		animalShelter.enqueue(DOG);
		animalShelter.enqueue(DOG);
		UUID oldestCatUuid = animalShelter.enqueue(CAT);
		animalShelter.enqueue(DOG);
		animalShelter.enqueue(CAT);
		animalShelter.enqueue(CAT);

		assertThat(animalShelter.dequeueCat().getKey(), is(oldestCatUuid));
	}

	@Test
	public void testMultipleDequeues() {
		AnimalShelter animalShelter = new AnimalShelter();

		UUID firstDogUuid = animalShelter.enqueue(DOG);
		UUID firstCatUuid = animalShelter.enqueue(CAT);
		UUID secondDogUuid = animalShelter.enqueue(DOG);
		UUID secondCatUuid = animalShelter.enqueue(CAT);

		assertThat(animalShelter.dequeueDog().getKey(), is(firstDogUuid));
		assertThat(animalShelter.dequeueCat().getKey(), is(firstCatUuid));
		assertThat(animalShelter.dequeueCat().getKey(), is(secondCatUuid));
		assertThat(animalShelter.dequeueDog().getKey(), is(secondDogUuid));
	}

}