package com.raro.web.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.raro.web.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.raro.web.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.raro.web.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Profile.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Follow.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Blockeduser.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".blogs", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".comments", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".messages", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".followeds", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".followings", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".blockingusers", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".blockedusers", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".interests", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".activities", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Party.class.getName() + ".celebs", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Blog.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Blog.class.getName() + ".posts", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Post.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Post.class.getName() + ".comments", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Post.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Post.class.getName() + ".topics", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Comment.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Frontpageconfig.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Album.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Album.class.getName() + ".photos", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Photo.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Message.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Notification.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Proposal.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Proposal.class.getName() + ".votes", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Vote.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Topic.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Topic.class.getName() + ".posts", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Tag.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Tag.class.getName() + ".posts", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Interest.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Interest.class.getName() + ".parties", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Activity.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Activity.class.getName() + ".parties", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Celeb.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Celeb.class.getName() + ".parties", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Country.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Country.class.getName() + ".states", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.State.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.State.class.getName() + ".cities", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.City.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.City.class.getName() + ".addresses", jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Address.class.getName(), jcacheConfiguration);
            cm.createCache(com.raro.web.domain.Urllink.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
