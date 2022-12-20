package crud;

import entity.LanguageEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class LanguageCrud {

    public static void newLanguageInput(EntityManager entityManager, Scanner scanner){
        System.out.println("Ange språk: ");
        String inputLanguage = scanner.nextLine();
        newLanguage(entityManager, inputLanguage);
        scanner.nextLine();
    }

    private static void newLanguage(EntityManager entityManager, String languageName){
        entityManager.getTransaction().begin();
        LanguageEntity language = new LanguageEntity();
        language.setLanguageName(languageName);
        entityManager.persist(language);
        entityManager.getTransaction().commit();

        System.out.println("Du har lagt till ett nytt språk");

    }
    public static void showAllLanguage(EntityManager entityManager){
        Query query = entityManager.createNamedQuery("languageQuery");

        List<LanguageEntity> list = query.getResultList( );

        for( LanguageEntity l:list ){
            System.out.println("SpråkID: " + l.getLanguageId() + "\n" +
                    "Språk: " + l.getLanguageName());
        }
    }
    public static void showLanguageInput(EntityManager entityManager, Scanner scanner){
        System.out.println("Enter id of language you wanna see: ");
        String id = scanner.nextLine();
        showLanguage(entityManager, Integer.parseInt(id));
    }
    private static void showLanguage(EntityManager entityManager, int id){
        LanguageEntity language = entityManager.find( LanguageEntity.class, id );
        System.out.println("Språk ID = " + language.getLanguageId());
        System.out.println("Språknamn: = " + language.getLanguageName());
    }
    public static void updateLanguageInput(EntityManager entityManager, Scanner scanner){
        System.out.println("Ange id på det språk du vill ändra: ");
        String id = scanner.nextLine();
        System.out.println("Ange nytt namn på språket: ");
        String languageName = scanner.nextLine();
        updateLanguage(entityManager, Integer.parseInt(id), languageName);
    }
    private static void updateLanguage(EntityManager entityManager, int id, String languageName){
        entityManager.getTransaction().begin();
        LanguageEntity language = entityManager.find( LanguageEntity.class, id );
        language.setLanguageName(languageName);
        entityManager.persist(language);
        entityManager.getTransaction().commit();

        System.out.println("Du har uppdaterat språket!");

    }
    public static void deleteLanguage(EntityManager entityManager, Scanner scanner){
        System.out.println("Ange id på det språket du vill utrota: ");
        String id = scanner.nextLine();
        entityManager.getTransaction().begin();
        LanguageEntity language = entityManager.find( LanguageEntity.class, Integer.parseInt(id));

        entityManager.remove(language);
        entityManager.getTransaction().commit();

        System.out.println("Du har utrotat språket!");
    }
    public static void countLanguage(EntityManager entityManager){
       Query query = entityManager.createQuery("SELECT COUNT(l.languageId) FROM LanguageEntity l");

        System.out.println("Antal språk: " + query.getSingleResult());
    }

}
