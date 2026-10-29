package entities;

import entities.City;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-02-08T09:56:00")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, Integer> idUser;
    public static volatile SingularAttribute<Korisnik, String> password;
    public static volatile SingularAttribute<Korisnik, Float> money;
    public static volatile SingularAttribute<Korisnik, String> surname;
    public static volatile SingularAttribute<Korisnik, String> name;
    public static volatile SingularAttribute<Korisnik, City> idCity;
    public static volatile SingularAttribute<Korisnik, String> adress;
    public static volatile SingularAttribute<Korisnik, Boolean> isAdmin;
    public static volatile SingularAttribute<Korisnik, String> username;

}