DO ' 
BEGIN 

-- status table --
IF (
  select
    count(*)
  from develop.status
) = 0 THEN
INSERT INTO develop.status
    (status_id, description, name)
values
    (1, ''the resource is ENABLED'', ''ENABLED'');
INSERT INTO develop.status
    (status_id, description, name)
values
    (2, ''the resource is DISABLED'', ''DISABLED'');
INSERT INTO develop.status
    (status_id, description, name)
values
    (3, ''the resource is DELETED'', ''DELETED'');

INSERT INTO develop.status
    (status_id, description, name)
values(4, ''the resource is WINED'', ''WINED'');

INSERT INTO develop.status
    (status_id, description, name)
values(5, ''the resource is NOT_WINED'', ''NOT_WINED'');
END IF;

-- lottery_type table --
IF (
  select
    count(*)
  from develop.lottery_type
) = 0 THEN
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (1, ''MEGA_MILLIONS'', ''Mega Millions lottery'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (2, ''POWER_BALL'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (3, ''EURO_MILLIONS'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (4, ''LOTO_MAS'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (5, ''SUPER_ENALOTTO'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (6, ''LOTO_REAL'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (7, ''MEGA_LOTO'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (8, ''LOTO_POOL'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (9, ''SUPER_KINO'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (10, ''PEGA_3_MAS'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (11, ''PALE_LEIDSA'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (12, ''QUINIELA_LEIDSA'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (13, ''TRIPLETA_LEIDSA'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (14, ''PALE_NACIONAL'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (15, ''QUINIELA_NACIONAL'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (16, ''TRIPLETA_NACIONAL'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (17, ''PALE_NACIONAL_NOCHE'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (18, ''QUINIELA_NACIONAL_NOCHE'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (19, ''TRIPLETA_NACIONAL_NOCHE'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (20, ''PALE_REAL'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (21, ''QUINIELA_REAL'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (22, ''TRIPLETA_REAL'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (23, ''PALE_NY'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (24, ''QUINIELA_NY'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (25, ''TRIPLETA_NY'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (26, ''PALE_NY_NOCHE'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (27, ''QUINIELA_NY_NOCHE'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (28, ''TRIPLETA_NY_NOCHE'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (29, ''PALE_LOTEKA'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (30, ''QUINIELA_LOTEKA'', ''descripcion pendiente'');
--INSERT INTO develop.lottery_type (lottery_type_id, name, description) values (31, ''TRIPLETA_LOTEKA'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (32, ''MEGA_CHANSE_LOTEKA'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (33, ''GANAMAS'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (34, ''JUEGAMAS_PEGAMAS'', ''descripcion pendiente'');
INSERT INTO develop.lottery_type
    (lottery_type_id, name, description)
values
    (35, ''LA_PRIMERA'', ''descripcion pendiente'');
END IF;

-- currency_type table --
IF (
    select
        count(*)
    from develop.currency_type
    ) = 0 THEN
    INSERT INTO develop.currency_type
        (currency_type_id, description, name)
    values
        (1, ''USD'', ''DOLLAR'');
    INSERT INTO develop.currency_type
        (currency_type_id, description, name)
    values
        (2, ''EUR'', ''EURO'');
    INSERT INTO develop.currency_type
        (currency_type_id, description, name)
    values
        (3, ''HTG'', ''GOURDE'');

    INSERT INTO develop.currency_type
        (currency_type_id, description, name)
    values(4, ''VEF'', ''BOLIVAR'');
    END IF;

-- consortium table: delete this insert in the future --
IF (
    select
        count(*)
    from develop.consortium
    ) = 0 THEN
    INSERT INTO develop.consortium
        (consortium_id, name, note, allowed_ips, created_date)
    values
        (1, ''banca_baez'', ''...'', 1, NOW());
    END IF;

    -- advertisement_type table --
    IF (
    select
        count(*)
    from develop.advertisement_type
    ) = 0 THEN
    INSERT INTO develop.advertisement_type
        (advertisement_type_id, description, name)
    values
        (1, ''Is a ADVERTISEMENT to place in TOP section'', ''TOP_ADVERTISEMENT'');
    INSERT INTO develop.advertisement_type
        (advertisement_type_id, description, name)
    values
        (2, ''Is a ADVERTISEMENT to place in MARQUEE section'', ''MARQUEE_ADVERTISEMENT'');
    END IF;

-- advertisement table: delete this insert in the future --
IF (
    select
        count(*)
    from develop.advertisement
    ) = 0 THEN
    INSERT INTO develop.advertisement
        (advertisement_id, advertisement_info, consortium_id, status_id, created_date, advertisement_type_id)
    values
        (1, ''<span>Consorcio de bancas Baez - Siguenos en las Redes Sociales: <i class="fa fa-facebook-square marquee-fa" style="color: blue;"></i> bancasBaez<i class="fa fa-instagram marquee-fa" style="color: lightcoral;"></i> @bancas_baez <i class="fa fa-whatsapp marquee-fa" style="color: green;"></i> 829-220-0000 - Con nosotros cobras tu dinero hoy mismo. Buena Suerte! </span><i class="fa fa-angle-double-right advise_divider marquee-fa"></i><span>Laboramos en horarios de Lunes a Sabado de 9:00 am a 9:30 pm y los Domingos de 9:00 am a 6:00 PM</span>'', 1, 1, NOW(), 2);
    END IF;        

END';



