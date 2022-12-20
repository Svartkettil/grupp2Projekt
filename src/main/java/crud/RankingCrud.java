package crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class RankingCrud {

    private static Scanner scanner = new Scanner(System.in);

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();




}
