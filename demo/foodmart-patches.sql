alter table store add column data jsonb;

update store set data = row_to_json(store);
