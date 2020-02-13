create schema resume;

create table resume.resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text
);

create table resume.contact
(
    id          serial   not null
        constraint contact_pk
            primary key,
    resume_uuid char(36) not null references resume.resume on delete cascade,
    type        text     not null,
    value       text     not null

);

create unique index contact_uuid_type_index
    on resume.contact (resume_uuid, type);



