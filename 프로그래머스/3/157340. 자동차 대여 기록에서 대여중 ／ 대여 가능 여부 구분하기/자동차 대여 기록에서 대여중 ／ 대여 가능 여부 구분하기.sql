-- 코드를 입력하세요
SELECT car_id, 
CASE WHEN CAR_ID IN (SELECT CAR_ID 
                    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                    WHERE '2022-10-16' between start_date and end_date) THEN '대여중'
                    else '대여 가능' end as 'availability'
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
order by car_id desc