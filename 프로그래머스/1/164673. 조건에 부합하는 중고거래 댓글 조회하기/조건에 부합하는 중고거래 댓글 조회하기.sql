-- 코드를 입력하세요
SELECT b.title, b.BOARD_ID, r.REPLY_ID, r.WRITER_ID, r.CONTENTS, r.CREATED_DATE
from USED_GOODS_REPLY r
left join USED_GOODS_BOARD b on b.board_id = r.board_id
where (year(r.created_date) = 2022 and month(r.created_date) = 10) or (year(b.created_date) = 2022 and month(b.created_date) = 10)
order by r.created_date, b.title
