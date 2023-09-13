-- 코드를 입력하세요
SELECT title, a.board_id, reply_id, b.writer_id, b.contents, date_format(b.created_date, '%Y-%m-%d') created_date
from used_goods_board a, used_goods_reply b
where b.board_id = a.board_id
and a.created_date between '2022-10-01' and '2022-10-31'
order by b.created_date, title asc