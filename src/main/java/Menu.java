import crud.LanguageCrud;
import crud.SentenceCrud;
import crud.UserCrud;
import crud.WordCrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        mainMenu();
    }


    public static void userMenu(){
        int input=-1;
        while (input!=0){
            printUserMenu();
            input = Integer.parseInt(scanner.nextLine());
            switch (input){
                case 1 -> usercrud.newUserInput();
                case 2 -> usercrud.showAllUsers();
                case 3 -> usercrud.updateUser();
                case 4 -> usercrud.deleteUser();
                case 5 -> usercrud.countUsersQuery();
                case 0 -> System.out.println("Lämnar användarmenyn");
            }
        }
    }
    public static void languageMenu() {
        int input = -1;
        LanguageCrud languageCrud = new LanguageCrud();
        while (input != 0) {
            printLanguageMenu();
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1 -> languageCrud.newLanguageInput(entityManager, scanner);
                case 2 -> languageCrud.showLanguageInput(entityManager, scanner);
                case 3 -> languageCrud.showAllLanguage(entityManager);
                case 4 -> languageCrud.updateLanguageInput(entityManager, scanner);
                case 5 -> languageCrud.deleteLanguage(entityManager, scanner);
                case 6 -> languageCrud.countLanguage(entityManager);
            }

        }
    }
    public static void sentenceMenu(){
        int input=-1;
        SentenceCrud sentenceCrud = new SentenceCrud();
        while (input!=0){
            printSentenceMenu();
            input = Integer.parseInt(scanner.nextLine());
            switch (input){
                case 1 -> sentenceCrud.Sentenceinput();
                case 2 -> sentenceCrud.showSentence();
                case 3 -> sentenceCrud.showAllSentence();
                case 4 -> sentenceCrud.updateSentence();
                case 5 -> sentenceCrud.deleteSentence();
                case 6 -> sentenceCrud.timeToCount();
            }
        }
    }
    public static void wordMenu(){
        int input=-1;
        WordCrud wordCrud = new WordCrud();
        while (input!=0){
            printWordMenu();
            input = Integer.parseInt(scanner.nextLine());
            switch (input){
                case 1 -> wordCrud.wordInput(entityManager, scanner);
                case 2 -> wordCrud.showWord(entityManager, scanner);
                case 3 -> wordCrud.showAllWord(entityManager);
                case 4 -> wordCrud.updateWord(entityManager, scanner);
                case 5 -> wordCrud.deleteWord(entityManager, scanner);
                case 6 -> wordCrud.countWord(entityManager);
            }
        }

    }
    public static void mainMenu(){
        int input=-1;
        while (input!=0){
            printMainMenu();
            input = Integer.parseInt(scanner.nextLine());
            switch (input){
                case 1 -> userMenu();
                case 2 -> languageMenu();
                case 3 -> sentenceMenu();
                case 4 -> wordMenu();
                case 0 -> closeProgram();
            }
        }
    }
    public static void printWordMenu(){
        System.out.println("""
                1. Skapa nytt ord
                2. Visa ord
                3. Visa alla ord
                4. Ändra ord
                5. Ta bort ord
                6. Antal ord
                0. Gå tillbaka
                """);
    }
    public static void printSentenceMenu(){
        System.out.println("""
                1. En ny mening
                2. Visa en mening
                3. Visa alla meningar
                4. Uppdatera en mening
                5. Ta bort en mening
                6. Räkna meningar
                0. Tillbaka
                """);
    }
    public static void printUserMenu(){
        System.out.println("""
                1. Skapa ny användare
                2. Visa alla användare
                3. Ändra användare
                4. Ta bort användare
                5. Antal användare
                0. Gå ifrån menyn
                """);
    }
    public static void printLanguageMenu(){
        System.out.println("""
                1. Lägg in nytt språk
                2. Visa språk
                3. Visa alla språk
                4. Uppdatera namn på språk
                5. Utrota ett språk
                6. Antal språk
                0. Backa
                """);
    }
    public static void printMainMenu(){
        System.out.println("""
                1. Användarmeny
                2. Språkmeny
                3. Meningsmeny
                4. Ordmeny
                5. OrdQuiz
                6. MeningQuiz
                7. Rankad användarmeny
                0. Avsluta program
                """);
    }

    public static void closeProgram(){
        entityManager.close();
        entityManagerFactory.close();
        System.exit(1);
    }
}
