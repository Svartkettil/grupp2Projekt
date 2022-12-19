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
//        entityManager.close();
//        entityManagerFactory.close();
        entityManager.getTransaction().begin();
        SentenceEntity Se = new SentenceEntity();

        LanguageEntity LgE = entityManager.find(LanguageEntity.class, 1);

        Se.setSentenceName(sentenceName);
        Se.setSentenceAnswer(sentenceAnswer);
        Se.setLanguageByLanguageSentenceId(LgE);

        //BokEntity bok = new BokEntity();
        //System.out.println("Enter name of category: ");
        //String kategoriVal = scanner.nextLine();
        //KategoriEntity kategori = new KategoriEntity();
        //kategori.setKategoriNamn(kategoriVal);
        //entityManager.persist(kategori);
        //KategoriEntity kategori = entityManager.find( KategoriEntity.class, 1);
        //bok.setBokTitel(titel);
        //bok.setBokForfattare(forfattare);
        //bok.setKategoriByBokKategoriId(kategori);

        entityManager.persist(Se);
        entityManager.getTransaction().commit();

        System.out.println("You have added a new sentence ");

    }

    public static void Sentenceinput(){
        System.out.println("Write a sentence:  ");
        String inputSentence = scanner.nextLine();
        System.out.println("Write the translation(in SWE):  ");
        String inputAnswer = scanner.nextLine();
        newSentence(inputSentence,inputAnswer);
        scanner.nextLine();
    }

    public static void showSentence(){
        Query query = entityManager.createNamedQuery("sentenceQuery");

        List<SentenceEntity> list = query.getResultList();
        for (SentenceEntity w:list){
            System.out.println("The ID: "+ w.getSentenceId()+ "Sentence: "+ w.getSentenceName()
                    + "translation: "+ w.getSentenceAnswer());
        }

//        System.out.println("Enter id of book you wanna se: ");
//        int id = scanner.nextInt();
//        BokEntity bok = entityManager.find( BokEntity.class, id );
//        System.out.println("Bok ID = " + bok.getBokId( ));
//        System.out.println("Bok Titel = " + bok.getBokTitel( ));
//        System.out.println("Kategori = " + bok.getKategoriByBokKategoriId());
    }

    public static void updateSentence(){
        entityManager.getTransaction().begin();
        SentenceEntity Se = entityManager.find( SentenceEntity.class, 1 );
        Se.setSentenceName("New sentence: "+ scanner.nextLine());
        Se.setSentenceAnswer("New translation(in SWE): "+ scanner.nextLine());
        entityManager.persist(Se);
        entityManager.getTransaction().commit();

        System.out.println("You have updated the sentence");
    }

    public static void deleteSentence(){
        System.out.println("What is the id for the sentence that you like to delete: ");
        String id = scanner.nextLine();
        entityManager.getTransaction().begin();
        SentenceEntity Se = entityManager.find( SentenceEntity.class, Integer.parseInt(id));

        entityManager.remove(Se);
        entityManager.getTransaction().commit();

        System.out.println("The sentence will be back");
    }

    public static void showAllSentence(){
        Query query = entityManager.createNamedQuery("sentenceQuery");

        List<SentenceEntity> list = query.getResultList();

        for (SentenceEntity s:list){
            System.out.println("Id: "+ s.getSentenceId()+"Sentence: "+s.getSentenceName()
            +"translation"+ s.getSentenceAnswer());
        }
    }

    public static void timeToCount(){
        Query query = entityManager.createNamedQuery("countSentence");
        System.out.println("Sentence number: " + query.getSingleResult());
    }
}
