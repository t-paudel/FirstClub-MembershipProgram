CREATE TABLE user_membership (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,

     user_id BIGINT NOT NULL,

     plan_id BIGINT NOT NULL,

     tier_id BIGINT NOT NULL,

     status VARCHAR(30) NOT NULL,

     start_date DATE NOT NULL,

     expiry_date DATE NOT NULL,

     auto_renew BOOLEAN NOT NULL DEFAULT FALSE,

     version BIGINT NOT NULL DEFAULT 0,

     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
         ON UPDATE CURRENT_TIMESTAMP,

     CONSTRAINT fk_um_plan
         FOREIGN KEY (plan_id)
             REFERENCES membership_plan(id),

     CONSTRAINT fk_um_tier
         FOREIGN KEY (tier_id)
             REFERENCES membership_tier(id)
);