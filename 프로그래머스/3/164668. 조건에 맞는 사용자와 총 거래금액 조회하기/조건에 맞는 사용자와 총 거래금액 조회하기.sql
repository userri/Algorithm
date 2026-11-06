-- 코드를 입력하세요
SELECt u.user_id, u.nickname, sum(b.price) as total_sales
from USED_GOODS_BOARD b
left join USED_GOODS_USER u on b.writer_id = u.user_id
where b.status = 'done'
group by b.writer_id
having sum(b.price) >= 700000
order by sum(b.price)