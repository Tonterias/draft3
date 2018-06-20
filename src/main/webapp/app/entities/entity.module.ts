import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SkeletonProfileModule } from './profile/profile.module';
import { SkeletonFollowModule } from './follow/follow.module';
import { SkeletonBlockeduserModule } from './blockeduser/blockeduser.module';
import { SkeletonPartyModule } from './party/party.module';
import { SkeletonBlogModule } from './blog/blog.module';
import { SkeletonPostModule } from './post/post.module';
import { SkeletonCommentModule } from './comment/comment.module';
import { SkeletonFrontpageconfigModule } from './frontpageconfig/frontpageconfig.module';
import { SkeletonAlbumModule } from './album/album.module';
import { SkeletonPhotoModule } from './photo/photo.module';
import { SkeletonMessageModule } from './message/message.module';
import { SkeletonNotificationModule } from './notification/notification.module';
import { SkeletonProposalModule } from './proposal/proposal.module';
import { SkeletonVoteModule } from './vote/vote.module';
import { SkeletonTopicModule } from './topic/topic.module';
import { SkeletonTagModule } from './tag/tag.module';
import { SkeletonInterestModule } from './interest/interest.module';
import { SkeletonActivityModule } from './activity/activity.module';
import { SkeletonCelebModule } from './celeb/celeb.module';
import { SkeletonCountryModule } from './country/country.module';
import { SkeletonStateModule } from './state/state.module';
import { SkeletonCityModule } from './city/city.module';
import { SkeletonAddressModule } from './address/address.module';
import { SkeletonUrllinkModule } from './urllink/urllink.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        SkeletonProfileModule,
        SkeletonFollowModule,
        SkeletonBlockeduserModule,
        SkeletonPartyModule,
        SkeletonBlogModule,
        SkeletonPostModule,
        SkeletonCommentModule,
        SkeletonFrontpageconfigModule,
        SkeletonAlbumModule,
        SkeletonPhotoModule,
        SkeletonMessageModule,
        SkeletonNotificationModule,
        SkeletonProposalModule,
        SkeletonVoteModule,
        SkeletonTopicModule,
        SkeletonTagModule,
        SkeletonInterestModule,
        SkeletonActivityModule,
        SkeletonCelebModule,
        SkeletonCountryModule,
        SkeletonStateModule,
        SkeletonCityModule,
        SkeletonAddressModule,
        SkeletonUrllinkModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SkeletonEntityModule {}
