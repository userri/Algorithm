select board_id, writer_id, title, price,
case when status = 'SALE' then '판매중'
    when status = 'reserved' then '예약중'
    else '거래완료' end as status
from USED_GOODS_BOARD
where created_date = '2022-10-05'
order by board_id desc