import { BaseEntity } from './../../shared';

export class Activity implements BaseEntity {
    constructor(
        public id?: number,
        public activityName?: string,
        public parties?: BaseEntity[],
    ) {
    }
}
