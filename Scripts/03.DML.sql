INSERT INTO DEPARTMENT VALUES(1, '����', 8);
INSERT INTO DEPARTMENT VALUES(2, '��ȹ', 10);
INSERT INTO DEPARTMENT VALUES(3, '����', 9);
INSERT INTO DEPARTMENT VALUES(4, '�ѹ�', 7);

INSERT INTO EMPLOYEE VALUES(4377, '�̼���', '����', NULL, 5000000, 2);
INSERT INTO EMPLOYEE VALUES(3426, '�ڿ���', '����', 4377, 3000000, 1);
INSERT INTO EMPLOYEE VALUES(1003, '������', '����', 4377, 3000000, 2);
INSERT INTO EMPLOYEE VALUES(3011, '�̼���', '����', 4377, 4000000, 3);
INSERT INTO EMPLOYEE VALUES(1365, '����', '���', 3426 , 1500000,1);
INSERT INTO EMPLOYEE VALUES (2106, '��â��', '�븮', 1003, 2500000, 2);
INSERT INTO EMPLOYEE VALUES (3427, '����ö', '���', 3011, 1500000, 3);

SELECT * FROM EMPLOYEE;
SELECT * FROM DEPARTMENT;