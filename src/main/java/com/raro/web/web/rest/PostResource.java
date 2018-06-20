package com.raro.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raro.web.domain.Post;

import com.raro.web.repository.PostRepository;
import com.raro.web.repository.search.PostSearchRepository;
import com.raro.web.web.rest.errors.BadRequestAlertException;
import com.raro.web.web.rest.util.HeaderUtil;
import com.raro.web.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Post.
 */
@RestController
@RequestMapping("/api")
public class PostResource {

    private final Logger log = LoggerFactory.getLogger(PostResource.class);

    private static final String ENTITY_NAME = "post";

    private final PostRepository postRepository;

    private final PostSearchRepository postSearchRepository;

    public PostResource(PostRepository postRepository, PostSearchRepository postSearchRepository) {
        this.postRepository = postRepository;
        this.postSearchRepository = postSearchRepository;
    }

    /**
     * POST  /posts : Create a new post.
     *
     * @param post the post to create
     * @return the ResponseEntity with status 201 (Created) and with body the new post, or with status 400 (Bad Request) if the post has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/posts")
    @Timed
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) throws URISyntaxException {
        log.debug("REST request to save Post : {}", post);
        if (post.getId() != null) {
            throw new BadRequestAlertException("A new post cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Post result = postRepository.save(post);
        postSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /posts : Updates an existing post.
     *
     * @param post the post to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated post,
     * or with status 400 (Bad Request) if the post is not valid,
     * or with status 500 (Internal Server Error) if the post couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/posts")
    @Timed
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post) throws URISyntaxException {
        log.debug("REST request to update Post : {}", post);
        if (post.getId() == null) {
            return createPost(post);
        }
        Post result = postRepository.save(post);
        postSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, post.getId().toString()))
            .body(result);
    }

    /**
     * GET  /posts : get all the posts.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of posts in body
     */
    @GetMapping("/posts")
    @Timed
    public ResponseEntity<List<Post>> getAllPosts(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("topnews1-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where topNews1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getTopNews1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("topnews2-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where topNews2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getTopNews2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("topnews3-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where topNews3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getTopNews3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("topnews4-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where topNews4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getTopNews4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("topnews5-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where topNews5 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getTopNews5() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("latestnews1-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where latestNews1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getLatestNews1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("latestnews2-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where latestNews2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getLatestNews2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("latestnews3-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where latestNews3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getLatestNews3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("latestnews4-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where latestNews4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getLatestNews4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("latestnews5-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where latestNews5 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getLatestNews5() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("breakingnews1-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where breakingNews1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getBreakingNews1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentposts1-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where recentPosts1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getRecentPosts1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentposts2-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where recentPosts2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getRecentPosts2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentposts3-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where recentPosts3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getRecentPosts3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentposts4-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where recentPosts4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getRecentPosts4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles1-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles2-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles3-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles4-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles5-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles5 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles5() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles6-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles6 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles6() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles7-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles7 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles7() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles8-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles8 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles8() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles9-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles9 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles9() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("featuredarticles10-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where featuredArticles10 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getFeaturedArticles10() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("popularnews1-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where popularNews1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getPopularNews1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("popularnews2-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where popularNews2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getPopularNews2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("popularnews3-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where popularNews3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getPopularNews3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("popularnews4-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where popularNews4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getPopularNews4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("popularnews5-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where popularNews5 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getPopularNews5() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("popularnews6-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where popularNews6 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getPopularNews6() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("popularnews7-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where popularNews7 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getPopularNews7() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("popularnews8-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where popularNews8 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getPopularNews8() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("weeklynews1-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where weeklyNews1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getWeeklyNews1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("weeklynews2-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where weeklyNews2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getWeeklyNews2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("weeklynews3-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where weeklyNews3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getWeeklyNews3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("weeklynews4-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where weeklyNews4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getWeeklyNews4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("newsfeeds1-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where newsFeeds1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getNewsFeeds1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("newsfeeds2-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where newsFeeds2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getNewsFeeds2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("newsfeeds3-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where newsFeeds3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getNewsFeeds3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("newsfeeds4-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where newsFeeds4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getNewsFeeds4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("newsfeeds5-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where newsFeeds5 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getNewsFeeds5() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("newsfeeds6-is-null".equals(filter)) {
            log.debug("REST request to get all Posts where newsFeeds6 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .filter(post -> post.getNewsFeeds6() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        log.debug("REST request to get a page of Posts");
        Page<Post> page = postRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/posts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /posts/:id : get the "id" post.
     *
     * @param id the id of the post to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the post, or with status 404 (Not Found)
     */
    @GetMapping("/posts/{id}")
    @Timed
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        log.debug("REST request to get Post : {}", id);
        Post post = postRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(post));
    }

    /**
     * DELETE  /posts/:id : delete the "id" post.
     *
     * @param id the id of the post to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/posts/{id}")
    @Timed
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        log.debug("REST request to delete Post : {}", id);
        postRepository.delete(id);
        postSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/posts?query=:query : search for the post corresponding
     * to the query.
     *
     * @param query the query of the post search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/posts")
    @Timed
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Posts for query {}", query);
        Page<Post> page = postSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/posts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
