select distinct(c.car_id), c.car_type, c.daily_fee * 30 *(
    select 100-discount_rate from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where car_type=c.car_type and duration_type='30일 이상') div 100 fee
from CAR_RENTAL_COMPANY_CAR c join (select car_id
from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
where car_id not in(select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY where start_date <= '2022-11-30' and '2022-11-01' <= end_date)) h
on c.car_id=h.car_id
where car_type in ('세단', 'SUV')
and c.daily_fee * 30 *(
    select 100-discount_rate from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where car_type=c.car_type and duration_type='30일 이상') div 100 >= 500000 and c.daily_fee * 30 *(
    select 100-discount_rate from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where car_type=c.car_type and duration_type='30일 이상') div 100 < 2000000
order by 3 desc, 2, 1 desc;

# SELECT A.CAR_ID, A.CAR_TYPE, ROUND(A.DAILY_FEE * (1- (P.DISCOUNT_RATE / 100)) * 30, 0) AS FEE
# FROM CAR_RENTAL_COMPANY_CAR AS A 
# JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS P
# ON A.CAR_TYPE = P.CAR_TYPE
# WHERE 
#     A.CAR_TYPE IN ('세단', 'SUV')
#     AND P.DURATION_TYPE = '30일 이상'
#     AND A.DAILY_FEE * (1 - (P.DISCOUNT_RATE / 100)) * 30 BETWEEN 500000 AND 2000000
#     AND A.CAR_ID NOT IN (
#         SELECT CAR_ID
#         FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
#         WHERE END_DATE >= '2022-11-01' AND START_DATE <= '2022-11-30'
# )
# ORDER BY FEE DESC, A.CAR_TYPE, A.CAR_ID DESC