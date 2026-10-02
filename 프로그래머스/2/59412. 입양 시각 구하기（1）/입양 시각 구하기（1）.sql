with recursive hours as (
    select 9 as hour
    union all
    select hour + 1 from hours where hour < 19
)

select hour, count(datetime)
from hours h
left join animal_outs on h.hour = hour(datetime)
group by hour
order by hour