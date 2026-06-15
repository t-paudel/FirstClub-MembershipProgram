CREATE TABLE membership_tiers (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(50) NOT NULL UNIQUE,
      tier_level INT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE membership_plans (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(50) NOT NULL UNIQUE,
      duration_months INT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE tier_plan_pricing (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       membership_tier_id BIGINT,
       membership_plan_id BIGINT,
       price DECIMAL(10, 2) NOT NULL,
       is_active BOOLEAN,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
           ON UPDATE CURRENT_TIMESTAMP,

       CONSTRAINT fk_membership_tier_id
           FOREIGN KEY (membership_tier_id)
               REFERENCES membership_tiers(id),

       CONSTRAINT fk_membership_plan_id
           FOREIGN KEY (membership_plan_id)
               REFERENCES membership_plans(id)
);

CREATE TABLE benefits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    benefit_type VARCHAR(50),
    description VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE tier_benefits (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   membership_tier_id BIGINT,
   benefit_id BIGINT,
   benefit_value VARCHAR(50),
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
       ON UPDATE CURRENT_TIMESTAMP,

   CONSTRAINT fk_tb_membership_tier_id
       FOREIGN KEY (membership_tier_id)
           REFERENCES membership_tiers(id),

   CONSTRAINT fk_tb_benefit_id
       FOREIGN KEY (benefit_id)
           REFERENCES benefits(id)
);

CREATE TABLE user_subscriptions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50),
    tier_plan_pricing_id BIGINT,
    status VARCHAR(50),
    cancel_at_period_end BOOLEAN,
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_us_tier_plan_pricing_id
        FOREIGN KEY (tier_plan_pricing_id)
            REFERENCES tier_plan_pricing(id)
);

CREATE TABLE user_monthly_metrics (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      user_name VARCHAR(50),
      month_year VARCHAR(50),
      order_count INT,
      total_order_value DECIMAL(10, 2),
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          ON UPDATE CURRENT_TIMESTAMP,

      CONSTRAINT UK_user_month UNIQUE (user_name, month_year)
);


