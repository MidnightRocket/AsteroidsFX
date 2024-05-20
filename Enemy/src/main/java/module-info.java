import com.github.midnightrocket.asteroidsfx.enemy.EnemyControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module com.github.midnightrocket.asteroidsfx.enemy.enemy {
	requires Common;
	requires CommonBullet;
	uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
	provides IEntityProcessingService with EnemyControlSystem;
}