package entity;

import jakarta.persistence.*;

@Entity
@NamedQuery(query = "SELECT l FROM LanguageEntity l", name = "languageQuery")
@Table(name = "language", schema = "glosor")
public class LanguageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "languageID")
    private int languageId;
    @Basic
    @Column(name = "languageName")
    private String languageName;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguageEntity that = (LanguageEntity) o;

        if (languageId != that.languageId) return false;
        if (languageName != null ? !languageName.equals(that.languageName) : that.languageName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = languageId;
        result = 31 * result + (languageName != null ? languageName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getLanguageName();
    }
}
