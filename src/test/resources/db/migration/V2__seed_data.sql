INSERT INTO users (id, city, email, name, phone_number, surname)
VALUES
    (1, 'Москва', 'ivanov@mail.ru', 'Иван', '+79991234567', 'Иванов'),
    (2, 'Санкт-Петербург', 'petrov@yandex.ru', 'Пётр', '+79997654321', 'Петров'),
    (3, 'Казань', 'sidorova@gmail.com', 'Мария', '+79995554433', 'Сидорова');

-- Вставляем объявления (Retail и Opt типы)
INSERT INTO advertisement (
    halal, has_medical_certificate, is_active, is_frozen, is_mramor, is_retail,
    months_age, price, quantity, weight, creation_date, id, kill_begin, kill_date,
    kill_end, seller_user_id, ad_type, animal_type, breed, description, location, title
)
VALUES
-- Retail объявление (птица)
(
    true,
    true,
    true,
    false,
    false,
    true,
    6,
    500.00,
    100,
    3.5,
    '2024-03-01 12:00:00',
    1,
    '2024-03-05',
    '2024-03-10',
    '2024-03-15',
    1,
    'bird',
    'Птица',
    'Бройлер',
    'Свежая курица высшей категории',
    'Московская область',
    'Курица бройлерная'
),
-- Opt объявление (КРС)
(
    false,
    false,
    true,
    true,
    true,
    false,
    24,
    300000.00,
    50,
    250.0,
    '2024-03-10 14:30:00',
    2,
    '2024-03-20',
    '2024-03-25',
    '2024-03-30',
    2,
    'beef',
    'КРС',
    'Абердин-ангус',
    'Мраморная говядина премиум',
    'Ленинградская область',
    'Говядина мраморная'
);

-- Вставляем розничный заказ (RetailOrder)
INSERT INTO retail_order (
    is_active, is_confirmed, weight, advertisement_id, buyer_user_id, id
)
VALUES
    (
        true,
        true,
        2.5,
        1,
        3,
        1
    );

-- Вставляем оптовый заказ (OptOrder)
INSERT INTO opt_order (
    is_active, is_confirmed, quantity, advertisement_id, buyer_user_id, id, kill_date
)
VALUES
    (
        true,
        false,
        15,
        2,
        3,
        1,
        '2024-03-25'
    );