import { BaseEntity } from './../../shared';

export class Message implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public messageText?: any,
        public isDeliverd?: boolean,
        public party?: BaseEntity,
    ) {
        this.isDeliverd = false;
    }
}
