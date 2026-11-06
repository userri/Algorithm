-- 코드를 입력하세요
SELECT history_id, car_id, start_date, end_date
,CASE WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 30 THEN '장기 대여'
            else '단기 대여' end as 'RENT_TYPE'
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE BETWEEN '2022-09-01' AND '2022-09-30'
order by HISTORY_ID desc