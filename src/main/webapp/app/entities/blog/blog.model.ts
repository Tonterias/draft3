import { BaseEntity } from './../../shared';

export class Blog implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public title?: string,
        public imageContentType?: string,
        public image?: any,
        public posts?: BaseEntity[],
        public party?: BaseEntity,
    ) {
    }
}
