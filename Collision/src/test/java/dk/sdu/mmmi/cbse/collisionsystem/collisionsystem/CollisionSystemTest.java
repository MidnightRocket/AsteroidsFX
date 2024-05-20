package com.github.sef24sp4.collisionsystem;

import dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;
import dk.sdu.mmmi.cbse.common.vector.IVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CollisionSystemTest {
	private ICollisionDetectionService collisionSystem;


	@Mock
	private World entityManager;

	@Mock
	private GameData gameData;

	@Mock
	private CollidableEntity entity1;
	@Mock
	private CollidableEntity entity2;
	@Mock
	private CollidableEntity entity3;
	@Mock
	private CollidableEntity entity4;

	@BeforeEach
	void setUp() {


		this.collisionSystem = new CollisionDetector();


		Mockito.lenient().when(this.entity1.getCoordinates()).thenReturn(new BasicVector(3, 3));
		Mockito.when(this.entity1.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(5, 5)});
		this.collisionSystem.addEntity(this.entity1);


		Mockito.lenient().when(this.entity2.getCoordinates()).thenReturn(new BasicVector(410, 310));
		Mockito.when(this.entity2.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(5, 5)});
		this.collisionSystem.addEntity(this.entity2);

		Mockito.lenient().when(this.entity3.getCoordinates()).thenReturn(new BasicVector(400, 300));
		Mockito.when(this.entity3.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(20, 30)});
		this.collisionSystem.addEntity(this.entity3);

		Mockito.lenient().when(this.entity4.getCoordinates()).thenReturn(new BasicVector(280, 200));
		Mockito.when(this.entity4.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(100, 100)});
		this.collisionSystem.addEntity(this.entity4);
	}


	@Test
	void processCollisions() {
		this.collisionSystem.processCollisions(this.gameData, this.entityManager);
		Mockito.verify(this.entity1, Mockito.atLeastOnce()).getCoordinates();
		Mockito.verify(this.entity2, Mockito.atLeastOnce()).getCoordinates();
		Mockito.verify(this.entity3, Mockito.atLeastOnce()).getCoordinates();
		Mockito.verify(this.entity4, Mockito.atLeastOnce()).getCoordinates();


		Mockito.verify(this.entity1, Mockito.never()).collide(Mockito.any(), Mockito.any());


		Mockito.verify(this.entity2).collide(this.entityManager, this.entity3);
		Mockito.verify(this.entity3).collide(this.entityManager, this.entity2);
		Mockito.verify(this.entity3).collide(this.entityManager, this.entity4);
		Mockito.verify(this.entity4).collide(this.entityManager, this.entity3);

		Mockito.verifyNoMoreInteractions(this.entity1, this.entity2, this.entity3, this.entity4);
	}

	@Test
	void verifyNoCollisionOfRemoved() {
		this.collisionSystem.removeEntity(this.entity1);
		this.collisionSystem.removeEntity(this.entity2);
		this.collisionSystem.removeEntity(this.entity3);

		this.collisionSystem.processCollisions(this.gameData, this.entityManager);

		Mockito.verify(this.entity1, Mockito.never()).collide(Mockito.any(), Mockito.any());
		Mockito.verify(this.entity2, Mockito.never()).collide(Mockito.any(), Mockito.any());
		Mockito.verify(this.entity3, Mockito.never()).collide(Mockito.any(), Mockito.any());
	}
}