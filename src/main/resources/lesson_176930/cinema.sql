create table cinema.movie
(
    id       int auto_increment
        primary key,
    name     varchar(255) null,
    duration int          null
);

INSERT INTO cinema.movie (id, name, duration) VALUES (1, 'Жизнь и не жизнь', 3600);
INSERT INTO cinema.movie (id, name, duration) VALUES (2, 'Последний мститель', 7200);
INSERT INTO cinema.movie (id, name, duration) VALUES (3, 'Властелин овец', 19200);

create table cinema.session
(
    id            int auto_increment
        primary key,
    movie_id      int null,
    start_at      int null,
    default_price int null,
    constraint session_movie_id_fk
        foreign key (movie_id) references cinema.movie (id)
);

INSERT INTO cinema.session (id, movie_id, start_at, default_price) VALUES (1, 3, 1639303200, 500);
INSERT INTO cinema.session (id, movie_id, start_at, default_price) VALUES (2, 1, 1639306800, 400);
INSERT INTO cinema.session (id, movie_id, start_at, default_price) VALUES (3, 2, 1639317600, 500);
INSERT INTO cinema.session (id, movie_id, start_at, default_price) VALUES (4, 1, 1639303201, 400);

create table cinema.ticket
(
    id         int auto_increment
        primary key,
    session_id int null,
    price      int null,
    constraint ticket_session_id_fk
        foreign key (session_id) references cinema.session (id)
);

INSERT INTO cinema.ticket (id, session_id, price) VALUES (1, 1, 500);
INSERT INTO cinema.ticket (id, session_id, price) VALUES (2, 1, 500);
INSERT INTO cinema.ticket (id, session_id, price) VALUES (3, 1, 500);
INSERT INTO cinema.ticket (id, session_id, price) VALUES (4, 2, 400);
INSERT INTO cinema.ticket (id, session_id, price) VALUES (5, 2, 400);
INSERT INTO cinema.ticket (id, session_id, price) VALUES (6, 2, 400);
INSERT INTO cinema.ticket (id, session_id, price) VALUES (7, 3, 500);
INSERT INTO cinema.ticket (id, session_id, price) VALUES (8, 4, 400);

/*
    ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
 */
WITH sessions as (
    SELECT s.id, m.name, s.start_at, (s.start_at + m.duration) end_at, m.duration FROM session s
                                                                                           LEFT JOIN movie m on m.id = s.movie_id
)
SELECT s1.name, s1.start_at, s1.duration, s2.name, s2.start_at, s2.duration FROM sessions s1
                                                                                     INNER JOIN sessions s2 on
            s1.start_at < s2.end_at AND s1.end_at > s2.start_at AND s1.id < s2.id;

/*
    перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
 */
WITH sessions as (
    SELECT s.id, m.name, s.start_at, (s.start_at + m.duration) end_at, m.duration FROM session s
                                                                                           LEFT JOIN movie m on m.id = s.movie_id
    ORDER BY s.start_at
)
SELECT s1.name, s1.start_at, s1.duration, s2.name, s2.start_at, s2.duration, (s2.start_at - s1.end_at) pause FROM sessions s1
                                                                                                                      INNER JOIN sessions s2 on
        s1.end_at < s2.start_at
ORDER BY pause DESC;

/*
    список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
 */
WITH sessions as (SELECT s2.id, s2.movie_id, s2.start_at, COUNT(t2.id) count_tickets, SUM(t2.price) session_money FROM session s2
                                                                                                                           LEFT JOIN ticket t2 on s2.id = t2.session_id
                  GROUP BY (s2.id))
SELECT m.id, m.name, SUM(s.count_tickets) tickets, SUM(s.session_money) money, AVG(s.count_tickets) average_tickets FROM sessions s
                                                                                                                             LEFT JOIN movie m on m.id = s.movie_id
GROUP BY m.id WITH ROLLUP;

/*
    число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
 */
SELECT 'с 9 до 15' time_part, COUNT(t.id) count_people, SUM(t.price) money FROM session s
    LEFT JOIN ticket t on s.id = t.session_id
    WHERE HOUR(FROM_UNIXTIME(s.start_at)) >= 9 AND HOUR(FROM_UNIXTIME(s.start_at)) < 15
    GROUP BY s.id
UNION
SELECT 'с 15 до 18' time_part, COUNT(t.id) count_people, SUM(t.price) money FROM session s
    LEFT JOIN ticket t on s.id = t.session_id
    WHERE HOUR(FROM_UNIXTIME(s.start_at)) >= 15 AND HOUR(FROM_UNIXTIME(s.start_at)) < 18
    GROUP BY s.id
UNION
SELECT 'с 18 до 21' time_part, COUNT(t.id) count_people, SUM(t.price) money FROM session s
    LEFT JOIN ticket t on s.id = t.session_id
    WHERE HOUR(FROM_UNIXTIME(s.start_at)) >= 18 AND HOUR(FROM_UNIXTIME(s.start_at)) < 21
    GROUP BY s.id
UNION
SELECT 'с 21 до 00' time_part, COUNT(t.id) count_people, SUM(t.price) money FROM session s
    LEFT JOIN ticket t on s.id = t.session_id
    WHERE HOUR(FROM_UNIXTIME(s.start_at)) >= 21
    GROUP BY s.id


