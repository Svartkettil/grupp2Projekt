package crud;

import entity.RankingEntity;
import entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class RankingCrud {

    private static Scanner scanner = new Scanner(System.in);

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    private static void newRankedUser(String userName, String rankName){
        entityManager.getTransaction().begin();
        RankingEntity rankingEntity = new RankingEntity();
        rankingEntity.setUserName(userName);
        rankingEntity.setRankName(rankName);


        entityManager.persist(rankingEntity);
        entityManager.getTransaction().commit();

        System.out.println("Du har nu lagt till en ny rankad användare.");
        scanner.next();

    }
    public static void newRankedUserInput(){
        System.out.println("Skriv in namnet på användaren: ");
        String userName = scanner.nextLine();
        System.out.println("Skriv in namnet på ranken: ");
        String rankName = scanner.nextLine();
        newRankedUser(userName,rankName);
    }
    public static void showAllRankedUsers(){
        Query query = entityManager.createNamedQuery("rankQuery");

        List<RankingEntity> list = query.getResultList( );
        for( RankingEntity r:list ){
            System.out.print("Användarnamn: " + r.getUserName());
            System.out.print("\t Rank: " + r.getRankName());
            System.out.println("\t ID: "+ r.getRankId());
        }
    }
    public static void updateRankedUser(){
        entityManager.getTransaction().begin();
        showAllRankedUsers();
        System.out.println("Skriv in ID på den rankade använderen: ");
        RankingEntity rankedUser = entityManager.find( RankingEntity.class, Integer.parseInt(scanner.nextLine()));
        System.out.println("Skriv ditt nya användarnamn: ");
        rankedUser.setUserName(scanner.nextLine());
        System.out.println("Skriv in din nya rank: ");
        rankedUser.setRankName(scanner.nextLine());
        entityManager.persist(rankedUser);
        entityManager.getTransaction().commit();

        System.out.println("Du har uppdaterat profilen.");

    }
    public static void deleteRankedUser(){
        entityManager.getTransaction().begin();
        showAllRankedUsers();
        System.out.println("Skriv in ID på den rankade använderen: ");
        RankingEntity rankedUser = entityManager.find( RankingEntity.class, scanner.nextLine());

        entityManager.remove(rankedUser);
        entityManager.getTransaction().commit();

        System.out.println("Du har tagit bort användaren.");
    }
    public static void countRankedUsersQuery(){
        Query query = entityManager.createQuery("SELECT COUNT(r.rankId) FROM RankingEntity r");
        System.out.println(query.getSingleResult());
    }

}
