package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ranking", schema = "glosor", catalog = "")
public class RankingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rankID")
    private int rankId;
    @Basic
    @Column(name = "userName")
    private String userName;
    @Basic
    @Column(name = "rankName")
    private String rankName;
    @Basic
    @Column(name = "rankUserID")
    private Integer rankUserId;

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Integer getRankUserId() {
        return rankUserId;
    }

    public void setRankUserId(Integer rankUserId) {
        this.rankUserId = rankUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RankingEntity that = (RankingEntity) o;

        if (rankId != that.rankId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (rankName != null ? !rankName.equals(that.rankName) : that.rankName != null) return false;
        if (rankUserId != null ? !rankUserId.equals(that.rankUserId) : that.rankUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rankId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (rankName != null ? rankName.hashCode() : 0);
        result = 31 * result + (rankUserId != null ? rankUserId.hashCode() : 0);
        return result;
    }
}
