package org.sid.service;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional//pour fair le commit automatiquement a chaque fois il y a un ajout dans la base de donnée
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    //on peut instancier nous meme les constructeur avec parametre si on ne veut pas utiliser @Autowired au dessus 
    //de chaque variable repository et dans ce cas ce sont (appUserRepository et appRoleRepository et bCryptPasswordEncoder)
    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,BCryptPasswordEncoder bCryptPasswordEncoder ) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword) {
    	//we must first check if the username already exists or not
    	AppUser user = appUserRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("User already exists");
        //an exception is generated if the password entered is not the same as the one reconfirmed
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        //if the conditions have been respected we add the new user
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setActived(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));//we record the encrypted password
        //pour eviter des erreurs de pointage sur le null on a interet a ajouter au début 
        //de l'app dans le main des appRole deja pret avec le nom des differents role existant 
        appUserRepository.save(appUser);
        addRoleToUser(username,"USER");//par defaut un nouveau user doit avoir le role USER
        return appUser;
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);

    }
}
