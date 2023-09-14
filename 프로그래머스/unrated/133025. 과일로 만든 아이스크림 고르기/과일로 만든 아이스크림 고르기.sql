-- 코드를 입력하세요
SELECT a.flavor
from first_half a, icecream_info b
where a.flavor = b.flavor
and a.total_order > 3000
and b.ingredient_type = 'fruit_based'