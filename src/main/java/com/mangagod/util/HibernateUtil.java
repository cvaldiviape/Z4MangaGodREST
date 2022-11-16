package com.mangagod.util;

import com.mangagod.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static SessionFactory factory = null;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(CategoryEntity.class);
        cfg.addAnnotatedClass(CharacterEntity.class);
        cfg.addAnnotatedClass(CountryEntity.class);
        cfg.addAnnotatedClass(DemographyEntity.class);
        cfg.addAnnotatedClass(GenreEntity.class);
        cfg.addAnnotatedClass(JobEntity.class);
        cfg.addAnnotatedClass(MangakaEntity.class);
        cfg.addAnnotatedClass(RoleEntity.class);
        cfg.addAnnotatedClass(StoryEntity.class);
        cfg.addAnnotatedClass(StoryMangakaEntity.class);
        cfg.addAnnotatedClass(TypeCharacterEntity.class);
        cfg.addAnnotatedClass(UserEntity.class);
        factory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
        return factory.openSession();
    }

}
