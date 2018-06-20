import { BaseEntity } from './../../shared';

export class Photo implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public imageContentType?: string,
        public image?: any,
        public album?: BaseEntity,
    ) {
    }
}
