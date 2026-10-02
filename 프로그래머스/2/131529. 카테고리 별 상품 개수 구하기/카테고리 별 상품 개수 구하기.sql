select substring(PRODUCT_CODE, 1, 2) as category, count(substring(product_code, 1,2)) as products
from product
group by substring(product_code, 1, 2)
order by product_code