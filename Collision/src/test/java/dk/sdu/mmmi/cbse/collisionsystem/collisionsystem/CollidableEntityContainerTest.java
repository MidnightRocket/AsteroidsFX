package dk.sdu.mmmi.cbse.collisionsystem.collisionsystem;

import dk.sdu.mmmi.cbse.collisionsystem.CollidableEntityContainer;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;
import dk.sdu.mmmi.cbse.common.vector.IVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CollidableEntityContainerTest {
	@Mock
	private CollidableEntity entity1;

	private CollidableEntityContainer container;

	@BeforeEach
	void setUp() {
		Mockito.when(this.entity1.getPolygonCoordinates()).thenReturn(new IVector[]{
				new BasicVector(3, 4),
				new BasicVector(5, 0),
				new BasicVector(0, -5),
				new BasicVector(-3, -3)
		});
		this.container = new CollidableEntityContainer(this.entity1);
	}

	@Test
	void getEntity() {
		assertSame(this.entity1, this.container.getEntity());
	}

	@Test
	void getCoordinates() {
		final BasicVector coordinates = new BasicVector(3, 3);
		Mockito.when(this.entity1.getCoordinates()).thenReturn(coordinates);
		assertEquals(coordinates, this.container.getCoordinates());
	}

	@Test
	void getRadius() {
		assertEquals(5, this.container.getRadius());
	}

	@Test
	void doesCollideWith() {
		final CollidableEntity otherEntity = Mockito.mock();
		Mockito.when(otherEntity.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(3, 4)});
		Mockito.when(otherEntity.getCoordinates()).thenReturn(new BasicVector(10, 0));
		final CollidableEntityContainer otherContainer = new CollidableEntityContainer(otherEntity);

		Mockito.when(this.entity1.getCoordinates()).thenReturn(new BasicVector(0, 0));
		assertTrue(this.container.doesCollideWith(otherContainer));
	}

	@Test
	void doesNotCollideWith() {
		final CollidableEntity otherEntity = Mockito.mock();
		Mockito.when(otherEntity.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(3, 4)});
		Mockito.when(otherEntity.getCoordinates()).thenReturn(new BasicVector(11, 0));
		final CollidableEntityContainer otherContainer = new CollidableEntityContainer(otherEntity);

		Mockito.when(this.entity1.getCoordinates()).thenReturn(new BasicVector(0, 0));
		assertFalse(this.container.doesCollideWith(otherContainer));
	}

	@Test
	void testHashCode() {
		final CollidableEntityContainer otherContainer = new CollidableEntityContainer(this.entity1);
		assertEquals(otherContainer.hashCode(), this.container.hashCode());

		// The hashCode of the raw entity should not be the same as for the decorated entity.
		assertNotEquals(this.entity1.hashCode(), this.container.hashCode());

		final CollidableEntity differentEntity = Mockito.when(Mockito.mock(CollidableEntity.class).getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(3, 4)}).getMock();
		final CollidableEntityContainer differentContainer = new CollidableEntityContainer(differentEntity);
		assertNotEquals(differentContainer.hashCode(), this.container.hashCode());
	}

	@Test
	void testEquals() {
		final CollidableEntityContainer otherContainer = new CollidableEntityContainer(this.entity1);
		assertEquals(otherContainer, this.container);

		final CollidableEntity differentEntity = Mockito.when(Mockito.mock(CollidableEntity.class).getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(3, 4)}).getMock();
		final CollidableEntityContainer differentContainer = new CollidableEntityContainer(differentEntity);
		assertNotEquals(differentContainer, this.container);
	}

	@Test
	void testHashCodeNotEqualsToRawEntity() {
		// The hashCode of the raw entity should not be the same as for the decorated entity.
		assertNotEquals(this.entity1.hashCode(), this.container.hashCode());
	}
}