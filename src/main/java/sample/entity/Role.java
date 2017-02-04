package sample.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liminghang on 1/17/17.
 */
@Entity
@Table(name="auth_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permission> permissionList;

    @ManyToMany
    @JoinTable(name = "auth_user_role",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = {@JoinColumn(name = "user_id") })
    private List<AuthUser> userList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<AuthUser> getUserList() {
        return userList;
    }

    public void setUserList(List<AuthUser> userList) {
        this.userList = userList;
    }

    @Transient
    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<>();
        getPermissionList().forEach(permission -> list.add(permission.getName()));
        return list;
    }
}
