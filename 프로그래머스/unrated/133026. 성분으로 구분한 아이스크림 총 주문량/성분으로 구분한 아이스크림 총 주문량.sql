-- 코드를 입력하세요
SELECT ingredient_type, sum(total_order)
from icecream_info a, first_half b
where a.flavor = b.flavor
group by ingredient_type
