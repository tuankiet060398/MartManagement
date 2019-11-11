package group.jpa.ogm.app.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SingletonPersistenceConfig {
	private static SingletonPersistenceConfig instance;
	private static EntityManager entityManager = Persistence.createEntityManagerFactory("MartDB").createEntityManager();

	private SingletonPersistenceConfig() {
	}

	public static SingletonPersistenceConfig getInstance() {
		SingletonPersistenceConfig result = instance;
		if (result == null) {
			synchronized (entityManager) {
				result = instance;
				if (result == null) {
					instance = result = new SingletonPersistenceConfig();
				}
			}
		}
		return result;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
