package fr.xebia.xke.model;

import com.google.common.collect.Sets;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Member extends BaseEntity {

    @ManyToMany(mappedBy = "members", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    protected Set<Team> teams;

    public Member() {
        super();

        this.teams = Sets.newHashSet();
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        team.getMembers().add(this);

        teams.add(team);
    }

    public void removeTeam(Team team) {
        team.getMembers().remove(this);

        teams.remove(team);
    }

}
