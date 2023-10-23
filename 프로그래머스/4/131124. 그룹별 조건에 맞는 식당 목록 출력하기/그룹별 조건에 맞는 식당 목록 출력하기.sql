select 
    (select member_name from member_profile where member_id = b.member_id) as member_name, 
    review_text, 
    date_format(review_date, '%Y-%m-%d')
     /* 해당하는 계정들의 리뷰들 가져오기 */
from (select member_id, review_text,review_date
        from rest_review
        where member_id in (
            select member_id
            from REST_REVIEW 
            group by member_id
            /* 리뷰개수가 최대개수인 계정들 가져오기 */
            having count(review_id) = (select max(a.cnt) from 
                                       (select count(review_id) cnt
                                        from REST_REVIEW 
                                        group by member_id) a
                                      )
        )
     ) b
order by 3, 2