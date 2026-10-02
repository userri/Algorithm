select f.flavor
from first_half f
inner join july j on f.flavor = j.flavor
group by j.flavor
order by sum(f.total_order) + sum(j.total_order) desc
limit 3