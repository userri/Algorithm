with recursive hours as (
    select 0 as hour
    union all
    select hour + 1 from hours where hour < 23
)

select hour, count(datetime)
from hours h
left join animal_outs on h.hour = hour(datetime)
group by h.hour
order by hour