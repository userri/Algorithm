select animal_id, name
from ANIMAL_INS
where animal_type = 'dog' and name like '%el%'
order by name, animal_id
