SELECT animal_id, animal_type, name
from animal_ins
where animal_id in (select animal_id from animal_outs
where sex_upon_outcome not like 'Intact%'
and animal_id in (select animal_id from animal_ins
where sex_upon_intake like 'Intact%'));
