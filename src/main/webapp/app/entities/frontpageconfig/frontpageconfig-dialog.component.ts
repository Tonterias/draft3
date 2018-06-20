import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Frontpageconfig } from './frontpageconfig.model';
import { FrontpageconfigPopupService } from './frontpageconfig-popup.service';
import { FrontpageconfigService } from './frontpageconfig.service';
import { Post, PostService } from '../post';
import { Urllink, UrllinkService } from '../urllink';

@Component({
    selector: 'jhi-frontpageconfig-dialog',
    templateUrl: './frontpageconfig-dialog.component.html'
})
export class FrontpageconfigDialogComponent implements OnInit {

    frontpageconfig: Frontpageconfig;
    isSaving: boolean;

    topnews1s: Post[];

    topnews2s: Post[];

    topnews3s: Post[];

    topnews4s: Post[];

    topnews5s: Post[];

    latestnews1s: Post[];

    latestnews2s: Post[];

    latestnews3s: Post[];

    latestnews4s: Post[];

    latestnews5s: Post[];

    breakingnews1s: Post[];

    recentposts1s: Post[];

    recentposts2s: Post[];

    recentposts3s: Post[];

    recentposts4s: Post[];

    featuredarticles1s: Post[];

    featuredarticles2s: Post[];

    featuredarticles3s: Post[];

    featuredarticles4s: Post[];

    featuredarticles5s: Post[];

    featuredarticles6s: Post[];

    featuredarticles7s: Post[];

    featuredarticles8s: Post[];

    featuredarticles9s: Post[];

    featuredarticles10s: Post[];

    popularnews1s: Post[];

    popularnews2s: Post[];

    popularnews3s: Post[];

    popularnews4s: Post[];

    popularnews5s: Post[];

    popularnews6s: Post[];

    popularnews7s: Post[];

    popularnews8s: Post[];

    weeklynews1s: Post[];

    weeklynews2s: Post[];

    weeklynews3s: Post[];

    weeklynews4s: Post[];

    newsfeeds1s: Post[];

    newsfeeds2s: Post[];

    newsfeeds3s: Post[];

    newsfeeds4s: Post[];

    newsfeeds5s: Post[];

    newsfeeds6s: Post[];

    usefullinks1s: Urllink[];

    usefullinks2s: Urllink[];

    usefullinks3s: Urllink[];

    usefullinks4s: Urllink[];

    usefullinks5s: Urllink[];

    usefullinks6s: Urllink[];

    recentvideos1s: Urllink[];

    recentvideos2s: Urllink[];

    recentvideos3s: Urllink[];

    recentvideos4s: Urllink[];

    recentvideos5s: Urllink[];

