create table prices(
  id serial primary key,
  price numeric,
  productId int references products (id)
);