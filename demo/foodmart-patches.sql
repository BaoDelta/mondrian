alter table store add column store_data jsonb;

update store set store_data = row_to_json(store);

alter table sales_fact_1997 add column sales_data jsonb;

update sales_fact_1997 set sales_data = row_to_json(sales_fact_1997);
