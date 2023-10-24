select YEAR(sales_date) YEAR, MONTH(sales_date) MONTH, 
    /* 2021년 가입한 구매한 회원 수 */
    count(distinct(user_id)) PUCHASED_USERS, 
    /* 2021년에 가입한 구매 회원 수 / 2021년에 가입한 총 회원 수 */
    round(count(distinct(user_id))/(select count(user_id) from user_info where joined like '2021-%'),1) PUCHASED_RATIO
    from ONLINE_SALE 
where user_id in(SELECT user_id from user_info where joined like '2021-%')
/* 연도, 월별로 그룹핑 */
group by YEAR, MONTH
order by 1, 2;