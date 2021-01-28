package org.sid.service;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

public interface AccountService
{
	public AppUser saveUser(String username, String password, String confirmedPassword);//à utiliser qd l user voudrait s'enregistrer pour la premiere fois
    public AppRole save(AppRole role);//pour ajouter un nouveau role
    public AppUser loadUserByUsername(String username);//pour charger un user sachant son username
    public void addRoleToUser(String username,String rolename);//pour ajouter un role a un user

}
