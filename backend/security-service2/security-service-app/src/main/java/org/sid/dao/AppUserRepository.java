package org.sid.dao;

import org.sid.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public AppUser findByUsername(String username);//lors de l'authentification et qd 
    //l utilisateur saisira son username on va vérifier si ce username existe dans 
    //la base de donnée ou non 
    
    //cette methode va faire automatiquement la meme 
    //demarche que la methode predefini findbyid donc pas la peine de chercher a implementer 
    //cette methode
}
