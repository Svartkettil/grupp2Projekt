package crud;

import entity.LanguageEntity;
import entity.SentenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class SentenceCrud {

    private static Scanner scanner = new Scanner(System.in);

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();


    private static void newSentence(String sentenceName, String sentenceAnswer){
        entityManager.getTransaction().begin();
        SentenceEntity Se = new SentenceEntity();


        //LanguageEntity LgE = entityManager.find(LanguageEntity.class, 1);

        Se.setSentenceName(sentenceName);
        Se.setSentenceAnswer(sentenceAnswer);
        Se.setLanguageByLanguageSentenceId(WordCrud.languageInput(entityManager, scanner));


        entityManager.persist(Se);
        entityManager.getTransaction().commit();

        System.out.println("Du har lagt till en ny mening.");

    }

    public static void Sentenceinput(){
        System.out.println("Skriv en mening(SVE):  ");
        String inputSentence = scanner.nextLine();
        System.out.println("Skriv en översättning:  ");
        String inputAnswer = scanner.nextLine();
        newSentence(inputSentence,inputAnswer);
        scanner.nextLine();
    }

    public static void showSentence(){
        System.out.println("Vad är ID: ");
        int sentenceInput = scanner.nextInt();
        SentenceEntity Se = entityManager.find( SentenceEntity.class, sentenceInput );
        System.out.println("ID: " + Se.getSentenceId() + " Mening: " + Se.getSentenceName( )
                +"\n"+ " Översättning: " + Se.getSentenceAnswer() + " Språk: " + Se.getLanguageByLanguageSentenceId());
    }



    public static void updateSentence(){
        entityManager.getTransaction().begin();
        System.out.println("Välj ett ID: ");
        String id = scanner.nextLine();
        SentenceEntity Se = entityManager.find( SentenceEntity.class, Integer.parseInt(id));
        System.out.println("Ny mening: ");
        Se.setSentenceName(scanner.nextLine());
        System.out.println("Översättningen: ");
        Se.setSentenceAnswer(scanner.nextLine());
        entityManager.persist(Se);
        entityManager.getTransaction().commit();

        System.out.println("Du har uppdaterat en mening.");
    }

    public static void deleteSentence(){
        System.out.println("Välj ett ID för den meningen du vill ta bort: ");
        String id = scanner.nextLine();
        entityManager.getTransaction().begin();
        SentenceEntity Se = entityManager.find( SentenceEntity.class, Integer.parseInt(id));

        entityManager.remove(Se);
        entityManager.getTransaction().commit();

        System.out.println("Meningen har tagits bort.(It will be back)");
    }

    public static void showAllSentence(){
        Query query = entityManager.createNamedQuery("sentenceQuery");

        List<SentenceEntity> list = query.getResultList();

        for (SentenceEntity s:list){
            System.out.println("ID: "+ s.getSentenceId()+" Mening:  "+s.getSentenceName()
            +"\n"+" Översättning: "+ s.getSentenceAnswer());
        }
    }

    public static void timeToCount(){
        Query query = entityManager.createNamedQuery("countSentence");
        System.out.println("Hur många meningar man har: " + query.getSingleResult());
    }
}
