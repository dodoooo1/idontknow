CREATE TABLE company (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         slug VARCHAR(255) NOT NULL UNIQUE,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         official_name VARCHAR(255) UNIQUE,
                         state_tax_id VARCHAR(255),
                         federal_tax_id VARCHAR(255) UNIQUE, -- CNPJ
                         phone VARCHAR(255),
                         email VARCHAR(255),

                         address_street VARCHAR(255),
                         address_street_number VARCHAR(255),
                         address_complement VARCHAR(255),
                         address_city_district VARCHAR(255),
                         address_post_code VARCHAR(255),
                         address_city VARCHAR(255),
                         address_state_code VARCHAR(255),
                         address_country VARCHAR(255),
                         address_latitude DECIMAL(10, 8),
                         address_longitude DECIMAL(11, 8),

                         is_platform BOOLEAN DEFAULT FALSE,
                         is_back_office BOOLEAN DEFAULT FALSE,
                         is_internal BOOLEAN DEFAULT FALSE,
                         is_management BOOLEAN DEFAULT FALSE,

                         created_by VARCHAR(255),
                         updated_by VARCHAR(255),

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE api_key (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         company_id BIGINT NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         `key` VARCHAR(255) NOT NULL UNIQUE,
                         is_active BOOLEAN NOT NULL DEFAULT FALSE,

                         created_by VARCHAR(255),
                         updated_by VARCHAR(255),

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                         CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE
);

CREATE INDEX api_key_key_is_active_idx ON api_key (`key`, is_active);

INSERT INTO company (slug, name, email, created_at, updated_at)
VALUES ('management', 'management-company', 'management-company@gmail.com', NOW(), NOW());

INSERT INTO company (slug, name, email, created_at, updated_at)
VALUES ('back-office', 'back-office-company', 'back-office-company@gmail.com', NOW(), NOW());

INSERT INTO company (slug, name, email, is_internal, created_at, updated_at)
VALUES ('internal', 'internal-company', 'internal-company@gmail.com', true, NOW(), NOW());
INSERT INTO api_key (company_id, name, `key`, is_active, created_at, updated_at)
VALUES (3, 'apikey-internal', 'internal-apikey', true, NOW(), NOW());

INSERT INTO company (slug, name, email, is_platform, created_at, updated_at)
VALUES ('platform', 'platform-company', 'platform-company@gmail.com', true, NOW(), NOW());
INSERT INTO api_key (company_id, name, `key`, is_active, created_at, updated_at)
VALUES (4, 'apikey-platform', 'platform-apikey', true, NOW(), NOW());
