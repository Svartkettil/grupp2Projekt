package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sentence", schema = "glosor")
public class SentenceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sentenceID")
    private int sentenceId;
    @Basic
    @Column(name = "languageSentenceID")
    private Integer languageSentenceId;
    @Basic
    @Column(name = "sentenceName")
    private String sentenceName;
    @Basic
    @Column(name = "sentenceAnswer")
    private String sentenceAnswer;
    @ManyToOne
    @JoinColumn(name = "languageSentenceID", referencedColumnName = "languageID")
    private LanguageEntity languageByLanguageSentenceId;

    public int getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(int sentenceId) {
        this.sentenceId = sentenceId;
    }

    public Integer getLanguageSentenceId() {
        return languageSentenceId;
    }

    public void setLanguageSentenceId(Integer languageSentenceId) {
        this.languageSentenceId = languageSentenceId;
    }

    public String getSentenceName() {
        return sentenceName;
    }

    public void setSentenceName(String sentenceName) {
        this.sentenceName = sentenceName;
    }

    public String getSentenceAnswer() {
        return sentenceAnswer;
    }

    public void setSentenceAnswer(String sentenceAnswer) {
        this.sentenceAnswer = sentenceAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SentenceEntity that = (SentenceEntity) o;

        if (sentenceId != that.sentenceId) return false;
        if (languageSentenceId != null ? !languageSentenceId.equals(that.languageSentenceId) : that.languageSentenceId != null)
            return false;
        if (sentenceName != null ? !sentenceName.equals(that.sentenceName) : that.sentenceName != null) return false;
        if (sentenceAnswer != null ? !sentenceAnswer.equals(that.sentenceAnswer) : that.sentenceAnswer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sentenceId;
        result = 31 * result + (languageSentenceId != null ? languageSentenceId.hashCode() : 0);
        result = 31 * result + (sentenceName != null ? sentenceName.hashCode() : 0);
        result = 31 * result + (sentenceAnswer != null ? sentenceAnswer.hashCode() : 0);
        return result;
    }

    public LanguageEntity getLanguageByLanguageSentenceId() {
        return languageByLanguageSentenceId;
    }

    public void setLanguageByLanguageSentenceId(LanguageEntity languageByLanguageSentenceId) {
        this.languageByLanguageSentenceId = languageByLanguageSentenceId;
    }
}
