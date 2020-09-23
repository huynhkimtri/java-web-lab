package trihk.socialnetwork.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.socialnetwork.entity.Account;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-22T16:14:39")
@StaticMetamodel(AccountRole.class)
public class AccountRole_ { 

    public static volatile CollectionAttribute<AccountRole, Account> accountCollection;
    public static volatile SingularAttribute<AccountRole, String> name;
    public static volatile SingularAttribute<AccountRole, Integer> id;

}