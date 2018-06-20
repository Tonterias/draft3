import { BaseEntity, User } from './../../shared';

export const enum Gender {
    'MALE',
    'FEMALE',
    'OTHER'
}

export const enum CivilStatus {
    'SINGLE',
    'MARRIED',
    'DIVORCED',
    'WIDOWER',
    'SEPARATED',
    'PAIRED_UP',
    'OTHER'
}

export const enum Purpose {
    'NOT_INTERESTED',
    'FRIENDSHIP',
    'MEET_PEOPLE',
    'STABLE_RELATIONSHIP',
    'MARRIAGE',
    'OTHER'
}

export const enum Physical {
    'NA',
    'THIN',
    'ATHLETIC',
    'NORMAL',
    'CORPULENT',
    'BULKY',
    'OTHER'
}

export const enum Religion {
    'NA',
    'ATHEIST',
    'AGNOSTIC',
    'CATHOLIC',
    'JEWISH',
    'ISLAMIC',
    'OTHER'
}

export const enum EthnicGroup {
    'NA',
    'MIXED',
    'WHITE',
    'LATIN',
    'GIPSY',
    'AFRO',
    'HINDU',
    'ARAB',
    'ASIAN',
    'INDIAN',
    'OTHER'
}

export const enum Studies {
    'NA',
    'PRIMARY',
    'HIGH_SCHOOL',
    'TECHNICAL',
    'COLLEGE',
    'MASTER',
    'DOCTORATE',
    'OTHER'
}

export const enum Eyes {
    'NA',
    'BLUE',
    'GREEN',
    'BROWN',
    'BLACK',
    'CHESTNUT',
    'OTHER'
}

export const enum Smoker {
    'NA',
    'YES',
    'NO',
    'OCCASIONALLY'
}

export const enum Children {
    'NA',
    'YES',
    'NO',
    'GREATER_THAN_18'
}

export const enum FutureChildren {
    'NA',
    'YES',
    'NO'
}

export class Profile implements BaseEntity {
    constructor(
        public id?: number,
        public creationDate?: any,
        public imageContentType?: string,
        public image?: any,
        public gender?: Gender,
        public phone?: string,
        public bio?: string,
        public birthdate?: any,
        public civilStatus?: CivilStatus,
        public lookingFor?: Gender,
        public purpose?: Purpose,
        public physical?: Physical,
        public religion?: Religion,
        public ethnicGroup?: EthnicGroup,
        public studies?: Studies,
        public sibblings?: number,
        public eyes?: Eyes,
        public smoker?: Smoker,
        public children?: Children,
        public futureChildren?: FutureChildren,
        public pet?: boolean,
        public userPoints?: number,
        public user?: User,
    ) {
        this.pet = false;
    }
}
