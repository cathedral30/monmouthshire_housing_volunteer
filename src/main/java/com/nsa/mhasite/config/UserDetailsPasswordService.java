package com.nsa.mhasite.config;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsPasswordService {

    /**
     * Modify the specified user's password. This should change the user's password in the
     * persistent user repository (datbase, LDAP etc).
     *
     * @param user the user to modify the password for
     * @param newPassword the password to change to
     * @return the updated UserDetails with the new password
     */
    UserDetails updatePassword(UserDetails user, String newPassword);
}
