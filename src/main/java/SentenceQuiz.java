import crud.UserCrud;
import crud.WordCrud;
import entity.LanguageEntity;
import entity.SentenceEntity;
import entity.WordEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SentenceQuiz {
    public static Scanner scanner = new Scanner(System.in);
    EntityManager entityManager;
    public SentenceQuiz(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public static int points = 0;
    public static void askQuizQuestions(EntityManager entityManager){
        points=0;
        Login.login(entityManager);
        ArrayList<SentenceEntity> languageFilter = chooseLanguage(entityManager);
        for( SentenceEntity w:languageFilter){
            System.out.println("Översätt meningen: " + w.getSentenceName());
            String answer = scanner.nextLine().toLowerCase();
            if (wordIsTrue(answer, String.valueOf(w.getSentenceAnswer()))){
                points++;
                System.out.println("Rätt!");
            }
            else System.out.println("Fel svar! Rätt svar är " + w.getSentenceAnswer());
        }
        System.out.println("Du fick " + points + " poäng!");
        addPointsToUser(points, entityManager);
    }

    private static ArrayList<SentenceEntity> chooseLanguage(EntityManager entityManager) {
        Query query = entityManager.createNamedQuery("sentenceQuery");
        LanguageEntity choiceOfLanguage = WordCrud.languageInput(entityManager, scanner);
        List<SentenceEntity> listOfGlosor = query.getResultList( );
        var languageFilter = new ArrayList<>(listOfGlosor.stream()
                .filter(i -> i.getLanguageByLanguageSentenceId().equals(choiceOfLanguage)).toList());
        Collections.shuffle(languageFilter);
        return languageFilter;
    }

    public static boolean wordIsTrue(String answer, String rightAnswer) {
        return answer.equalsIgnoreCase(rightAnswer);
    }
    public static void addPointsToUser(int points, EntityManager entityManager){
        if(!Login.activeUser.equals("")){
            UserCrud.updatePoints(points, UserCrud.findUserByName(Login.activeUser));
        }

    }

}



