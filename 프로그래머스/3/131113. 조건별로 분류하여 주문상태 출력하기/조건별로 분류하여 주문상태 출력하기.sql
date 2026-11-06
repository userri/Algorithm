-- 코드를 입력하세요
SELECT ORDER_ID, PRODUCT_ID, OUT_DATE,
CASE WHEN out_date > '2022-05-01' THEN '출고대기'
when out_date <= '2022-05-01' then '출고완료'
else '출고미정' end as '출고여부'
FROM FOOD_ORDER
ORDER BY ORDER_ID