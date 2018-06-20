import { BaseEntity } from './../../shared';

export class Follow implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public followed?: BaseEntity,
        public following?: BaseEntity,
    ) {
    }
}
