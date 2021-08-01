package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.dto.LoginDTO;
import com.iktpreobuka.Gradebook.entities.Admin;
import com.iktpreobuka.Gradebook.entities.Roles;
import com.iktpreobuka.Gradebook.entities.User;
import com.iktpreobuka.Gradebook.repositories.AdminRepository;
import com.iktpreobuka.Gradebook.repositories.RolesRepository;
import com.iktpreobuka.Gradebook.repositories.UserRepository;
import com.iktpreobuka.Gradebook.scurity.TokenUtils;
import com.iktpreobuka.Gradebook.servicies.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleInfoNotFoundException;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepository rolesRepository;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<String>(tokenUtils.generateToken(loginDTO.getUsername()),
                    HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){

        User user1=new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user1);

        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addAdmin")
    public ResponseEntity<?>addAdmin(@RequestParam String firstName,@RequestParam String lastName ){

        Admin admin1=new Admin();
       admin1.setFirstName(firstName);
       admin1.setLastName(lastName);

        final Admin save = adminRepository.save(admin1);

        return new ResponseEntity<>(save,HttpStatus.ACCEPTED);
    }

       @RequestMapping(method = RequestMethod.PUT,value = "/addUserToAdmin/{userId}")
      public ResponseEntity<?> addUserToAdmin(@PathVariable Long userId,@RequestParam Long adminId){

        User user=userRepository.findById(userId).get();
        Admin admin=adminRepository.findById(adminId).get();
        admin.setUser(user);
        adminRepository.save(admin);
        return new ResponseEntity<>(admin,HttpStatus.ACCEPTED);


       }
//       @RequestMapping(method = RequestMethod.GET,value = "/")
//      public List<User> canFind(String username){
//
//        List<User> us =userDao.findUserByUsername(username);
//        return us;
//       }
        @RequestMapping(method = RequestMethod.POST,path = "/addRole")
      public ResponseEntity<?> addRole(@RequestParam String roleName){
        Roles role=new Roles();
        role.setName(roleName);
        rolesRepository.save(role);

        return new ResponseEntity<>(role,HttpStatus.ACCEPTED);
        }
       @RequestMapping(method = RequestMethod.POST,value = "addRoleToUser")
    public ResponseEntity<User> addRoleToUser(@RequestParam Long userId,@RequestParam Long roleId){

        User user=userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Roles role=rolesRepository.findById(roleId).orElseThrow(()-> new UsernameNotFoundException("Role not found"));
        user.setRoles(role);
        userRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
       }
}
