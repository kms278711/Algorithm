-- 코드를 입력하세요
select YEAR(sales_date) YEAR, MONTH(sales_date) MONTH, 
    count(distinct(user_id)) PUCHASED_USERS, 
    round(count(distinct(user_id))/(select count(user_id) from user_info where joined like '2021-%'),1) PUCHASED_RATIO
    from ONLINE_SALE 
where user_id in(SELECT user_id from user_info where joined like '2021-%')
group by YEAR, MONTH
order by 1, 2;


