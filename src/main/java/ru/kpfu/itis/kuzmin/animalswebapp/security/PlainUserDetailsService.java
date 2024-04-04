package ru.kpfu.itis.kuzmin.animalswebapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.RoleRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.UsersRepository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlainUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser =  userRepository.findByLogin(username);


        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRoles(roleRepository.findRolesByUserId(user.getId()));
            return new PlainUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found");

    }
}
