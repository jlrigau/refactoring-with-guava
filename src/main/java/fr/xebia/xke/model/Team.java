package fr.xebia.xke.model;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Team extends Member {

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "TEAM_MEMBER", joinColumns = @JoinColumn(name = "TEAM_ID"), inverseJoinColumns = @JoinColumn(name = "MEMBER_ID"))
    private Set<Member> members;

    public Team() {
        super();

        this.members = Sets.newHashSet();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        member.getTeams().add(this);

        members.add(member);
    }

    public void removeMember(Member member) {
        member.getTeams().remove(this);

        members.remove(member);
    }

    public List<Team> getTeamMembers() {
        return Lists.newArrayList(Iterables.filter(members, Team.class));
    }

    public List<User> getUserMembers() {
        return Lists.newArrayList(Iterables.filter(members, User.class));
    }

}
