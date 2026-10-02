select u.user_id, nickname, concat_ws(' ', CITY, STREET_ADDRESS1, STREET_ADDRESS2) as '전체주소'
    , concat(substr(tlno, 1,3), '-', substr(tlno, 4,4), '-', substr(tlno, 8,4)) as '전화번호'
from USED_GOODS_BOARD b
left join used_goods_user u on b.writer_id = u.user_id
group by b.writer_id
having count(b.writer_id) >= 3
order by b.writer_id desc