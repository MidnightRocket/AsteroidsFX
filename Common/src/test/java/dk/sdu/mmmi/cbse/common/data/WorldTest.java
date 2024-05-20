package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

	private World world;

	@BeforeEach
	void setUp() {
		this.world = new World();
	}

	@Test
	void addEntity() {
		final Entity entity = new TestEntityA();
		assertTrue(this.world.addEntity(entity));
		assertFalse(this.world.addEntity(entity));

		assertEquals(1, this.world.getEntities().size());

		assertTrue(this.world.getEntities().contains(entity));
	}

	@Test
	void removeEntity() {
		final Entity entity1 = new TestEntityA();
		final Entity entity2 = new TestEntityA();

		assertTrue(this.world.addEntity(entity1));
		assertTrue(this.world.addEntity(entity2));
		assertEquals(2, this.world.getEntities().size());
		assertTrue(this.world.getEntities().contains(entity1));

		assertTrue(this.world.removeEntity(entity1));
		assertEquals(1, this.world.getEntities().size());
		assertFalse(this.world.getEntities().contains(entity1));

		assertFalse(this.world.removeEntity(entity1));

		assertTrue(this.world.getEntities().contains(entity2));
	}

	@Test
	void getAllEntities() {
		final Entity entity = new TestEntityA();
		assertTrue(this.world.addEntity(entity));

		final Collection<Entity> collection = this.world.getEntities();

		assertTrue(collection.contains(entity));

		assertThrows(UnsupportedOperationException.class, () -> {
			collection.add(new TestEntityA());
		});
		assertThrows(UnsupportedOperationException.class, () -> {
			collection.remove(entity);
		});
	}

	@Test
	void getEntitiesByClass() {
		final Entity entityA = new TestEntityA();
		final Entity entityB = new TestEntityB();

		assertTrue(this.world.addEntity(entityA));
		assertTrue(this.world.addEntity(entityB));

		final Collection<CommonEntity> collection = this.world.getEntitiesByClass(CommonEntity.class);

		assertEquals(2, collection.size());
		assertTrue(collection.contains(entityA));
		assertTrue(collection.contains(entityB));

		final Collection<TestEntityA> collectionA = this.world.getEntitiesByClass(TestEntityA.class);
		final Collection<TestEntityB> collectionB = this.world.getEntitiesByClass(TestEntityB.class);

		assertEquals(1, collectionA.size());
		assertEquals(1, collectionB.size());

		assertTrue(collectionA.contains(entityA));
		assertTrue(collectionB.contains(entityB));

		assertThrows(UnsupportedOperationException.class, () -> {
			collectionA.remove(entityA);
		});

		assertThrows(UnsupportedOperationException.class, () -> {
			collectionB.remove(entityB);
		});
	}

	@Test
	void removeEntitiesByClass() {
		final Entity entityA = new TestEntityA();
		final Entity entityB = new TestEntityB();

		assertTrue(this.world.addEntity(entityA));
		assertTrue(this.world.addEntity(entityB));

		final Collection<TestEntityA> removedA = this.world.removeEntitiesByClass(TestEntityA.class);

		assertTrue(removedA.contains(entityA));
		assertFalse(removedA.contains(entityB));

		assertFalse(this.world.getEntities().contains(entityA));
		assertTrue(this.world.getEntities().contains(entityB));
	}


	private static final class TestEntityA extends CommonEntity {
	}

	private static final class TestEntityB extends CommonEntity {
	}
}