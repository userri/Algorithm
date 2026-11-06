-- 코드를 입력하세요
SELECT food_type, rest_id, rest_name, favorites
from REST_INFO

where (food_type, favorites) in (select food_type, max(favorites)
                                from REST_INFO
                                 group by food_type
                                )
group by food_type
order by food_type desc