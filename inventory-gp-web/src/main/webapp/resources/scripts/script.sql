INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (1, NULL, NULL, 'ROOT', NULL);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (2, 'ui-icon ui-icon-home', '/web/pages/index.xhtml', 'menu.home', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (3, NULL, NULL, 'menu.inventory', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (4, NULL, NULL, 'menu.processor', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (5, NULL, NULL, 'menu.motherboard', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (6, NULL, NULL, 'menu.video.card', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (7, NULL, NULL, 'menu.memory', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (8, NULL, NULL, 'menu.hd', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (9, NULL, NULL, 'menu.tower', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (10, NULL, NULL, 'menu.drive', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (11, NULL, NULL, 'menu.font', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (12, 'ui-icon ui-icon-note', NULL, 'menu.report', 1);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (13, 'ui-icon ui-icon-circlesmall-close', NULL, 'menu.logout', 1);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (14, 'ui-icon ui-icon-disk', '/web/pages/inventario/cadastro.xhtml', 'submenu.inventory.registry', 3);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (15, 'ui-icon ui-icon-search', '/web/pages/inventario/lista.xhtml', 'submenu.inventory.search', 3);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (16, 'ui-icon ui-icon-disk', '/web/pages/processador/cadastro.xhtml', 'submenu.processor.registry', 4);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (17, 'ui-icon ui-icon-search', '/web/pages/processador/lista.xhtml', 'submenu.processor.search', 4);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (18, 'ui-icon ui-icon-disk', '/web/pages/placa-mae/cadastro.xhtml', 'submenu.motherboard.registry', 5);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (19, 'ui-icon ui-icon-search', '/web/pages/placa-mae/lista.xhtml', 'submenu.motherboard.search', 5);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (20, 'ui-icon ui-icon-disk', '/web/pages/placa-video/cadastro.xhtml', 'submenu.video.card.registry', 6);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (21, 'ui-icon ui-icon-search', '/web/pages/placa-video/lista.xhtml', 'submenu.video.card.search', 6);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (22, 'ui-icon ui-icon-disk', '/web/pages/memoria/cadastro.xhtml', 'submenu.memory.registry', 7);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (23, 'ui-icon ui-icon-search', '/web/pages/memoria/lista.xhtml', 'submenu.memory.search', 7);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (24, 'ui-icon ui-icon-disk', '/web/pages/hd/cadastro.xhtml', 'submenu.hd.registry', 8);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (25, 'ui-icon ui-icon-search', '/web/pages/hd/lista.xhtml', 'submenu.hd.search', 8);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (26, 'ui-icon ui-icon-disk', '/web/pages/gabinete/cadastro.xhtml', 'submenu.tower.registry', 9);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (27, 'ui-icon ui-icon-search', '/web/pages/tower/lista.xhtml', 'submenu.tower.search', 9);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (28, 'ui-icon ui-icon-disk', '/web/pages/drive/cadastro.xhtml', 'submenu.drive.registry', 10);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (29, 'ui-icon ui-icon-search', '/web/pages/drive/lista.xhtml', 'submenu.drive.search', 10);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (30, 'ui-icon ui-icon-disk', '/web/pages/fonte/cadastro.xhtml', 'submenu.font.registry', 11);
INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (31, 'ui-icon ui-icon-search', '/web/pages/fonte/lista.xhtml', 'submenu.font.search', 11);

INSERT INTO INV_MENU (COD_MENU, IMAGEM, URL, NOME, COD_PARENTE) VALUES (32, 'ui-icon ui-icon-clipboard', '/web/pages/relatorio/lista.xhtml', 'menu.report', 12);

INSERT INTO INV_CATEGORIA (COD_CATEGORIA, NAME) VALUES (1, 'PROCESSADOR');
INSERT INTO INV_CATEGORIA (COD_CATEGORIA, NAME) VALUES (2, 'PLACA MÃE');
INSERT INTO INV_CATEGORIA (COD_CATEGORIA, NAME) VALUES (3, 'MEMORIA');
INSERT INTO INV_CATEGORIA (COD_CATEGORIA, NAME) VALUES (4, 'PLACA DE VIDEO');
INSERT INTO INV_CATEGORIA (COD_CATEGORIA, NAME) VALUES (5, 'HD/SSD');
INSERT INTO INV_CATEGORIA (COD_CATEGORIA, NAME) VALUES (6, 'GABINETE');
INSERT INTO INV_CATEGORIA (COD_CATEGORIA, NAME) VALUES (7, 'DRIVE');
INSERT INTO INV_CATEGORIA (COD_CATEGORIA, NAME) VALUES (8, 'FONTE');
