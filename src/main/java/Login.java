import entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;
public class Login {
    public static String activeUser;
    public static Scanner scanner = new Scanner(System.in);
    public static void login(EntityManager entityManager){
        System.out.println("Skriv in användarnamn: ");
        String usr = scanner.nextLine();
        System.out.println("Skriv in lösenord: ");
        String pass = scanner.nextLine();
        activeUser=tryToLogin(usr, pass, entityManager);
    }
    public static String tryToLogin(String username, String pass, EntityManager entityManager){
        Query query = entityManager.createQuery("SELECT u FROM UserEntity u");
        List<UserEntity> list = query.getResultList( );
        for( UserEntity u:list ){
            if (u.getUsername().equals(username) && u.getUserPassword().equals(pass)){
                System.out.println("Inloggning lyckad.");
                return username;
            }
        }
        System.out.println("Inloggning misslyckad. Du är nu inloggad som gäst");
        return "";
    }
}
