package by.teachmeskills.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private final static Logger log = LogManager.getLogger(ConnectionPool.class);
    private static final String DB_PROPERTY_FILE = "application";
    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASS = "db.pass";
    private static final int MAX_CONNECTION_COUNT = 10;
    private static final int MIN_CONNECTION_COUNT = 5;

    private static final String url;
    private static final String login;
    private static final String pass;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_PROPERTY_FILE);
        url = resourceBundle.getString(DB_URL);
        login = resourceBundle.getString(DB_LOGIN);
        pass = resourceBundle.getString(DB_PASS);
    }

    private volatile int currentConnectionNumber = MIN_CONNECTION_COUNT;
    private final BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT, true);

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }

        return instance;
    }

    private ConnectionPool() {

        for (int i = 0; i < MIN_CONNECTION_COUNT; i++) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                pool.add(DriverManager.getConnection(url, login, pass));
            } catch (SQLException | ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        }
    }

    private void openAdditionalConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pool.add(DriverManager.getConnection(url, login, pass));
            currentConnectionNumber++;
        } catch (SQLException | ClassNotFoundException e) {
            log.error("New connection wasn't add in the connection pool");
            throw new Exception("New connection wasn't add in the connection pool", e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            if (pool.isEmpty() && currentConnectionNumber < MAX_CONNECTION_COUNT) {
                openAdditionalConnection();
            }
            connection = pool.take();
        } catch (Exception ex) {
            Thread.currentThread().interrupt();
            log.error("Max count of connections was reached!");
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            if (currentConnectionNumber > MIN_CONNECTION_COUNT) {
                currentConnectionNumber--;
            }
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Connection wasn't returned into pool properly");
            }
        }
    }

    public void disconnect() {
        pool.forEach(s -> {
            try {
                s.close();
            } catch (SQLException e) {
                log.error("Cannot disconnect pool connection");
            }
        });
    }
}