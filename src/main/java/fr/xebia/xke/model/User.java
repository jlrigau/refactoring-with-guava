package fr.xebia.xke.model;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class User extends Member {

    private static Predicate<Role> HAS_ROLE_NAME(final String roleName) {
        return new Predicate<Role>() {

            @Override
            public boolean apply(Role role) {
                return role != null && roleName.equals(role.getName());
            }

        };
    }

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Set<Role> roles;

    public User() {
        super();

        this.roles = Sets.newHashSet();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean hasRole(String roleName) {
        return roleName != null && Iterables.any(roles, HAS_ROLE_NAME(roleName));
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 29 : getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        User rhs = (User) obj;

        if (getId() == null) {
            return Objects.equal(name, rhs.name);
        } else {
            return getId().equals(rhs.getId());
        }
    }

}
