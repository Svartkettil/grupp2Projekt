import crud.UserCrud;
import crud.WordCrud;
import entity.LanguageEntity;
import entity.WordEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WordQuiz {
    public static Scanner scanner = new Scanner(System.in);
    EntityManager entityManager;
    public WordQuiz(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public static int points = 0;
    public static void askQuizQuestions(EntityManager entityManager){
        points=0;
        Login.login(entityManager);
        Query query = entityManager.createNamedQuery("wordQuery");

        LanguageEntity choiceOfLanguage = WordCrud.languageInput(entityManager, scanner);

        List<WordEntity> listOfGlosor = query.getResultList( );
        var languageFilter = new ArrayList<>(listOfGlosor.stream()
                .filter(i -> i.getLanguageByWordLanguageId().equals(choiceOfLanguage)).toList());
        Collections.shuffle(languageFilter);
        for( WordEntity w:languageFilter){
            System.out.println("Översätt ordet: " + w.getWordName());
            String answer = scanner.nextLine().toLowerCase();
            if (wordIsTrue(answer, String.valueOf(w.getWordAnswer()))){
                points++;
                System.out.println("Rätt!");
            }
            else System.out.println("Fel svar! Rätt svar är " + w.getWordAnswer());
        }
        System.out.println("Du fick " + points + " poäng!");
        addPointsToUser(points, entityManager);
    }
    public static boolean wordIsTrue(String answer, String rightAnswer) {
        return answer.equalsIgnoreCase(rightAnswer);
    }
    public static void addPointsToUser(int points, EntityManager entityManager){
        if(!Login.activeUser.equals("")){
            UserCrud.updatePoints(points, UserCrud.findUserByName(Login.activeUser));
        }
        points=0;
    }

}
