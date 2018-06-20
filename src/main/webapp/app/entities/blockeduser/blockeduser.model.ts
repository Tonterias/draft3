import { BaseEntity } from './../../shared';

export class Blockeduser implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public blockinguser?: BaseEntity,
        public blockeduser?: BaseEntity,
    ) {
    }
}
