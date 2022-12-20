package entity;

import jakarta.persistence.*;

@Entity
@NamedQuery(query = "SELECT w FROM WordEntity w", name = "wordQuery")
@NamedQuery(query = "SELECT COUNT(w.wordId) FROM WordEntity w", name = "countWord")
@Table(name = "word", schema = "glosor")
public class WordEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "wordID")
    private int wordId;
    @Basic
    @Column(name = "wordLanguageID", insertable = false, updatable = false)
    private Integer wordLanguageId;
    @Basic
    @Column(name = "wordName")
    private String wordName;
    @Basic
    @Column(name = "wordAnswer")
    private String wordAnswer;
    @ManyToOne
    @JoinColumn(name = "wordLanguageID", referencedColumnName = "languageID")
    private LanguageEntity languageByWordLanguageId;

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public Integer getWordLanguageId() {
        return wordLanguageId;
    }

    public void setWordLanguageId(Integer wordLanguageId) {
        this.wordLanguageId = wordLanguageId;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordAnswer() {
        return wordAnswer;
    }

    public void setWordAnswer(String wordAnswer) {
        this.wordAnswer = wordAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordEntity that = (WordEntity) o;

        if (wordId != that.wordId) return false;
        if (wordLanguageId != null ? !wordLanguageId.equals(that.wordLanguageId) : that.wordLanguageId != null)
            return false;
        if (wordName != null ? !wordName.equals(that.wordName) : that.wordName != null) return false;
        if (wordAnswer != null ? !wordAnswer.equals(that.wordAnswer) : that.wordAnswer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wordId;
        result = 31 * result + (wordLanguageId != null ? wordLanguageId.hashCode() : 0);
        result = 31 * result + (wordName != null ? wordName.hashCode() : 0);
        result = 31 * result + (wordAnswer != null ? wordAnswer.hashCode() : 0);
        return result;
    }

    public LanguageEntity getLanguageByWordLanguageId() {
        return languageByWordLanguageId;
    }

    public void setLanguageByWordLanguageId(LanguageEntity languageByWordLanguageId) {
        this.languageByWordLanguageId = languageByWordLanguageId;
    }

    @Override
    public String toString() {
        return "Id: " + wordId +
                " Ord: " + wordName +
                " Översättning: " + wordAnswer +
                " Språk: " + languageByWordLanguageId;
    }
}
