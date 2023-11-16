INSERT INTO users (username, email, password, first_name, last_name, location, registration_date, phone_number)
VALUES
    ('user1', 'user1@example.com', 'password1', 'John', 'Doe', 'City1', '2023-01-01', '1234567890'),
    ('user2', 'user2@example.com', 'password2', 'Jane', 'Smith', 'City2', '2023-02-01', '9876543210');

INSERT INTO clothing (trader_id, created_at, updated_at, title, gender, condition, age_group, description, status, size, color, clothing_category)
VALUES
    (1, '2023-03-01 12:00:00', '2023-03-01 12:30:00', 'Cute pink overall', 'GIRL', 'NEW', 'NEWBORN', 'Description 1', 'ACTIVE', 'SIZE_56', 'PINK', 'ONESIE'),
    (2, '2023-03-02 14:00:00', '2023-03-02 14:45:00', 'Warm blue pants', 'BOY', 'AVERAGE', 'TEEN', 'Description 2', 'PENDING', 'SIZE_140', 'BLUE', 'PANTS');

INSERT INTO toys (trader_id, created_at, updated_at, title, gender, condition, age_group, description, status, toy_category)
VALUES
    (1, '2023-03-01 12:00:00', '2023-03-01 12:30:00', 'Puzzle', 'UNISEX', 'NEW', 'PRESCHOOL', 'Description 1', 'ACTIVE', 'PUZZLES'),
    (2, '2023-03-02 14:00:00', '2023-03-02 14:45:00', 'Barbie doll', 'GIRL', 'AVERAGE', 'PRESCHOOL', 'Description 2', 'ACTIVE', 'DOLLS');
