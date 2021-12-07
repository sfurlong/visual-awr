select * from  DBA_HIST_SNAPSHOT;

    SELECT snap_id,
        instance_number,
        MAX (DECODE (stat_name, 'SGA', stat_value, NULL)) "SGA",
        MAX (DECODE (stat_name, 'PGA', stat_value, NULL)) "PGA",
        MAX (DECODE (stat_name, 'SGA', stat_value, NULL)) + MAX (DECODE (stat_name, 'PGA', stat_value,
        NULL)) "TOTAL"
       FROM
        (SELECT snap_id,
            instance_number,
            ROUND (SUM (bytes) / 1024 / 1024 / 1024, 1) stat_value,
            MAX ('SGA') stat_name
           FROM dba_hist_sgastat
          WHERE dbid = 1836093013
            AND snap_id BETWEEN 159 AND 174
       GROUP BY snap_id,
            instance_number
      UNION ALL
         SELECT snap_id,
            instance_number,
            ROUND (value / 1024 / 1024 / 1024, 1) stat_value,
            'PGA' stat_name
           FROM dba_hist_pgastat
          WHERE dbid = 1836093013
            AND snap_id BETWEEN  159 AND 174
            AND NAME = 'total PGA allocated'
        )
    GROUP BY snap_id,
        instance_number
    ORDER BY snap_id,
        instance_number;
