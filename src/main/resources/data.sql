-- Jeu de donnees de depart.
-- ON CONFLICT evite les doublons quand l'application est relancee.
INSERT INTO produits (nom, categorie, prix, quantite) VALUES
    ('Clavier mecanique', 'Informatique', 45000.00, 12),
    ('Souris sans fil',   'Informatique',  9000.00,  3),
    ('Ecran 24 pouces',   'Informatique', 85000.00,  7),
    ('Cahier 200 pages',  'Fourniture',    1500.00, 120),
    ('Stylo bille',       'Fourniture',     250.00,  4),
    ('Cartouche encre',   'Fourniture',   12000.00,  2),
    ('Chaise de bureau',  'Mobilier',     55000.00,  6),
    ('Bureau simple',     'Mobilier',     78000.00,  2)
ON CONFLICT (nom) DO NOTHING;
