import { BaseEntity } from './../../shared';

export class Topic implements BaseEntity {
    constructor(
        public id?: number,
        public topicName?: string,
        public posts?: BaseEntity[],
    ) {
    }
}
