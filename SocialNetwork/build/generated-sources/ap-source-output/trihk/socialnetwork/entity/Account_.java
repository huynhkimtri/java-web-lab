package trihk.socialnetwork.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.socialnetwork.entity.AccountRole;
import trihk.socialnetwork.entity.Article;
import trihk.socialnetwork.entity.ArticleEmotion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-24T13:53:32")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile CollectionAttribute<Account, Article> articleCollection;
    public static volatile SingularAttribute<Account, String> encryptPassword;
    public static volatile SingularAttribute<Account, AccountRole> roleId;
    public static volatile SingularAttribute<Account, String> name;
    public static volatile CollectionAttribute<Account, ArticleEmotion> articleEmotionCollection;
    public static volatile SingularAttribute<Account, Boolean> isActive;
    public static volatile SingularAttribute<Account, String> email;

}