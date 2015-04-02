/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Test
 *
 * @author Oscar Wei
 * @since 2015/4/2
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/2 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate;

import tw.com.oscar.orm.hibernate.domain.Role;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/2
 * @since 2015/4/2
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Collection<Role> roles = new ArrayList<>();
        Role role;

        for (int i = 0; i < 3; i++) {
            role = new Role();
            role.setRoleName("ROLE_".concat("" + (i + 1)));
            role.setDescription("DES : ".concat("" + (i + 1)));
            roles.add(role);
        }

        Role aRole = new Role();
        aRole.setRoleName("ROLE_1");

        Role role1 = roles.iterator().next();
        boolean isSameReference = role1 != aRole;
        boolean isContain = roles.contains(aRole);
        assert isSameReference : "WRONG...";
    }
}
