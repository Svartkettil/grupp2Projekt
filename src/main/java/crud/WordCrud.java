package crud;

import entity.LanguageEntity;
import entity.WordEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class WordCrud {

    public static void newWord(String wordName, String wordAnswer, EntityManager entityManager, Scanner scanner){
        entityManager.getTransaction().begin();
        WordEntity word = new WordEntity();

        word.setWordName(wordName);
        word.setWordAnswer(wordAnswer);
        word.setLanguageByWordLanguageId(languageInput(entityManager, scanner));

        entityManager.persist(word);
        entityManager.getTransaction().commit();

        System.out.println("Du har lagt till ett nytt ord");
    }

    public static void wordInput(EntityManager entityManager, Scanner scanner){
        System.out.println("Skriv in ordet: (Svenska)");
        String inputWord = scanner.nextLine();
        System.out.println("Skriv in översättningen: ");
        String inputAnswer = scanner.nextLine();
        newWord(inputWord,inputAnswer, entityManager, scanner);
    }

    public static LanguageEntity languageInput(EntityManager entityManager, Scanner scanner){
        LanguageCrud.showAllLanguage(entityManager);
        System.out.println("Välj språk: (Ange id)");
        int languageId = scanner.nextInt();
        scanner.nextLine();
        return entityManager.find(LanguageEntity.class, languageId);
    }

    public static void updateWord(EntityManager entityManager, Scanner scanner){
        entityManager.getTransaction().begin();
        showAllWord(entityManager);
        System.out.println("Ange ID på ordet: ");
        WordEntity word = entityManager.find( WordEntity.class, Integer.parseInt(scanner.nextLine()) );
        System.out.println("Skriv det nya ordet: ");
        word.setWordName(scanner.nextLine());
        System.out.println("Skriv in den nya översättningen: ");
        word.setWordAnswer(scanner.nextLine());
        entityManager.persist(word);
        entityManager.getTransaction().commit();

        System.out.println("Du har uppdaterat ordet");
    }

    public static void deleteWord(EntityManager entityManager, Scanner scanner){
        System.out.println("Vilket ord vill du ta bort? (Ange id)");
        String id = scanner.nextLine();
        entityManager.getTransaction().begin();
        WordEntity word = entityManager.find( WordEntity.class, Integer.parseInt(id));

        entityManager.remove( word );
        entityManager.getTransaction().commit();

        System.out.println("Du har tagit bort ordet");
    }

    public static void showAllWord(EntityManager entityManager){
        Query query = entityManager.createNamedQuery("wordQuery");

        List<WordEntity> list = query.getResultList( );

        for( WordEntity w:list ){
            System.out.println("WordId: " + w.getWordId() + " Ord: " + w.getWordName()
                    + " Översättning: " + w.getWordAnswer() + " Språk: " + w.getLanguageByWordLanguageId());
        }
    }

    public static void countWord(EntityManager entityManager){
        Query query = entityManager.createNamedQuery("countWord");

        System.out.println("Antal ord: " + query.getSingleResult());
    }

    public static void showWord(EntityManager entityManager, Scanner scanner){
        System.out.println("Skriv in ordet du vill se (Svenska): ");
        String wordInput = scanner.nextLine();

        Query query = entityManager.createNamedQuery("wordQuery");
        List<WordEntity> list = query.getResultList( );
        list.stream()
                .filter(n -> n.getWordName().equals(wordInput))
                .forEach(System.out::println);
    }
}
