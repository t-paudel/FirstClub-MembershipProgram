CREATE TABLE tier_benefit_mapping (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,

      tier_id BIGINT NOT NULL,

      benefit_id BIGINT NOT NULL,

      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

      CONSTRAINT fk_tbm_tier
          FOREIGN KEY (tier_id)
              REFERENCES membership_tier(id),

      CONSTRAINT fk_tbm_benefit
          FOREIGN KEY (benefit_id)
              REFERENCES membership_benefit(id),

      CONSTRAINT uk_tier_benefit
          UNIQUE (tier_id, benefit_id)
);