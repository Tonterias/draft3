import { BaseEntity } from './../../shared';

export class City implements BaseEntity {
    constructor(
        public id?: number,
        public cityName?: string,
        public addresses?: BaseEntity[],
        public state?: BaseEntity,
    ) {
    }
}
