import { BaseEntity } from './../../shared';

export class State implements BaseEntity {
    constructor(
        public id?: number,
        public stateName?: string,
        public cities?: BaseEntity[],
        public country?: BaseEntity,
    ) {
    }
}
