package crud;

import entity.UserEntity;
import entity.WordEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserCrud {
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

        System.out.println("Du har lagt till en ny användare.");
        scanner.next();

    }



    public static void newUserInput(){
        System.out.println("Skriv in användarnamn: ");
        String username = scanner.nextLine();
        System.out.println("Skriv in lösenord: ");
        String password = scanner.nextLine();
        newUser(username,password);
    }
    public static void showAllUsers(){
        Query query = entityManager.createQuery("SELECT u FROM UserEntity u");

        List<UserEntity> list = query.getResultList( );
        for( UserEntity u:list ){
            System.out.print("Användarnamn: " + u.getUsername());
            System.out.print("\t Poäng: " + u.getPoints());
            System.out.println("\t ID: "+ u.getUserId());
        }
    }

    public static void updateUser(){
        entityManager.getTransaction().begin();
        showAllUsers();
        System.out.println("Skriv in ID på användaren du vill ändra: ");
        UserEntity user = entityManager.find( UserEntity.class, Integer.parseInt(scanner.nextLine()));
        System.out.println("Skriv ditt nya användarnamn: ");
        user.setUsername(scanner.nextLine());
        System.out.println("Skriv in ditt nya lösenord: ");
        user.setUserPassword(scanner.nextLine());
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        System.out.println("Du har uppdaterat profilen.");

    }
    public static void updatePoints(int points, int id){
        entityManager.getTransaction().begin();
        UserEntity user = entityManager.find( UserEntity.class, id);
        user.setPoints(user.getPoints()+points);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
    public static int findUserByName(String search){
        Query query = entityManager.createNamedQuery("userQuery");
        List<UserEntity> list = query.getResultList( );
        Optional<Integer> a = list.stream()
                .filter(n -> n.getUsername().equals(search)).map(UserEntity::getUserId).findFirst();
            return a.get();
    }
    public static void deleteUser(){
        entityManager.getTransaction().begin();
        showAllUsers();
        System.out.println("Skriv in ID på användaren du vill ta bort.");
        UserEntity user = entityManager.find( UserEntity.class, scanner.nextLine());

        entityManager.remove( user );
        entityManager.getTransaction().commit();

        System.out.println("Du har tagit bort användaren.");
    }
    public static void countUsersQuery(){
        Query query = entityManager.createQuery("SELECT COUNT(u.userId) FROM UserEntity u");
        System.out.println(query.getSingleResult());
    }
}
