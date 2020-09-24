package trihk.socialnetwork.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.socialnetwork.entity.Account;
import trihk.socialnetwork.entity.ArticleEmotion;
import trihk.socialnetwork.entity.ArticleStatus;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-24T13:53:32")
@StaticMetamodel(Article.class)
public class Article_ { 

    public static volatile SingularAttribute<Article, Account> authorEmail;
    public static volatile SingularAttribute<Article, Date> createdDate;
    public static volatile SingularAttribute<Article, ArticleStatus> statusId;
    public static volatile SingularAttribute<Article, String> contents;
    public static volatile SingularAttribute<Article, String> imageUrl;
    public static volatile SingularAttribute<Article, Date> publishDate;
    public static volatile SingularAttribute<Article, String> description;
    public static volatile SingularAttribute<Article, Integer> numOfDislike;
    public static volatile SingularAttribute<Article, Integer> id;
    public static volatile SingularAttribute<Article, String> title;
    public static volatile SingularAttribute<Article, Integer> numOfLike;
    public static volatile CollectionAttribute<Article, ArticleEmotion> articleEmotionCollection;

}