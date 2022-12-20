import entity.LanguageEntity;
import entity.WordEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    public static Scanner scanner = new Scanner(System.in);
    EntityManager entityManager;
    public Quiz(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public static int points = 0;
    public static void askQuizQuestions(EntityManager entityManager){
        Query query = entityManager.createNamedQuery("wordQuery");

        List<WordEntity> listOfGlosor = query.getResultList( );
        Collections.shuffle(listOfGlosor);

        for( WordEntity w:listOfGlosor ){
            System.out.println("Hur säger man " + w.getWordName() +" på språket " + w.getLanguageByWordLanguageId());
            String answer = scanner.nextLine().toLowerCase();
            if (wordIsTrue(answer, String.valueOf(w.getWordAnswer()))){
                points++;
                System.out.println("Rätt!");
            }
            else System.out.println("Fel svar! Rätt svar är " + w.getWordAnswer());
        }
        System.out.println("Du fick " + points + " poäng!");
    }
    public static boolean wordIsTrue(String answer, String rightAnswer) {
        return answer.equalsIgnoreCase(rightAnswer);
    }
    public static void addPointsTouser(EntityManager entityManager, int points){
        if(!Login.login(entityManager).equals("")){

        }
    }
    //public LanguageEntity chooseLanguage(){
    //}

}
