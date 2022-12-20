import entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class Login {
    public static Scanner scanner = new Scanner(System.in);
    public static String login(EntityManager entityManager){
        System.out.println("skriv in användarnamn");
        String usr = scanner.nextLine();
        System.out.println("skriv in lösenord");
        String pass = scanner.next();
        return tryToLogin(usr, pass, entityManager);
    }
    public static String tryToLogin(String username, String pass, EntityManager entityManager){
        Query query = entityManager.createQuery("SELECT u FROM UserEntity u");
        List<UserEntity> list = query.getResultList( );
        for( UserEntity u:list ){
            if (u.getUsername().equals(username) && u.getUserPassword().equals(pass)){
                System.out.println("inloggning lyckad");
                return username;
            }
        }
        System.out.println("inloggning misslyckades");
        return "";
    }
}
