import { BaseEntity } from './../../shared';

export class Album implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public title?: string,
        public party?: BaseEntity,
        public photos?: BaseEntity[],
    ) {
    }
}
