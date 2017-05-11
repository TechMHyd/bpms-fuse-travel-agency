package org.blogdemo.travelagency.persistenceData;
import java.util.concurrent.atomic.AtomicInteger;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;

public final class CamelJmsTestHelper {
    private static AtomicInteger counter = new AtomicInteger(0);
    private CamelJmsTestHelper() {
    }
    public static ConnectionFactory createConnectionFactory() { return createConnectionFactory(null);}
    public static ConnectionFactory createConnectionFactory(String options) {
       int id = counter.incrementAndGet();
        String url = "vm://test-broker-" + id + "?broker.persistent=false&broker.useJmx=false";
        if (options != null) { url = url + "&" + options; }
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connectionFactory.setCopyMessageOnSend(false);
        connectionFactory.setOptimizeAcknowledge(true);
        connectionFactory.setOptimizedMessageDispatch(true);        
        connectionFactory.setUseAsyncSend(false);
        connectionFactory.setAlwaysSessionAsync(false);
        PooledConnectionFactory pooled = new PooledConnectionFactory(connectionFactory);
        pooled.setMaxConnections(8);
        return pooled;
    }
}