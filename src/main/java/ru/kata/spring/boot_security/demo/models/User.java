package ru.kata.spring.boot_security.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private Integer age;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Column(name = "password")
    private String password;

    @Column(name = "profession")
    private String profession;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new java.util.LinkedHashSet<>();


    public User() {
    }

    public User(String username, int age, String password, String profession, Set<Role> roles) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.profession = profession;
        this.roles = roles;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Integer getAge() {
        return this.age;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getProfession() {
        return this.profession;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @JsonProperty("roles")
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toList());
//        return getRoles();
    }
    public boolean isAdmin() {
        for (Role role : roles) {
            if (Objects.equals(role.getAuthority(), "ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.getId() +
                ", username='" + this.getUsername() + '\'' +
                ", age=" + this.getAge() +
                ", password='" + this.getPassword() + '\'' +
                ", profession='" + this.getProfession() + '\'' +
                ", roles=" + this.getRoles() +
                '}';
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (!Objects.equals(this$id, other$id)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (!Objects.equals(this$username, other$username)) return false;

        final Object this$age = this.getAge();
        final Object other$age = other.getAge();
        if (!Objects.equals(this$age, other$age)) return false;

        final Object this$profession = this.getProfession();
        final Object other$profession = other.getProfession();
        if (!Objects.equals(this$profession, other$profession)) return false;

        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (!Objects.equals(this$password, other$password)) return false;
        final Object this$roles = this.getRoles();
        final Object other$roles = other.getRoles();
        if (!Objects.equals(this$roles, other$roles)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());

        final Object $age = this.getAge();
        result = result * PRIME + ($age == null ? 43 : $age.hashCode());

        final Object $profession = this.getProfession();
        result = result * PRIME + ($profession == null ? 43 : $profession.hashCode());

        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $roles = this.getRoles();
        result = result * PRIME + ($roles == null ? 43 : $roles.hashCode());
        return result;
    }
}
