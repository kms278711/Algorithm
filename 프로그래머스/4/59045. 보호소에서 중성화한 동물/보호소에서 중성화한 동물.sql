SELECT animal_id, animal_type, name
from animal_ins
where animal_id in (
    /* 나갈 때 중성화 되있던 동물목록 */
    select animal_id from animal_outs
    where sex_upon_outcome not like 'Intact%'
    and animal_id in (
        /* 들어올 때 중성화 거치지 않은 동물목록 */
        select animal_id from animal_ins
        where sex_upon_intake like 'Intact%')
    );