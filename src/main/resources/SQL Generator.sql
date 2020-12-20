create table TRAINS
(
    TRAIN_ID              VARCHAR(255) not null,
    NUMBER_OF_WAGONS      INT          not null,
    NUMBER_OF_LOCOMOTIVES LONG         not null,
    TRAIN_TYPE            VARCHAR(255) not null,
    constraint TRAINS_PK
        primary key (TRAIN_ID)
);

create unique index TRAINS_ID_UINDEX
    on TRAINS (TRAIN_ID);

create table PASSENGER_WAGONS
(
    WAGON_ID              VARCHAR(255) not null,
    AGE                   DECIMAL      not null,
    CONDITION             DECIMAL      not null,
    WEIGHT                LONG         not null,
    NUMBER_IN_COMPOSITION INT,
    TRAIN_ID              VARCHAR(255),
    NUMBER_OF_PASSENGERS  INT          not null,
    NUMBER_OF_SEATS       INT          not null,
    WAGON_TYPE            VARCHAR(255) not null,
    constraint PASSENGER_WAGONS_TRAINS_TRAIN_ID_FK
        foreign key (TRAIN_ID) references TRAINS (TRAIN_ID)
            on delete cascade
);

create unique index PASSENGER_WAGONS_WAGON_ID_UINDEX
    on PASSENGER_WAGONS (WAGON_ID);

alter table PASSENGER_WAGONS
    add constraint PASSENGER_WAGONS_PK
        primary key (WAGON_ID);

create table LOCOMOTIVES
(
    WAGON_ID                 VARCHAR(255) not null,
    AGE                      DECIMAL      not null,
    CONDITION                DECIMAL      not null,
    WEIGHT                   LONG         not null,
    NUMBER_IN_COMPOSITION    LONG,
    TRAIN_ID                 VARCHAR(255),
    POWER                    LONG         not null,
    MAX_SPEED                DECIMAL      not null,
    ENGINE                   BOOLEAN      not null,
    WAGON_TYPE               VARCHAR(255) not null,
    ELECTRIC_GRID_CONNECTION BOOLEAN,
    MAX_VOLUME_FUEL          DECIMAL,
    VOLUME_OF_FUEL           DECIMAL,
    CONSUMPTION              DECIMAL,
    constraint LOCOMOTIVES_TRAINS_TRAIN_ID_FK
        foreign key (TRAIN_ID) references TRAINS (TRAIN_ID)
            on delete cascade
);

create unique index LOCOMOTIVES_LOCOMOTIVE_ID_UINDEX
    on LOCOMOTIVES (WAGON_ID);

alter table LOCOMOTIVES
    add constraint LOCOMOTIVES_PK
        primary key (WAGON_ID);

create table FREIGHT_WAGONS
(
    WAGON_ID              VARCHAR(255) not null,
    TRAIN_ID              VARCHAR(255),
    WEIGHT                LONG         not null,
    AGE                   DECIMAL      not null,
    CONDITION             DECIMAL      not null,
    NUMBER_IN_COMPOSITION INT,
    CARGO_WEIGHT          LONG         not null,
    MAX_CARRYING          LONG         not null,
    WAGON_TYPE            VARCHAR(255) not null,
    IS_OPEN               BOOLEAN,
    MAX_TEMPERATURE       DECIMAL,
    CURRENT_TEMPERATURE   DECIMAL,
    MIN_TEMPERATURE       DECIMAL,
    constraint FREIGHT_WAGONS_TRAINS_TRAIN_ID_FK
        foreign key (TRAIN_ID) references TRAINS (TRAIN_ID)
            on delete cascade
);

create unique index FREIGHT_WAGONS_WAGON_ID_UINDEX
    on FREIGHT_WAGONS (WAGON_ID);

alter table FREIGHT_WAGONS
    add constraint FREIGHT_WAGONS_PK
        primary key (WAGON_ID);

