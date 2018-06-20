import { BaseEntity, User } from './../../shared';

export class Vote implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public numberOfPoints?: number,
        public user?: User,
        public proposal?: BaseEntity,
    ) {
    }
}
