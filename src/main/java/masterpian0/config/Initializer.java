package masterpian0.config;

import org.springframework.stereotype.Component;
import masterpian0.model.Role;
import masterpian0.model.User;
import masterpian0.service.RoleService;
import masterpian0.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Initializer {
    private final UserService userService;
    private final RoleService roleService;

    public Initializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        roleService.addRole(new Role("ADMIN"));
        roleService.addRole(new Role("USER"));

        User user = new User();
        user.setName("admin");
        user.setLastName("Ivanov");
        user.setCity("Moscow");
        user.setPassword("admin");
        user.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.addUser(user);

        User user1 = new User();
        user1.setName("Petr");
        user1.setLastName("Petrov");
        user1.setCity("Saint-Petersburg");
        user1.setPassword("Petr");
        user1.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.addUser(user1);

        User user2 = new User();
        user2.setName("Kate");
        user2.setLastName("Sidorova");
        user2.setCity("Kaluga");
        user2.setPassword("Kate");
        user2.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.addUser(user2);

        User user3 = new User();
        user3.setName("Oleg");
        user3.setLastName("Stepanov");
        user3.setCity("Nizhniy Novgorod");
        user3.setPassword("Oleg");
        user3.setRoles(Set.of(roleService.getRoleByName("ADMIN"),
                roleService.getRoleByName("USER")));
        userService.addUser(user3);

        User user4 = new User();
        user4.setName("Anya");
        user4.setLastName("Vasilyeva");
        user4.setCity("Sochi");
        user4.setPassword("Anya");
        user4.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.addUser(user4);
    }
}


