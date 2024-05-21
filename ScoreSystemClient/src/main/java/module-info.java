import com.github.midnightrocket.asteroidsfx.scoresystemclient.ScoreSystemClient;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module com.github.midnightrocket.asteroidsfx.scoresystemclient {
	requires Common;
	requires java.net.http;
	provides IEntityProcessingService with ScoreSystemClient;
}