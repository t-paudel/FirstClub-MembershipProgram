INSERT INTO membership_tiers (name, tier_level) VALUES ('Silver', 1);
INSERT INTO membership_tiers (name, tier_level) VALUES ('Gold', 2);
INSERT INTO membership_tiers (name, tier_level) VALUES ('Platinum', 3);

INSERT INTO membership_plans (name, duration_months) VALUES ('Monthly', 1);
INSERT INTO membership_plans (name, duration_months) VALUES ('Quarterly', 3);
INSERT INTO membership_plans (name, duration_months) VALUES ('Yearly', 12);

INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (1, 1, 9.99, 1);   -- Silver Monthly
INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (1, 2, 24.99, 1);  -- Silver Quarterly
INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (1, 3, 89.99, 1);  -- Silver Yearly

INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (2, 1, 19.99, 1);  -- Gold Monthly
INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (2, 2, 49.99, 1);  -- Gold Quarterly
INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (2, 3, 179.99, 1); -- Gold Yearly

INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (3, 1, 34.99, 1);  -- Platinum Monthly
INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (3, 2, 89.99, 1);  -- Platinum Quarterly
INSERT INTO tier_plan_pricing (tier_id, plan_id, price, is_active) VALUES (3, 3, 299.99, 1); -- Platinum Yearly

INSERT INTO tier_benefits (tier_id, benefit_key, benefit_value) VALUES (1, 'CATEGORY_DISCOUNT', '5');
INSERT INTO tier_benefits (tier_id, benefit_key, benefit_value) VALUES (1, 'FREE_DELIVERY', 'false');

INSERT INTO tier_benefits (tier_id, benefit_key, benefit_value) VALUES (2, 'CATEGORY_DISCOUNT', '10');
INSERT INTO tier_benefits (tier_id, benefit_key, benefit_value) VALUES (2, 'FREE_DELIVERY', 'true');

INSERT INTO tier_benefits (tier_id, benefit_key, benefit_value) VALUES (3, 'CATEGORY_DISCOUNT', '15');
INSERT INTO tier_benefits (tier_id, benefit_key, benefit_value) VALUES (3, 'FREE_DELIVERY', 'true');