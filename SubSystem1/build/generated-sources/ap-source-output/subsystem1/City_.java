package subsystem1;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import subsystem1.Korisnik;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-12-20T11:10:16")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, String> cityName;
    public static volatile SingularAttribute<City, Integer> idCity;
    public static volatile CollectionAttribute<City, Korisnik> korisnikCollection;

}