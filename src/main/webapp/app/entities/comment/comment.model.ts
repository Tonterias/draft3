import { BaseEntity } from './../../shared';

export class Comment implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public commentText?: any,
        public isOffensive?: boolean,
        public post?: BaseEntity,
        public party?: BaseEntity,
    ) {
        this.isOffensive = false;
    }
}
