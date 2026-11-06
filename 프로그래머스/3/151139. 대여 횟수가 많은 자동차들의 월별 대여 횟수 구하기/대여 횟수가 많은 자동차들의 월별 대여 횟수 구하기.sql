-- 코드를 입력하세요
SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
    and car_id in (
        select car_id
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where start_date between '2022-08-01' AND '2022-10-31'
        group by car_id
        having count(*) >= 5
    )
GROUP BY MONTH(START_DATE), CAR_ID
having count(*) > 0
ORDER BY MONTH(START_DATE), CAR_ID DESC

