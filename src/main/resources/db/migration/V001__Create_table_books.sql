create table if not exists books
(
    id     SERIAL       primary key,
    title  text         not null,
    author varchar(40)  not null
)