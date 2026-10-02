select c.car_id
from CAR_RENTAL_COMPANY_CAR c
left join CAR_RENTAL_COMPANY_RENTAL_HISTORY r
    on c.car_id = r.car_id
where car_type = '세단' and start_date like '2022-10%'
group by car_id
order by c.car_id desc