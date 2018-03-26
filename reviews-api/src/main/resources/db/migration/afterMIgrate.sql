package db.migration


INSERT INTO reviews
        (user_id, camis, dba, bldg, street, boro, zip, review, rating, grade, category)
VALUES
        (1, '2345', 'Starbucks', '23', '23rd St', 'Manhattan', '10010', 'Great coffee, get the nitro brew', 4, 'A', 'coffee'),
        (1, '4321', 'Morimoto', '539', '10th Ave', 'Manhattan', '10020', 'Delish dish', 5, 'A', 'Asian'),
        (1, '74663', 'The Joint', '1234', 'Perry St', 'Manhattan', '10011', 'Avoid at all costs', 1, 'B', 'American');
