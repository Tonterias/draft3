import { BaseEntity, User } from './../../shared';

export class Party implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public partyname?: string,
        public partydescription?: string,
        public imageContentType?: string,
        public image?: any,
        public isActive?: boolean,
        public blogs?: BaseEntity[],
        public comments?: BaseEntity[],
        public messages?: BaseEntity[],
        public followeds?: BaseEntity[],
        public followings?: BaseEntity[],
        public blockingusers?: BaseEntity[],
        public blockedusers?: BaseEntity[],
        public user?: User,
        public address?: BaseEntity,
        public album?: BaseEntity,
        public interests?: BaseEntity[],
        public activities?: BaseEntity[],
        public celebs?: BaseEntity[],
    ) {
        this.isActive = false;
    }
}
