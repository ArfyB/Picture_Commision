package commision.UserDetailService;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private final String usernick;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String usernick) {
        super(username, password, authorities);
        this.usernick = usernick;
    }

    public String getUsernick() {
        return usernick;
    }
}
