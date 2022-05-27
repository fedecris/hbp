package health.hbp.monitor;

import com.mongodb.client.MongoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component @RequiredArgsConstructor
public class DatabaseService implements HealthIndicator {

    @Value("${spring.data.mongodb.database}")
    private String DB_NAME;

    private final MongoClient client;

    @Override
    public Health health() {
        if (dbInfo()!=null)
            return Health.up().withDetail(DB_NAME + " collections", dbInfo()).build();
        return Health.down().withDetail(DB_NAME + " collections", "Service not available").build();
    }

    protected String dbInfo() {
        try {
            StringBuffer retVal = new StringBuffer();
            client.getDatabase(DB_NAME).listCollections().forEach(c -> retVal.append(c).append(" "));
            return retVal.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
