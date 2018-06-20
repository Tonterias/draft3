import { BaseEntity } from './../../shared';

export class Address implements BaseEntity {
    constructor(
        public id?: number,
        public addressLine1?: string,
        public addressLine2?: string,
        public postalCode?: string,
        public latitude?: number,
        public longitude?: number,
        public party?: BaseEntity,
        public city?: BaseEntity,
    ) {
    }
}
