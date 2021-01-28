package org.sid.sec;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="med@youssfi.net";
    public static final long EXPIRATION=10*24*3600*1000;//il faut ajouter 1000 pour que ca soit en miliseconde et par consequent on aura 10jours
    public static final String HEADER_PREFIX="Bearer ";
}
