package trihk.socialnetwork.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.socialnetwork.entity.Account;
import trihk.socialnetwork.entity.Article;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-24T13:53:32")
@StaticMetamodel(ArticleEmotion.class)
public class ArticleEmotion_ { 

    public static volatile SingularAttribute<ArticleEmotion, Account> authorEmail;
    public static volatile SingularAttribute<ArticleEmotion, Date> createdDate;
    public static volatile SingularAttribute<ArticleEmotion, Boolean> isLike;
    public static volatile SingularAttribute<ArticleEmotion, Article> articleId;
    public static volatile SingularAttribute<ArticleEmotion, Integer> id;
    public static volatile SingularAttribute<ArticleEmotion, Boolean> isDislike;

}