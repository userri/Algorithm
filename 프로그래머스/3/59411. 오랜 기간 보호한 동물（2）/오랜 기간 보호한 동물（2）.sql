select o.animal_id, o.name
from ANIMAL_OUTS o
inner join ANIMAL_INS i on o.animal_id = i.animal_id
group by o.animal_id
order by (o.datetime - i.datetime) desc
limit 2