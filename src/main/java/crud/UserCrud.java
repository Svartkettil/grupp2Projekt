package crud;

import entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class UserCrud {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager;
    public static Scanner scanner = new Scanner(System.in);
    public UserCrud(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    private static void newUser(String username, String password){
        entityManager.getTransaction().begin();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setUserPassword(password);
        userEntity.setPoints(0);

        entityManager.persist(userEntity);
        entityManager.getTransaction().commit();

        System.out.println("Du har lagt till en ny user");

    }



    public static void newUserInput(){
        System.out.println("Skriv in användarnamn: ");
        String username = scanner.nextLine();
        System.out.println("Skriv in lösenord: ");
        String password = scanner.nextLine();
        newUser(username,password);
        scanner.nextLine();
    }
    public static void showAllUsers(){
        Query query = entityManager.createQuery("SELECT u FROM UserEntity u");

        List<UserEntity> list = query.getResultList( );

        for( UserEntity u:list ){
            System.out.print("username:" + u.getUsername());
            System.out.println("\t points:" + u.getPoints());
            System.out.println("\t id:"+ u.getUserId());
        }
    }

    public static void updateUser(){
        entityManager.getTransaction().begin();
        UserEntity user = entityManager.find( UserEntity.class, 1 );
        System.out.println("skriv ditt nya användarnamn");
        user.setUsername(scanner.nextLine());
        System.out.println("skriv in ditt nya lösenord");
        user.setUserPassword(scanner.nextLine());
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        System.out.println("Du har uppdaterat profilen");

    }

    public static void deleteUser(int id){
        entityManager.getTransaction().begin();
        UserEntity user = entityManager.find( UserEntity.class, id);

        entityManager.remove( user );
        entityManager.getTransaction().commit();

        System.out.println("Du har tagit bort boken");
    }
    public static void countUsersQuery(){
        Query query = entityManager.createQuery("SELECT COUNT(u.userId) FROM UserEntity u");
        System.out.println(query.getSingleResult());
    }
}
