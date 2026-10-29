package entities;

import entities.Korisnik;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-12-21T11:21:09")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, String> cityName;
    public static volatile SingularAttribute<City, Integer> idCity;
    public static volatile CollectionAttribute<City, Korisnik> korisnikCollection;

}