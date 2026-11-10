-- 1. Remove the existing foreign key
ALTER TABLE employees
DROP CONSTRAINT fk_employee_department;

-- 2. Replace the employees ID and department ID
ALTER TABLE employees
DROP COLUMN id;

ALTER TABLE employees
DROP COLUMN department_id;

ALTER TABLE employees
ADD COLUMN id UUID DEFAULT gen_random_uuid() NOT NULL;

ALTER TABLE employees
ADD COLUMN department_id UUID NOT NULL;

ALTER TABLE employees
ADD CONSTRAINT employees_pkey PRIMARY KEY (id);

-- 3. Replace the departments ID
ALTER TABLE departments
DROP COLUMN id;

ALTER TABLE departments
ADD COLUMN id UUID DEFAULT gen_random_uuid() NOT NULL;

ALTER TABLE departments
ADD CONSTRAINT departments_pkey PRIMARY KEY (id);

-- 4. Re-establish the foreign key relationship
ALTER TABLE employees
ADD CONSTRAINT fk_employee_department
FOREIGN KEY (department_id)
REFERENCES departments(id);