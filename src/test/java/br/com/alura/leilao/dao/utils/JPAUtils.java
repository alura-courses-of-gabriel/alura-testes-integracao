package br.com.alura.leilao.dao.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("tests");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
