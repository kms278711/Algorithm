-- 코드를 입력하세요
select product_code, (amount*price) sales
from (
select product_id, sum(sales_amount) amount
from offline_sale
group by product_id) tmp join product p
on tmp.product_id = p.product_id
order by sales desc, product_code