package entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {


        boolean quit = false;
        printActions();
        while(!quit) {
            System.out.println("\nVälj (8 för att visa val):");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("\nStänger ner...");
                    entityManager.close();
                    entityManagerFactory.close();
                    quit = true;
                    break;

                case 1:
                    showBook();
                    break;
                case 2:
                    showBookByAuthor();
                    break;
                case 3:
                    showCategory();
                    break;
                case 4:
                    newBookInput();
                    break;
                case 5:
                    updateBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    //showBooksQuery();
                    showBooksNamedQuery();
                    break;
                case 8:
                    printActions();
                    break;
            }
        }
    }

    private static void printActions() {
        System.out.println("\nVälj:\n");
        System.out.println("0  - Stäng av\n" +
                "1  - Visa en bok\n" +
                "2  - Visa alla böcker för en författare\n" +
                "3  - Visa alla kategorier\n" +
                "4  - Lägg till en bok\n" +
                "5  - Uppdatera en bok\n" +
                "6  - Ta bort en bok\n" +
                "7  - Visa alla böcker.\n" +
                "8  - Visa en lista över alla val.");
    }

    private static void newBook(String titel, String forfattare){
        entityManager.getTransaction().begin();
        BokEntity bok = new BokEntity();
        System.out.println("Enter name of category: ");
        String kategoriVal = scanner.nextLine();
        KategoriEntity kategori = new KategoriEntity();
        kategori.setKategoriNamn(kategoriVal);
        entityManager.persist(kategori);


        //KategoriEntity kategori = entityManager.find( KategoriEntity.class, 1);
        bok.setBokTitel(titel);
        bok.setBokForfattare(forfattare);
        bok.setKategoriByBokKategoriId(kategori);

        entityManager.persist(bok);
        entityManager.getTransaction().commit();

        System.out.println("Du har lagt till en ny bok");

    }

    private static void newBookInput(){
        System.out.println("Skriv in titel på boken: ");
        String inputTitel = scanner.nextLine();
        System.out.println("Skriv in författare på boken: ");
        String inputForfattare = scanner.nextLine();
        newBook(inputTitel,inputForfattare);
        scanner.nextLine();
    }

    private static void showBook(){
        System.out.println("Enter id of book you wanna se: ");
        int id = scanner.nextInt();
        BokEntity bok = entityManager.find( BokEntity.class, id );
        System.out.println("Bok ID = " + bok.getBokId( ));
        System.out.println("Bok Titel = " + bok.getBokTitel( ));
        System.out.println("Kategori = " + bok.getKategoriByBokKategoriId());
    }

    private static void updateBook(){
        entityManager.getTransaction().begin();
        BokEntity bok = entityManager.find( BokEntity.class, 1 );
        bok.setBokTitel("Karlsson på taket igen");
        bok.setBokForfattare("Astrid Lindgren");
        entityManager.persist(bok);
        entityManager.getTransaction().commit();

        System.out.println("Du har uppdaterat boken");

    }

    private static void deleteBook(){
        String id = scanner.nextLine();
        entityManager.getTransaction().begin();
        BokEntity bok = entityManager.find( BokEntity.class, Integer.parseInt(id));

        entityManager.remove( bok );
        entityManager.getTransaction().commit();

        System.out.println("Du har tagit bort boken");
    }

    private static void showBookByAuthor(){
        Query query = entityManager.createNamedQuery("bokForfattareQuery");

        query.setParameter("forfattare", "Astrid Lindgren");
        List<BokEntity> list = query.getResultList( );

        for( BokEntity b:list ){
            System.out.print("Bok ID :" + b.getBokTitel());
            System.out.println("\t Bok Titel :" + b.getBokForfattare());
            System.out.println("Kategori = " + b.getKategoriByBokKategoriId());
        }
    }

    private static void showBooksNamedQuery(){
        Query query = entityManager.createNamedQuery("bokQuery");

        List<BokEntity> list = query.getResultList( );

        for( BokEntity b:list ){
            System.out.println("BokID: " + b.getBokId() + "\n" +
                    "Boktitel: " + b.getBokTitel() + "\n" +
                    "Bokförfattare: " + b.getBokForfattare() + "\n");
        }
    }

    private static void showBooksQuery(){
        Query query = entityManager.createQuery("SELECT b FROM BokEntity b");

        List<BokEntity> list = query.getResultList( );

        for( BokEntity b:list ){
            System.out.print("Bok ID :" + b.getBokTitel());
            System.out.println("\t Bok Titel :" + b.getBokForfattare());
        }
    }

    private static void countBooksQuery(){
        Query query = entityManager.createQuery("SELECT COUNT(b.bokId) FROM BokEntity b");

        System.out.println(query.getSingleResult());
    }

    private static void showCategory(){
        Query query = entityManager.createNamedQuery("kategoriQuery");

        query.setParameter("kategori", "Barnbok");
        List<KategoriEntity> list = query.getResultList( );

        for( KategoriEntity k:list ){
            System.out.println("\t Bok Titel :" + k.getKategoriNamn());
        }
    }
}