    recentvideos6s: Urllink[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private frontpageconfigService: FrontpageconfigService,
        private postService: PostService,
        private urllinkService: UrllinkService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.postService
            .query({filter: 'topnews1(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.topNews1 || !this.frontpageconfig.topNews1.id) {
                    this.topnews1s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.topNews1.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.topnews1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'topnews2(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.topNews2 || !this.frontpageconfig.topNews2.id) {
                    this.topnews2s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.topNews2.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.topnews2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'topnews3(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.topNews3 || !this.frontpageconfig.topNews3.id) {
                    this.topnews3s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.topNews3.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.topnews3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'topnews4(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.topNews4 || !this.frontpageconfig.topNews4.id) {
                    this.topnews4s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.topNews4.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.topnews4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'topnews5(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.topNews5 || !this.frontpageconfig.topNews5.id) {
                    this.topnews5s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.topNews5.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.topnews5s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'latestnews1(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.latestNews1 || !this.frontpageconfig.latestNews1.id) {
                    this.latestnews1s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.latestNews1.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.latestnews1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'latestnews2(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.latestNews2 || !this.frontpageconfig.latestNews2.id) {
                    this.latestnews2s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.latestNews2.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.latestnews2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'latestnews3(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.latestNews3 || !this.frontpageconfig.latestNews3.id) {
                    this.latestnews3s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.latestNews3.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.latestnews3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'latestnews4(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.latestNews4 || !this.frontpageconfig.latestNews4.id) {
                    this.latestnews4s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.latestNews4.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.latestnews4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'latestnews5(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.latestNews5 || !this.frontpageconfig.latestNews5.id) {
                    this.latestnews5s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.latestNews5.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.latestnews5s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'breakingnews1(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.breakingNews1 || !this.frontpageconfig.breakingNews1.id) {
                    this.breakingnews1s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.breakingNews1.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.breakingnews1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'recentposts1(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.recentPosts1 || !this.frontpageconfig.recentPosts1.id) {
                    this.recentposts1s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.recentPosts1.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.recentposts1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'recentposts2(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.recentPosts2 || !this.frontpageconfig.recentPosts2.id) {
                    this.recentposts2s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.recentPosts2.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.recentposts2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'recentposts3(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.recentPosts3 || !this.frontpageconfig.recentPosts3.id) {
                    this.recentposts3s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.recentPosts3.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.recentposts3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'recentposts4(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.recentPosts4 || !this.frontpageconfig.recentPosts4.id) {
                    this.recentposts4s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.recentPosts4.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.recentposts4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles1(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles1 || !this.frontpageconfig.featuredArticles1.id) {
                    this.featuredarticles1s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles1.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles2(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles2 || !this.frontpageconfig.featuredArticles2.id) {
                    this.featuredarticles2s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles2.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles3(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles3 || !this.frontpageconfig.featuredArticles3.id) {
                    this.featuredarticles3s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles3.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles4(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles4 || !this.frontpageconfig.featuredArticles4.id) {
                    this.featuredarticles4s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles4.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles5(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles5 || !this.frontpageconfig.featuredArticles5.id) {
                    this.featuredarticles5s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles5.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles5s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles6(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles6 || !this.frontpageconfig.featuredArticles6.id) {
                    this.featuredarticles6s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles6.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles6s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles7(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles7 || !this.frontpageconfig.featuredArticles7.id) {
                    this.featuredarticles7s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles7.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles7s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles8(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles8 || !this.frontpageconfig.featuredArticles8.id) {
                    this.featuredarticles8s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles8.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles8s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles9(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles9 || !this.frontpageconfig.featuredArticles9.id) {
                    this.featuredarticles9s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles9.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles9s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'featuredarticles10(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.featuredArticles10 || !this.frontpageconfig.featuredArticles10.id) {
                    this.featuredarticles10s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.featuredArticles10.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.featuredarticles10s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'popularnews1(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.popularNews1 || !this.frontpageconfig.popularNews1.id) {
                    this.popularnews1s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.popularNews1.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.popularnews1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'popularnews2(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.popularNews2 || !this.frontpageconfig.popularNews2.id) {
                    this.popularnews2s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.popularNews2.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.popularnews2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'popularnews3(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.popularNews3 || !this.frontpageconfig.popularNews3.id) {
                    this.popularnews3s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.popularNews3.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.popularnews3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'popularnews4(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.popularNews4 || !this.frontpageconfig.popularNews4.id) {
                    this.popularnews4s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.popularNews4.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.popularnews4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'popularnews5(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.popularNews5 || !this.frontpageconfig.popularNews5.id) {
                    this.popularnews5s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.popularNews5.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.popularnews5s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'popularnews6(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.popularNews6 || !this.frontpageconfig.popularNews6.id) {
                    this.popularnews6s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.popularNews6.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.popularnews6s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'popularnews7(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.popularNews7 || !this.frontpageconfig.popularNews7.id) {
                    this.popularnews7s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.popularNews7.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.popularnews7s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'popularnews8(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.popularNews8 || !this.frontpageconfig.popularNews8.id) {
                    this.popularnews8s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.popularNews8.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.popularnews8s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'weeklynews1(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.weeklyNews1 || !this.frontpageconfig.weeklyNews1.id) {
                    this.weeklynews1s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.weeklyNews1.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.weeklynews1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'weeklynews2(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.weeklyNews2 || !this.frontpageconfig.weeklyNews2.id) {
                    this.weeklynews2s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.weeklyNews2.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.weeklynews2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'weeklynews3(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.weeklyNews3 || !this.frontpageconfig.weeklyNews3.id) {
                    this.weeklynews3s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.weeklyNews3.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.weeklynews3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'weeklynews4(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.weeklyNews4 || !this.frontpageconfig.weeklyNews4.id) {
                    this.weeklynews4s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.weeklyNews4.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.weeklynews4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'newsfeeds1(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.newsFeeds1 || !this.frontpageconfig.newsFeeds1.id) {
                    this.newsfeeds1s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.newsFeeds1.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.newsfeeds1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'newsfeeds2(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.newsFeeds2 || !this.frontpageconfig.newsFeeds2.id) {
                    this.newsfeeds2s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.newsFeeds2.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.newsfeeds2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'newsfeeds3(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.newsFeeds3 || !this.frontpageconfig.newsFeeds3.id) {
                    this.newsfeeds3s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.newsFeeds3.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.newsfeeds3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'newsfeeds4(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.newsFeeds4 || !this.frontpageconfig.newsFeeds4.id) {
                    this.newsfeeds4s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.newsFeeds4.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.newsfeeds4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'newsfeeds5(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.newsFeeds5 || !this.frontpageconfig.newsFeeds5.id) {
                    this.newsfeeds5s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.newsFeeds5.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.newsfeeds5s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query({filter: 'newsfeeds6(id)-is-null'})
            .subscribe((res: HttpResponse<Post[]>) => {
                if (!this.frontpageconfig.newsFeeds6 || !this.frontpageconfig.newsFeeds6.id) {
                    this.newsfeeds6s = res.body;
                } else {
                    this.postService
                        .find(this.frontpageconfig.newsFeeds6.id)
                        .subscribe((subRes: HttpResponse<Post>) => {
                            this.newsfeeds6s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'usefullinks1(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.usefulLinks1 || !this.frontpageconfig.usefulLinks1.id) {
                    this.usefullinks1s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.usefulLinks1.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.usefullinks1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'usefullinks2(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.usefulLinks2 || !this.frontpageconfig.usefulLinks2.id) {
                    this.usefullinks2s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.usefulLinks2.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.usefullinks2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'usefullinks3(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.usefulLinks3 || !this.frontpageconfig.usefulLinks3.id) {
                    this.usefullinks3s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.usefulLinks3.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.usefullinks3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'usefullinks4(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.usefulLinks4 || !this.frontpageconfig.usefulLinks4.id) {
                    this.usefullinks4s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.usefulLinks4.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.usefullinks4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'usefullinks5(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.usefulLinks5 || !this.frontpageconfig.usefulLinks5.id) {
                    this.usefullinks5s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.usefulLinks5.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.usefullinks5s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'usefullinks6(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.usefulLinks6 || !this.frontpageconfig.usefulLinks6.id) {
                    this.usefullinks6s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.usefulLinks6.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.usefullinks6s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'recentvideos1(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.recentVideos1 || !this.frontpageconfig.recentVideos1.id) {
                    this.recentvideos1s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.recentVideos1.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.recentvideos1s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'recentvideos2(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.recentVideos2 || !this.frontpageconfig.recentVideos2.id) {
                    this.recentvideos2s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.recentVideos2.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.recentvideos2s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'recentvideos3(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.recentVideos3 || !this.frontpageconfig.recentVideos3.id) {
                    this.recentvideos3s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.recentVideos3.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.recentvideos3s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'recentvideos4(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.recentVideos4 || !this.frontpageconfig.recentVideos4.id) {
                    this.recentvideos4s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.recentVideos4.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.recentvideos4s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'recentvideos5(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.recentVideos5 || !this.frontpageconfig.recentVideos5.id) {
                    this.recentvideos5s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.recentVideos5.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.recentvideos5s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.urllinkService
            .query({filter: 'recentvideos6(id)-is-null'})
            .subscribe((res: HttpResponse<Urllink[]>) => {
                if (!this.frontpageconfig.recentVideos6 || !this.frontpageconfig.recentVideos6.id) {
                    this.recentvideos6s = res.body;
                } else {
                    this.urllinkService
                        .find(this.frontpageconfig.recentVideos6.id)
                        .subscribe((subRes: HttpResponse<Urllink>) => {
                            this.recentvideos6s = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.frontpageconfig.id !== undefined) {
            this.subscribeToSaveResponse(
                this.frontpageconfigService.update(this.frontpageconfig));
        } else {
            this.subscribeToSaveResponse(
                this.frontpageconfigService.create(this.frontpageconfig));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Frontpageconfig>>) {
        result.subscribe((res: HttpResponse<Frontpageconfig>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Frontpageconfig) {
        this.eventManager.broadcast({ name: 'frontpageconfigListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackPostById(index: number, item: Post) {
        return item.id;
    }

    trackUrllinkById(index: number, item: Urllink) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-frontpageconfig-popup',
    template: ''
})
export class FrontpageconfigPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private frontpageconfigPopupService: FrontpageconfigPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.frontpageconfigPopupService
                    .open(FrontpageconfigDialogComponent as Component, params['id']);
            } else {
                this.frontpageconfigPopupService
                    .open(FrontpageconfigDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
