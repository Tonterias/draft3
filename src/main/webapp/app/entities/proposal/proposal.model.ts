import { BaseEntity } from './../../shared';

export const enum ProposalType {
    'UNDER_STUDY',
    'APPROVED',
    'DEVELOPMENT',
    'PRODUCTION'
}

export class Proposal implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public releaseDate?: any,
        public functionality?: string,
        public proposalType?: ProposalType,
        public party?: BaseEntity,
        public post?: BaseEntity,
        public votes?: BaseEntity[],
    ) {
    }
}
