import { BaseEntity, User } from './../../shared';

export const enum NotificationReason {
    'FOLLOWING',
    'UNFOLLOWING',
    'PROPOSAL_RELEASED',
    'FOLLOWER_STATUS',
    'AUTHORIZE_PARTY_FOLLOWER',
    'UNAUTHORIZE_PARTY_FOLLOWER'
}

export class Notification implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public notificationDate?: any,
        public notificationReason?: NotificationReason,
        public notificationText?: string,
        public isDeliverd?: boolean,
        public user?: User,
    ) {
        this.isDeliverd = false;
    }
}
