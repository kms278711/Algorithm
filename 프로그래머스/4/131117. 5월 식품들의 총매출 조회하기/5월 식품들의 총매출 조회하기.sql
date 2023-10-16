-- 코드를 입력하세요
select p.product_id, p.product_name, sum(amount * p.price) total_sales
from 
food_product p join food_order o
on p.product_id = o.product_id
where o.produce_date between '2022-05-01' and '2022-05-31'
group by product_id
order by total_sales desc, product_id
