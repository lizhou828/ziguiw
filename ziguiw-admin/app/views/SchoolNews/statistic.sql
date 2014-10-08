select s.sch_name as sname,
  nvl(t_yc.s_yc, 0) + nvl(t_zz.s_zz, 0) + nvl(t_cxw.c_xw, 0) + nvl(t_xxyd.xxyd, 0) as zs,
  nvl(t_yc.s_yc, 0) as yc,
  nvl(t_zz.s_zz, 0) as zz,
  nvl(t_cxw.c_xw, 0) as gg,
  nvl(t_xxyd.xxyd, 0) as  xxyd
  from
       t_schoolinfo s left join
       (
       select count(0) s_yc, s.school_id as xx_id
       from s_school_news s where s.reprinted = 0 and s.status=${status}
       #{if startTime}
         and s.create_time >= to_date('${startTime}', 'yyyy-mm-dd')
       #{/}
       #{if endTime}
         and s.create_time <= to_date('${endTime}', 'yyyy-mm-dd')
       #{/}
       group by s.school_id
       ) t_yc on t_yc.xx_id = s.xx_id

       left join
       (
       select count(0) as s_zz, s.school_id as xx_id
       from s_school_news s where s.reprinted = 1 and s.status=${status}
       #{if startTime}
         and s.create_time >= to_date('${startTime}', 'yyyy-mm-dd')
       #{/}
       #{if endTime}
         and s.create_time <= to_date('${endTime}', 'yyyy-mm-dd')
       #{/}
       group by s.school_id
       ) t_zz on t_zz.xx_id = s.xx_id

       left join
       (
       select count(0) as c_xw, sc.xx_id as xx_id
       from s_class_news s, t_schoolinfo sc where sc.xxid=s.xxid and s.status=${status}
       #{if startTime}
         and s.create_time >= to_date('${startTime}', 'yyyy-mm-dd')
       #{/}
       #{if endTime}
         and s.create_time <= to_date('${endTime}', 'yyyy-mm-dd')
       #{/}
       group by sc.xx_id
       ) t_cxw
       on t_cxw.xx_id = s.xx_id

       left join
       (
       select count(0) as xxyd, ssx.school_id as xx_id
       from s_school_xxyd ssx where ssx.status=${status}
       #{if startTime}
         and ssx.create_time >= to_date('${startTime}', 'yyyy-mm-dd')
       #{/}
       #{if endTime}
         and ssx.create_time <= to_date('${endTime}', 'yyyy-mm-dd')
       #{/}
       group by ssx.school_id
       ) t_xxyd
       on t_xxyd.xx_id = s.xx_id