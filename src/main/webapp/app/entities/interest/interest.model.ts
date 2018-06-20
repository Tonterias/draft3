import { BaseEntity } from './../../shared';

export class Interest implements BaseEntity {
    constructor(
        public id?: number,
        public interestName?: string,
        public parties?: BaseEntity[],
    ) {
    }
}
