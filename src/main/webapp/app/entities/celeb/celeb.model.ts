import { BaseEntity } from './../../shared';

export class Celeb implements BaseEntity {
    constructor(
        public id?: number,
        public celebName?: string,
        public parties?: BaseEntity[],
    ) {
    }
}
